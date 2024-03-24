package com.fiap.postechpagamentos.infra.bancodedados;

import com.fiap.postechpagamentos.dominio.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExtratoRepository extends JpaRepository<Extrato, UUID> {

	List<Extrato> findAllByUsuarioID(UUID id);
}
