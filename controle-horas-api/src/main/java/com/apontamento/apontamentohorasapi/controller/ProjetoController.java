package com.apontamento.apontamentohorasapi.controller;

import com.apontamento.apontamentohorasapi.controller.dto.ProjetoDto;
import com.apontamento.apontamentohorasapi.model.Projeto;
import com.apontamento.apontamentohorasapi.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity findAll() {
        List<Projeto> projetos = this.projetoService.findAll();
        return ok(ProjetoDto.from(projetos));
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity getProjetosByUserId(@PathVariable("userId") Long userId) {
        List<Projeto> projetos = this.projetoService.getProjetosByUserId(userId);
        return ok(ProjetoDto.from(projetos));
    }

}
