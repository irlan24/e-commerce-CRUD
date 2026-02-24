package com.cheiroesabor.ecommerce.infrastructure.entity.enums;

import java.math.BigDecimal;

public enum CombosList {

    COMBO_FESTA("100 Unidades", new BigDecimal(100.00)),
    COMBO_FAMILIA("50 Unidades", new BigDecimal(52.50)),
    COMBO_MINI ("25 Unidades", new BigDecimal(28.00)),
    COMBO_PERSONALIZADO("Personalizado (+ 100 Un)", new BigDecimal(0.0));
    
    private final String descricao;
    private final BigDecimal valorCombo;

    CombosList(String descricao, BigDecimal valorCombo){
        this.descricao = descricao;
        this.valorCombo = valorCombo;
    }

    public String getDescricao(){
        return descricao;
    }

    public BigDecimal getValorCombo(){
        return valorCombo;
    }

}
