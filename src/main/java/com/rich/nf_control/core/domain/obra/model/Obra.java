package com.rich.nf_control.core.domain.obra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Obra {

    private String id;
    private String nome;
    private String descricao;

    private UUID responsavelId;

    private LocalDate createdAt;
    private LocalDate updateAt;

}