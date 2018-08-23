package com.epam.internet_provider.model;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Credentials {

  @Column(name = "login")
  @NonNull private String login;

  @Column(name = "password")
  @NonNull private String password;
  private Status status;
  private Role role;
}
