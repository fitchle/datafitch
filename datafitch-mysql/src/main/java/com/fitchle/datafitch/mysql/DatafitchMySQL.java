package com.fitchle.datafitch.mysql;

import com.fitchle.datafitch.mysql.services.MysqlTable;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatafitchMySQL {
    private final String host;
    private final int port;
    private final String db;
    private final String username;
    private final String password;

    public Connection connect() {
        MysqlDataSource dataSource = new MysqlDataSource(this.host, this.port, this.db, this.username, this.password);
        dataSource.addProperty("dataSource.cachePrepStmts", "true");
        dataSource.addProperty("dataSource.prepStmtCacheSize", "250");
        dataSource.addProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        dataSource.addProperty("connectionProperties.characterEncoding", "utf8");

        try {
            return dataSource.connect();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public MysqlTable table(Connection conn, String name) {
        return new MysqlTable(conn, name);
    }

    public DatafitchMySQL(String host, int port, String db, String username, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.username = username;
        this.password = password;
    }
}
