package com.autobots.msempresa.modelo;

import com.autobots.msempresa.entidades.Endereco;

public class EnderecoAtualizador {
    public void atualizar(Endereco endereco, Endereco atualizacao) {
        if (endereco != null && atualizacao != null) {
            if (atualizacao.getEstado() != null && !atualizacao.getEstado().isBlank())
                endereco.setEstado(atualizacao.getEstado());
            if (atualizacao.getCidade() != null && !atualizacao.getCidade().isBlank())
                endereco.setCidade(atualizacao.getCidade());
            if (atualizacao.getBairro() != null && !atualizacao.getBairro().isBlank())
                endereco.setBairro(atualizacao.getBairro());
            if (atualizacao.getRua() != null && !atualizacao.getRua().isBlank())
                endereco.setRua(atualizacao.getRua());
            if (atualizacao.getNumero() != null && !atualizacao.getNumero().isBlank())
                endereco.setNumero(atualizacao.getNumero());
            if (atualizacao.getCodigoPostal() != null && !atualizacao.getCodigoPostal().isBlank())
                endereco.setCodigoPostal(atualizacao.getCodigoPostal());
            if (atualizacao.getInformacoesAdicionais() != null && !atualizacao.getInformacoesAdicionais().isBlank())
                endereco.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
        }
    }
}
