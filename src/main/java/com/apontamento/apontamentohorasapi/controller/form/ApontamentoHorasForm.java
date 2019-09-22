package com.apontamento.apontamentohorasapi.controller.form;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import com.apontamento.apontamentohorasapi.model.Projeto;
import com.apontamento.apontamentohorasapi.model.User;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Data
public class ApontamentoHorasForm implements Serializable {

    @NotNull
    @Temporal(TemporalType.TIME)
    private LocalTime tempo;

    @NotNull
    private Long projetoId;

    @NotNull
    private Long userId;

    public static ApontamentoHoras to(ApontamentoHorasForm apontamentoForm) {
        Projeto projeto = new Projeto();
        projeto.setId(apontamentoForm.getProjetoId());

        User user = new User();
        user.setId(apontamentoForm.getUserId());

        ApontamentoHoras apontamentoHoras = new ApontamentoHoras();
        apontamentoHoras.setProjeto(projeto);

        apontamentoHoras.setTempo(apontamentoForm.getTempo());
        apontamentoHoras.setUser(user);

        return apontamentoHoras;
    }
}
