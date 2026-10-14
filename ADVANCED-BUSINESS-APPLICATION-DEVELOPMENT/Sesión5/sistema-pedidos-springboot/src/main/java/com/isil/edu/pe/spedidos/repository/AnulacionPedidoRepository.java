package com.isil.edu.pe.spedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.isil.edu.pe.spedidos.entity.AnulacionPedido;
import java.util.Optional;

@Repository
public interface AnulacionPedidoRepository extends JpaRepository<AnulacionPedido, Long> {
    Optional<AnulacionPedido> findByPedidoId(Long pedidoId);
}
