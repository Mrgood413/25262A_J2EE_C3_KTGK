package com.example.KTGK.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorForm {
    @NotBlank
    private String name;

    private String image;

    @NotBlank
    private String specialty;

    @NotNull
    private Long departmentId;
}
