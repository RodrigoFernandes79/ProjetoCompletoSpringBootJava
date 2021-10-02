#PROJETO WEBSERVICES COM SPRINGBOOT E JPA/HIBERNATE# 

 

 

 

Abaixo , nós temos o modelo de domínio, com varios objetos relacionais: 

 

E abaixo , nos temos um modelo de instanciação dos objetos os quais iremos criar nossas bases de dados relacional automaticamente, assim , iremos aprender a criar automaticamente essas bases de dados relacional e povoá -las  conforme modelo a seguir: 

 

 

CRIANDO NOSSO PROJETO: 

https://start.spring.io/ 

 

E vamos configurar nosso projeto: 

 

roject 

  

Inicialmente, iremos trabalhar com o spring Web , e seguiremos conforme projeto acima. 

Vamos abrir o Eclipse e vamos importar o projeto já extraído e descompactado. 

 

O primeiro passo é criar nossa entidade User, a qual será um espelhamento da entidade no banco de dados: 

Criaremos atributos , getters and setters e construtores, sendo que obrigatoriamente um deles será sempre vazio, pois para o spring boot exige sempre um construtor vazio por entidade. 

A entidade user: 

 

Ciraremos os atributos: 

 private Long id; 

private  String name; 

private String email; 

private String phone; 

private String password; 

 

Criaremos construtores: 

public User(Long id, String name, String email, String phone, String password) { 

this.id = id; 

this.name = name; 

this.email = email; 

this.phone = phone; 

this.password = password; 

} 

//Construtor vazio obrigatório: 

public User() { 

 

} 

 

Criar Getters and Setters: 

public Long getId() { 

return id; 

} 

public void setId(Long id) { 

this.id = id; 

} 

public String getName() { 

return name; 

} 

public void setName(String name) { 

this.name = name; 

} 

public String getEmail() { 

return email; 

} 

public void setEmail(String email) { 

this.email = email; 

} 

public String getPhone() { 

return phone; 

} 

public void setPhone(String phone) { 

this.phone = phone; 

} 

public String getPassword() { 

return password; 

} 

public void setPassword(String password) { 

this.password = password; 

}  

 

Agora iremos implementar uma interface chamada Serializable que transforma objetos da classe em cadeias de bytes , necessário para que esses objetos para rodar na internet ou serem gravados em arquivos: 

 

public class User implements Serializable{ 

 

private static final long serialVersionUID = 1L; 

 

Feito isso , está pronta nossa classe User. 

 

Agora precisamos implementar nossa classe Controller, lembrando que temos que seguir o esquema abaixo: 

 

Onde nossa aplicação dependerá de uma classe controller(resource) , que por sua vez depende da classe Servico que depende da interface repository. E todas elas conversam com as suas respectivas entidades. 

 

Criaremos um pacote controllers e dentro do pacote criaremos uma interface chamada UserController 

 

Dentro da classe criaremos duas anotaçoes : 

@RestController 

@RequestMapping("/users") 

public class UserController { 

  

} 

A @RestController informa ao Spring Boot que esta classe é uma classe Controller; 

A @RequestMapping informa ao Spring Boot que  o caminho (uri) é “users” no caso a url ficaria >>> localhost:8080/users  

Request mapping -> mapeia a requisição para que aponte o caminho do localhost 

 

E vamos criar uma aplicação Rest que vai mostrar o “/user”: 

@GetMapping 

public ResponseEntity<User> findAll(){ 

User u = new User(1l, "Maria", "mariabrown@gmail.com", "3334-5520","3232" ); 

return ResponseEntity.ok().body(u); 

} 

 

Criamos uma anotação @GetMapping , onde iremos mostrar ao spring Boot que essa aplicação é do tipo GET. E usamos o ResponseEntity que receberá uma lista de usuários(User) através do método findAll(). 

Instanciaremos o objeto u  e através do construtor criaremos um usuário “Maria”. E esse método nos retorna a ResponseEntity como ok e no corpo da aplicação(body) nos retorna o objeto u com o usuário criado. 

 

Ao rodar a aplicação, e digitarmos na web 

 http://localhost:8080/users   , veremos que nossa aplicação está funcionando. Ele retornará o objeto criado no formato JSON. 

 

Agora iremos configurar o banco de dados de testes , que é o H2 . Esse banco de dados é muito utilizado em Java. É um banco de dados em memória e não quem que instalar nada no computador. 

 

Primeiro , vamos incluir as dependencias JPA no nosso projeto para que possa fazer o mapeamento das entidades orientada a objeto criadas no Java  no Banco de dados. 

No Pom.xml, vamos inserir essas dependencias: 

 

<dependency> 

<groupId>org.springframework.boot</groupId> 

<artifactId>spring-boot-starter-test</artifactId> 

<scope>test</scope> 

</dependency> 

<dependency> 

<groupId>org.springframework.boot</groupId> 

<artifactId>spring-boot-starter-data-jpa</artifactId> 

</dependency> 

<dependency> 

<groupId>com.h2database</groupId> 

<artifactId>h2</artifactId> 

<scope>runtime</scope> 

</dependency> 

O pom.xml é um arquivo gerenciado pelo Maven, e dentro do pom.xml, estão as dependências do projeto e as versões das dependências. 

 

O proximo passo é configurar o arquivo application.properties. E vamos inserir os seguintes codigos: 

 

spring.profiles.active=test 

spring.jpa.open-in-view=true   

 

Feito isso , vamos configurar o nosso banco de dados H2, que é o nosso banco de dados teste. Então para isso criaremos outro application.properties para que possamos inserir as configurações dentro dele. Para criar , clicaremos com o botao direito em src/main/resources no Eclipse e na opção “Files” . Esse novo .properties se chamará: 

application-test.properties 

 

E dentro dele ficarão as seguintes configurações: 

#configurações do Banco de Dados teste H2: 

spring.datasource.url=jdbc:h2:mem:testdb 

spring.datasource.username=sa 

spring.datasource.password= 

#h2 criado em console e o caminho no navegador: /h2-console 

spring.h2.console.enabled=true 

spring.h2.console.path=/h2-console 

#comandos Jpa para mostrar no log de aplicação o sql 

spring.jpa.show-sql=true 

spring.jpa.properties.hibernate.format_sql=true 

 

Feito isso agora iremos mapear nossa entidade User para relacionar ela ao H2 

Na classe User: 

 

@Entity(name = "user") 

public class User implements Serializable{ 

 

private static final long serialVersionUID = 1L; 

@Id 

@GeneratedValue(strategy = GenerationType.IDENTITY) 

private Long id; 

 

@Entity é uma anotação javax.persistence para mapear a nossa entidade com o banco de dado, o nome dessa tabela no banco de dados será “user”; 

@id informa que o atributo Long id é uma chave primaria; 

@GeneratedValue(strategy= GenerationType.IDENTITY) -> cria auto incremento , ou seja , o proprio JPA vai gerar ID automaticamente porém em alguns bancos de dados não funciona o IDENTITY 

 

Feito isso vamos rodar a aplicação e verificar se não dá nenhum erro. 

 

E vamos acessar o BD h2 no  

http://localhost:8080/h2-console 

 

E vai aparecer a tela de ferramentas para acesso ao banco de dados H2 

E vamos preencher a url de conexão  

O username e password de acordo com as configurações no application-test.properties: 

 

 

 

 

Clica em connect: 

E vai aparecer nossa Entidade 

 

 

Agora iremos criar nossa UserRepository que ira vai ser o repositório responsável por fazer operações com a entidade user. 

 

Vamos criar um pacote  chamado repositories e dentro dele iremos criar uma interface  UserRepository: 

@Repository 

public interface UserRepository extends JpaRepository<User, Long>{ 

} 

 

@Repository para informar ao Spring Boot que é uma interface do repository; 

E ela deve estender outra interface jpa que é a JpaRepository, que pede a entidade a se relacionar e o tipo de id: <User, Long> 

 

 

Agora podemos criar o primeiro Rest Api de User , Vamos para a classe UserController e criar duas api, uma que nos mostre todas a lista de usuario e a outra que insira um usuario novo  

 

@RestController 

@RequestMapping("/users") // localhost:8080/users 

public class UserController { 

 

@Autowired 

private UserRepository userRepository; 

 

 

@PostMapping 

public ResponseEntity<User> createUser(@RequestBody User user) { 

return ResponseEntity.ok(userRepository.save(user)); 

 

} 

@GetMapping 

public ResponseEntity<List<User>>findAll(){ 

  

return ResponseEntity.ok(userRepository.findAll());  

} 

} 

 

Criamos um atributo  

@Autowired 

private UserRepository userRepository; 

Para receber a interface UserRepository , onde contém metodos JPA inseridos para aplicações no banco de dados.é preciso criar o @Autowired que transfere todos os metodos e atributos da classe UserRepository na classe UserController. 

 

E criaremos um metodo que ira lista todos os usuarios inseridos na entidade: 

