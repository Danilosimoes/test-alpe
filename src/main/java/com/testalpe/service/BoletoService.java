package com.testalpe.service;

import com.testalpe.domain.Boleto;
import com.testalpe.domain.Tax;
import com.testalpe.repository.BoletoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoletoService {

    private final BoletoRepository boletoRepository;

    public Boleto save(Boleto boleto){
        return boletoRepository.save(boleto);
    }
}
