package com.epam.internet_provider.dao.impl;

import java.util.List;

import com.epam.internet_provider.dao.RewardDao;
import com.epam.internet_provider.model.Reward;

public class RewardDaoImpl implements RewardDao {

  private static final String SELECT_REWARDS = "SELECT * FROM internet_provider.reward";
  private static final String INSERT_USER_2REWARD =
      "INSERT into internet_provider.user_2reward "
          + "values ((select user_id from user where login = ?), ?)";
  private static final String UPDATE_BONUS_AMOUNT =
      "update user set bonus_amount = "
          + "bonus_amount - (select bonus_points from reward where reward_id = ?) where login = ?";

  @Override
  public List<Reward> getRewards() {
      return null;
//    return Try.withResources(() -> connectionPool.getConnection())
//        .of(connection -> DSL.using(connection).fetch(SELECT_REWARDS).into(Reward.class))
//        .getOrNull();
  }

  @Override
  public boolean addReward(int reward_id, String login) {
      return true;
//    return Try.withResources(() -> connectionPool.getConnection())
//        .of(
//            connection -> {
//              DSL.using(connection)
//                  .transaction(
//                      configuration -> {
//                        DSL.using(configuration).execute(INSERT_USER_2REWARD, login, reward_id);
//                        DSL.using(configuration).execute(UPDATE_BONUS_AMOUNT, reward_id, login);
//                      });
//              return true;
//            })
//        .getOrElse(false);
  }
}
