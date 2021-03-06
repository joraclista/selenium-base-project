package com.github.joraclista.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Long.parseLong;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

/**
 * Created by Alisa
 * version 1.0.
 */
@Slf4j
@Builder
@AllArgsConstructor
public class Configuration {
    @Getter
    private DriverConfig driverConfig;
    @Getter
    private String hostUrl;

    public Configuration() {
        Properties properties = new Properties();
        try(InputStream is = new FileInputStream(get("src","test", "resources", "driver-config.properties").toFile())) {
            properties.load(is);
            init(properties);
        } catch (Exception e) {
            log.error("couldn't load properties due to err.msg = {}", e.getMessage());
        }
    }

    private void init(Properties properties) {
        requireNonNull(properties.getProperty(Props.WEB_DRIVER_ID.getProperty()));
        requireNonNull(properties.getProperty(Props.HOST.getProperty()));
        String id = properties.getProperty(Props.WEB_DRIVER_ID.getProperty());
        this.hostUrl = properties.getProperty(Props.HOST.getProperty());
        this.driverConfig = new DriverConfig(
                        id,
                        properties.getProperty(id + "." + Props.BROWSER_INSTALLATION_BINARY.getProperty()),
                        properties.getProperty(id + "." + Props.DRIVER_EXE.getProperty()),
                        parseLong(properties.getProperty(Props.PAGE_LOAD_TIMEOUT.getProperty())));
    }

}
