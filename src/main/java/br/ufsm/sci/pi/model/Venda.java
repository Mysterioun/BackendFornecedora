package br.ufsm.sci.pi.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Venda.getTodasVendas", query = "select v from Venda v order by v.id desc")
@NamedQuery(name = "Venda.getVendaPeloUsername", query = "select v from Venda v where v.criadoPor=:username order by v.id desc")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "numeroContato")
    private String numeroContato;

    @Column(name = "metodoPagamento")
    private String metodoPagamento;

    @Column(name = "total")
    private Float total;

    @Column(name = "produtoDetalhes", columnDefinition = "json")
    private String produtoDetalhes;

    @Column(name = "criadoPor")
    private String criadoPor;

}
