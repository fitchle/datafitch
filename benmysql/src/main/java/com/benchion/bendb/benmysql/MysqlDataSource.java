package com.benchion.bendb.benmysql;

import com.benchion.bendb.benmysql.utils.DataSource;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

final class MysqlDataSource extends DataSource<Connection> {
    private final String host;
    private final String db;
    private final String username;
    private final String password;
    private final String url;
    private final int port;

    public MysqlDataSource(String host, int port, String db, String username, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + db;
    }

    public void build() {
        super.build();
        this.config.setJdbcUrl(this.url);
        this.config.setUsername(this.username);
        this.config.setPassword(this.password);
        this.dataSource = new HikariDataSource(this.config);
    }

    public Connection connect() throws SQLException {
        this.build();
        return this.dataSource.getConnection();
    }

    public void close() {
        this.dataSource.close();
    }

    public String getHost() {
        return this.host;
    }

    public String getDb() {
        return this.db;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return this.url;
    }

    public int getPort() {
        return this.port;
    }
}
