package com.avaliacao.ekan.repository;

import com.avaliacao.ekan.model.Documento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByBeneficiarioId(Long beneficiarioId);

    @Transactional
    void deleteAllByBeneficiarioId(Long id);
}
