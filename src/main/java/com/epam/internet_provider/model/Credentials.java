package com.epam.internet_provider.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
/**
 * Class Credentials that has properties: {@link Credentials#login}, {@link Credentials#password},
 * {@link Credentials#status} and {@link Credentials#role}.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Credentials {

  @Column(name = "login")
  @NonNull
  private String login;

  @Column(name = "password")
  @NonNull
  private String password;

  private Status status;
  private Role role;
}
