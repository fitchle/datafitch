package com.fitchle.datafitch.mysql.utils;

import java.sql.SQLException;

interface IDataSource<T> {
    T connect() throws SQLException;

    void close();
}
