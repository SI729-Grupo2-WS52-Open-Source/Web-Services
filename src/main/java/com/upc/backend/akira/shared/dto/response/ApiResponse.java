package com.upc.backend.akira.shared.dto.response;

import com.upc.backend.akira.shared.dto.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private EStatus status;
    private T data;
}
