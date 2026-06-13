# AV5-WEBIII — Arquitetura de Microsserviços

## Microsserviços

| Microsserviço | Porta | Responsabilidade                                            |
|---------------|-------|--------------------------------------------------------------|
| ms-usuario    | 8081  | Clientes, usuários (funcionários), autenticação JWT (`/login`) |
| ms-empresa    | 8082  | Empresas (lojas) + agregação (`/empresa/{id}/...`)            |
| ms-catalogo   | 8083  | Mercadorias e serviços                                        |
| ms-venda      | 8084  | Vendas (+ `/venda/{id}/completa`)                             |
| ms-veiculo    | 8085  | Veículos atendidos                                            |
| api-gateway   | 8080  | Roteamento + validação JWT (Spring Cloud Gateway)             |

## Como executar

Cada pasta é um projeto Maven independente. Em terminais separados:

```bash
cd ms-usuario  && ./mvnw spring-boot:run
cd ms-empresa  && ./mvnw spring-boot:run
cd ms-catalogo && ./mvnw spring-boot:run
cd ms-venda    && ./mvnw spring-boot:run
cd ms-veiculo  && ./mvnw spring-boot:run
cd api-gateway && ./mvnw spring-boot:run
```

Recomenda-se iniciar `ms-usuario`, `ms-empresa`, `ms-catalogo` e `ms-veiculo`
primeiro (eles populam dados iniciais com ids fixos referenciados pelo
`ms-venda`), e por último o `api-gateway`.

## Autenticação

Todo o tráfego passa pelo `api-gateway` (porta 8080), que valida o JWT
(exceto `POST /login`) e roteia para o microsserviço correspondente.

1. `POST http://localhost:8080/login` com corpo:
   ```json
   { "nomeUsuario": "admin", "senha": "admin123" }
   ```
   O JWT retornado no header `Authorization: Bearer <token>` contém a claim
   `perfis` com os papéis (`ROLE_ADMIN`, `ROLE_GERENTE`, `ROLE_VENDEDOR`,
   `ROLE_CLIENTE`), usada por todos os microsserviços para autorização.

2. Use o token nas demais requisições, ex:
   `GET http://localhost:8080/empresa/1/mercadorias`

Usuários de teste (criados pelo `ms-usuario` na inicialização):

| usuário  | senha       | perfil        |
|----------|-------------|---------------|
| admin    | admin123    | ROLE_ADMIN    |
| gerente  | gerente123  | ROLE_GERENTE  |
| vendedor | vendedor123 | ROLE_VENDEDOR |
| cliente  | cliente123  | ROLE_CLIENTE  |

## Comunicação entre microsserviços

- `ms-empresa` consulta `ms-usuario` (`/usuario`), `ms-catalogo`
  (`/mercadoria/empresa/{id}`, `/servico/empresa/{id}`), `ms-venda`
  (`/venda/periodo`) e `ms-veiculo` (`/veiculo/venda?ids=...`) para compor
  `/empresa/{id}/clientes`, `/funcionarios`, `/mercadorias`, `/servicos`,
  `/vendas` e `/veiculos`.
- `ms-venda` consulta `ms-usuario`, `ms-catalogo` e `ms-veiculo` para compor
  `GET /venda/{id}/completa`.
- `ms-usuario`, ao excluir um usuário, chama `DELETE /empresa/usuarios/{id}`
  no `ms-empresa`.

Todas as chamadas entre microsserviços reenviam o header `Authorization`
recebido na requisição original.
