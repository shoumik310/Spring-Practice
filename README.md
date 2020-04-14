# Spring Boot
- Modern and recommended way of building Spring Applications
- Uses declarative approach and Cloud native ready
- Requires only JRE to run the application
- Contains embedded tomcat for deploying web applications
- Supports other VM based languages like *Groovy*, *Kotlin* and *Scala*
- Excellent tooling support for development (*dev-tools*)
- Leverages following properties from Spring Framework
  - Spring BOM for dependency management
  - Conditional Configuration for Conditionally loading beans
  - Built in Actuators for getting health check, stats and metrics
  - Configurations can be tuned the `application.properties` file
  
## Project setup
Different ways to set up Spring Boot Project 
- **start.spring.io** - Official website to generate the scaffolding code
- From IDE (Eclipse, IntelliJ Idea)
- From Spring Boot CLI

## Starters dependencies
Spring Boot comes with various starter dependencies
 
1. web - ``spring-boot-starter-web``
2. aop - ``spring-boot-starter-web``
3. jdbc - ``spring-boot-starter-jdbc``
4. jpa - ``spring-boot-starter-jpa``
5. mongodb - ``spring-boot-starter-mongodb`` 

[Complete List of Starter Dependencies](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-starter)

## Packaging Spring Boot applications
Spring boot applications can be deployed with 
- jar
- war

## Running Spring Boot applications
1. Using springboot maven plugin
   ```bash
       spring-boot:run
    ```
    [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/run-mojo.html)
    
    Useful for development
2. As a fat jar
   ```bash
    java -jar helloworld.jar
   ```
   
## Setting up Lombok for generating boilerplate code
1. Add the maven dependency 
```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

```

2. Run the following command 

```bash
java -jar lombok-1.18.12.jar
```

3. Eclipse Users follow the steps    

1. Download the jar from the website - https://projectlombok.org/download
2. Run the jar ``java -jar lombok-1.18.12.jar``
3. Select the installable
4. Close the Eclipse IDE and open it again
5. Clean all the Projects and rebuild the projects
6. Refersh the project

![Lombok installation](https://gitlab.com/classpath-spring-core/spring-boot-examples/-/blob/master/assets/lombok.png)


## Spring Boot with Spring Data JPA

Steps to perform `One-To-Many` mapping

1. POM dependencies
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.19</version>
    </dependency>

```
2. Update the `application.properties`
```properties
#datsource properties
spring.datasource.username=root
spring.datasource.password=welcome
spring.datasource.url=jdbc:mysql://localhost:3306/emp_db
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57InnoDBDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

3. Create the `Order` Entity class
```java
@Entity
@Table(name = "orders")
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "orderId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private long orderId;

    @Column(name = "order_date")
    @JsonProperty("order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private LocalDate orderDate;

    @Column(name="merchant_name")
    @JsonProperty("merchant_name")
    private String merchantName;

    @OneToMany(mappedBy = "order",
            fetch = LAZY,
            cascade = {PERSIST, REMOVE})
    @JsonProperty("line_items")
    @Column(name = "line_items")
    private Set<OrderLineItem> orderLineItems;

    // scaffolding code
    public void addOrderLineItem(OrderLineItem orderLineItem){
        this.orderLineItems.add(orderLineItem);
        orderLineItem.setOrder(this);
    }
}
```

4. Create the `OrderLineItem` Entity class
```java
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "order_line_item")
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "price")
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    @JsonIgnore
    private Order order;

    //default constructor used for serialization and deseriazation
    OrderLineItem(){}

    public OrderLineItem(String name, double price){
        this.name = name;
        this.price = price;
    }
}
```

5. `OrderServiceImpl` class
```java
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        System.out.println("Inside the save method of Order service .... :: ");
        System.out.println(order);
        for(OrderLineItem orderLineItem: order.getOrderLineItems()) {
            orderLineItem.setOrder(order);
        }

        return this.orderRepository.save(order);
    }
    @Override
    public Set<Order> fetchAllOrders() {
        return new HashSet<>(this.orderRepository.findAll());
    }
    @Override
    public Order findByOrderId(long orderId) throws ResourceNotFoundException {
        return this.orderRepository
                    .findById(orderId)
                    .orElseThrow(()->new ResourceNotFoundException("Resource not found"));

    }
}
```

6. Repository interface
```java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
}
```
7. Controller
```java
@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @GetMapping
    public Set<Order> fetchOrders(){
        return this.orderService.fetchAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderByOrderId(@PathVariable long orderId) throws ResourceNotFoundException {
        System.out.println("Inside the get Order by Order id "+orderId);
        return this.orderService.findByOrderId(orderId);
    }
}
```
## Testing the application

### POST request - http://localhost:8080/v1/orders/

```json
{
    "order_date": "20-03-20",
    "merchant_name":"Kalyan",
    "line_items": [
        {
            "name": "gold-chain",
            "price": 65000
        },
        {
            "name": "bracelet",
            "price": 65000
        }

    ]
}
```

### GET request - http://localhost:8080/v1/orders/

```json
[
    {
        "order_id": 1,
        "order_date": "20-03-20",
        "merchant_name": "Kalyan",
        "line_items": [
            {
                "id": 3,
                "name": "gold-chain",
                "price": 65000,
                "quantity": 0
            },
            {
                "id": 2,
                "name": "bracelet",
                "price": 65000,
                "quantity": 0
            }
        ]
    }
]
```

### GET with order-id - http://localhost:8080/v1/orders/1

```json
{
    "order_id": 1,
    "order_date": "20-03-20",
    "merchant_name": "Kalyan",
    "line_items": [
        {
            "id": 3,
            "name": "gold-chain",
            "price": 65000,
            "quantity": 0
        },
        {
            "id": 2,
            "name": "bracelet",
            "price": 65000,
            "quantity": 0
        }
    ]
}
```