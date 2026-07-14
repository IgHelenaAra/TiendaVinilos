package cl.duoc.fullstack.pedido.service;

import cl.duoc.fullstack.pedido.dto.PedidoItemRequest;
import cl.duoc.fullstack.pedido.dto.PedidoRequest;
import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private ResponseSpec responseSpec;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void crearPedido_Exito() {
        PedidoRequest request = new PedidoRequest();
        request.setRutCliente("11.111.111-1");

        PedidoItemRequest itemReq = new PedidoItemRequest();
        itemReq.setSkuVinilo("VIN-TEST");
        itemReq.setCantidad(2);
        itemReq.setPrecioUnitario(10000);
        request.setItems(List.of(itemReq));

        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(new Object()));

        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        Pedido resultado = pedidoService.crearPedido(request);


        assertNotNull(resultado);
        assertEquals("11.111.111-1", resultado.getRutCliente());
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals(20000, resultado.getTotal()); // 2 ítems * 10000
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void obtenerPedidosPorCliente_Exito() {
        Pedido pedido = new Pedido();
        pedido.setRutCliente("11.111.111-1");
        pedido.setTotal(20000);

        when(pedidoRepository.findByRutCliente("11.111.111-1")).thenReturn(List.of(pedido));
        List<Pedido> resultado = pedidoService.obtenerPedidosPorCliente("11.111.111-1");
        assertFalse(resultado.isEmpty());
        assertEquals("11.111.111-1", resultado.get(0).getRutCliente());
        verify(pedidoRepository, times(1)).findByRutCliente("11.111.111-1");
    }
}