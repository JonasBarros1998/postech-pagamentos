package com.fiap.postechpagamentos.infra.bancodedados;

import com.fiap.postechpagamentos.dominio.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

	Optional<Pagamento> findByUsuarioID(UUID usuarioID);
}
