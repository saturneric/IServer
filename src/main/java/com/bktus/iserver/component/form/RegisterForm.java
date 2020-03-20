package com.bktus.iserver.component.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterForm {

    @NotEmpty
    @Length(min = 4, max = 16)
    private String nickName;

    @NotEmpty
    @Length(min = 6, max = 24)
    private String username;

    @Length(min = 6, max = 24)
    private String password;

    @Length(min = 6, max = 24)
    private  String retryPassword;

}
