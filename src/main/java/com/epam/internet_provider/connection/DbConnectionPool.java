package com.epam.internet_provider.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.vavr.control.Try;

import java.sql.Connection;
import java.util.Objects;
import java.util.function.Function;

public class DbConnectionPool {

    private static final String DB_RESOURCE = "db.properties";
    private HikariDataSource dataSource;
    private static DbConnectionPool instance;

    private DbConnectionPool() {

        dataSource = new HikariDataSource(new HikariConfig(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(DB_RESOURCE)).getFile()));
    }

    public static DbConnectionPool getInstance() {
        if (instance == null) {
            instance = new DbConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        return Try.of(() -> dataSource.getConnection())
                .getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);
    }

}
