package com.isil.edu.pe.spedidos.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.isil.edu.pe.spedidos.client.DevolucionStockClient;
import com.isil.edu.pe.spedidos.client.dto.DevolucionStockRequest;
import com.isil.edu.pe.spedidos.entity.AnulacionPedido;
import com.isil.edu.pe.spedidos.entity.Pedido;
import com.isil.edu.pe.spedidos.repository.AnulacionPedidoRepository;
import com.isil.edu.pe.spedidos.repository.PedidoRepository;
import com.isil.edu.pe.spedidos.service.AnulacionPedidoService;

import java.util.List;

@Service
public class AnulacionPedidoServiceImpl implements AnulacionPedidoService {

    private final PedidoRepository pedidoRepository;
    private final AnulacionPedidoRepository anulacionPedidoRepository;
    private final DevolucionStockClient devolucionStockClient;

    public AnulacionPedidoServiceImpl(
            PedidoRepository pedidoRepository,
            AnulacionPedidoRepository anulacionPedidoRepository,
            DevolucionStockClient devolucionStockClient
    ) {
        this.pedidoRepository = pedidoRepository;
        this.anulacionPedidoRepository = anulacionPedidoRepository;
        this.devolucionStockClient = devolucionStockClient;
    }

    @Override
    @Transactional
    public AnulacionPedido anularPedido(Long pedidoId, String motivo) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        if (pedido.getEstado() == Pedido.PedidoEstado.ANULADO) {
            throw new IllegalStateException("El pedido ya se encuentra anulado.");
        }

        if (anulacionPedidoRepository.findByPedidoId(pedidoId).isPresent()) {
            throw new IllegalStateException("Ya existe un registro de anulación para este pedido.");
        }

        // Usando los getters reales de tu entidad Pedido.java
        String codigoProducto = pedido.getProductoCodigo();
        int cantidad = pedido.getCantidad();

        // Llamada al cliente HTTP remoto de inventario
        devolucionStockClient.devolverStock(codigoProducto, new DevolucionStockRequest(cantidad));

        // Cambiar estado y guardar en H2
        pedido.setEstado(Pedido.PedidoEstado.ANULADO);
        pedidoRepository.save(pedido);

        AnulacionPedido anular = new AnulacionPedido(pedidoId, codigoProducto, cantidad, motivo);
        return anulacionPedidoRepository.save(anular);
    }

    @Override
    public List<AnulacionPedido> listarAnulaciones() {
        return anulacionPedidoRepository.findAll();
    }
}
