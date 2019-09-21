package com.apontamento.apontamentohorasapi;

import com.apontamento.apontamentohorasapi.model.Projeto;
import com.apontamento.apontamentohorasapi.model.User;
import com.apontamento.apontamentohorasapi.repository.ProjetoRepository;
import com.apontamento.apontamentohorasapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList("Projeto A", "Projeto B").forEach(v -> {
            Projeto projeto1 = new Projeto();
            projeto1.setNome(v);
            this.projetoRepository.saveAndFlush(projeto1);
        });

        log.debug("printing all vehicles...");
        this.projetoRepository.findAll().forEach(v -> log.debug(" Projeto :" + v.toString()));

        this.users.save(User.builder()
                .username("admin@mail.com")
                .password(this.passwordEncoder.encode("12345678"))
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
        );

        User user = new User();

        this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
        );

        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
