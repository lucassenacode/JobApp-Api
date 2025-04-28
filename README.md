# 📚 JobAPI - API REST de Vagas de Emprego

API de cadastro de **empresas**, **vagas de emprego** e **reviews** de empresas.  
Desenvolvido com **Spring Boot**, **Docker**, **PostgreSQL** e **H2 Database**.

---

## ✨ Tecnologias Usadas
- Java 21
- Spring Boot 3
- Spring Data JPA
- Banco de Dados H2 (em memória) ou PostgreSQL (Docker)
- Lombok
- Docker e Docker Compose
- Maven

---

## 🚀 Como Rodar o Projeto Localmente

### 1️⃣ Clone o repositório
```bash
git clone https://github.com/seu-usuario/jobapi.git
cd jobapi
```

---

## 🔧 Configuração Local (Banco H2)

### 2️⃣ Rodar no perfil `h2` (memória)

O projeto já vem configurado para rodar com o **H2 Database**.

- Arquivo `application.properties`:
  ```properties
  spring.datasource.driver-class-name=org.h2.Driver
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.username=sa
  spring.datasource.password=
  spring.h2.console.enabled=true
  spring.h2.console.path=/h2-console
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=update
  ```

- Para rodar:
  ```bash
  ./mvnw spring-boot:run
  ```
  ou rodar direto na sua IDE (VSCode, IntelliJ).

- Acesse:
  - Aplicação: `http://localhost:8080`
  - Console H2: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User: `sa`

---

## 🐳 Configuração com Docker + PostgreSQL

### 3️⃣ Banco de dados PostgreSQL com Docker

Crie uma rede para conectar os containers:
```bash
docker network create postgres_network
```

Rode o container do PostgreSQL:
```bash
docker run -d \
  --name postgres_container \
  --network postgres_network \
  -e POSTGRES_USER=lucas \
  -e POSTGRES_PASSWORD=123456 \
  -e POSTGRES_DB=jobapi \
  -p 5432:5432 \
  postgres
```

Rode o container do PgAdmin4 (opcional):
```bash
docker run -d \
  --name pgadmin_container \
  --network postgres_network \
  -e PGADMIN_DEFAULT_EMAIL=admin@admin.com \
  -e PGADMIN_DEFAULT_PASSWORD=12345 \
  -p 5050:80 \
  dpage/pgadmin4
```

Acesse o PgAdmin:
- `http://localhost:5050`
- Login: `admin@admin.com`
- Senha: `12345`

---

### 4️⃣ Configurar o Spring Boot para PostgreSQL

Arquivo `application-postgres.properties`:
```properties
spring.datasource.url=jdbc:postgresql://postgres_container:5432/jobapi
spring.datasource.username=lucas
spring.datasource.password=123456
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Altere o perfil ativo em `application.properties`:
```properties
spring.profiles.active=postgres
```

---

### 5️⃣ Build e Rodar com Docker Compose

Build da aplicação:
```bash
docker build -t jobapi .
```

Rode todos os containers:
```bash
docker-compose up --build -d
```

Sua aplicação ficará disponível em:
- `http://localhost:8080`

---

## 🏩 Estrutura das Entidades

- `Company`
  - `id`, `name`, `description`
  - Relação: 1 Empresa → Muitas Vagas (`Job`) e Reviews (`Review`)

- `Job`
  - `id`, `title`, `description`, `minSalary`, `maxSalary`, `location`, `company`

- `Review`
  - `id`, `title`, `description`, `rating`, `company`

---

## 🛠️ Endpoints da API

| Verbo | Caminho                         | Descrição                         |
|:------|:---------------------------------|:----------------------------------|
| POST  | `/companies`                     | Criar uma empresa                 |
| GET   | `/companies`                     | Listar todas empresas             |
| GET   | `/companies/{id}`                 | Buscar empresa por ID             |
| PUT   | `/companies/{id}`                 | Atualizar empresa                 |
| DELETE| `/companies/{id}`                 | Deletar empresa                   |
| POST  | `/jobs`                           | Criar uma vaga                    |
| GET   | `/jobs`                           | Listar todas vagas                |
| GET   | `/jobs/{id}`                      | Buscar vaga por ID                |
| PUT   | `/jobs/{id}`                      | Atualizar vaga                    |
| DELETE| `/jobs/{id}`                      | Deletar vaga                      |
| POST  | `/companies/{id}/reviews`         | Criar review para empresa         |
| GET   | `/companies/{id}/reviews`         | Listar reviews da empresa         |
| GET   | `/companies/{id}/reviews/{reviewId}` | Buscar review por ID             |
| PUT   | `/companies/{id}/reviews/{reviewId}` | Atualizar review                |
| DELETE| `/companies/{id}/reviews/{reviewId}` | Deletar review                  |

---

## 🐳 Comandos Docker Úteis

```bash
# Ver containers rodando
docker ps

# Ver imagens locais
docker images

# Ver logs do container
docker logs nome-ou-id

# Parar um container
docker stop nome-ou-id

# Remover container
docker rm nome-ou-id

# Remover imagem
docker rmi nome-da-imagem
```

---

## 📋 Observações

- Projeto preparado para rodar com H2 (testes) ou PostgreSQL (Docker).
- Aplicação **dockerizada** usando **build multi-stage** (Maven + JRE).
- Porta padrão da aplicação: `8080`.

---

## ✍️ Autor

- [Lucas Sena](https://github.com/seu-usuario)

---

