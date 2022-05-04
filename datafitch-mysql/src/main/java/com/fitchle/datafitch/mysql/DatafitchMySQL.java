package com.fitchle.datafitch.mysql;

import com.fitchle.datafitch.mysql.models.MysqlTable;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public final class DatafitchMySQL {
    private final String host;
    private final int port;
    private final String db;
    private final String username;
    private final String password;

    public Connection connect() {
        MysqlDatasource dataSource = new MysqlDatasource(this.host, this.port, this.db, this.username, this.password);
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
}
