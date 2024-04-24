package com.testalpe.service;

import com.testalpe.exception.NotFoundException;
import com.testalpe.domain.Tax;
import com.testalpe.repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaxService {

    private final TaxRepository taxRepository;

    public Tax save(Tax tax){
        return taxRepository.save(tax);
    }

    public Tax pesquisaNota(long id){
        var optional = taxRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Nota não encontrada");
    }
}
