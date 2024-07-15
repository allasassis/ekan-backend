package com.avaliacao.ekan.mapper;

import com.avaliacao.ekan.dto.BeneficiarioDTO;
import com.avaliacao.ekan.dto.DocumentoDTO;
import com.avaliacao.ekan.model.Beneficiario;
import com.avaliacao.ekan.model.Documento;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class BeneficiarioMapper {

    public static BeneficiarioDTO toDTO(Beneficiario beneficiario) {
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setNome(beneficiario.getNome());
        beneficiarioDTO.setTelefone(beneficiario.getTelefone());
        beneficiarioDTO.setDataNascimento(beneficiario.getDataNascimento());
        beneficiarioDTO.setDataInclusao(beneficiario.getDataInclusao());
        beneficiarioDTO.setDataAtualizacao(beneficiario.getDataAtualizacao());
        beneficiarioDTO.setDocumentos(beneficiario.getDocumentos().stream()
                .map(BeneficiarioMapper::toDTO)
                .collect(Collectors.toList()));
        return beneficiarioDTO;
    }

    public static DocumentoDTO toDTO(Documento documento) {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setId(documento.getId());
        documentoDTO.setTipoDocumento(documento.getTipoDocumento());
        documentoDTO.setDescricao(documento.getDescricao());
        documentoDTO.setDataInclusao(documento.getDataInclusao());
        documentoDTO.setDataAtualizacao(documento.getDataAtualizacao());
        return documentoDTO;
    }

    public static Beneficiario toEntity(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(beneficiarioDTO.getNome());
        beneficiario.setTelefone(beneficiarioDTO.getTelefone());
        beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
        beneficiario.setDataInclusao(LocalDate.now());
        beneficiario.setDataAtualizacao(LocalDate.now());
        beneficiario.setDocumentos(beneficiarioDTO.getDocumentos().stream()
                .map(BeneficiarioMapper::toEntity)
                .peek(documento -> documento.setBeneficiario(beneficiario))
                .collect(Collectors.toList()));
        return beneficiario;
    }

    public static Documento toEntity(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTipoDocumento(documentoDTO.getTipoDocumento());
        documento.setDescricao(documentoDTO.getDescricao());
        documento.setDataInclusao(LocalDate.now());
        documento.setDataAtualizacao(LocalDate.now());
        return documento;
    }
}
