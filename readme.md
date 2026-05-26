# 🕹️ Jogo de Damas

Um jogo de damas desenvolvido em **Java** com interface gráfica utilizando **Swing**, gerenciamento de dependências com **Maven** e integração com banco de dados **MySQL** para armazenamento do ranking das partidas.

---

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de possuir instalado:

- **Java 17** ou superior
- **Maven 4**
- **MySQL**

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório e vá para a pasta raíz do projeto

```bash
git clone https://github.com/Paulouuul/projetoDama.git
cd projetoDama
```

---

### 2. Configure o banco de dados

Crie um arquivo `.env` na pasta raíz do projeto com base na estrutura do arquivo arquivo `.env.example`.

Exemplo:

```env
DB_URL=jdbc:mysql://localhost:3306/
DB_NAME=projetodama
DB_USER=root
DB_PASSWORD=sua_senha
```

> ⚠️ O arquivo `.env` está no `.gitignore` para evitar o compartilhamento de credenciais.

---

### 3. Compile o projeto

```bash
mvn clean compile
```

---

### 4. Execute o jogo

#### Opção 1 — Via Maven

```bash
mvn exec:java
```

#### Opção 2 — Executando diretamente

```bash
java -cp target/classes controller.JogoDeDamas
```

---

### 5. Gerar arquivo `.jar` (Opcional)

```bash
mvn package
```

Executar o `.jar`:

```bash
java -jar target/projetoDama.jar
```

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** — Linguagem principal
- **Swing** — Interface gráfica
- **Maven 4** — Gerenciamento de dependências
- **MySQL** — Banco de dados
- **MySQL Connector/J** — Conector JDBC
- **Dotenv Java** — Variáveis de ambiente

---

## 📦 Dependências Maven

```xml
<dependencies>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.5.0</version>
    </dependency>

    <!-- Dotenv -->
    <dependency>
        <groupId>io.github.cdimascio</groupId>
        <artifactId>dotenv-java</artifactId>
        <version>3.0.0</version>
    </dependency>

</dependencies>
```

---

## 🎮 Funcionalidades

- Interface gráfica completa
- Sistema de movimentação das peças
- Captura de peças adversárias
- Transformação em dama
- Controle de turnos
- Contagem de movimentos
- Cronômetro da partida
- Ranking persistido em banco de dados
- Registro automático do vencedor
---

## 🖼️ Telas do Projeto

### Tela do Tabuleiro

![Tela do tabuleiro](images/jogo_andamento.png)

---

### Tela de Seleção de Jogadores

![Tela da partida](images/selecionar_jogador.png)

---

### Tela de Ranking

![Tela de ranking](images/ranking.png)

---

## 📁 Estrutura do Projeto

```text
├──projetoDama/
│  ├──src/
│  │  └── main/
│  │      ├── java/
│  │      │   ├── controller/
│  │      │   ├── dao/
│  │      │   ├── model/
│  │      │   └── view/
│  │      └── resources/
│  └──pom.xml
├── images/
├── .env.example
├── .gitignore
└── README.md
```

---

## 🎯 Como Jogar

1. Informe o nome dos jogadores
2. As peças brancas iniciam a partida
3. Clique na peça desejada
4. Clique na posição de destino
5. Capture peças pulando sobre elas
6. Alcance o lado oposto para virar dama
7. O vencedor será salvo automaticamente no ranking

---

## 🗄️ Banco de Dados

O sistema cria automaticamente:

- Database
- Tabela: `vencedores`

### Estrutura da tabela

| Campo               | Tipo                 | Descrição                     |
| ------------------- | -------------------- | ----------------------------- |
| id                  | INT AUTO_INCREMENT   | Identificador único           |
| nome_jogador        | VARCHAR(255)         | Nome do vencedor              |
| cor_peca            | VARCHAR(10)          | Cor da peça vencedora         |
| minutos_partida     | INT                  | Minutos da partida            |
| segundos_partida    | INT                  | Segundos restantes            |
| segundos_totais     | INT                  | Tempo total da partida        |
| movimentos_validos  | INT                  | Quantidade de movimentos      |

---

## 👨‍💻 Autor

Desenvolvido por Paulo Ricardo Tebet Lyrio como projeto de estudo de:

- Programação Orientada a Objetos
- Java Desktop
- Maven
- JDBC
- MySQL
- Arquitetura MVC
- DAO
- Interfaces gráficas com Swing
