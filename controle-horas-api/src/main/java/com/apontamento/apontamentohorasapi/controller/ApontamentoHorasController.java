package com.apontamento.apontamentohorasapi.controller;

import com.apontamento.apontamentohorasapi.controller.dto.ApontamentoHorasDto;
import com.apontamento.apontamentohorasapi.controller.form.ApontamentoHorasForm;
import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import com.apontamento.apontamentohorasapi.service.ApontamentoHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/apontamento-horas")
@CrossOrigin(origins = "*")
public class ApontamentoHorasController {

    @Autowired
    private ApontamentoHorasService apontamentoHorasService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity findAll() {
        List<ApontamentoHoras> horas = this.apontamentoHorasService.findAll();
        return ok(ApontamentoHorasDto.from(horas));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid ApontamentoHorasForm apontamentoForm){
        ApontamentoHoras apontamentoHoras = ApontamentoHorasForm.to(apontamentoForm);
        return ok(new ApontamentoHorasDto(this.apontamentoHorasService.save(apontamentoHoras)));
    }
}
