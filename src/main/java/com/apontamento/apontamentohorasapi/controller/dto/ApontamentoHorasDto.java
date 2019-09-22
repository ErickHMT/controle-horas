package com.apontamento.apontamentohorasapi.controller.dto;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ApontamentoHorasDto {

    private Long id;
    private LocalTime tempo;
    private UserDto user;

    public ApontamentoHorasDto(ApontamentoHoras apontamentoHoras) {
        if(Objects.isNull(apontamentoHoras)){
            return;
        }
        this.id = apontamentoHoras.getId();

        this.tempo = apontamentoHoras.getTempo();
        this.user = new UserDto(apontamentoHoras.getUser());
    }

    public static List<ApontamentoHorasDto> from(List<ApontamentoHoras> apontamentoHorasList) {
        return apontamentoHorasList.stream().map(hospede ->  new ApontamentoHorasDto(hospede)).collect(Collectors.toList());
    }
}
