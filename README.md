# 🐾 Clínica Veterinária — Sistema de Gestão
Sistema de gestão desenvolvido em **Java**, com arquitetura em camadas e persistência em **MySQL**, para administração de animais, proprietários, veterinários e consultas, incluindo emissão de faturas.

---

## 📌 Objetivo do Projeto
Este projeto implementa um sistema completo para uma **Clínica Veterinária**, permitindo gerir:

- Animais e respetivos proprietários
- Veterinários e especialidades
- Consultas médicas
- Emissão de faturas com contribuinte
- Armazenamento em base de dados MySQL

O sistema é modular e segue boas práticas de programação orientada a objetos.

---

## 🗂 Estrutura do Projeto
src/main/java/projeto/
│
├── database/
│   ├── DatabaseConnection.java
│   └── TesteDBeaver.java
│
├── exception/
│   ├── AnimalNaoEncontradoException.java
│   └── ConsultaInvalidaException.java
│
├── model/
│   ├── Animal.java
│   ├── Ave.java
│   ├── Cao.java
│   ├── Gato.java
│   ├── Proprietario.java
│   ├── Veterinario.java
│   ├── Consulta.java
│   └── Consultavel.java
│
├── repository/
│   ├── AnimalCsvRepository.java
│   ├── AnimalRepositoryMySQL.java
│   ├── ConsultaCsvRepository.java
│   ├── ConsultaRepositoryMySQL.java
│   ├── ProprietarioRepositoryMySQL.java
│   └── VeterinarioRepositoryMySQL.java
│
├── service/
│   └── ClinicaService.java
│
└── ui/
├── Main.java
└── Menu.java         # Interface de consola

✨ Funcionalidades
🐶 Gestão de Animais
Registar animais (cão, gato, ave, etc.)

Associar a um proprietário

Listar animais existentes

👨‍⚕️ Gestão de Veterinários
Registar veterinários

Guardar especialidade e cédula profissional

Listar veterinários

🩺 Consultas
Agendar consulta com:

Animal

Veterinário

Diagnóstico

Data automática (LocalDate.now)

Guardar consulta em MySQL

Listar consultas por animal ou veterinário

💶 Emissão de Faturas
Pergunta ao utilizador se deseja emitir fatura

Validação do contribuinte (9 dígitos)

Guardado na base de dados

Mostrado no toString() da consulta

🗄 Base de Dados (MySQL)
Tabela consultas
sql
CREATE TABLE consultas (
id INT AUTO_INCREMENT PRIMARY KEY,
animal_id INT NOT NULL,
veterinario_id INT NOT NULL,
diagnostico TEXT NOT NULL,
data DATE NOT NULL,
emitir_fatura BOOLEAN DEFAULT FALSE,
contribuinte VARCHAR(20),
FOREIGN KEY (animal_id) REFERENCES animais(id),
FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id)
);


⚙️ Requisitos
Java 17+

MySQL 8+

Driver JDBC

DBeaver / MySQL Workbench (opcional)

▶️ Como Executar
Criar a base de dados:

sql
CREATE DATABASE clinica_veterinaria;
Configurar DatabaseConnection.java com:

URL

Utilizador

Password

Criar as tabelas necessárias

Executar o projeto:

Via IDE (IntelliJ/Eclipse) → correr Main

Ou via terminal:

Código
javac Main.java
java Main
🧪 Melhorias Futuras
Exportar fatura em PDF

Sistema de login (admin / funcionário)

Dashboard com estatísticas

Histórico clínico completo

👨‍💻 Autor
Projeto desenvolvido por Bruno Monteiro, Cristiana Dionisio e Ana Cruz
Estudante de programador Java.