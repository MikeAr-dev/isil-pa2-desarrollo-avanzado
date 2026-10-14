package com.isil.edu.pe.spedidos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anulacion_pedido", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pedido_id"})
})
public class AnulacionPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @Column(name = "producto_codigo", nullable = false)
    private String productoCodigo;

    @Column(name = "cantidad_devuelta", nullable = false)
    private int cantidadDevuelta;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    private String motivo;

    public AnulacionPedido() {}

    public AnulacionPedido(Long pedidoId, String productoCodigo, int cantidadDevuelta, String motivo) {
        this.pedidoId = pedidoId;
        this.productoCodigo = productoCodigo;
        this.cantidadDevuelta = cantidadDevuelta;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public Long getPedidoId() { return pedidoId; }
    public String getProductoCodigo() { return productoCodigo; }
    public int getCantidadDevuelta() { return cantidadDevuelta; }
    public LocalDateTime getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
}
