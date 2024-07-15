package com.avaliacao.ekan.controller;

import com.avaliacao.ekan.dto.BeneficiarioDTO;
import com.avaliacao.ekan.dto.DocumentoDTO;
import com.avaliacao.ekan.model.Beneficiario;
import com.avaliacao.ekan.model.Documento;
import com.avaliacao.ekan.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping
    public ResponseEntity<BeneficiarioDTO> createBeneficiario(@RequestBody BeneficiarioDTO beneficiarioDTO) {
        return ResponseEntity.ok(beneficiarioService.createBeneficiario(beneficiarioDTO));
    }

    @GetMapping
    public ResponseEntity<List<BeneficiarioDTO>> listAllBeneficiarios() {
        return ResponseEntity.ok(beneficiarioService.listAllBeneficiarios());
    }

    @GetMapping("/{id}/documentos")
    public ResponseEntity<List<DocumentoDTO>> listDocumentosByBeneficiarioId(@PathVariable Long id) {
        return ResponseEntity.ok(beneficiarioService.listDocumentsByBeneficiarioId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioDTO> updateBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioDTO beneficiarioDTO) {
        return ResponseEntity.ok(beneficiarioService.updateBeneficiario(id, beneficiarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiario(@PathVariable Long id) {
        beneficiarioService.deleteBeneficiario(id);
        return ResponseEntity.noContent().build();
    }
}
