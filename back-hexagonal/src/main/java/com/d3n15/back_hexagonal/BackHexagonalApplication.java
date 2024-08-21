package com.d3n15.back_hexagonal;

import com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories.ItemPortRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = ItemPortRepository.class)
public class BackHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackHexagonalApplication.class, args);
    }

}
