package com.unchk.backend.users.dto;

import lombok.*;

@Getter
@Setter
public class UpdateProfileRequestDTO {

    private String fullName;

    private String password;
}
