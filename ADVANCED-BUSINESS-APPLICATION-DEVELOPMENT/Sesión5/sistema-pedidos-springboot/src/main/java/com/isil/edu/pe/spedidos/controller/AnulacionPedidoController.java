package com.isil.edu.pe.spedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.edu.pe.spedidos.entity.AnulacionPedido;
import com.isil.edu.pe.spedidos.service.AnulacionPedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/anulaciones")
public class AnulacionPedidoController {

    private final AnulacionPedidoService anulacionPedidoService;

    public AnulacionPedidoController(AnulacionPedidoService anulacionPedidoService) {
        this.anulacionPedidoService = anulacionPedidoService;
    }

    public static class AnulacionRequestDto {
        private Long pedidoId;
        private String motivo;

        public Long getPedidoId() { return pedidoId; }
        public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }
    }

    @PostMapping
    public ResponseEntity<AnulacionPedido> anular(@RequestBody AnulacionRequestDto request) {
        AnulacionPedido resultado = anulacionPedidoService.anularPedido(request.getPedidoId(), request.getMotivo());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<AnulacionPedido>> listar() {
        return ResponseEntity.ok(anulacionPedidoService.listarAnulaciones());
    }
}
