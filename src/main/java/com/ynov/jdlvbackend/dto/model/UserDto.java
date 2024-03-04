package com.ynov.jdlvbackend.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String login;
    private String mdp;
}
