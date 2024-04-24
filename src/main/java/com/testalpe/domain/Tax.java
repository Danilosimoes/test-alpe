package com.testalpe.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tax")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "tax")
    private List<Boleto> boletos;
    private String taxNote;
    private String CNPJ;
    private String empresaNome;
    private String endereco;
    private double valor;

}
