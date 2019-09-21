package com.apontamento.apontamentohorasapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApontamentoHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private DateTime tempo;

    @ManyToOne
    @NotNull
    private Projeto projeto;

    @ManyToOne
    @NotNull
    private User user;
}
