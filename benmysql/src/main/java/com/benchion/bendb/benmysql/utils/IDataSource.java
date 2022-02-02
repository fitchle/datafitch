package com.benchion.bendb.benmysql.utils;

import java.sql.SQLException;

interface IDataSource<T> {
    T connect() throws SQLException;

    void close();
}
