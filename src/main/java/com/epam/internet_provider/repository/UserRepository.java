package com.epam.internet_provider.repository;

import com.epam.internet_provider.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  @RestResource(path = "email", rel = "email")
  boolean existsByEmail(@Param("email") String email);

  @RestResource(path = "login", rel = "login")
  boolean existsByLogin(@Param("login") String login);

  User findByLogin(@Param("login") String login);
}
