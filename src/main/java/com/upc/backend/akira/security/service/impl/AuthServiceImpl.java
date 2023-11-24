package com.upc.backend.akira.security.service.impl;

import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import com.upc.backend.akira.ecommerce.domain.model.enums.ERole;
import com.upc.backend.akira.ecommerce.domain.repository.RoleRepository;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.security.jwt.provider.JwtTokenProvider;
import com.upc.backend.akira.security.model.dto.request.LoginRequestDto;
import com.upc.backend.akira.security.model.dto.request.RegisterRequestDto;
import com.upc.backend.akira.security.model.dto.response.RegisteredUserResponseDto;
import com.upc.backend.akira.security.model.dto.response.TokenResponseDto;
import com.upc.backend.akira.security.service.AuthService;
import com.upc.backend.akira.shared.dto.enums.EStatus;
import com.upc.backend.akira.shared.dto.response.ApiResponse;
import com.upc.backend.akira.shared.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request) {
        //si el email ya está registrado
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(HttpStatus.CONFLICT, "El email '" + request.getEmail() + "' ya está registrado");
        }

        //si no existe, lo registra
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .payment(request.getPayment())
                .numberCellphone(request.getNumberCellphone())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        //establecer los roles
        var roles = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo registrar el usuario, no se encontró el rol USER"));
        user.setRoles(Collections.singleton(roles)); //establece un solo rol

        //guarda el usuario
        var newUser = userRepository.save(user);

        //mapea de la entidad al dto
        var responseData = modelMapper.map(newUser, RegisteredUserResponseDto.class);

        return new ApiResponse<>("Registro correcto", EStatus.SUCCESS, responseData);
    }

    @Override
    public ApiResponse<TokenResponseDto> login(LoginRequestDto request) {
        // Se validan las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Se establece la seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Se obtiene el usuario autenticado desde el repositorio
        User authenticatedUser = userRepository.findByEmail(request.getEmail());

        // Verifica si el usuario es nulo
        if (authenticatedUser == null) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo obtener el usuario autenticado");
        }

        // Se obtiene el userId del usuario autenticado
        Long userId = authenticatedUser.getId();

        // Se obtiene el resto de la información del usuario
        String name = authenticatedUser.getName();
        String surname = authenticatedUser.getSurname();
        String numberCellphone = authenticatedUser.getNumberCellphone();
        String email = authenticatedUser.getEmail();
        String password = authenticatedUser.getPassword();
        // Añade otros campos según sea necesario

        // Se obtiene el token
        String token = jwtTokenProvider.generateToken(authentication);
        // Asegúrate de que tu método generateToken ahora acepte estos nuevos campos

        // Se crea la instancia de TokenResponseDto con el token y el userId
        var responseData = new TokenResponseDto(token, userId, name, surname, numberCellphone, email, password);
        // Ajusta la clase TokenResponseDto para aceptar estos nuevos campos

        return new ApiResponse<>("Autenticación correcta", EStatus.SUCCESS, responseData);
    }

}
