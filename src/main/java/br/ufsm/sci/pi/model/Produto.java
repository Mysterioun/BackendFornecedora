package br.ufsm.sci.pi.model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name = "Produto.getTodosProdutos", query = "select new br.ufsm.sci.pi.wrapper.ProdutoWrapper(p.id, p.nome, p.descricao, p.preco, p.status, p.categoria.id, p.categoria.nome) from Produto p")
@NamedQuery(name = "Produto.editarProdutoStatus", query = "update Produto p set p.status=:status where p.id=:id")
@NamedQuery(name = "Produto.getProdutoPelaCategoria", query = "select new br.ufsm.sci.pi.wrapper.ProdutoWrapper(p.id,p.nome) from Produto p where p.categoria.id=:id and p.status ='true'")
@NamedQuery(name = "Produto.getProdutoPeloId", query = "select new br.ufsm.sci.pi.wrapper.ProdutoWrapper(p.id, p.nome, p.descricao, p.preco) from Produto p where p.id =: id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "produto")
public class Produto implements Serializable {

    public static final Long serialVersionUid = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_fk", nullable = false)
    private Categoria categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "status")
    private String status;

}
