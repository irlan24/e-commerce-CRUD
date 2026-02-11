package com.cheiroesabor.ecommerce.infrastructure.entity.enumFolder;

public enum CombosList {

    COMBO_FESTA("100 Un - R$ 100,00"),
    COMBO_FAMILIA("50 Un - R$ 52,50"),
    COMBO_MINI ("25 Un- R$ 28,00"), 
    COMBO_PERSONALIZADO("+ 100 Un");
    
    private String descricao;

    CombosList(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

}
