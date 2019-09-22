package com.apontamento.apontamentohorasapi.controller.form;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import com.apontamento.apontamentohorasapi.model.Projeto;
import com.apontamento.apontamentohorasapi.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ApontamentoHorasForm implements Serializable {

    @NotNull
    private LocalDateTime tempo;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(apontamentoForm.tempo.toString()));
        System.out.println(localDateTime);

//        apontamentoHoras.setTempo(apontamentoForm.getTempo());
        apontamentoHoras.setUser(user);

        return apontamentoHoras;
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
