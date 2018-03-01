package com.github.joraclista.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Alisa
 * version 1.0.
 */
@Data
@AllArgsConstructor
public class DriverConfig {
    private String id;
    private String browserBinary;
    private String browserExe;
    private long pageLoadTimeoutInSec;
}
