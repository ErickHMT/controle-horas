package com.apontamento.apontamentohorasapi.controller.dto;

import com.apontamento.apontamentohorasapi.model.User;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private Long id;
    private String nome;

    public UserDto(User user) {
        if(Objects.isNull(user)){
            return;
        }
        this.id = user.getId();
        this.nome = user.getNome();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(user ->  new UserDto(user)).collect(Collectors.toList());
    }
}
