package br.ufsm.sci.pi.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioWrapper {

    private Integer id;
    private String nome;
    private String email;
    private String numeroContato;
    private String status;


    public UsuarioWrapper(Integer id, String nome, String email, String numeroContato, String status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numeroContato = numeroContato;
        this.status = status;
    }
}
