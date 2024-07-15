package com.avaliacao.ekan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DocumentoDTO {

    private Long id;
    private String tipoDocumento;
    private String descricao;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;
}
