package com.alura.forohub.infra.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String type;
    private String code;
    private String details;
    private String location;
    private String moreInfo;
    private String uuid;
    private LocalDateTime timestamp;


}
