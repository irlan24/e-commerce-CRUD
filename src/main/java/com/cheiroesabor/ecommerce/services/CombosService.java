package com.cheiroesabor.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.CombosRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.CombosResponseDTO;
import com.cheiroesabor.ecommerce.exception.BusinessException;
import com.cheiroesabor.ecommerce.exception.ResourceNotFoundException;
import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;
import com.cheiroesabor.ecommerce.infrastructure.repository.CombosRepository;
import com.cheiroesabor.ecommerce.mapper.CombosMapper;

import jakarta.transaction.Transactional;


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

        // Retorna apenas combos Status ativo (true)
        List<CombosEntity> combos = repository.findByStatusComboTrue();
        
        return mapper.toDTOList(combos);
    }


    // Método para salvar combo
    @Transactional
    public CombosResponseDTO salvar(CombosRequestDTO dto){

        if(repository.existsByComboListAndStatusComboTrue(dto.comboList())){
            throw new BusinessException("Já existe um " + dto.comboList() + " ativo no sistema.");
        }

        // Cria um combo completo com base no dto (Combo, preço e descrição)
        CombosEntity combo = CombosEntity.criar(dto.comboList());

        combo = repository.save(combo);

        return mapper.toDTO(combo);
    }

}
