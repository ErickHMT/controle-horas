package com.apontamento.apontamentohorasapi.repository;

import com.apontamento.apontamentohorasapi.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT * FROM projeto p " +
            "INNER JOIN projeto_user pu on p.id = pu.projeto_id WHERE pu.user_id = :userId ", nativeQuery = true)
    List<Projeto> findProjetosByUserId(@Param("userId") Long userId);
}
