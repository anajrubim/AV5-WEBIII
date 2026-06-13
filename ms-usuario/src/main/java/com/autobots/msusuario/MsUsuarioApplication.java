package com.autobots.msusuario;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.autobots.msusuario.entidades.CredencialUsuarioSenha;
import com.autobots.msusuario.entidades.Documento;
import com.autobots.msusuario.entidades.Email;
import com.autobots.msusuario.entidades.Endereco;
import com.autobots.msusuario.entidades.Telefone;
import com.autobots.msusuario.entidades.Usuario;
import com.autobots.msusuario.enumeracoes.PerfilUsuario;
import com.autobots.msusuario.enumeracoes.TipoDocumento;
import com.autobots.msusuario.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class MsUsuarioApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepositorio repositorioUsuario;

    public static void main(String[] args) {
        SpringApplication.run(MsUsuarioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

        Usuario admin = new Usuario();
        admin.setNome("Administrador do Sistema");
        admin.setNomeSocial("Admin");
        admin.setEmpresaId(1L);
        admin.getPerfis().add(PerfilUsuario.ROLE_ADMIN);

        CredencialUsuarioSenha credAdmin = new CredencialUsuarioSenha();
        credAdmin.setInativo(false);
        credAdmin.setNomeUsuario("admin");
        credAdmin.setSenha(codificador.encode("admin123"));
        credAdmin.setCriacao(new Date());
        credAdmin.setUltimoAcesso(new Date());
        admin.getCredenciais().add(credAdmin);
        repositorioUsuario.save(admin);

        Usuario gerente = new Usuario();
        gerente.setNome("Pedro Alcântara de Bragança e Bourbon");
        gerente.setNomeSocial("Dom Pedro Gerente");
        gerente.setEmpresaId(1L);
        gerente.getPerfis().add(PerfilUsuario.ROLE_GERENTE);

        Email emailGerente = new Email();
        emailGerente.setEndereco("gerente@toyota.com");
        gerente.getEmails().add(emailGerente);

        Endereco endGerente = new Endereco();
        endGerente.setEstado("São Paulo");
        endGerente.setCidade("São Paulo");
        endGerente.setBairro("Jardins");
        endGerente.setRua("Av. São Gabriel");
        endGerente.setNumero("00");
        endGerente.setCodigoPostal("01435-001");
        gerente.setEndereco(endGerente);

        Telefone telGerente = new Telefone();
        telGerente.setDdd("011");
        telGerente.setNumero("9854633728");
        gerente.getTelefones().add(telGerente);

        Documento cpfGerente = new Documento();
        cpfGerente.setDataEmissao(new Date());
        cpfGerente.setNumero("856473819229");
        cpfGerente.setTipo(TipoDocumento.CPF);
        gerente.getDocumentos().add(cpfGerente);

        CredencialUsuarioSenha credGerente = new CredencialUsuarioSenha();
        credGerente.setInativo(false);
        credGerente.setNomeUsuario("gerente");
        credGerente.setSenha(codificador.encode("gerente123"));
        credGerente.setCriacao(new Date());
        credGerente.setUltimoAcesso(new Date());
        gerente.getCredenciais().add(credGerente);
        repositorioUsuario.save(gerente);

        Usuario vendedor = new Usuario();
        vendedor.setNome("Componentes Varejo de Partes Automotivas Ltda");
        vendedor.setNomeSocial("Vendedor");
        vendedor.setEmpresaId(1L);
        vendedor.getPerfis().add(PerfilUsuario.ROLE_VENDEDOR);

        Email emailVendedor = new Email();
        emailVendedor.setEndereco("vendedor@toyota.com");
        vendedor.getEmails().add(emailVendedor);

        CredencialUsuarioSenha credVendedor = new CredencialUsuarioSenha();
        credVendedor.setInativo(false);
        credVendedor.setNomeUsuario("vendedor");
        credVendedor.setSenha(codificador.encode("vendedor123"));
        credVendedor.setCriacao(new Date());
        credVendedor.setUltimoAcesso(new Date());
        vendedor.getCredenciais().add(credVendedor);

        Documento cnpj = new Documento();
        cnpj.setDataEmissao(new Date());
        cnpj.setNumero("00014556000100");
        cnpj.setTipo(TipoDocumento.CNPJ);
        vendedor.getDocumentos().add(cnpj);

        Endereco endVendedor = new Endereco();
        endVendedor.setEstado("Rio de Janeiro");
        endVendedor.setCidade("Rio de Janeiro");
        endVendedor.setBairro("Centro");
        endVendedor.setRua("Av. República do Chile");
        endVendedor.setNumero("00");
        endVendedor.setCodigoPostal("20031-170");
        vendedor.setEndereco(endVendedor);
        vendedor.getMercadoriaIds().add(1L);
        repositorioUsuario.save(vendedor);

        Usuario cliente = new Usuario();
        cliente.setNome("Pedro Alcântara de Bragança e Bourbon");
        cliente.setNomeSocial("Dom Pedro Cliente");
        cliente.setEmpresaId(1L);
        cliente.getPerfis().add(PerfilUsuario.ROLE_CLIENTE);

        Email emailCliente = new Email();
        emailCliente.setEndereco("c@c.com");
        cliente.getEmails().add(emailCliente);

        Documento cpfCliente = new Documento();
        cpfCliente.setDataEmissao(new Date());
        cpfCliente.setNumero("12584698533");
        cpfCliente.setTipo(TipoDocumento.CPF);
        cliente.getDocumentos().add(cpfCliente);

        CredencialUsuarioSenha credCliente = new CredencialUsuarioSenha();
        credCliente.setInativo(false);
        credCliente.setNomeUsuario("cliente");
        credCliente.setSenha(codificador.encode("cliente123"));
        credCliente.setCriacao(new Date());
        credCliente.setUltimoAcesso(new Date());
        cliente.getCredenciais().add(credCliente);

        Endereco endCliente = new Endereco();
        endCliente.setEstado("São Paulo");
        endCliente.setCidade("São José dos Campos");
        endCliente.setBairro("Centro");
        endCliente.setRua("Av. Dr. Nelson D'Ávila");
        endCliente.setNumero("00");
        endCliente.setCodigoPostal("12245-070");
        cliente.setEndereco(endCliente);
        cliente.getVeiculoIds().add(1L);
        cliente.getVendaIds().add(1L);
        repositorioUsuario.save(cliente);
    }
}
