package com.cheiroesabor.ecommerce.infrastructure.entity;

import java.math.BigDecimal;
import java.util.List;

import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "combos")
public class CombosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CombosList comboList; // Depois achar uma outra alternativa que substitua o ENUM, para maior dinamismo no banco

    private String descricaoCombo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCombo;

    @Column(nullable = false)
    private Boolean statusCombo = true;

    @OneToMany(mappedBy = "combo", fetch = FetchType.LAZY)
    private List<AgendamentosEntity> agendamentos;


    // Facilitar a criação de um combo
    public static CombosEntity criar(CombosList tipo){
    CombosEntity combo = new CombosEntity();
    combo.setComboList(tipo);
    combo.setPrecoCombo(tipo.getValorCombo());
    combo.setDescricaoCombo(tipo.getDescricao());
    combo.setStatusCombo(tipo.getStatus());
    return combo;
}

    
}
