package com.parfait.study.simplemultitx.chained.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.hikari2")
public class Hikari2Properties extends DataSourceProperties {
}
