package com.cheiroesabor.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.CombosRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.CombosResponseDTO;
import com.cheiroesabor.ecommerce.exception.BusinessException;
import com.cheiroesabor.ecommerce.exception.ResourceNotFoundException;
import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;
import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;
import com.cheiroesabor.ecommerce.infrastructure.repository.CombosRepository;
import com.cheiroesabor.ecommerce.mapper.CombosMapper;

@Service
public class CombosService {

    private CombosRepository repository;
    private CombosMapper mapper;


    public CombosService(CombosRepository repository, CombosMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    // Método para localizar um combo por ID
    public CombosResponseDTO findById(Long id){
        CombosEntity combo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Combo não localizado pelo id: " + id));

        return mapper.toDTO(combo);
    }

    // Método para localizar todos os combos disponíveis
    public List<CombosResponseDTO> findAll(){

        List<CombosEntity> combos = repository.findAll();

        
        return mapper.toDTOList(combos);
    }


    // Método para salvar combo
    public CombosResponseDTO salvar(CombosRequestDTO dto){

        if(repository.existsByComboListAndStatusComboTrue(dto.comboList())){
            throw new BusinessException("Houve duplicidade na escolha dos combos");
        }

        CombosEntity combo = mapper.toEntity(dto);   
        CombosList tipoEscolhido = dto.comboList();
        
        BigDecimal precoDefinido = tipoEscolhido.getValorCombo();
        String descricaoCombo = tipoEscolhido.getDescricao();
        
        combo.setPrecoCombo(precoDefinido);
        combo.setDescricaoCombo(descricaoCombo);

        combo = repository.save(combo);

        return mapper.toDTO(combo);
    }

}
