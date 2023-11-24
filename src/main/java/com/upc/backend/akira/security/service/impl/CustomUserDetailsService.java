package com.upc.backend.akira.security.service.impl;

import com.upc.backend.akira.shared.util.Utilities;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la interfaz UserDetailsService de Spring Security para obtener los datos del usuario autenticado
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene los datos del usuario autenticado por su username o email
     * @param email Email del usuario autenticado
     * @return Datos del usuario autenticado
     * @throws UsernameNotFoundException Excepción de usuario no encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //busca al usuario por su username o email
        var amigo = userRepository.findByEmail(email);

        return new User(amigo.getEmail(), amigo.getPassword(), Utilities.mapRoles(amigo.getRoles()));
    }
}
