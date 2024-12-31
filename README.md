# API de Blog com Ktor e Firebase

## Índice

1. [Descrição](#descrição)
2. [Requisitos](#requisitos)
3. [Configuração do Firebase](#configuração-do-firebase)
4. [Como Executar](#como-executar)
5. [Endpoints Disponíveis](#endpoints-disponíveis)
6. [Estrutura do Projeto](#estrutura-do-projeto)
7. [Exemplo de Requisição](#exemplo-de-requisição)
8. [Observações](#observações)
9. [Contribuições](#contribuições)

## Descrição

Uma aplicação de exemplo que fornece uma API para gerenciar posts de blog, utilizando Ktor e Firebase. Ideal para aprender a integrar Ktor com Firebase e implementar operações CRUD.

## Requisitos

- **IntelliJ IDEA**: Ambiente de desenvolvimento recomendado.
- **JDK 11 ou superior**: Certifique-se de ter uma versão compatível instalada.
- **Gradle**: Usado como sistema de build.
- **Arquivo de configuração do Firebase**: Fornecido separadamente, deve ser colocado no diretório `resources`.

## Configuração do Firebase

1. **Obtenha o arquivo de configuração**:
   - Solicite o arquivo de chave do serviço (ex.: `serviceAccountKey.json`).

2. **Coloque o arquivo no projeto**:
   - Copie o arquivo para o diretório `src/main/resources`.

## Como Executar

1. **Clone o repositório**:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_DIRETORIO>
   ```

2. **Abra o projeto no IntelliJ IDEA**.

3. **Instale as dependências e compile o projeto**:

   ```bash
   ./gradlew build
   ```

4. **Inicie o servidor**:
   - Execute a função `main` no arquivo principal (ex.: `application.kt`).

5. **Teste a API**:
   - Utilize uma ferramenta como o [Postman](https://www.postman.com/) para testar os endpoints.

6. **A API estará disponível em** `http://127.0.0.1:8080`.

## Endpoints Disponíveis

| Método HTTP | Endpoint        | Descrição                           |
|-------------|-----------------|-------------------------------------|
| GET         | `/blogs`        | Retorna a lista de posts do blog.   |
| POST        | `/blogs`        | Adiciona um novo post ao blog.      |
| PUT         | `/blogs/{id}`   | Atualiza um post existente pelo ID. |
| DELETE      | `/blogs/{id}`   | Remove um post existente pelo ID.   |

## Estrutura do Projeto

- `application.kt`: Configuração do servidor e inicialização do Ktor.
- `FirebaseModule.kt`: Integração com o Firebase (Firestore e Firebase Auth).
- `Routing.kt`: Configuração das rotas da API.
- `BlogPost.kt`: Definição da classe de dados para posts do blog.
- `build.gradle.kts`: Configuração do Gradle, incluindo dependências.

## Exemplo de Requisição

### GET `/blogs`
**Resposta de exemplo**:

```json
[
  {
    "title":"Estou Triste",
    "content":"Estou Triste",
    "author":"Estou Triste",
    "likes":"Estou Triste",
    "comments":"Estou Triste"
  }
]
```

### POST `/blogs`
**Requisição de exemplo**:

```json
{
    "title":"Estou Triste",
    "content":"Estou Triste",
    "author":"Estou Triste",
    "likes":"5"
}
```

**Resposta de exemplo**:

```json
{
  "message": "Blog post adicionado com sucesso!",
  "id": "2"
}
```

## Observações

- **Configuração do Firebase**: Certifique-se de que o arquivo `serviceAccountKey.json` está corretamente configurado e na pasta `resources`.
- **Porta da aplicação**: A API padrão utiliza a porta 8080. Altere conforme necessário no arquivo de configuração do Ktor.

## Contribuições

Sinta-se à vontade para contribuir com melhorias ou relatar problemas por meio de issues no repositório.
