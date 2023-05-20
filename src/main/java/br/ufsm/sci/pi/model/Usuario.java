package br.ufsm.sci.pi.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Usuario.findByEmailId", query = "select u from Usuario u where u.email=:email")
@NamedQuery(name = "Usuario.obterTodosUsuarios", query = "select new br.ufsm.sci.pi.wrapper.UsuarioWrapper(u.id, u.nome, u.email, u.numeroContato, u.status) from Usuario u where u.role='cliente'")
@NamedQuery(name = "Usuario.editarStatus", query = "update Usuario u set u.status=:status where u.id=:id")
@NamedQuery(name = "Usuario.obterUsuarioPeloId", query = "select new br.ufsm.sci.pi.wrapper.UsuarioWrapper(u.id, u.nome, u.email, u.numeroContato, u.status) from Usuario u where u.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numeroContato")
    private String numeroContato;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;

    @Column(name = "token")
    private String token;

}
