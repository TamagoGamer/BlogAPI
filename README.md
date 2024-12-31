# API de Blog com Ktor e Firebase

Esta é uma aplicação de exemplo que fornece uma API para gerenciar posts de blog, construída com Ktor e Firebase.

## Requisitos

- **IntelliJ IDEA**: Ambiente de desenvolvimento recomendado.
- **JDK 11 ou superior**.
- **Gradle**: Usado como sistema de build.
- **Arquivo de configuração Firebase**: O arquivo será fornecido separadamente e deve ser colocado na pasta `resources` do projeto.

## Configuração do Firebase

1. **Receba o arquivo de configuração**:
  - O arquivo de configuração do Firebase será fornecido separadamente (ex.: `serviceAccountKey.json`).

2. **Coloque o arquivo na pasta `resources`**:
  - Copie o arquivo fornecido e coloque-o no diretório `src/main/resources` do projeto.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Abra o projeto no IntelliJ IDEA.

3. Certifique-se de que as dependências estão instaladas corretamente executando:
   ```bash
   ./gradlew build
   ```

4. Inicie o servidor:
  - Execute a função `main` no arquivo principal.

5. A API estará disponível em `http://127.0.0.1:8080`.

## Endpoints Disponíveis

Abaixo está a lista de endpoints fornecidos pela API:

| Método HTTP | Endpoint         | Descrição                                  |
|-------------|------------------|------------------------------------------|
| GET         | `/blogs`         | Retorna a lista de posts do blog.        |
| POST        | `/data`          | Adiciona dados genéricos ao Firebase.    |
| POST        | `/blogs`         | Adiciona um novo post ao blog.           |
| PUT         | `/blogs/{id}`    | Atualiza um post existente pelo ID.      |
| DELETE      | `/blogs/{id}`    | Remove um post existente pelo ID.        |

## Estrutura do Projeto

- `main.kt`: Configurações principais e inicialização do servidor.
- `BlogPost.kt`: Definição da classe `BlogPost`.
- `FirebaseModule.kt`: Configurações e inicialização do Firebase.
- `Routing.kt`: Configuração das rotas da API.
- `build.gradle.kts`: Configuração do Gradle.

## Exemplo de Requisição

### GET `/blogs`
**Resposta de exemplo:**
```json
[
  {
    "id": "1",
    "title": "Primeiro Post",
    "content": "Conteúdo do primeiro post",
    "authorId": "123",
    "likes": 10,
    "comments": []
  }
]
```

### POST `/blogs`
**Requisição de exemplo:**
```json
{
  "title": "Novo Post",
  "content": "Conteúdo do novo post",
  "authorId": "123",
  "likes": 0,
  "comments": []
}
```
**Resposta:**
```
Blog post added
```

## Observações

- Certifique-se de configurar corretamente o Firebase antes de executar a aplicação.
- O arquivo de configuração do Firebase deve ser colocado manualmente na pasta `resources` do projeto.

---
Se você tiver dúvidas ou problemas, abra uma issue no repositório.