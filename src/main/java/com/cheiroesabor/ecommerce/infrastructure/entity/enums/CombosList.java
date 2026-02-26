package com.cheiroesabor.ecommerce.infrastructure.entity.enums;

import java.math.BigDecimal;



public enum CombosList {

    COMBO_FESTA("100 Unidades", new BigDecimal(100.00), true),
    COMBO_FAMILIA("50 Unidades", new BigDecimal(52.50), true),
    COMBO_MINI ("25 Unidades", new BigDecimal(28.00), true),
    COMBO_PERSONALIZADO("Personalizado (+ 100 Un)", new BigDecimal(0.0), true);
    
    private final String descricao;
    private final BigDecimal valorCombo;
    private final Boolean status;

    CombosList(String descricao, BigDecimal valorCombo, Boolean status){
        this.descricao = descricao;
        this.valorCombo = valorCombo;
        this.status = status;
    }

    public String getDescricao(){
        return descricao;
    }

    public BigDecimal getValorCombo(){
        return valorCombo;
    }

    public Boolean getStatus(){
        return status;
    }

}
