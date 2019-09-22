package com.apontamento.apontamentohorasapi.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @Transient
    @Getter(AccessLevel.NONE)
    private LocalTime tempoTotal;

    @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ApontamentoHoras> apontamentoHoras;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "projeto_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "projeto_id"))
    private Set<User> users;

    public LocalTime getTempoTotal() {
        LocalTime localTime = LocalTime.MIN;
        for(ApontamentoHoras hrs : this.apontamentoHoras) {
            LocalTime tempo = hrs.getTempo();

            localTime = localTime.plusHours(tempo.getHour());
            localTime = localTime.plusMinutes(tempo.getMinute());
        }
        return localTime;
    }
}
