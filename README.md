# Cadastro de Ninjas

API REST em Spring Boot para cadastro de ninjas e missões, com relacionamento entre
as duas entidades, camada de DTOs e migrações de banco versionadas.

Projeto de estudo construído para praticar a stack de backend Java: mapeamento
objeto-relacional com JPA, separação em camadas, versionamento de schema com Flyway
e uma interface web simples em Thymeleaf consumindo o mesmo serviço da API.

## Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 4.0.6 |
| Persistência | Spring Data JPA / Hibernate |
| Banco | H2 |
| Migrações | Flyway |
| View | Thymeleaf |
| Build | Maven |
| Utilitários | Lombok |

## Arquitetura

Cada domínio (`Ninjas` e `Missoes`) é um pacote fechado com as mesmas cinco peças:

```
Controller  →  Service  →  Repository  →  Banco
                  ↕
              Mapper ↔ DTO
```

- **Controller** — expõe as rotas HTTP e traduz o resultado em `ResponseEntity` com o status certo
- **Service** — concentra a lógica; é o único ponto que o controller conhece
- **Repository** — `JpaRepository`, sem SQL escrito à mão
- **Mapper / DTO** — o `Model` (entidade JPA) nunca é devolvido direto na resposta

Essa separação é o motivo de o `NinjaController` e o `NinaControllerUi` (Thymeleaf)
conseguirem reaproveitar o mesmo `NinjaService` sem duplicar regra.

```
src/main/java/dev/v1ctorvm/cadastrodeninjas/
├── CadastroDeNinjasApplication.java
├── H2ConsoleConfig.java          # registra o console do H2 como servlet
├── Ninjas/
│   ├── NinjaController.java      # API REST
│   ├── NinaControllerUi.java     # tela Thymeleaf
│   ├── NinjaService.java
│   ├── NinjaRepository.java
│   ├── NinjaModel.java           # entidade
│   ├── NinjaDTO.java
│   └── NinjaMapper.java
└── Missoes/
    └── (mesma estrutura)
```

## Modelo de dados

Uma missão tem vários ninjas; cada ninja pertence a uma missão.

```
tb_missoes                        tb_cadastro
──────────────                    ─────────────────────
id          bigint  PK       ┌──  id          bigint  PK
nome        varchar          │    nome        varchar
dificuldade varchar          │    email       varchar  UNIQUE
                             │    idade       int
         1 ──────── N ───────┘    rank        varchar
                                  img_url     varchar
                                  missoes_id  bigint   FK
```

O lado `@OneToMany` em `MissaoModel` é anotado com `@JsonIgnore` e o `@ToString` de
`NinjaModel` exclui `missoes` — as duas coisas existem para cortar a recursão infinita
que um relacionamento bidirecional causa na serialização.

## Endpoints

### Ninjas — `/ninjas`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/ninjas/boasvindas` | Rota de teste |
| `GET` | `/ninjas/listar` | Lista todos os ninjas |
| `GET` | `/ninjas/listar/{id}` | Busca por ID — `404` se não existir |
| `POST` | `/ninjas/criar` | Cria um ninja — responde `201` |
| `PUT` | `/ninjas/alterar/{id}` | Atualiza — `404` se não existir |
| `DELETE` | `/ninjas/deletar/{id}` | Remove — `404` se não existir |

### Missões — `/missoes`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/missoes/listar` | Lista todas as missões |
| `GET` | `/missoes/listar/{id}` | Busca por ID |
| `POST` | `/missoes/criar` | Cria uma missão — responde `201` |
| `PUT` | `/missoes/alterar/{id}` | Atualiza |
| `DELETE` | `/missoes/deletar/{id}` | Remove |

### Interface web

| Rota | Descrição |
|---|---|
| `/ninjas/ui/listar` | Tabela de ninjas renderizada com Thymeleaf |
| `/h2-console` | Console do banco H2 |

## Exemplo de uso

Criar um ninja:

```bash
curl -X POST http://localhost:8080/ninjas/criar -H "Content-Type: application/json" -d "{\"nome\":\"Naruto\",\"email\":\"naruto@konoha.com\",\"idade\":17,\"rank\":\"Genin\"}"
```

Listar todos:

```bash
curl http://localhost:8080/ninjas/listar
```

## Como rodar

Requisitos: **JDK 17+** e **Maven** (ou use o `mvnw` incluído no projeto).

As credenciais do banco vêm de variáveis de ambiente — nada de senha versionada no
`application.properties`. Defina as três antes de subir:

```bash
export DATABASE_URL=jdbc:h2:file:./data/ninjas
export DATABASE_USERNAME=sa
export DATABASE_PASSWORD=
```

No Windows (PowerShell):

```powershell
$env:DATABASE_URL = "jdbc:h2:file:./data/ninjas"
$env:DATABASE_USERNAME = "sa"
$env:DATABASE_PASSWORD = ""
```

Depois:

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

## Migrações

O schema é controlado pelo Flyway, não pelo Hibernate — `spring.jpa.hibernate.ddl-auto`
está em `none` de propósito. Toda mudança de estrutura entra como um arquivo novo em
`src/main/resources/db/migration`, seguindo o padrão `V<n>__<descricao>.sql`.

```
db/migration/
└── V2__Add_rank_tb_cadastro.sql   # adiciona a coluna rank em tb_cadastro
```

## Próximos passos

- [ ] Validação de entrada com Bean Validation (`@Valid`, `@NotBlank`, `@Email`)
- [ ] Tratamento centralizado de erros com `@RestControllerAdvice`
- [ ] Documentação interativa com Swagger / OpenAPI
- [ ] Testes de integração dos controllers com `MockMvc`
- [ ] Trocar H2 por PostgreSQL em container Docker
