package com.autobots.msempresa.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.msempresa.entidades.Empresa;
import com.autobots.msempresa.modelo.AdicionadorLinkEmpresa;
import com.autobots.msempresa.modelo.EnderecoAtualizador;
import com.autobots.msempresa.repositorios.EmpresaRepositorio;

@Service
public class ServicoEmpresa {

    @Autowired
    private EmpresaRepositorio repositorio;

    @Autowired
    private AdicionadorLinkEmpresa adicionadorLink;

    public List<Empresa> listar() {
        List<Empresa> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Empresa buscar(long id) {
        Empresa empresa = repositorio.findById(id).orElse(null);
        if (empresa != null) adicionadorLink.adicionarLink(empresa);
        return empresa;
    }

    public void cadastrar(Empresa empresa) {
        repositorio.save(empresa);
    }

    public void atualizar(Empresa atualizacao) {
        Empresa empresa = repositorio.findById(atualizacao.getId()).orElseThrow();

        if (atualizacao.getRazaoSocial() != null && !atualizacao.getRazaoSocial().isBlank())
            empresa.setRazaoSocial(atualizacao.getRazaoSocial());
        if (atualizacao.getNomeFantasia() != null && !atualizacao.getNomeFantasia().isBlank())
            empresa.setNomeFantasia(atualizacao.getNomeFantasia());

        if (empresa.getEndereco() != null && atualizacao.getEndereco() != null) {
            EnderecoAtualizador atualizador = new EnderecoAtualizador();
            atualizador.atualizar(empresa.getEndereco(), atualizacao.getEndereco());
        } else if (empresa.getEndereco() == null && atualizacao.getEndereco() != null) {
            empresa.setEndereco(atualizacao.getEndereco());
        }

        repositorio.save(empresa);
    }

    public void excluir(long id) {
        repositorio.deleteById(id);
    }
}
