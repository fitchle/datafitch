package com.fitchle.datafitch.mysql.models;

import com.fitchle.datafitch.mysql.services.MysqlFetcher;
import com.fitchle.datafitch.mysql.services.MysqlInserter;
import com.fitchle.datafitch.mysql.services.MysqlRemover;
import com.fitchle.datafitch.mysql.services.MysqlUpdater;

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

    public MysqlTable remove() throws SQLException {
        try(PreparedStatement statement = this.conn.prepareStatement("DROP TABLE IF EXISTS " + this.table)) {
            statement.executeUpdate();
        }

        return this;
    }

    public MysqlTable create(String... columns) {
        try (PreparedStatement statement = this.conn.prepareStatement("CREATE TABLE if not exists " + this.table + " (" + String.join(", ", columns) + ");")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