 @GetMapping 

public ResponseEntity<List<User>>findAll(){ 

  

return ResponseEntity.ok(userRepository.findAll());  

} 

Com a anotação @GetMapping para que o SpringBoot saiba que é uma ApiRest do tipo GET, e nosso metodo recebe uma lista de usuarios chamada findAll() que nos retorna o metodo JPA que está na classe userRepository chamado findAll() . 

 

A outra aplicação api Rest é o POST , onde irá inserir usuários atraves do seguinte método: 

@PostMapping 

public ResponseEntity<User> createUser(@RequestBody User user) { 

return ResponseEntity.ok(userRepository.save(user)); 

 

} 

@PostMapping informa ao Spring Boot que é uma Api Rest do tipo POST. E temos um metodo createUser que recebe objetos da classe usuario(User user).  

@RequestBody informa ao Spring Boot que tem que inserir um corpo e o formato é em JSON. 

Este metodo nos retorna o objeto da classe User salvo pela UserRepository que faz a transação com o BD. 

 

 

Em algumas aplicações Rest, temos que criar uma classe Service onde ficarão as regras de negócio dessas aplicações, deixando somente para a classe Controller os métodos controladores. Por exemplo no metodo que criaremos a seguir, o FindById, iremos criar o metodo base na classe Controller e a nossa execução desse metodo ficaria na classe Service. Isso separa cada classe e ao mudar ou acrescentar algo nas APi , iriamos mudar somente na CLasse Service. 

É importante observar que a classe Controller é injetada pela dependencia da classe Service, que por sua vez é injetada pela dependencia da interface Repository. Para fazer as ligaçoes entre elas é preciso inserir o @Autowired. 

 

Na classe UserController , iremos criar o seguinte metodo: 

@GetMapping("/{id}")  

public User findById(@PathVariable long id){ 

return userService.findById(id); 

Onde @GetMapping informa ao spring Boot 	que é um api Rest do tipo GET sendo que no uri nós inserimos o “id” para que nos retorne o usuario. 

No metodo criaremos um findById que recebe como parametro o tipo long “id” com a anotação @PathVariable a qual requisita o id que está escrito na uri.  esse metodo nos retorna a Classe userService onde criaremos o corpo do metodo: 

 

Na Classe userService: 

public User findById(long id) { 

Optional<User> obj =userRepository.findById(id); 

return obj.get(); 

} 

O corpo do metodo é um Optional<User>  que é instanciado pelo objeto obj, e este recebe o metodo findbyId(id) da interface userRepository que por sua vez retorna o objeto “obj” .get() , onde o .get() recebe todos os atributos do objeto User  

 

Feito isso , agora iremos criar uma outra entidade, chamada Order, que será o pedido do usuario. 

 

Note que User e order teem uma relação: 

 

 

Temos essa relação de um usuário para vários pedidos e vários pedidos feito por um usuário.  

Por enquanto não iremos trabalhar o Status do pedido(OrderStatus) e nem o total do pedido.  

 

Vamos criar nossa entidade Order (pedido): 

 

Dentro do pacote model criaremos uma classe Order, que ficará assim: 

@Entity(name ="orders") 

public class Order implements Serializable{ 

 

@Id  

@GeneratedValue(strategy = GenerationType.IDENTITY) 

private Long id; 

 

private Instant date; 

 

private User client; 

  

public Order(Long id, Instant date, User client) { 

super(); 

this.id = id; 

this.date = date; 

this.client = client; 

} 

  

public Order() { 

super(); 

} 

  

public Long getId() { 

return id; 

} 

  

public void setId(Long id) { 

this.id = id; 

} 

  

public Instant getDate() { 

return date; 

} 

  

public void setDate(Instant date) { 

this.date = date; 

} 

 

public User getClient() { 

return client; 

} 

public void setClient(User client) { 

this.client = client; 

}  

} 

 

Criaremos uma anotação @Entity a qual se chamará “order” ,com atributos id, date e client, sendo que o id recebe as anotações @Id, que informa que é uma chave primária, e também recebe @GeneratedValue que tem como estratégia o auto incremento, ou seja, o próprio BD irá gerar Ids automaticamente. 

O atributo date recebe um tipo Instant, que é usada para formatação de datas mais precisas que o tipo Date. 

E o client recebe a classe User a qual faz relação com a entidade users através de chave estrangeiras. 

Criaremos uma implementação Serializable que transforma objetos da classe em cadeias de bytes, necessário para que esses objetos para rodar na internet ou serem gravados em arquivos 

Depois vamos criar os Getters and Setters dos atributos; 

E também criaremos os construtores, sempre criando um construtor padrão vazio sem argumentos que é obrigatório e outro construtor com argumentos. 

 

 

Feito isso , iremos implementar o relacionamento das chaves estrangeiras entre Order e User lá no banco de dados. 

Na classe User, iremos criar um novo atributo de relacionamento com a classe Order, que será um tipo lista: 

private List<Order> orders; 

 

Esse atributo deve ser uma lista , pois a relação entre User e Order é de um para muitos, ou seja, um usuário tem muitos pedidos, e se ele tem muitos pedidos, será entregue uma lista dos pedidos que o usuário fez através desse atributo, que é uma lista. O inverso ocorre na classe Order, quando criamos um atributo client que recebe a classe User, pois muitos pedido são feitos apenas por um usuário, então não será uma lista, e sim um tipo classe. 

Ainda na classe User, iremos criar um Getter pra List<Order> orders. Não é necessário criar um setter, pois apenas iremos mostrar a lista de pedidos na classe. 

E por fim acrescentaremos o atributo order no construtor com argumentos: 

public User(Long id, String name, String email, String phone, String password, List<Order> orders) { 

super(); 

this.id = id; 

this.name = name; 

this.email = email; 

this.phone = phone; 

this.password = password; 

this.orders = orders; 

} 

Feito isso, vamos mapear essa relação users –orders com as anotações @OneToMany e @ManyToOne para o JPA transformar isso lá no BD com chaves estrangeiras entre as duas tabelas. 

Então temos uma associação de muitos para um entre pedido e usuário. O pedido é o lado do muitos e o usuário é o lado do um. 

Então na classe Order: 

Em cima do atributo client: 

@ManyToOne 

@JoinColumn(name="client_id") 

private User client;  

 

@ManyToOne -> muitos pedidos para um cliente; 

@JoinColumn -> anotação para criar uma chave estrangeira a qual referencia a chave primaria da tabela users, sendo chamada de “client_id”. 

 

E na classe User, iremos criar a relação com a tabela da entidade “orders”, pois caso , ao acessar a tabela "users” , se quisermos também acessar a tabela"orders” (é opcional, só iremos colocar o mapeamento relacional, se caso quisermos mostrar a lista de pedidos através do usuario, no nosso caso iremos colocar): 

No atributo da lista Order , ficaria assim: 

@JsonIgnore 

@OneToMany(mappedBy ="client") 

private List<Order> orders; 

 

@OneToMany-> mapeia a chave estrangeira onde um usuario tem muitos pedidos e , como a classe User é a classe dominante(ou seja é através dela que podemos acessar a classe Order), entao colocamos o mappedBy (mapeada por) na classe dominante. E sempre referenciando o atributo da classe User que no caso é client(esse atributo está definido na classe Order. 

@JsonIgnore faz com que o relacionamento deixe de ser bilateral e como nós colocamos essa anotação na entidade “users”, em cima do atributo da lista<Order>  , ao acessar  e mostrar os objetos, ela não irá mostrar os pedidos(order) , somente ao acessar a entidade “orders” nós teremos acesso aos objetos da entidade “user”, tornando assim unilateral . 

 

Vamos ao banco de dados h2 para saber se foi persistida a nossa tabela: 

 

Banco de dados criado ,então vamos ao Eclipse e criar nosso OrderRepository e nosso OrderService e OrderController, que ficaria a mesma coisa do User, sendo que substituiriam as classes User por Order: 

No OrderRepository: 

@Repository 

public interface OrderRepository extends JpaRepository<Order, Long> { 

  } 

 

No OrderService: 

@Service 

public class OrderService { 

@Autowired 

private OrderRepository orderRepository; 

  

public Order findById(long id) { 

Optional<Order> obj = orderRepository.findById(id); 

return obj.get(); 

} 

} 

 

E por fim na classe OrderController: 

@RestController 

@RequestMapping("/orders") // localhost:8080/orders 

public class OrderController { 

@Autowired 

private  OrderService orderService; 

 

@Autowired 

private OrderRepository orderRepository; 

 

@PostMapping 

public ResponseEntity<Order> createOrder(@RequestBody Order order) { 

return ResponseEntity.ok(orderRepository.save(order));	 

} 

@GetMapping 

public ResponseEntity<List<Order>>findAll(){ 

  

return ResponseEntity.ok(orderRepository.findAll());  

} 

 

//Encontrar pedido pelo id:  

 

@GetMapping("/{id}") //localhost:8080/Orders/{id} 

public Order findById(@PathVariable long id){ 

return orderService.findById(id);	 

} 

} 

 

Existe um atalho Ctrl +f que serve para mudar os nomes que existem na classe automaticamente, então é só colocar a palavra User  e marcar o casesensitive , que irá mudar automaticamente: 

 

Feito isso, vamos rodar nossa aplicação para que possamos implementar os objetos no insomnia que ficará assim: 

 

Note que ao criar um pedido e no atributo “cliente” eu inserir o id de quem fez o pedido, ele nos retornará  o pedido e o cliente relacionado a ele; isso conferimos ao mostrar os pedidos atraves do metodo get , conforme figura abaixo: 

 

Perceba que ao mostrar todos os pedidos, ele levará consigo todos os usuarios relacionados, como no exemplo acima o mesmo usuario fez dois pedidos.O mesmo não ocorre com o GET de usuários, que somente mostra a lista de usuarios. Isso é graças à anotação @JsonIgnore  colocado no atributo List<Order> na classe User, que deixou a relação unilateral 

 

Feito isso , vamos fazer um Order Status do tipo enum, conforme nosso Domain Model: 

Lembrando que no nosso diagrama temos o Order e dentro dele temos um atributo do tipo OrderStatus: 

 

Sendo que esse OrderStatus é um tipo enumerado com os seguintes valores conforme mostrados no diagrama de classes: 

 

Entao no eclipse, vamos no pacote model, e criar um subpacote dentro dele chamado enums , e dentro desse pacote , iremos criar um classe do tipo “enum” chamada OrderStatus: 

E nossa classe enum ficará assim: 

 

public enum OrderStatus {	 

WAITING_PAYMENT ("Waiting Payment"), 

PAID ("Paid"), 

SHIPPED("Shipped"), 

DELIVERED("Delivered"), 

CANCELED ("Cancelled"); 

  

private final String description; 

  

private OrderStatus(String description) { 

this.description = description; 

} 

 

public String getDescription() { 

return description; 

} 

} 

Criamos os tipos enum (waiting, paid, shipped, delivered, canceled) que serão mostrados através de uma String,  

Criamos um atributo que receberá essas strings, depois criamos um construtor e um Getter. 

 

Agora vamos na classe Order inserir o atributo tipo enum: 

@Enumerated (EnumType.STRING) 

@Column( nullable = false) 

private OrderStatus orderStatus; 

 

Temos as anotações @Enumerated que recebe como parâmetro uma string, pra informar ao JPA que é uma classe tipo enumerated que vão receber tipos STrings. 

@Column é pra informar que será uma coluna da tabela “orders” e que não recebe nulo. 

 

Agora precisamos criar Getters and Setters e os construtores com o novo atributo: 

Criando o construtor: 

 

public Order(Long id, Instant date, User client, OrderStatus orderStatus) { 

super(); 

this.id = id; 

this.date = date; 

this.client = client; 

this.orderStatus = orderStatus; 

} 

Getters and setters: 

 

public OrderStatus getOrderStatus() { 

return orderStatus; 

} 

public void setOrderStatus(OrderStatus orderStatus) { 

this.orderStatus = orderStatus; 

} 

 

Feito isso , agora vamos rodar nossa aplicação e inserir no insomnia: 

 

Ao mostrar o GetOrders: 

 

 

 

E no Banco de dados H2: 

 

 

Vamos agora , criar a entidade Category, que no diagrama de classes é essa aqui: 

 

 

No pacote model, vamos criar uma classe chamada Category: 

@Entity (name=” category”) 

public class Category implements Serializable{ 

  

private static final long serialVersionUID = 1L; 

@Id 

@GeneratedValue (strategy= GenerationType.IDENTITY) 

private Long id; 

 

private String name; 

  

public Category() { 

super(); 

} 

  

public Category(Long id, String name) { 

super(); 

this.id = id; 

this.name = name; 

} 

  

public Long getId() { 

return id; 

} 

  

public void setId(Long id) { 

this.id = id; 

} 

  

public String getName() { 

return name; 

} 

  

public void setName(String name) { 

this.name = name; 

} 

} 

 

Seguimos com as mesmas configurações das entidades anteriores , criando anotação @Entity com nome na tabela “category”, implementamos o Serializable para enviar objetos pela rede 

, inserimos dois atributos : id e name , com @Id e @GeneratedValue para informar a chave primaria da entidade e com autoincremento. 

Criamos o construtor padrão sem argumentos e outro constuto padrão com argumentos, e por último criamos os Getters and Setters. 

 

Feito isso , precisamos fazer as classes CategoryRepository, CategoryService e CategoryController da mesma maneira que fizemos com as outras classes: 

 

CategoryRepository: 

@Repository 

public interface CategoryRepository extends JpaRepository<Category, Long>{ 

} 

 

CategoryService: 

@Service 

public class CategoryService { 

@Autowired 

private   CategoryRepository categoryRepository; 

public Category findById(long id) { 

Optional<Category> obj =categoryRepository.findById(id); 

return obj.get(); 

} 

 

E na CategoryController: 

@RestController 

@RequestMapping("/categoryservice") // localhost:8080/categoryservice 

public class CategoryController { 

@Autowired 

private  CategoryService categoryService; 

 

@Autowired 

private CategoryRepository categoryRepository; 

 

@PostMapping 

public ResponseEntity<Category> createCategory(@RequestBody Category category) { 

return ResponseEntity.ok(categoryRepository.save(category)); 

 

} 

 

@GetMapping 

public ResponseEntity<List<Category>>findAll(){ 

  

return ResponseEntity.ok(categoryRepository.findAll());  

} 

 

//Encontrar usuário pelo id:  

 

@GetMapping("/{id}") //localhost:8080/categorys/{id} 

public Category findById(@PathVariable long id){ 

return categoryService.findById(id);	 

} 

} 

 

Feito isso, vamos rodar nossa aplicação e vamos no insomnia , inserir objetos para a nossa entidade da tabela “category”: 

No metodo Create: 

 

Inserindo o método FindCategory: 

 

E procurando pelo id, através do FindById: 

 

 

Verificando a aplicação persistida no BD H2: 

 

 

 

Agora vamos seguir o Diagrama de classes e criar a entidade Product: 

 

Vamos no projeto, dentro do pacote model, criar uma classe Product com todos os atributos, construtores e getters and setters e Implementar Serializable. 

Feito isso, vamos criar as associações com a entidade Category, que neste caso seria @ManyToMany , porque vários produtos possuem várias categorias e o mesmo ocorre em várias categorias pertencem a vários produtos: 

  

Sendo que neste nosso caso, em vez de passar uma lista List, iremos passar uma coleção que recebe elementos sem duplicatas, e para isso, usamos o Set. No caso, as duas entidades terão esse tipo de coleção, pois em Category, não podemos receber uma coleção de produtos repetidos e nem na classe Product podemos receber uma coleção de Category repetidos, pois cada produto pertence a uma categoria especifica e cada categoria possui um produto diferente 

Então na classe Product: 

@ManyToMany 

@JoinTable(name = "product_category",joinColumns =@JoinColumn(name= "product_id"), 

inverseJoinColumns =@JoinColumn(name="category_id")) 

private Set<Category> categories; 

 

Queremos que , ao mostrar a tabela de produtos , ela também nos mostre suas categorias , então primeiro criamos a anotação @ManyToMany pra informar o tipo relacional com Category e formaremos a tabela relacional chamada “product_category”.juntamos os ids de cada entidade através do @JoinColumn entre o “product_id” e o inverseJoinColumns (que é a chave primaria da outra tabela) chamada “category_id”. 

E na classe Category: 

@JsonIgnore 

@ManyToMany(mappedBy ="categories") 

private Set<Product> products; 

 

@JsonIgnore para não mapear e mostrar a lista de produtos ao acessar a classe Category. 

@ManyToMany para mapear as entidades relacionais e mappedBy=”categories” onde “categories” é o nome do atributo da outra entidade da classe Product . 

MACETE: sempre quando SOMENTE queremos mostrar apenas a entidade ao acessar o GET , temos que informar o @JsonIgnore e na anotação relacional(no nosso caso o @ManyToMany) tem que estar informada o mappedBy e informando o atributo da chave estrangeira. 

Vamos mostrar como fica a classe Category: 

@Entity(name="category") 

public class Category implements Serializable{ 

  

private static final long serialVersionUID = 1L; 

@Id 

@GeneratedValue(strategy= GenerationType.IDENTITY) 

private Long id; 

 

private String name; 

@JsonIgnore 

@ManyToMany(mappedBy ="categories") 

private Set<Product> products; 

 

  

public Category() { 

super(); 

} 

public Category(Long id, String name, Set<Product> products) { 

super(); 

this.id = id; 

this.name = name; 

this.products = products; 

} 

  

public Long getId() { 

return id; 

} 

  

public void setId(Long id) { 

this.id = id; 

} 

  

public String getName() { 

return name; 

} 

  

public void setName(String name) { 

this.name = name; 

} 

  

public Set<Product> getProducts() { 

return products; 

} 

} 

 

Vamos mostrar como fica a classe Product: 

@Entity(name= "product") 

public class Product implements Serializable { 

 

private static final long serialVersionUID = 1L; 

  

@Id 

@GeneratedValue(strategy=GenerationType.IDENTITY) 

private Long id; 

 

private String name; 

 

private String description; 

 

private Double price; 

 

private String imgUrl;  

@ManyToMany 

@JoinTable(name = "product_category",joinColumns =@JoinColumn(name= "product_id"), 

inverseJoinColumns =@JoinColumn(name="category_id")) 

private Set<Category> categories; 

  

public Product() { 

super(); 

} 

public Product(Long id, String name, String description, Double price, String imgUrl, Set<Category> categories) { 

super(); 

this.id = id; 

this.name = name; 

this.description = description; 

this.price = price; 

this.imgUrl = imgUrl; 

this.categories = categories; 

} 

  

public Long getId() { 

return id; 

} 

  

public void setId(Long id) { 

this.id = id; 

} 

  

public String getName() { 

return name; 

} 

  

public void setName(String name) { 

this.name = name; 

} 

  

public String getDescription() { 

return description; 

} 

    

public void setDescription(String description) { 

this.description = description; 

} 

  

public Double getPrice() { 

return price; 

} 

  

public void setPrice(Double price) { 

this.price = price; 

} 

  

public String getImgUrl() { 

return imgUrl; 

} 

  

public void setImgUrl(String imgUrl) { 

this.imgUrl = imgUrl; 

} 

  

public Set<Category> getCategories() { 

return categories; 

} 

} 

 

Vamos criar as mesmas classes ProductRepository, ProductService e ProductController. 

 

Classe ProductRepository: 

@Repository 

public interface ProductRepository extends JpaRepository<Product, Long>{ 

  

} 

 

ProductService: 

@Service 

public class ProductService { 

@Autowired 

private   ProductRepository productRepository; 

  

public Product findById(long id) { 

Optional<Product> obj =productRepository.findById(id); 

return obj.get(); 

} 

  

ProductController: 

@RestController 

@RequestMapping("/product") // localhost:8080/product 

public class ProductController { 

@Autowired 

private  ProductService productService; 

 

@Autowired 

private ProductRepository productRepository; 

 

 

@PostMapping 

public ResponseEntity<Product> createProduct(@RequestBody Product product) { 

return ResponseEntity.ok(productRepository.save(product)); 

 

} 

 

 

 

@GetMapping 

public ResponseEntity<List<Product>>findAll(){ 

  

return ResponseEntity.ok(productRepository.findAll());  

} 

 

//Encontrar produto pelo id:  

 

@GetMapping("/{id}") //localhost:8080/product/{id} 

public Product findById(@PathVariable long id){ 

return productService.findById(id);	 

} 

 

} 

Agora vamos rodar nossa aplicação e vamos inserir os métodos no Insomnia: 

 

Products: 

CreateProducts: 

 

Agora vamos no FindProducts: 

 

 

 

 

Note que ao mostrar os dados de produtos, automaticamente ele nos mostra o id e o nome da categoria de cada um deles; 

 

Em FindById: 

 

 

E na tabela do BD h2: 

Criou a tabela product: 

 

E a tabela relacional PRODUCT_CATEGORY: 

 

 

Entidade OrderItem. Associação muitos-para-muitos com dados extras 

Agora iremos fazer o nosso item de pedido(OrderItem). Essa classe é um item especial: 

É uma entidade @ManyToMany com atributos extras. 

 

Vamos comparar a associação anterior entre Category e Product, podemos perceber que não temos nenhum dado entre essas associações. Simplesmente temos uma categoria que possui uma coleção de produtos e um produto que tem uma coleção de categorias. 

Agora, a relação entre produtos e serviços, possui dados da relação no meio: 

 

Note que o produto(Product) no pedido(Order) ele tem um dado que é a quantidade. Então essa quantidade é um dado da associação , e não exclusivamente do produto ou do pedido. 

Então o nosso item de pedido(OrderItem) possui a quantidade de cada produto e o preço do produto. 

 

Esse preço(price) tem que estar aqui no item de pedidos pra fins histórico, se amanhã esse preço do produto na classe Product mudar, eu tenho que ter o preço do idem de pedidos e o preço na classe de pedido(Order) de quando custou na época do pedido. 

Só que tem um porém. No paradigma orientado a objetos eu não tenho o conceito de chave primária composta o atributo identificador do meu objeto ele é um só. 

Então na verdade eu vou ter que criar uma classe auxiliar para representar o produto e pedido isso porque o produto é pedido e que vai identificar o objeto item pedido  

Como nossa classe OrderItem não possui uma chave primária, quem vai identificar o OrderItem é o par produto e pedido (Product e Order). 

Então iremos criar uma classe OrderItem, essa classe vai ter a quantidade , o preço e uma associação com produto e pedido: 

 

Primeiro vamos criar uma classe auxiliar chamada OrderItemPK -> essa classe auxiliar vai ser a chave primaria que vai ser a referencia para as duas classes pedido e produto; 

Criando a classe auxiliar OrderitemPK: 

No pacote model, iremos criar um SUB pacote chamado pk. Sempre que formos criar uma classe auxiliar que vai ser uma chave composta, chamamos o nome do pacote de pk. Dentro do subpacote ,criaremos a a classe auxiliar OrderItemPK. 

 

Então essa subclasse vai ter uma referência para o produto e uma referência para o pedido: 

public class OrderItemPK { 

private Order order; 

private Product product; 

} 

Essa subclasse em especial, não terá os construtores, apenas getters and setters e Serializable: 

public class OrderItemPK implements Serializable { 

 

private static final long serialVersionUID = 1L; 

 

private Order order; 

private Product product; 

 

 

public Order getOrder() { 

return order; 

} 

public void setOrder(Order order) { 

this.order = order; 

} 

public Product getProduct() { 

return product; 

} 

public void setProduct(Product product) { 

this.product = product; 

} 

  

} 

Agora, iremos fazer o mapeamento do JPA, que no caso de chave primaria composta, nós usamos o annotation @Embeddable para informar ao jpa que é uma chave primaria composta. 

Então o mapeamento relacional das duas entidades Product e Order possuem a annotation @ManyToOne (muitos itens de pedidos de um produto e de um pedido). 

E com as anotações @JoinColumns passando os respectivos order_id e product_id: 

@ManyToOne 

@JoinColumn(name="order_id") 

private Order order; 

@ManyToOne 

@JoinColumn(name="product_id") 

private Product product; 

 

Então terminamos nossa classe auxiliar OrdemItemPK que ficará assim: 

@Embeddable 

public class OrderItemPK implements Serializable { 

 

private static final long serialVersionUID = 1L; 

 

@ManyToOne 

@JoinColumn(name="order_id") 

private Order order; 

@ManyToOne 

@JoinColumn(name="product_id") 

private Product product; 

 

public Order getOrder() { 

return order; 

} 

public void setOrder(Order order) { 

this.order = order; 

} 

public Product getProduct() { 

return product; 

} 

public void setProduct(Product product) { 

this.product = product; 

} 

  

} 

Agora vamos criar a classe OrderItem, dentro do pacote model. 

E vamos criar o primeiro atributo que é o nosso id que referencia a chave primaria composta OrderItemPK: 

public class OrderItem { 

private OrderItemPK id; 

Agora iremos criar os outros atributos quantidade e preço, em seguida os construtores , o vazio que é o padrão e o com argumentos-> sendo que no construtor com argumentos não iremos colocar o id como argumento, pois sabemos que esse Id não será mostrado na tabela, e sim para ser referenciado pelas outras entidades produto e pedido. Então para isso, dentro do construtor com parâmetros, vamos inserir os objetos order e product que serão atribuídos ao id através da instanciação do método set. Fazendo isso, estaremos instanciando nosso Order Product através do id. 

public OrderItem(Order order,Product product, Integer quantity, Double price) { 

super(); 

id.setOrder(order); 

id.setProduct(product); 

this.quantity = quantity; 

this.price = price; 

} 

 

Após personalizar o construtor com parametros, criaremos getters and setters e a implementação do Serializable.nao faremos o getter and setter do id , e sim do Order e Product de forma manual: 

public class OrderItem implements Serializable { 

private static final long serialVersionUID = 1L; 

private OrderItemPK id; 

private Integer quantity; 

private Double price; 

public OrderItem() { 

super(); 

} 

public OrderItem(Order order,Product product, Integer quantity, Double price) { 

super(); 

id.setOrder(order); 

id.setProduct(product); 

this.quantity = quantity; 

this.price = price; 

public Order getOrder() { 

return  id.getOrder(); 

 

} 

 

public void setOrder(Order order) { 

 id.setOrder(order); 

 

} 

public Product getProduct() { 

 return id.getProduct(); 

 

} 

public void setProduct(Product product) { 

id.setProduct(product); 

} 

 

public Integer getQuantity() { 

return quantity; 

} 

  

public void setQuantity(Integer quantity) { 

this.quantity = quantity; 

} 

  

public Double getPrice() { 

return price; 

} 

  

public void setPrice(Double price) { 

this.price = price; 

} 

} 

Feita a classe OrderItem , e a tabela “order_item” criada, agora vamos mapear a entidade com o JPA no Banco de Dados: 

 

Para isso iremos criar a associação @OneToMany na classe Order, porque na classe Order, nós temos uma associação com vários produtos(Product). Dentro da classe pedido(Order) , queremos ter uma operação getItem() que nos retorne os dados da OrderItem(items de pedido) associados ao pedido instanciado pela classe Order. 

Então vamos na classe Order e colocar um atributo de uma coleção não repetitiva chamada Set: 

@OneToMany(mappedBy="id.order") 

private Set<OrderItem> items = new HashSet<>(); 

Criei um atributo de uma coleçao Set chamado items, que tem associaçao @OneToMany mapeado pelo atributo id na classe OrderItem. Onde esse atributo id é uma classe OrderItemsPK e no OrderItemsPK é que temos o order. 

 

Depois criaremos os getters (APENAS o Getter) do atributo items. 

Agora iremos criar apenas as classes OrderItemRepository, OrderItemController , com os mesmos metodos menos o metodo get By Id , pois na classe OrderItem não retorna um id e sim os ids de Order e de Product .  

E temos que instanciar a classe OrderItemPK no atributo id na Classe OrderItem: 

private OrderItemPK id =new OrderItemPK(); 

 

Para que não receba nenhum objeto nulo na hora de inserir dados da tabela. 

e lembrando que , para que não dê o loop infinito no insomnia ao mostrar os dados , Você pode anotar o relacionamento das suas entidades com @JsonManagedReference e @JsonBackReference: 

@JsonManagedReference é colocado na parte da referência que quer avançar na serialização. Ou seja, a parte que é serializada normalmente. 

@JsonBackReference é colocado na parte da referência que não quer na serialização. Ou seja, a parte que será omitida. Assim , o relacionamento entre Order e OrderItem será unidirecional. 

No nosso caso a que queremos que seja serializada é na classe ORDER então colocamos a @JsonManagedReference: 

@JsonManagedReference 

@OneToMany(mappedBy="id.order") 

private Set<OrderItem> items = new HashSet<>(); 

 

E a classe a qual não queremos que seja serializada é a OrderItem e colocaremos a anotação @JsonBackReference no metodo getOrder, pois é lá que é instanciado o atributo order vindo da sublcasse OrderItemPK: 

@JsonBackReference 

public Order getOrder() { 

return  id.getOrder(); 

 

Feito isso, agora vamos no Insomnia e criar um método createOrderItem que receba quantidade, preços e ids de order e products: 

 

Criado os objetos, agora vamos em Order e dar um FindOrder: 

 

Note que persistiram todos os dados relacionais com produto e pedido, preço , categoria etc... 

 

Agora vamos na nossa tabela H2: 

Tabela ORDER_ITEM: 

 

Agora, temos que mapear também a classe Product para a associação com a classe Order, e vejamos que para que possamos referenciá –la , primeiro vamos criar um atributo na classe Product que recebe uma coleçao sem repetiçoes do tipo Set que busca OrderItem(ela tem que varrer a lista da classe OrderItem pra pegar os pedidos lá da classe Order) e criar a annotation @OneToMany mapeada pelo id de produtos.na classe Product: 

@OneToMany(mappedBy="id.product") 

private Set<OrderItem> items=new HashSet<>(); 

 

E para que em Product, possamos mostrar a lista de pedidos(Order) , precisamos criar um método que nos retorne a lista de pedidos(Order) para isso temos que percorrer a lista de OrderItem(itens de pedidos) então o método ficará assim: 

@JsonIgnore 

public Set<Order> getOrder() { 

Set<Order> set = new HashSet<>(); 

for(OrderItem x :items) { 

set.add(x.getOrder()); 

} 

return set; 

} 

Entao entendendo o método: eu criei um metodo getOrder() que nos retorna uma lista de objetos da classe Order , e para encontrar esses objetos , teremos que varrer a lista de OrderItem(que é onde está a classe Order dentro do atributo items) e essa lista vai la na classe OrderItem e pega os objetos da classe Order. Também criaremos a annotation @JsonIgnore para que não serialize a lista de pedidos(Order) na classe Product 

A Classe Product mostrará os mesmos dados: 

 

 E será apenas serializado todos os objetos na classe Order: 

 

 

Agora , iremos criar a entidade Payment(pagamento que segundo o diagrama de classe, se relaciona com Order (pedido): 

 

Podemos notar que a classe Payment se relaciona com Order numa relação @OneToMany (um pedido tem um pagamento e um pagamento é feito através de um pedido. Então para isso iremos criar a classe Payment dentro do nosso pacote model. E lá criaremos nossos atributos id , Date e a entidade relacional Order que possui o atributo order, vamos criar  seus construtores e getters and setters e vamos implementar o Serializable 

public class Payment  implements Serializable{ 

private static final long serialVersionUID = 1L; 

private Long id; 

private Instant moment; 

private Order order; 

public Payment() { 

super(); 

} 

public Payment(Long id, Instant moment, Order order) { 

super(); 

this.id = id; 

this.moment = moment; 

this.order = order; 

} 

  

public Long getId() { 

return id; 

} 

  

public void setId(Long id) { 

this.id = id; 

} 

  

public Instant getMoment() { 

return moment; 

} 

  

public void setMoment(Instant moment) { 

this.moment = moment; 

} 

  

public Order getOrder() { 

return order; 

} 

  

public void setOrder(Order order) { 

this.order = order; 

}	 

} 

 

Na classe Order, também vamos criar um atributo que referencia a entidade Payment e criaremos os getters and setters: 

private Payment payment; 

public Payment getPayment() { 

return payment; 

} 

  

public void setPayment(Payment payment) { 

this.payment = payment; 

} 

Agora , vamos mapear o relacionamento Order-Payment, e conforme mencionado anteriormente, essa relação terá a annotation @OneToOne, que na classe Payment será a classe filha, pois é da classe Order que criaremos o pagamento e de lá será serializado e criado a tabela Payment. Primeiro vamos na classe Payment criar a anotação @Entity que se chamará “payment”: 

@Entity(name="payment") 

public class Payment implements Serializable{ 

 

private static final long serialVersionUID = 1L; 

@Id 

@GeneratedValue(strategy=GenerationType.IDENTITY) 

private Long id; 

private Instant moment; 

@OneToOne 

@JsonBackReference 

@MapsId 

private Order order; 

Inserimos como de costume as annotation @Id e @GeneratedValue para informar achave primaria com sequencias que serão criadas pelo proprio JPA; 

No atributo order, criamos a annotation @OneToOne para informar ao JPA qual o tipo da relação da classe com a entidade Order; 

Também vamos inserir a annotation @JsonBackReference, para apenas serializar os dados de pagamento ao serem criados na tabela de pedidos. Ou seja, estamos dizendo ao jpa que a serialização do atributo order só acontecerá na outra entidade(Order); 

Criaremos a annotation @MapsId, usada para compartilhar a mesma chave primária entre 2 tabelas.ou seja, ao criar o id do pedido, o id de pagamento terá a mesma chave primaria e id de pedido. 

 

Feito isso, iremos mapear a annotation @OneToOne na entidade Order, e como ela será a classe dominante(pai) onde eu inserir os dados de pagamento la na classe Order, automaticamente criará uma tabela relacional Payment, então para isso temos que dar um mappedBy relacionando o atributo order na classe Payment e inserir o CascadeType.All , que irá propagar as operações de persistir, atualizar, remover e recarregar um objeto da classe pai(Order) para a classe filha(Payment).Também iremos inserir a annotation @JsonManagedReference para serializar todos os dados da entidade Order e ao criar objetos em pedidos , nos mostrará o momento e o id do pedido. 

Na classe Order: 

@JsonManagedReference 

@OneToOne(mappedBy = "order",cascade = CascadeType.ALL) 

private Payment payment; 

 

Persistindo os dados no insommiac: 

 

No banco de dados H2: 

 

 

 

Agora iremos inserir o pagamento total dos pedidos e o subtotal dos itens. 

Podemos notar que na entidade OrderItem temos os atributos price(preço) e quantity(quantidade) e como sabemos, o subtotal de cada item corresponde a quantidade x preço, entao cada item de pedido possui este subtotal . Na entidade Order, temos varios items de pedidos com seus respectivos subtotais.Para que possamos saber o total de cada pedido, precisamos somar os subtotais relacionados à entidade; 

 

Portanto para calcular o subtotal, devemos ir na classe OrderItem e adicionar um método getSubTotal() e que seja de um double e que nos retorne a quantidade vezes o preço. 

Na classe OrderItem: 

public Double getSubTotal() { 

return quantity * price; 

} 

Agora, para calcular o total, devemos ir à classe Order e criaremos um método getTotal que nos devolva um Double e que nos retorne o resultado de uma lista a qual iremos pegar a soma dos subtotais dessa lista, já que o total nada mais é do que a soma dos subtotais da lista de itens de pedidos(OrderItem) 

Na classe Order: 

public Double getTotal() { 

double sum = 0; 

for(OrderItem x :items) { 

sum= sum +x.getSubTotal(); 

} 

return sum;	 

} 

Vamos entender o método getTotal(); ele é do tipo Double e possui uma variável “sum” do tipo double que recebe “0”, depois iremos fazer um for each , ou seja , iremos percorrer para cada item da lista de OrderItem  e somaremos o subtotal cada vez que encontrarmos ele na lista , e por fim retornamos o valor “sum” o qual recebe todas as somas do subtotal da lista 

Agora vamos rodar nossa aplicação e ver como funciona no Insomnia: 

Escolhemos o pedido com id =1: 

 

E vamos mostrar a seguir os subtotais de cada item de pedido: 

 

 

E por fim o total: 

 

 

O nosso projeto esta quase pronto, porem temos algumas correções a fazer e uma delas é que ao criar objetos das entidades, a resposta deve der HTTP 201 Created, e nós temos no momento o HTTP 200 ok, para isso devemos usar uma annotation @ResponseStatus(HttpStatus.CREATED) em todos os métodos created das classes controller: 

 

@PostMapping 

@ResponseStatus(HttpStatus.CREATED) 

public ResponseEntity<User> createUser(@RequestBody User user) { 

return ResponseEntity.ok(userRepository.save(user)); 

} 

As classes OrderController, CategoryController, ProductController e OrderItemController receberão a mesma annotation 

@ResponseStatus (HttpStatus.CREATED) 

 

 

Agora faremos as outras Apis Rest na entidade de User 

Delete 

Update 

 

DELETE: 

Vamos criar nossa aplicação delete na classe Controller , onde ficará o modelo padrão: 

@DeleteMapping("/{id}") 

@ResponseStatus(HttpStatus.NO_CONTENT) 

public void deleteById(@PathVariable long id) throws PersonNotFoundException { 

userService.delete(id); 

} 

Onde temos a annotation @DeleteMapping em que o JPA irá mapear o método deleteById() que passa um parâmetro id. 

O @ResponseStatus que é uma annotation que define o Status Http , neste caso ,ao deletar um usuario pelo id o Stattus deve nos retornar um  sem conteúdo(no content) que indica que a solicitação foi bem definida e o cliente não precisa sair da pagina atual. O metodo deleteById retorna um tipo void (vazio) e recebe como parametro o id e a ação do método é deletar o usuário pelo Id, sendo que quem vai aplicar a regra de negócio é a classe UserService: 

Na classe UserService: 

public void delete(long id) throws PersonNotFoundException { 

 userRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)); 

userRepository.deleteById(id);	 

} 

É criado um método  delete() que também recebe um vazio e tem como parâmetro o id e a ação desse método é pegar o metodo findById da classe repository passando o parametro id e ao encontrar o id solicitado, ele vai apagar o usuario de acordo com o id. 

Note que temos um tratamento de exceção personalizado dentro do método, para que no caso , ao digitar um id inexistente, ele nos retorne com uma mensagem e o status http 404 notfound. Para isso iremos criar uma classe dentro de um pacote Exception: 

A classe se chamará PersonNotFoundException 

@ResponseStatus(HttpStatus.NOT_FOUND) 

public class PersonNotFoundException extends Exception { 

private static final long serialVersionUID = 1L; 

public PersonNotFoundException(long id) { 

super("Person not found with id " +id); 

} 

} 

Essa classe estenderá a interface Exception do Java, e tem como annotation @ResponseStatus(HttpStatus.NOT_FOUND) que deve retornar um codigo de resposta HTTP 404 not found , que indica que o servidor não pôde encontrar o que foi pedido. E por fim iremos criar uma mensagem caso ocorra essa exceção , através de um construtor que recebe como parametro o id inexistente e ,ao identificar ele retorna a mensagem not found: 

Vamos rodar nossa aplicação e ver se funcionou. No insomnia: 

Ao inserir um id não existente, perceba que ele nos retornou um 404 not found: 

 

E ao deletar um usuário existente, ao apagar, ele nos retorna o 204 no content: 

 

Vamos aproveitar e tratar a exceção da Aplicação Rest FindById(), seguindo o mesmo  modelo do metodo delete: 

Na Classe UserService, vamos acrescentar a exceção: 

public User findById(long id) throws PersonNotFoundException { 

Optional<User> obj =userRepository.findById(id); 

obj.orElseThrow(() -> new PersonNotFoundException(id)); 

return obj.get(); 

} 

 E na UserController tb iremos dar um throws PersonNotFoundException: 

@GetMapping("/{id}") //localhost:8080/users/{id} 

public User findById(@PathVariable long id) throws PersonNotFoundException{ 

return userService.findById(id);	 

} 

Vamos rodar a aplicação e ver se esta funcionando: 

Ao inserir um Id não encontrado, ele me retorna um erro 404 not Found 

 

 

UPDATE: 

Vamos criar nossa aplicação delete na classe UserController , onde ficará o modelo padrão: 

@PutMapping("/{id}") // localhost:8080/users/{id} 

public ResponseEntity<User> updateById(@PathVariable long id , @RequestBody User user) throws ResourceNotFoundException{ 

return userService.updateById(id, user);	 

} 

@PutMapping passando o id do usuário para informar ao Spring Boot que essa annotation se refere a uma atualização  

Criaremos um método do tipo ResponseEntity  da entidade User, chamado updateById() que recebe dois parametros id e user e com suas respectivas annotations. Esse método nos retorna o próprio método updateById passando os mesmos parâmetros id e user , sendo que passado para a classe UserService, onde será implementada nossas regras de negócio: 

Na classe UserService: 

public ResponseEntity<User> updateById(long id, User user) throws ResourceNotFoundException  { 

return userRepository.findById(id) 

 .map(record -> { 

             record.setName(user.getName()); 

             record.setEmail(user.getEmail()); 

             record.setPhone(user.getPhone()); 

             User updated = userRepository.save(record); 

             return ResponseEntity.ok().body(updated); 

         }).orElseThrow(() -> new ResourceNotFoundException(id)); 

} 

} 

Aqui temos nossa regra de negócio para o método updateById. A ação desse método   nos retorna um id e o método updateById da classe userRepository. Em seguida criamos uma função lambda na qual iremos apenas dar updates em 3 atributos: name, email e phone. Logicamente não vamos dar update em password.  por fim nos devolve no corpo da resposta as alterações e caso não ocorra, nos trará uma exceção personalizada. 

 

Vamos rodar nossa aplicação para ver se funciona la no Insomnia: 

 

Ao alterar o telefone do usuário, ele nos retorna o objeto com os dados alterados. 

 

Agora o nosso projeto está completo, e precisamos fazer um Deploy no Heroku . Também vamos alterar o nosso Banco de Dados para PostgreSQL: 

Vamos entrar no site www.heroku.com 

Vamos fazer uma conta no heroku, no meu caso já havia criado a conta, só preciso logar  

Após logado, na página principal 

 https://dashboard.heroku.com/apps 

E vamos clicar no botão NEW -> CREATE NEW APP 

 

Escolhe o nome do projeto e a região, depois é só clicar no botão create app. 

O Heroku criou a aplicação e te levou para o Dashboard. 

Agora vamos provisionar o banco de dado PostgreSQL. 

Clicando em Resources  e na caixa “add on”  e dentro da caixa vamos escrever Postgre e ele vai nos dar opções e vamos escolher “Heroku Postgre” e escolher o plano free.  

 

Agora iremos instalar o PostgreSQL na máquina: 

https://www.postgresql.org/download/ 

 Escolhe a opção windows 

E a versão 12.8 

 

Ao instalar o PostgreSql ele vai nos perguntar o password e a porta , então vamos colocar os seguintes dados: 

Password: 1312  

 Port: 5432 

E será feito o download do Banco de Dados: 

 

 

E vamos checar se a instalação funcionou e dar um start em PGAdmin 4. Vamos acessar em iniciar, e digitar PgAdmin 

E clicar nele, o PgAdmin vai chamar a versão dele na web, então digitamos a senha que criamos (1312). E ao expandir o Servers1 , vai nos mostrar o servidor local que instalamos 

Vamos criar uma base de dados 

Databases -> botão direito em postgress -> create -> database  

E vamos dar um nome ao nosso banco de dados: 

SpringBoot_apiRestData 

E clica em save. Então com isso , ele já criou uma segunda base de dados no meu servidor local. E está funcionando nossa base de dados local Postrgresql. 

  

 No inicio do nosso projeto, configuramos um profile aplicattion test- profiles no eclipse, que é uma configuração de teste em banco de dados h2. Mas agora iremos criar outro profile dev para que possamos trabalhar com o banco de dados postgres 

 

Primeiro vamos adicionar a dependencia  no maven do postgresql la no pom.xml: 

<dependency> 

			<groupId>org.postgresql</groupId> 

			<artifactId>postgresql</artifactId> 

			<scope>runtime</scope> 

		</dependency> 

 

Agora vamos mapear e criar outra properties chamada application-dev.properties e vamos mapear os dados do postgres: 

spring.datasource.url=jdbc:postgresql://localhost:5432/ SpringBoot_apiRestData 

 

spring.datasource.username=postgres  

spring.datasource.password=1312  

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true  

spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true  

spring.jpa.properties.hibernate.format_sql=true  

jwt.secret=MYJWTSECRET jwt.expiration=3600000	 

Feito essas configurações, vamos em application.properties e mudar de test para dev, onde ele irá mapear agora o BD Postgres: 

spring.profiles.active=dev 

E vamos rodar nossa aplicação pra ver se está funcionando 

 

Agora vamos pro nosso banco de dados Postgresql e vamos para a nossa base de dados criada -> schemas -> table  

E lá estarão nossas tabelas criadas 

 

 

Agora vamos pegar o script SQL do banco postgres local: 

Vamos no postgres e no banco de dados vamos clicar com o botao direito e clicar em backup e seguir as seguintes instruções:  

Vamos em filename e colocar o seguinte diretório: 

 

E vamos clicar em create. Após isso vamos configurar conforme dados abaixo: 

 

 Format: Plain -> formato de texto 

  Encoding: UTF8 

  Dump options:  

 Only schema: YES  

 Blobs: NO  

 Do not save: (ALL YES)  

 Verbose messages: NO  

 

E vamos salvar essas configurações, e pronto! Está criado nosso script para o nosso BD. 

E vamos deletar tudo o que tiver antes do primeiro create , dentro do script.sql. 

 

A partir daí , vamos rodar o nosso script sql para criar as estruturas das tabelas lá no Heroku 

Vamos no Heroku, e acessar nossa aplicação , e no dasboard da aplicação  clicaremos em settings e config vars 

Heroku -> App dashboard -> Settings - > Config Vars 

 

Em DATABASE_URL  temos  a variável url da aplicação no heroku. Então iremos copiar essa variável e colar num bloco de notas e então iremos fragmentar nos seguintes passos a seguir: 

 postgres://erzhedyzoulsey:8d3cfea71fee7663199b188a1ada217626982c2e6484342348c7e9548bf11a60@ec2-34-227-120-94.compute-1.amazonaws.com:5432/dcmjm0lggrte6u 

Onde: 

 

User: erzhedyzoulsey 

Password: 8d3cfea71fee7663199b188a1ada217626982c2e6484342348c7e9548bf11a60 

Server: ec2-34-227-120-94.compute-1.amazonaws.com 

Port: 5432 

Database: dcmjm0lggrte6u 

 

Agora iremos lá no PgAdmin e criar um novo servidor de banco de dados, que vai ser o servidor remoto apontando pro Heroku. 

PgAdmin: Servers -> Create -> Server 

E vamos configurar :  

O nome , colocaremos qualquer um, eu escolhi HerokuProject 

Depois do nome , vamos na aba “connection” e no Host iremos inserir o host fornecido pelo Heroku: 

 => ec2-34-227-120-94.compute-1.amazonaws.com 

Port: 5432 

Maintenance Database: dcmjm0lggrte6u 

Username : erzhedyzoulsey 

Password: 8d3cfea71fee7663199b188a1ada217626982c2e6484342348c7e9548bf11a60 

E clica em salvar password 

 

Uma coisa importante: na aba Advanced , iremos colocar o nome da Database dentro do Db restriction. 

Advanced -> Db restriction -> dcmjm0lggrte6u 

feito isso , iremos rodar nossa query script pra criar nossas tabelas 

Em cima da Database criada, clicamos sobre QueryTool 

Database -> Query Tool 

Vai abrir uma aba e vamos inserir o script.sql já criado anteriormente selecionando no botão de open file e depois clicamos no botão execute , e serão criadas as tabelas no nosso banco de dados remoto: 

 

 

Feito isso vamos pro Heroku e instalar o HerokuCLI: 

 

Heroku -> Seu projeto -> Deploy -> Download and install HerokuCli 

 

Apos instalado vamos ver esta realmente funcionando: 

No git bash digite: 

Heroku –v 

E o git irá nos mostrar a versão do HerokuCli instalada. 

 

Feito isso agora, vamos fazer o Deploy da aplicação no Heroku: 

 

Vamos no dashboard da aplicação no heroku , e na aba Deploy  

u app dashboard -> Deploy heroku 

E lá vai ter as instruções do deploy no git: 

 

Vamos ao git , no diretório da nossa aplicação e vamos digitar os seguintes comandos: 

 git:remote -a "nome da aplicação” 

E para testar se o comando funcionou , digitamos: 

git remote –v 

 

Ele vai mostrar os remotes tanto no github quanto no Heroku 

 Agora vamos configurar as aplicações do heroku no config vars: 

Dashboard app -> settings -> config vars -> reveals config vars 

 

E vamos adicionar mais dois config vars: 

JWT_EXPIRATION   36000000 

JWT_SECRET  MYSECRET 

 

Ficará conforme o quadro abaixo: 

 

 

Próximo passo, iremos criar o arquivo application-prod.properties: 

Vamos no eclipse e criar o arquivo e dentro dele vamos inserir esses comandos: 

spring.datasource.url=${DATABASE_URL}  

spring.jpa.hibernate.ddl-auto=none  

spring.jpa.show-sql=false spring.jpa.properties.hibernate.format_sql=false jwt.secret=${JWT_SECRET} 

 jwt.expiration=${JWT_EXPIRATION} 

 

E dentro do arquivo application.properties : 

Vamos mudar o spring.profiles.active de “dev” para “prod” 

 

spring.profiles.active=prod 

 

Agora iremos avisar ao heroku que a nossa aplicação é o java 1.8 

Para isso vamos criar um arquivo na raiz da aplicação chamado : system.properties 

E dentro dele colocaremos: 

java.runtime.version=1.8 

 

Vamos salvar nossa aplicação e enviar pro Heroku 

 

No git, iremos inserir os seguintes comandos: 

 

git add .  

git commit -m "Deploy app to Heroku"  

git push heroku master 

 

Então será feito o downloads das dependências e será feito os buils da nossa aplicação 

E foi feito o deploy do projeto no Heroku 

 

Podemos testar indo no site do Heroku , no dashboard da aplicação e clicar no botão OPEN APP e irá mostrar uma mensagem de erro dizendo que está funcionando. 

 

agora vamos testar a aplicação em produção 

Vamos no insomnia e testar se está funcionando: 

Vamos fazer uma requisição e ver se esta funcionando. Vamos pegar o endereço que o heroku nos forneceu que é: 

https://project-spring-boot-user-order.herokuapp.com  

E vamos na aplicação post e copiar para la na uri, e adicionar /users: 

https://project-spring-boot-user-order.herokuapp.com/users 

Conforme figura abaixo: 

 

Vamos ver se foram persistidas na tabela users la no postrges: 

No postgres , vamos clicar em cima do nome da database -> Schemas -> Tables -> Users -> View Edit Data -> All Rows 

 

E irá aparecer nosso usuário criado pelo Heroku: 

 

Parabéns. 

Agora você tem uma aplicação on line que você fez o código fonte no guichê de aplicação em produção. 

 

 

 

 

 

 

 

 

  

  