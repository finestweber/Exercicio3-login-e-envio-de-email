package com.finestweber.exercicio3.service;

import com.finestweber.exercicio3.dto.UserResponse;
import com.finestweber.exercicio3.entety.Usuario;
import com.finestweber.exercicio3.repository.UsuarioRepository;
import com.finestweber.exercicio3.util.RandomString;
import jakarta.mail.MessagingException;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public UserResponse registerUsuario(Usuario usuario) throws MessagingException, IOException {
        if(usuarioRepository.findByEmail(usuario.getEmail()) != null){
            throw new RuntimeException("Este email já existe.");
        } else {
            String codificaSenha = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(codificaSenha);

            String codigoAleatorio = RandomString.generateRandom(64);
            usuario.setVerificationCode(codigoAleatorio);
            usuario.setEnabled(false);

            Usuario saveUsuario = usuarioRepository.save(usuario);

            UserResponse userResponse = new UserResponse(
                    saveUsuario.getId(),
                    saveUsuario.getName(),
                    saveUsuario.getEmail(),
                    saveUsuario.getPassword()
            );
            mailService.sendVerificationEmail(usuario);
            return userResponse;
        }
    }

    public boolean verify(String codeVerification){
        Usuario usuario = usuarioRepository.findByVerificationCode(codeVerification);
        if(usuario == null || usuario.isEnabled()){
            return false;
        } else {
            usuario.setVerificationCode(null);
            usuario.setEnabled(true);
            usuarioRepository.save(usuario);
            return true;
        }
    }
}
