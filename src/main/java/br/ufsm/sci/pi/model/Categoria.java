package br.ufsm.sci.pi.model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Categoria.getTodasCategorias", query = "select c from Categoria c where c.id in (select p.categoria from Produto p where p.status = 'true')")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "categoria")
public class Categoria implements Serializable {

    //essa variavel é usada para serializar a classe e enviar para o banco de dados
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "nome")
    private String nome;
}
