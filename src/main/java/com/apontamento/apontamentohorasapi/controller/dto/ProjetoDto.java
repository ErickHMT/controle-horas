package com.apontamento.apontamentohorasapi.controller.dto;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import com.apontamento.apontamentohorasapi.model.Projeto;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ProjetoDto {

    private Long id;
    private String nome;
    private List<ApontamentoHorasDto> apontamentoHoras;

    public ProjetoDto(Projeto projeto) {
        if(Objects.isNull(projeto)){
            return;
        }
        this.id = projeto.getId();
        this.nome = projeto.getNome();

        List<ApontamentoHoras> apontamentoHoras = projeto.getApontamentoHoras();
        if(!apontamentoHoras.isEmpty()) {
            this.apontamentoHoras = ApontamentoHorasDto.from(apontamentoHoras);
        }
    }

    public static List<ProjetoDto> from(List<Projeto> projetos) {
        return projetos.stream().map(projeto ->  new ProjetoDto(projeto)).collect(Collectors.toList());
    }
}
