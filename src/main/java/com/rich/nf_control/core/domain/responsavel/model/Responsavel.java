package com.rich.nf_control.core.domain.responsavel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Responsavel {

    private String nome;
    private String email;
    private String celular;

    private LocalDate createdAt;
    private LocalDate updateAt;
}