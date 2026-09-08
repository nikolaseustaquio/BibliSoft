# 📚 BibliotecaSoft — Sistema de Gerenciamento de Biblioteca

Sistema de gerenciamento de acervo e empréstimos de livros, desenvolvido em Java. Projeto principal de prática durante o roadmap de desenvolvimento Java, com evolução contínua de funcionalidades e boas práticas.

---

## 🛠️ Tecnologias

- Java 17+
- Orientação a Objetos
- Streams API
- Persistência em arquivo `.txt`
- IntelliJ IDEA

---

## ⚙️ Funcionalidades

- ✅ Cadastrar livro no acervo
- ✅ Listar todos os livros
- ✅ Buscar livro por título (via Stream)
- ✅ Remover livro do acervo
- ✅ Registrar empréstimo de livro
- ✅ Persistência dos dados em arquivo `.txt` (salvar e carregar automaticamente)
- ✅ Menu interativo via terminal
- ✅ Tratamento robusto de exceções customizadas

---

## 🏗️ Exceções Customizadas

```
exceptions/
├── LivroDuplicadoException.java
├── LivroNaoEncontradoException.java
└── AcervoVazioException.java
```

---

## 🚀 Como executar

### Pré-requisitos
- Java 17+ instalado

### Passos
```bash
git clone https://github.com/[seu-usuario]/BibliotecaSoft.git
cd BibliotecaSoft
```
Abra no IntelliJ IDEA e execute a classe `Main.java`.

> Os dados do acervo são salvos automaticamente em arquivo `.txt` e carregados na próxima execução.

---

## 📁 Estrutura do Projeto

```
src/
└── com/projectnik/
    ├── Main.java
    ├── Biblioteca.java
    ├── Livro.java
    ├── Emprestimo.java
    └── exceptions/
        ├── LivroDuplicadoException.java
        ├── LivroNaoEncontradoException.java
        └── AcervoVazioException.java
```

---

## 📚 Conceitos Aplicados

- Programação orientada a interface (`List` em vez de `ArrayList`)
- Streams API (`filter`, `findFirst`, `orElseThrow`)
- Method references (`forEach(System.out::println)`)
- Hierarquia correta de `catch` (específico antes do genérico)
- Encapsulamento com métodos privados auxiliares
- Persistência com `BufferedReader` / `BufferedWriter`
- Tratamento de `InputMismatchException` no menu

---

## 🔄 Próximas melhorias

- [ ] Persistência em banco de dados (MySQL + JDBC)
- [ ] Repositório genérico com Generics
- [ ] Interface gráfica (JavaFX)

---

Desenvolvido por **Nikolas Carvalho Eustáquio** — estudante de Ciência da Computação.
