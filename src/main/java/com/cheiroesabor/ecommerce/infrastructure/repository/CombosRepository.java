package com.cheiroesabor.ecommerce.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;
import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;



public interface CombosRepository extends JpaRepository<CombosEntity, Long>{

    boolean existsByComboListAndStatusComboTrue(CombosList combosList);

    List<CombosEntity> findByStatusComboTrue();

}
