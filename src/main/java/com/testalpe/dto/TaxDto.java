package com.testalpe.dto;

import java.util.List;

public record TaxDto(String taxNote, String cnpj, String empresaNome, String endereco, double valor, List<BoletoDto> boletos) {
}
