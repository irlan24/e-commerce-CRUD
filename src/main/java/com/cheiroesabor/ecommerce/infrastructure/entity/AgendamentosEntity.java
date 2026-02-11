package com.cheiroesabor.ecommerce.infrastructure.entity;

import com.cheiroesabor.ecommerce.infrastructure.entity.enumFolder.StatusAgendamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamentos")
public class AgendamentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientesEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combo_id", nullable = false )
    private CombosEntity combo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorNoMomentoDoAgendamento;

    @Column(nullable = false)
    private LocalDateTime dataHoraEntrega; // impedir no Service a impossibilidade de agendar uma data passada

    private String observacao;

    
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) 
    private StatusAgendamentos status; // Campo Enum (enumFolder)




    // Garante o preenchimento no momento da persistência do banco
    @PrePersist
    protected void aoCriar() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusAgendamentos.PENDENTE;
        
    }

       
}
