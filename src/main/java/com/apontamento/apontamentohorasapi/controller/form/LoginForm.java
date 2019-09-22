package com.apontamento.apontamentohorasapi.controller.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
