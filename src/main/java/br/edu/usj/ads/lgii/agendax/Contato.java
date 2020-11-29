package br.edu.usj.ads.lgii.agendax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    Long Id;
    
    @Column
    String nome;
    
    @Column
    String tipo;
    
    @Column
    String telefone;

    @Column
    String city;  
}
