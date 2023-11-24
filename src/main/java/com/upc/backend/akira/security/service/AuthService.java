package com.upc.backend.akira.security.service;

import com.upc.backend.akira.security.model.dto.request.LoginRequestDto;
import com.upc.backend.akira.security.model.dto.request.RegisterRequestDto;
import com.upc.backend.akira.security.model.dto.response.RegisteredUserResponseDto;
import com.upc.backend.akira.security.model.dto.response.TokenResponseDto;
import com.upc.backend.akira.shared.dto.response.ApiResponse;

public interface AuthService {
    /**
     * Registra un usuario
     * @param request Datos para el registro
     * @return Usuario registrado
     */
    ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request);

    /**
     * Realiza el login del usuario
     * @param request Credenciales
     * @return Token de acceso
     */
    ApiResponse<TokenResponseDto> login(LoginRequestDto request);
}
