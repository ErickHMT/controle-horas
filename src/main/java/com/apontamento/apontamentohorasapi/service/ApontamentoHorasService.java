package com.apontamento.apontamentohorasapi.service;

import com.apontamento.apontamentohorasapi.model.ApontamentoHoras;
import com.apontamento.apontamentohorasapi.repository.ApontamentoHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApontamentoHorasService {

    @Autowired
    private ApontamentoHorasRepository apontamentoHorasRepository;

    public ApontamentoHoras save(ApontamentoHoras apontamentoHoras) {
        return this.apontamentoHorasRepository.save(apontamentoHoras);
    }
}
