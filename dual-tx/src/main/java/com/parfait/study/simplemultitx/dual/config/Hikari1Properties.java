package com.parfait.study.simplemultitx.dual.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.hikari1")
public class Hikari1Properties extends DataSourceProperties {
}
