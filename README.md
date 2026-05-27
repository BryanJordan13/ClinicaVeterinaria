# 🐾 Sistema de Gestão de Clínica Veterinária

Projeto desenvolvido em **Java** para gestão de uma clínica veterinária, com funcionalidades de cadastro de animais, proprietários, veterinários e consultas, utilizando persistência em **MySQL** e ficheiros CSV.

---

## 📂 Estrutura do Projeto

```text
main
└── java
    └── projeto
        ├── database
        │   ├── DatabaseConnection
        │   └── TesteDBeaver
        │
        ├── exception
        │   ├── AnimalNaoEncontradoException
        │   └── ConsultaInvalidaException
        │
        ├── model
        │   ├── Animal
        │   ├── Ave
        │   ├── Cao
        │   ├── Consulta
        │   ├── Consultavel
        │   ├── Gato
        │   ├── Proprietario
        │   └── Veterinario
        │
        ├── repository
        │   ├── AnimalCsvRepository
        │   ├── AnimalRepositoryMySQL
        │   ├── ConsultaCsvRepository
        │   ├── ConsultaRepositoryMySQL
        │   ├── ProprietarioRepositoryMySQL
        │   └── VeterinarioRepositoryMySQL
        │
        ├── service
        │   └── ClinicaService
        │
        ├── ui
        │   ├── Main
        │   └── Menu
        │
        └── utils
            └── PdfExporter
```

---

# 📌 Funcionalidades

- ✅ Registo de animais
- ✅ Registo de proprietários
- ✅ Registo de veterinários
- ✅ Marcação de consultas
- ✅ Persistência de dados em MySQL
- ✅ Exportação para PDF
- ✅ Leitura e escrita em CSV
- ✅ Menu interativo em consola
- ✅ Tratamento de exceções personalizadas

---

# 🏗️ Arquitetura

O projeto segue uma estrutura organizada em camadas:

| Camada | Responsabilidade |
|---|---|
| `model` | Entidades do sistema |
| `repository` | Acesso e persistência de dados |
| `service` | Regras de negócio |
| `ui` | Interface com utilizador |
| `database` | Configuração da ligação à BD |
| `utils` | Funcionalidades auxiliares |
| `exception` | Exceções personalizadas |

---

# 🐶 Modelos do Sistema

## Animal
Classe base para os animais da clínica.

### Especializações:
- `Cao`
- `Gato`
- `Ave`

---

## Consulta
Representa uma consulta veterinária.

---

## Proprietario
Representa o dono do animal.

---

## Veterinario
Representa os médicos veterinários.

---

# 💾 Base de Dados

O projeto utiliza:

- **MySQL**
- Ligação JDBC
- Classe:
  - `DatabaseConnection`

---

# 📄 Exportação PDF

A classe:

```java
PdfExporter
```

permite gerar relatórios e documentos em PDF.

---

# ⚠️ Exceções Personalizadas

## `AnimalNaoEncontradoException`
Lançada quando um animal não existe no sistema.

## `ConsultaInvalidaException`
Lançada quando uma consulta possui dados inválidos.

---

# ▶️ Como Executar

## 1. Clonar o projeto

```bash
git clone <url-do-repositorio>
```

---

## 2. Configurar a Base de Dados

Criar a base de dados no MySQL e atualizar as credenciais em:

```java
DatabaseConnection.java
```

---

## 3. Executar a aplicação

Executar a classe:

```text
Main.java
```

---

# 🛠️ Tecnologias Utilizadas

- Java
- MySQL
- JDBC
- CSV
- PDF Export
- IntelliJ IDEA

---

# 📌 Possíveis Melhorias

- Interface gráfica (JavaFX/Swing)
- Sistema de autenticação
- API REST
- Testes unitários
- Dockerização
- Relatórios avançados

---

# 👨‍💻 Autores
Cristiana Dionisio, Bruno Monteiro, Ana Cruz

Projeto académico desenvolvido para prática de:

- Programação Orientada a Objetos
- Persistência de Dados
- Arquitetura em Camadas
- Manipulação de Ficheiros
- Integração com Base de Dados
