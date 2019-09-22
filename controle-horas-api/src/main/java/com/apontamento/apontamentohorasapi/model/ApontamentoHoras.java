package com.apontamento.apontamentohorasapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
//import org.joda.time.LocalTime;

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
    private LocalTime tempo;

    @ManyToOne
    @NotNull
    private Projeto projeto;

    @ManyToOne
    @NotNull
    private User user;
}
