package br.com.system.auth.ambr.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Olá, Diego! Você está autenticado com sucesso!";
    }
}
