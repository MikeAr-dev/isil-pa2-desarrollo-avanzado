package com.isil.edu.pe.spedidos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.isil.edu.pe.spedidos.client.dto.DevolucionStockRequest;
import com.isil.edu.pe.spedidos.client.dto.DevolucionStockResponse;

@Component
public class DevolucionStockRestClient implements DevolucionStockClient {

    private final RestClient restClient;

    public DevolucionStockRestClient(
            RestClient.Builder restClientBuilder,
            @Value("${inventario.service.url:http://localhost:8082/inventario-service}") String inventarioUrl
    ) {
        this.restClient = restClientBuilder
                .baseUrl(inventarioUrl)
                .build();
    }

    @Override
    public DevolucionStockResponse devolverStock(String codigoProducto, DevolucionStockRequest request) {
        return restClient.post()
                .uri("/api/inventario/productos/codigo/{codigo}/devoluciones", codigoProducto)
                .body(request)
                .retrieve()
                .body(DevolucionStockResponse.class);
    }
}
