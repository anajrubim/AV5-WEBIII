# AV5-WEBIII — Arquitetura de Microsserviços

## Microsserviços

| Microsserviço | Porta | Responsabilidade                                             |
|---------------|-------|---------------------------------------------------------------|
| ms-usuario    | 8081  | Clientes, usuários, autenticação JWT (`/login`)                |
| ms-empresa    | 8082  | Empresas (lojas) + agregação (`/empresa/{id}/...`)             |
| ms-catalogo   | 8083  | Mercadorias e serviços                                         |
| ms-venda      | 8084  | Vendas (+ `/venda/{id}/completa`)                              |
| ms-veiculo    | 8085  | Veículos atendidos                                             |
| api-gateway   | 8080  | Roteamento + validação JWT (Spring Cloud Gateway)              |

## Pré-requisitos

- **JDK 17** instalado
- **JAVA_HOME** configurado apontando para esse JDK

Verifique com:
```bash
java -version
```
Deve mostrar `17.x.x`. Se não tiver o JDK 17, baixe em:
https://adoptium.net/temurin/releases/?version=17

Cada projeto já inclui o Maven Wrapper (`mvnw`/`mvnw.cmd`), então não é
necessário instalar o Maven.

## Como executar

Abra 6 terminais (um por serviço) e rode em cada:

```bash
# Windows
cd ms-usuario  && .\mvnw.cmd spring-boot:run

# Linux/macOS
cd ms-usuario  && ./mvnw spring-boot:run
```

Repita para `ms-empresa`, `ms-catalogo`, `ms-veiculo`, `ms-venda` e por
último `api-gateway`. A ordem importa: o `ms-venda` referencia dados criados
pelos outros, então inicie os demais primeiro.

A primeira execução baixa as dependências do Maven Central (pode demorar).
Aguarde a mensagem `Started Ms...Application` em cada terminal.

## Testando com Postman

Todo o tráfego passa pelo `api-gateway` (porta 8080).

**1. Login**
```
POST http://localhost:8080/login
Content-Type: application/json

{ "nomeUsuario": "admin", "senha": "admin123" }
```
O token JWT vem no header `Authorization: Bearer ...` da resposta (não no
corpo). Copie o token (sem "Bearer ") e use como Bearer Token nas próximas
requisições. Expira em 10 minutos.

**2. Usuários de teste**

| usuário  | senha       | perfil        |
|----------|-------------|---------------|
| admin    | admin123    | ROLE_ADMIN    |
| gerente  | gerente123  | ROLE_GERENTE  |
| vendedor | vendedor123 | ROLE_VENDEDOR |
| cliente  | cliente123  | ROLE_CLIENTE  |

**3. Endpoints principais**
```
GET http://localhost:8080/usuario
GET http://localhost:8080/empresa/1
GET http://localhost:8080/empresa/1/mercadorias
GET http://localhost:8080/empresa/1/clientes
GET http://localhost:8080/mercadoria
GET http://localhost:8080/servico
GET http://localhost:8080/venda/1/completa
GET http://localhost:8080/veiculo/1
```

`GET /venda/1/completa` é o melhor exemplo de comunicação entre
microsserviços: o ms-venda consulta ms-usuario, ms-catalogo e ms-veiculo via
REST para montar a resposta.

## Comunicação entre microsserviços

- `ms-empresa` consulta `ms-usuario`, `ms-catalogo` e `ms-veiculo`/`ms-venda`
  para compor `/empresa/{id}/clientes`, `/funcionarios`, `/mercadorias`,
  `/servicos`, `/vendas` e `/veiculos`.
- `ms-venda` consulta `ms-usuario`, `ms-catalogo` e `ms-veiculo` para compor
  `GET /venda/{id}/completa`.
- Todas as chamadas reenviam o header `Authorization` recebido na requisição
  original.
- Todos os microsserviços compartilham o mesmo `jwt.secret` para validar o
  token emitido pelo `ms-usuario`.
