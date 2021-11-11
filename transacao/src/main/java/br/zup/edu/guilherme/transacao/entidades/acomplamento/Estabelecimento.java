package br.zup.edu.guilherme.transacao.entidades.acomplamento;

import javax.persistence.Embeddable;

@Embeddable
public class Estabelecimento {
    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public Estabelecimento() {

    }

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
