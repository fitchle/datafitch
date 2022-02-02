package com.benchion.bendb.benmysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class MysqlTable {
    private final Connection conn;
    private final String table;

    public MysqlTable(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public MysqlTable remove() {
        PreparedStatement statement = null;

        try {
            statement = this.conn.prepareStatement("DROP TABLE IF EXISTS " + this.table);
            statement.executeUpdate();
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }

        }

        return this;
    }

    public MysqlTable create(String... columns) {
        PreparedStatement statement = null;

        try {
            statement = this.conn.prepareStatement("CREATE TABLE if not exists " + this.table + " (" + String.join(", ", columns) + ");");
            statement.execute();
        } catch (SQLException var12) {
            var12.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException var11) {
                var11.printStackTrace();
            }

        }

        return this;
    }

    public MysqlFetcher fetch(Connection connection, String table) {
        return new MysqlFetcher(connection, table);
    }

    public MysqlUpdater update(Connection connection, String table) {
        return new MysqlUpdater(connection, table);
    }

    public MysqlInserter insert(Connection connection, String table) {
        return new MysqlInserter(connection, table);
    }

    public MysqlRemover remove(Connection connection, String table) {
        return new MysqlRemover(connection, table);
    }
}
