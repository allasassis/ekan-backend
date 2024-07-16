package com.avaliacao.ekan.service;

import com.avaliacao.ekan.configuration.JWTConfig;
import com.avaliacao.ekan.dto.UsuarioDTO;
import com.avaliacao.ekan.model.Usuario;
import com.avaliacao.ekan.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTConfig jwtConfig;

    public String signUp(UsuarioDTO userDTO) {
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        Usuario user = new Usuario();
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashedPassword);
        usuarioRepository.save(user);
        return "Usuário cadastrado com sucesso!";
    }

    public String login(UsuarioDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        return jwtConfig.generateToken(userDetails);
    }
}
