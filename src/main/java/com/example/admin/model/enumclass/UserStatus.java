package com.example.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0,"등록","사용자등록상태"),
    UNREGISTERED(0,"해지","사용자등록해지");

    private Integer id;
    private String title;
    private String description;
}
