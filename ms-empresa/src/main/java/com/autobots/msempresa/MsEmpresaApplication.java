package com.autobots.msempresa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.msempresa.entidades.Empresa;
import com.autobots.msempresa.entidades.Endereco;
import com.autobots.msempresa.entidades.Telefone;
import com.autobots.msempresa.repositorios.EmpresaRepositorio;


@SpringBootApplication
public class MsEmpresaApplication implements CommandLineRunner {

    @Autowired
    private EmpresaRepositorio repositorio;

    public static void main(String[] args) {
        SpringApplication.run(MsEmpresaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Car Service Toyota Ltda");
        empresa.setNomeFantasia("Toyota Car Service");
        empresa.setCadastro(new Date());

        Endereco endereco = new Endereco();
        endereco.setEstado("São Paulo");
        endereco.setCidade("São Paulo");
        endereco.setBairro("Itaim Bibi");
        endereco.setRua("Av. Brigadeiro Faria Lima");
        endereco.setNumero("1500");
        endereco.setCodigoPostal("01452-001");
        empresa.setEndereco(endereco);

        Telefone telefone = new Telefone();
        telefone.setDdd("011");
        telefone.setNumero("30001234");
        empresa.getTelefones().add(telefone);

        repositorio.save(empresa); // gera id=1L
    }
}
