# Respostas

# 1) 
Acredito que em toda Entidade/Model, para segurança de comportamento, é interessante implementar o `equals` e `hashCode`, a fim de validar que os campos que serão utilizados para considerar as instâncias iguais.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    
    if (o == null || getClass() != o.getClass()) return false;
    Entidade entidade = (Entidade) o;
    return id.equals(entidade.id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
```

# 2)

Utilizaria o padrão Adapter, sei que na nossa área de trabalho muito já foi feito antes, nem sempre precisamos reinventar a roda. Criando classes que herdem o comportamento dessas bibliotecas, podemos diminuir o esforço de criar uma feature na mão e o esforço de refatorar todos os lugares onde os métodos são utilizados, sendo necessário apenas refatorar a classe que está herdando, seja para uma nova biblioteca ou uma nova versão.

# 3) 
Já trabalho há cerca de 3 anos com Angular e sou familiarizado com o framework, tendo atuado em cerca de 8 projetos durante esse tempo. O mais notório foi a nova interface do site da Justiça Federal no Rio Grande do Norte. Nos pequenos trechos de código responsáveis pelas imagens do carrossel do site, é possível ver a integração do serviço no `component.ts`, no caso o backend realizando o método GET. O data binding é feito no arquivo `.html`.

```html
<div *ngIf="noticia.croppedImageSafeUrl; else originalImage">
    <img alt="Capa da notícia cortada" [src]="noticia.croppedImageSafeUrl"/>
</div>
```
```html
this.http.get(url, { headers }).subscribe(
    (response: any) => {
        this.noticias = response.content.slice(0, 6).map((item: { id: any; titulo: any; chamada: any; data: any; arquivo: any; coordenadas: any; }) => ({
            id: item.id,
            titulo: item.titulo,
            chamada: item.chamada,
            data: item.data,
            imagem: item.arquivo,
            coordenadas: item.coordenadas
        }));
    },
);
```

# 4)
Utilizaria HQL ou Criteria, primeiro para garantir a segurança inicial de não lidar com uma query de forma nativa. A validação e tratamento de informações inseridas poderiam ser realizados na camada de serviço, antes mesmo de chegar no repository, eliminando também esse perigo. Endpoints críticos com segurança de OAuth.

# 5) 
Utilizaria mecanismos assíncronos para garantir que não existiriam gargalos de interrupção ou atraso nas operações. Uma fila do RabbitMQ, por exemplo, poderia tratar de ferramentas como `@Cache`. Também trabalharia com queries com limites, aplicando o `limit` no fim de uma consulta. Além disso, aumentaria os meus logs, provavelmente utilizando a abordagem ELK, para ter um controle melhor de tempo para cada operação, calcular médias, pisos e tetos dos melhores e piores casos.


# 6) 

**a)**

```sql
-- a
SELECT * FROM salesperson sp
JOIN orders od ON od.salesperson_id = sp.id
WHERE od.customer_id <> 4;
```
```sql
-- b
UPDATE Salesperson
SET name = CONCAT(name, '*')
WHERE ID IN (SELECT salesperson_id
             FROM Orders
             GROUP BY salesperson_id
             HAVING COUNT(order_date) >= 2);
```
```sql
-- c
DELETE FROM salesperson
WHERE id IN (SELECT sp.id FROM salesperson sp
              JOIN orders od ON od.salesperson_id = sp.id
              WHERE od.customer_id IN (7, 9));
```
```sql
-- d
SELECT s.name AS salesperson_name, COALESCE(SUM(o.amount), 0) AS total_sales
FROM Salesperson s
LEFT JOIN Orders o ON s.ID = o.salesperson_id
GROUP BY s.name;
```

# 7) Requisitos para CRUD de Plantas

**a) Tarefa: CRUD de Plantas no Sistema XYZ**

**Descrição:** Como usuário do sistema XYZ, quero poder cadastrar, editar (somente a descrição), buscar e, se eu for admin, deletar plantas, para preparar os dados que serão usados na próxima fase do projeto.

**Critérios de aceite:**
- Usuários autenticados podem cadastrar, editar (descrição) e buscar plantas.
- Apenas admins podem deletar.
- Código da planta é obrigatório, numérico, único e não pode ser alterado após o cadastro.
- Descrição é opcional, com limite de 10 caracteres.
- Não pode haver códigos duplicados.
- Buscar deve permitir filtros e paginação.

**Validações:**
- Checar se usuário é admin antes do delete.
- Código com validação de unicidade.
- Todas as operações exigem autenticação.

**Testes esperados:**
- Cadastro válido.
- Cadastro duplicado.
- Cadastro sem descrição.
- Update da descrição.
- Tentativa de update no código.
- Delete por admin.
- Delete por não-admin.
- Busca com filtro.
- Busca com planta inexistente.

**b)**

As regras de negócio já foram definidas, sendo um CRUD de plantas com um campo obrigatório e um opcional, sendo que apenas admins podem realizar deletes, dado que as permissões estão bem definidas. Todos os usuários têm poder de buscar, cadastrar e atualizar. O código da planta é único, portanto o update do campo ID estaria desativado, permitindo apenas a atualização da descrição, para maior segurança do sistema.

**c)**

A chave primária será o código, com a garantia de unicidade, utilizando tanto um gatilho no banco quanto a anotação JPA para garantir que não haverá duplicidade de dados. Caso necessário, pode-se usar um `Map` para evitar duplicidade.

**d)**

Faria um teste utilizando H2, para que todos os testes fossem em memória, sem persistência de dados. Aplicaria o Mockito para simular perfis e resultados esperados, testando casos como cadastro normal, update, delete, listagem paginada com filtros aplicados, busca com filtros que não retornem resultados, cadastro com campos inválidos, cadastro duplicado, delete de um admin, delete de um usuário sem permissão, etc.

# 8) Tipos de Testes

**a)**

Faria um teste unitário validando as regras de negócio, seguido de um teste de integração para validar a persistência de dados, por fim um teste de ponta a ponta, considerando as validações na tela de cadastro.

**b)**

- Cadastro de um email já cadastrado.
- Cadastro sem os campos obrigatórios (email e nome).
- Delete de um usuário por um admin, por um não-admin.
- Delete de um cadastro de um admin por um admin.
- Update de campos obrigatórios.
- Testes de comportamentos esperados e validação de regras de negócio.

**c)** 

Dado que já existe um usuário com email `email@exemplo.com`,  
Quando um novo usuário tenta se cadastrar com o mesmo email (`email@exemplo.com`),  
Então o sistema deve retornar erro "Email já cadastrado" e o cadastro não deve ser salvo no banco.
