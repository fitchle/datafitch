package com.benchion.bendb.benmysql;

import com.benchion.bendb.benmysql.services.MysqlTable;

import java.sql.Connection;
import java.sql.SQLException;

public final class BenMySQL {
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
        } catch (SQLException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public MysqlTable table(Connection conn, String name) {
        return new MysqlTable(conn, name);
    }

    public BenMySQL(String host, int port, String db, String username, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.username = username;
        this.password = password;
    }
}
