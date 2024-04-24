package com.testalpe.controller;

import com.testalpe.cobranca.MockCobranca;
import com.testalpe.domain.Boleto;
import com.testalpe.domain.Tax;
import com.testalpe.dto.BoletoDto;
import com.testalpe.dto.TaxDto;
import com.testalpe.service.BoletoService;
import com.testalpe.service.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax")
@AllArgsConstructor
public class TaxController {

    private final TaxService taxService;
    private final BoletoService boletoService;
    private final MockCobranca mockCobranca;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody TaxDto taxDto){
        try {
            Tax tax = new Tax();
            tax.setTaxNote(taxDto.taxNote());
            tax.setCNPJ(taxDto.cnpj());
            tax.setEmpresaNome(taxDto.empresaNome());
            tax.setEndereco(taxDto.endereco());
            tax.setValor(taxDto.valor());

            taxService.save(tax);

            List<BoletoDto> boletoDtos = taxDto.boletos();
            for (BoletoDto boletoDto : boletoDtos){
                Boleto boleto = new Boleto();
                boleto.setNomeEmissor(boletoDto.nomeEmissor());
                boleto.setNomePagador(boletoDto.nomePagador());
                boleto.setValor(boletoDto.valor());
                boleto.setTax(tax);

                boletoService.save(boleto);
                mockCobranca.enviarBoleto(boletoDto.nomePagador());

            }
            return ResponseEntity.ok("Nota emititda com sucesso!");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar nota " + e.getMessage());

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> get(@PathVariable long id){
        return ResponseEntity.ok(taxService.pesquisaNota(id));
    }
}