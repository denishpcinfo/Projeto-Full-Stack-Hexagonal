package com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories;

import com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemPortRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findById(Long id);

    void deleteById(Long id);
}
