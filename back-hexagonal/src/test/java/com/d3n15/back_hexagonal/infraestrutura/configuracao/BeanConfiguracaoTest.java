package com.d3n15.back_hexagonal.infraestrutura.configuracao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import com.d3n15.back_hexagonal.dominio.portas.repositories.ItemRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {BeanConfiguracao.class, BeanConfiguracaoTest.TestConfig.class})
public class BeanConfiguracaoTest {

    @Mock
    private ItemRepositoryPort itemRepositoryPort;

    private BeanConfiguracao beanConfiguracao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        beanConfiguracao = new BeanConfiguracao();
    }

    @Test
    void testItemServiceBean() {
        ItemServicePort itemServicePort = beanConfiguracao.itemService(itemRepositoryPort);
        assertNotNull(itemServicePort);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public ItemRepositoryPort itemRepositoryPort() {
            return mock(ItemRepositoryPort.class);
        }
    }
}