package com.github.joraclista.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Alisa
 * version 1.0.
 */
@AllArgsConstructor
@Getter
public enum Props {
    WEB_DRIVER_ID("web.driver.id"),
    BROWSER_INSTALLATION_BINARY("browser.installation.binary"),
    DRIVER_EXE("driver.exe"),
    PAGE_LOAD_TIMEOUT("page.load.timeout.sec"),
    HOST("website.host");

    private final String property;

}
