package com.finestweber.exercicio3.repository;

import com.finestweber.exercicio3.entety.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
    Usuario findByVerificationCode(String verificationCode);
}
