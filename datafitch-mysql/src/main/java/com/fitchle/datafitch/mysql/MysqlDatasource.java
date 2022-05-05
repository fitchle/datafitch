package com.fitchle.datafitch.mysql;

import com.fitchle.datafitch.mysql.utils.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
final class MysqlDatasource extends DataSource<Connection> {
    private final String host;
    private final String db;
    private final String username;
    private final String password;
    private final String url;
    private final int port;

    public MysqlDatasource(String host, int port, String db, String username, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + db;
    }

    @Override
    public void build() {
        super.build();
        this.config.setJdbcUrl(this.url);
        this.config.setUsername(this.username);
        this.config.setPassword(this.password);
        this.hikariDataSource = new HikariDataSource(this.config);
    }

    public Connection connect() throws SQLException {
        this.build();
        return this.hikariDataSource.getConnection();
    }

    public void close() {
        this.hikariDataSource.close();
    }
}
