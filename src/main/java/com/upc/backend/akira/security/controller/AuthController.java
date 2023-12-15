package com.upc.backend.akira.security.controller;

import com.upc.backend.akira.security.model.dto.request.LoginRequestDto;
import com.upc.backend.akira.security.model.dto.request.RegisterRequestDto;
import com.upc.backend.akira.security.model.dto.request.UpdatePasswordRequestDto;
import com.upc.backend.akira.security.model.dto.response.RegisteredUserResponseDto;
import com.upc.backend.akira.security.model.dto.response.TokenResponseDto;
import com.upc.backend.akira.security.service.AuthService;
import com.upc.backend.akira.security.service.impl.AuthServiceImpl;
import com.upc.backend.akira.shared.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@SecurityRequirements
@RequestMapping("/")
@RestController
public class AuthController {
    private final AuthService service;

    public AuthController(AuthServiceImpl service) {
        this.service = service;
    }


    @Operation(summary = "Inicia sesión")
    @GetMapping("login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        var res = service.login(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Registra un nuevo usuario
     * @param request Datos para el registro
     * @return Usuario registrado
     */
    @Operation(summary = "Registra un nuevo usuario")
    @PostMapping("register")
    public ResponseEntity<ApiResponse<RegisteredUserResponseDto>> registerUser(@Valid @RequestBody RegisterRequestDto request) {
        var res = service.registerUser(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza la contraseña del usuario")
    @PutMapping("update-password")
    public ResponseEntity<ApiResponse<Void>> updatePassword(
            @RequestParam("email") String email,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword
    ) {
        var updatePasswordRequest = new UpdatePasswordRequestDto();
        updatePasswordRequest.setEmail(email);
        updatePasswordRequest.setOldPassword(oldPassword);
        updatePasswordRequest.setNewPassword(newPassword);

        var res = service.updatePassword(updatePasswordRequest);

        if (res.isSuccess()) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
