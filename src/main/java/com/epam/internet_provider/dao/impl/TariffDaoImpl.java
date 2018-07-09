package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.TariffDao;
import com.epam.internet_provider.model.Tariff;
import io.vavr.control.Try;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TariffDaoImpl implements TariffDao {

    private static final Logger LOG = LogManager.getLogger(TariffDaoImpl.class);
    private DbConnectionPool connectionPool = DbConnectionPool.getInstance();
    private static final String SELECT_ALL = "SELECT * FROM internet_provider.tariff";

    @Override
    public List<Tariff> getTariffs() {
        Connection connection = connectionPool.getConnection();

        return Try.withResources(() -> connection, () -> connection.prepareStatement(SELECT_ALL))
                .of((connection1, preparedStatement) -> {
                    ResultSet result = preparedStatement.executeQuery();
                    List<Tariff> tariffs = new ArrayList<>();
                    while (result.next()) {
                        tariffs.add(new Tariff(result.getString("title"),
                                result.getInt("cost"),
                                result.getInt("downloadSpeed"),
                                result.getInt("uploadSpeed"),
                                result.getInt("traffic")));
                    }
                    return tariffs;
                }).getOrElseGet(e -> {
                    LOG.error("Runtime exception was throw in process of registerUser: ", e);
                    return null;
                });
    }
}
