package com.finestweber.exercicio3.controller;


import com.finestweber.exercicio3.dto.UserRequest;
import com.finestweber.exercicio3.dto.UserResponse;
import com.finestweber.exercicio3.entety.Usuario;
import com.finestweber.exercicio3.service.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registroLogin(@RequestBody @Valid UserRequest userRequest) throws MessagingException, IOException {
        Usuario usuario = userRequest.toModel();
        UserResponse usuarioSave = usuarioService.registerUsuario(usuario);
        return ResponseEntity.ok().body(usuarioSave);
    }

    @GetMapping("/verify")
    public String verifyUsuario(@RequestParam("code") String code) {
        if (usuarioService.verify(code)) {
            return "verificado"; // supondo que "unverified" seja o nome da página ou a URL para redirecionamento após a verificação mal-sucedida
        } else {
            return "não verificado "; // supondo que "verified" seja o nome da página ou a URL para redirecionamento após a verificação bem-sucedida
        }
    }
}


