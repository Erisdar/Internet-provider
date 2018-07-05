package com.epam.internet_provider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Credentials {
    @NonNull
    private String login;
    @NonNull
    private String password;
    private int role;
}
