package com.testalpe.cobranca;

import org.springframework.stereotype.Component;

@Component
public class MockCobranca implements Cobranca{
    @Override
    public void enviarBoleto(String boleto) {
        System.out.println("Cobrança enviada para o pagador " + boleto);
    }
}
