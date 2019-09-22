package com.apontamento.apontamentohorasapi.service;

import com.apontamento.apontamentohorasapi.model.Projeto;
import com.apontamento.apontamentohorasapi.model.User;
import com.apontamento.apontamentohorasapi.repository.ProjetoRepository;
import com.apontamento.apontamentohorasapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public List<Projeto> getProjetosByUserId(final Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()){
            return Collections.emptyList();
        }

        return user.get().isAdmin()
                ? this.projetoRepository.findAll()
                : this.projetoRepository.findProjetosByUserId(userId);
    }
}
