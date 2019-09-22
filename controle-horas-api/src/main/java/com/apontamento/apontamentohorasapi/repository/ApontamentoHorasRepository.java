package com.apontamento.apontamentohorasapi.repository;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApontamentoHorasRepository extends JpaRepository<ApontamentoHoras, Long> {
}
