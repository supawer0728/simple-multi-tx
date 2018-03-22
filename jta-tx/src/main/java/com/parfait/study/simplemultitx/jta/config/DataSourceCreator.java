package com.parfait.study.simplemultitx.jta.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@UtilityClass
public class DataSourceCreator {

    public DataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());

        return new LazyConnectionDataSourceProxy(dataSource);
    }
}
