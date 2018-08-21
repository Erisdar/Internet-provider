package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.model.*;
import io.vavr.control.Try;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

  private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

  private static final String INSERT_NEW =
      "INSERT INTO internet_provider.user "
          + "(login, password, email, role, status, bonus_amount, cash) "
          + "VALUES(?,?,?,?,?,?,?)";
  private static final String SELECT_USER =
      "SELECT email, login, bonus_amount, role, status, cash, "
          + "user.tariff_id, title, cost, download_speed, upload_speed, tariff.traffic, img_url "
          + "FROM internet_provider.user "
          + "LEFT join internet_provider.tariff on user.tariff_id = tariff.tariff_id "
          + "where login = ?";
  private static final String SELECT_CREDENTIALS =
      "SELECT password, login, role, status FROM internet_provider.user where login = ? ";
  private static final String UPDATE_CASH =
      "UPDATE internet_provider.user set cash = cash + ? WHERE login = ?";
  private static final String UPDATE_TARIFF =
      "UPDATE internet_provider.user "
          + "inner JOIN internet_provider.tariff ON tariff.tariff_id = ? "
          + "set user.tariff_id = tariff.tariff_id, cash =  cash - cast(cost as signed) "
          + "WHERE user.login = ?";
  private static final String DELETE_TARIFF =
      "UPDATE internet_provider.user set tariff_id = ? where login = ?";
  private static final String SELECT_REWARDS =
      "select reward.reward_id, title, bonus_points, img_href "
          + "from ((user_2reward inner join reward on reward.reward_id = user_2reward.reward_id) "
          + "inner join user on user.user_id = user_2reward.user_id) "
          + "where login = ?";
  private static final String SELECT_USERS =
      "SELECT login, tariff.title, cash, status "
          + "FROM internet_provider.user left join tariff on user.tariff_id = tariff.tariff_id "
          + "where role != 1";
  private static final String UPDATE_STATUS =
      "UPDATE internet_provider.user set status = ? where login = ? ";
  private static String GET_USER_DATA = "SELECT %s from user where %s = ?";

  private DbConnectionPool connectionPool = DbConnectionPool.getInstance();

  @Override
  public boolean registerUser(User user) {

    Connection connection = connectionPool.getConnection();

    return Try.withResources(() -> connection, () -> connection.prepareStatement(INSERT_NEW))
        .of(
            (connection1, preparedStatement) -> {
              preparedStatement.setString(1, user.getLogin());
              preparedStatement.setString(2, user.getPassword());
              preparedStatement.setString(3, user.getEmail());
              preparedStatement.setInt(4, user.getRole().getValue());
              preparedStatement.setInt(5, user.getStatus().getValue());
              preparedStatement.setInt(6, user.getBonusAmount());
              preparedStatement.setInt(7, user.getCash());
              preparedStatement.execute();
              return true;
            })
        .getOrElseGet(
            e -> {
              LOG.error("Runtime exception was throw in process of registerUser: ", e);
              return false;
            });
  }

  @Override
  public List<User> getUsers() {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(
            connection ->
                DSL.using(connection)
                    .fetch(SELECT_USERS)
                    .map(
                        record -> {
                          User user = new User();
                          Tariff tariff = new Tariff();
                          tariff.setTitle(record.getValue("title", String.class));
                          user.setTariff(tariff);
                          user.setLogin(record.getValue("login", String.class));
                          user.setCash(record.getValue("cash", Integer.class));
                          user.setStatus(
                              Status.getStatus(record.getValue("status", Integer.class)));
                          return user;
                        }))
        .getOrNull();
  }

  @Override
  public User getUser(String login) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(
            connection ->
                DSL.using(connection)
                    .fetchOne(SELECT_USER, login)
                    .map(
                        record -> {
                          User user = new User();
                          Tariff tariff = new Tariff();
                          tariff.setId(
                              Optional.ofNullable(record.getValue("tariff_id", Integer.class))
                                  .orElse(0));
                          tariff.setTitle(record.getValue("title", String.class));
                          tariff.setCost(
                              Optional.ofNullable(record.getValue("cost", Integer.class))
                                  .orElse(0));
                          tariff.setDownloadSpeed(
                              Optional.ofNullable(record.get("download_speed", Integer.class))
                                  .orElse(0));
                          tariff.setUploadSpeed(
                              Optional.ofNullable(record.getValue("upload_speed", Integer.class))
                                  .orElse(0));
                          tariff.setTraffic(
                              Optional.ofNullable(record.getValue("traffic", Integer.class))
                                  .orElse(0));
                          tariff.setImgUrl(record.getValue("img_url", String.class));
                          user.setEmail(record.getValue("email", String.class));
                          user.setLogin(record.getValue("login", String.class));
                          user.setBonusAmount(record.getValue("bonus_amount", Integer.class));
                          user.setRole(Role.getRole(record.getValue("role", Integer.class)));
                          user.setStatus(
                              Status.getStatus(record.getValue("status", Integer.class)));
                          user.setTariff(tariff);
                          user.setCash(record.getValue("cash", Integer.class));
                          user.setRewards(
                              DSL.using(connection)
                                  .fetch(SELECT_REWARDS, login)
                                  .map(
                                      reward ->
                                          new Reward(
                                              reward.getValue("reward_id", Integer.class),
                                              reward.getValue("title", String.class),
                                              reward.getValue("bonus_points", Integer.class),
                                              reward.getValue("img_href", String.class))));
                          return user;
                        }))
        .getOrNull();
  }

  @Override
  public Credentials getCredentials(String login) {
    Connection connection = connectionPool.getConnection();

    return Try.withResources(
            () -> connection, () -> connection.prepareStatement(SELECT_CREDENTIALS))
        .of(
            (connection1, preparedStatement) -> {
              preparedStatement.setString(1, login);
              ResultSet resultSet = preparedStatement.executeQuery();
              Credentials credentials = new Credentials();
              while (resultSet.next()) {
                credentials.setLogin(resultSet.getString("login"));
                credentials.setPassword(resultSet.getString("password"));
                credentials.setRole(Role.getRole(resultSet.getInt("role")));
                credentials.setStatus(Status.getStatus(resultSet.getInt("status")));
              }
              return credentials;
            })
        .getOrNull();
  }

  @Override
  public String getData(String value, String field) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(
            connection ->
                DSL.using(connection)
                    .fetchOne(String.format(GET_USER_DATA, field, field), value)
                    .map(record -> record.getValue(field, String.class)))
        .get();
  }

  @Override
  public boolean updateCash(String login, int cash) {
    Connection connection = connectionPool.getConnection();

    return Try.withResources(() -> connection, () -> connection.prepareStatement(UPDATE_CASH))
        .of(
            (connection1, preparedStatement) -> {
              preparedStatement.setInt(1, cash);
              preparedStatement.setString(2, login);
              preparedStatement.execute();
              return true;
            })
        .getOrElse(false);
  }

  @Override
  public boolean updateTariff(String login, int tariff_id) {
    Connection connection = connectionPool.getConnection();

    return Try.withResources(
            () -> connection,
            () -> connection.prepareStatement(tariff_id < 0 ? DELETE_TARIFF : UPDATE_TARIFF))
        .of(
            (connection1, preparedStatement) -> {
              if (tariff_id < 0) {
                preparedStatement.setNull(1, Types.NULL);
              } else {
                preparedStatement.setInt(1, tariff_id);
              }
              preparedStatement.setString(2, login);
              preparedStatement.execute();
              return true;
            })
        .getOrElse(false);
  }

  @Override
  public boolean changeStatus(String login, int status) {
    return Try.withResources(() -> connectionPool.getConnection())
        .of(connection -> DSL.using(connection).fetch(UPDATE_STATUS, status, login).isNotEmpty())
        .getOrElse(false);
  }
}
