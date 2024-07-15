package com.avaliacao.ekan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BeneficiarioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;
    private List<DocumentoDTO> documentos;
}
