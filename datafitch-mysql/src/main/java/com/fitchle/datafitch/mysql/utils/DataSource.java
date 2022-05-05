package com.fitchle.datafitch.mysql.utils;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

public abstract class DataSource<T> implements IDataSource<T> {
    protected HikariConfig config = new HikariConfig();
    protected Properties properties = new Properties();
    protected HikariDataSource hikariDataSource;

    public void addProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public void build() {
        this.config.setDataSourceProperties(this.properties);
    }
}
