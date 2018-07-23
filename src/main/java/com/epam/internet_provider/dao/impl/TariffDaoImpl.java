package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.TariffDao;
import com.epam.internet_provider.model.Tariff;
import io.vavr.control.Try;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TariffDaoImpl implements TariffDao {

  private static final Logger LOG = LogManager.getLogger(TariffDaoImpl.class);
  private DbConnectionPool connectionPool = DbConnectionPool.getInstance();
  private static final String SELECT_TARIFFS = "SELECT * FROM internet_provider.tariff";
  private static final String INSERT_TARIFF =
      "INSERT INTO internet_provider.tariff "
          + "(title, cost, download_speed, upload_speed, traffic) VALUES (?,?,?,?,?)";
  private static final String DELETE_TARIFF =
      "delete from internet_provider.tariff where tariff_id = ?";
  private static final String UPDATE_TARIFF =
      "update internet_provider.tariff "
          + "set title = ?, cost = ?, download_speed = ?, upload_speed = ?, traffic = ? "
          + "where tariff_id = ?";

  @Override
  public List<Tariff> getTariffs() {
    Connection connection = connectionPool.getConnection();

    return Try.withResources(() -> connection, () -> connection.prepareStatement(SELECT_TARIFFS))
        .of(
            (connection1, preparedStatement) -> {
              ResultSet result = preparedStatement.executeQuery();
              List<Tariff> tariffs = new ArrayList<>();
              while (result.next()) {
                tariffs.add(
                    new Tariff(
                        result.getInt("tariff_id"),
                        result.getString("title"),
                        result.getInt("cost"),
                        result.getInt("download_speed"),
                        result.getInt("upload_speed"),
                        result.getInt("traffic")));
              }
              return tariffs;
            })
        .getOrElseGet(
            e -> {
              LOG.error("Runtime exception was throw in process of getTariffs: ", e);
              return null;
            });
  }

  @Override
  public boolean createTariff(Tariff tariff) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(
            connection ->
                DSL.using(connection)
                    .fetch(
                        INSERT_TARIFF,
                        tariff.getTitle(),
                        tariff.getCost(),
                        tariff.getDownloadSpeed(),
                        tariff.getUploadSpeed(),
                        tariff.getTraffic())
                    .isNotEmpty())
        .getOrElse(false);
  }

  @Override
  public boolean updateTariff(Tariff tariff) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(
            connection ->
                DSL.using(connection)
                    .fetch(
                        UPDATE_TARIFF,
                        tariff.getTitle(),
                        tariff.getCost(),
                        tariff.getDownloadSpeed(),
                        tariff.getUploadSpeed(),
                        tariff.getTraffic(),
                        tariff.getId())
                    .isNotEmpty())
        .getOrElse(false);
  }

  @Override
  public boolean deleteTariff(int id) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(connection -> DSL.using(connection).fetch(DELETE_TARIFF, id).isNotEmpty())
        .getOrElse(false);
  }
}
