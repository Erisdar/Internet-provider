package com.epam.internet_provider.model;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Credentials {
    @NonNull
    private String login;
    @NonNull
    private String password;
    private Role role;
}
