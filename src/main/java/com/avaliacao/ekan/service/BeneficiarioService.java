package com.avaliacao.ekan.service;

import com.avaliacao.ekan.dto.BeneficiarioDTO;
import com.avaliacao.ekan.dto.DocumentoDTO;
import com.avaliacao.ekan.mapper.BeneficiarioMapper;
import com.avaliacao.ekan.model.Beneficiario;
import com.avaliacao.ekan.model.Documento;
import com.avaliacao.ekan.repository.BeneficiarioRepository;
import com.avaliacao.ekan.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional
    public BeneficiarioDTO createBeneficiario(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(beneficiarioDTO);
        Beneficiario savedBeneficiario = beneficiarioRepository.save(beneficiario);
        return BeneficiarioMapper.toDTO(savedBeneficiario);
    }

    public List<BeneficiarioDTO> listAllBeneficiarios() {
        return beneficiarioRepository.findAll().stream()
                .map(BeneficiarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BeneficiarioDTO updateBeneficiario(Long id, BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiario não encontrado"));

        beneficiario.setNome(beneficiarioDTO.getNome());
        beneficiario.setTelefone(beneficiarioDTO.getTelefone());
        beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
        beneficiario.setDataAtualizacao(LocalDate.now());

        documentoRepository.deleteAllByBeneficiarioId(id);
        List<Documento> documentos = beneficiarioDTO.getDocumentos().stream()
                .map(BeneficiarioMapper::toEntity)
                .peek(documento -> documento.setBeneficiario(beneficiario))
                .collect(Collectors.toList());

        beneficiario.setDocumentos(documentos);

        Beneficiario updatedBeneficiario = beneficiarioRepository.save(beneficiario);
        return BeneficiarioMapper.toDTO(updatedBeneficiario);
    }

    @Transactional
    public void deleteBeneficiario(Long id) {
        documentoRepository.deleteAllByBeneficiarioId(id);
        beneficiarioRepository.deleteById(id);
    }

    public List<DocumentoDTO> listDocumentsByBeneficiarioId(Long beneficiarioId) {
        return documentoRepository.findByBeneficiarioId(beneficiarioId).stream()
                .map(BeneficiarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
