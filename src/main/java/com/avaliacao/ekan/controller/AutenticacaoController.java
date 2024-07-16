package com.avaliacao.ekan.controller;

import com.avaliacao.ekan.dto.UsuarioDTO;
import com.avaliacao.ekan.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class AutenticacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDTO userDTO) {
        return ResponseEntity.ok(usuarioService.signUp(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioDTO userDTO) {
        return ResponseEntity.ok(usuarioService.login(userDTO));
    }
}
