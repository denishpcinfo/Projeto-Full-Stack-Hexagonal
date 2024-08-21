package com.d3n15.back_hexagonal.infraestrutura.configuracao;

import com.d3n15.back_hexagonal.dominio.adaptadores.services.PedidoServiceImp;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import com.d3n15.back_hexagonal.dominio.portas.repositories.ItemRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    ItemServicePort itemService(ItemRepositoryPort itemRepositoryPort) {
        return new PedidoServiceImp(itemRepositoryPort);
    }

}
