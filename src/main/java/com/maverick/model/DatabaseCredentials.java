package com.maverick.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DatabaseCredentials {
    private String url;
    private String username;
    private String password;
}
