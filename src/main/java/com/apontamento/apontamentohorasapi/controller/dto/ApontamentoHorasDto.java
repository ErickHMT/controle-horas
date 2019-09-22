package com.apontamento.apontamentohorasapi.controller.dto;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ApontamentoHorasDto {

    private Long id;
    private DateTime tempo;

    public ApontamentoHorasDto(ApontamentoHoras apontamentoHoras) {
        if(Objects.isNull(apontamentoHoras)){
            return;
        }
        this.id = apontamentoHoras.getId();
        this.tempo = apontamentoHoras.getTempo();
    }

    public static List<ApontamentoHorasDto> from(List<ApontamentoHoras> apontamentoHorasList) {
        return apontamentoHorasList.stream().map(hospede ->  new ApontamentoHorasDto(hospede)).collect(Collectors.toList());
    }
}
