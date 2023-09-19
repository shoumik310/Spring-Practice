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
6. security - ``spring-boot-starter-security``

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



## Spring Security
Spring Security deals with
1. Authentication - Are you really the same person, whom you claim to be?
2. Authorization - Do you have privilivges to perform the action?

## Steps to Integrate Spring Security
    
1. Add the `spring-security-starter` dependency
```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

2. Annotate the main class with `@EnableWebSecurity`

3. Default username will be `user` and password will be printed in the console

4. Create user and password explicitly inside `application.properties` file
```properties
spring.security.user.name=laxman
spring.security.user.password=welcome
```

5. To create users and roles, customize the Spring Security by extending through extension points

6. Create a `SecurityConfiguration` class, annotate with `@Configuration` and extend `WebSecurityConfigurerAdaptor`

```java
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    ...
}
```    
7. Override the `configure(HttpSecurity httpSecurity)` method

```java
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
```

## Configuring InMemory Authentication
1. Comment the user and password defined in `application.properties` file
2. Create a Bean for password encoding
```java
  @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
```
3. Override `configure(AuthenticationManagerBuilder authManagerBuilder)` method in the `SecurityConfiguration` class

```java
  @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //configure users
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("kiran")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("vinay")
                .password(passwordEncoder().encode("adminuser"))
                .roles("USER","ADMIN");
    }
}
```

4. Encrypt the password and use the `BcryptPasswordEncoder` 

```java
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //configure users
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("kiran")
                .password(bcryptPasswordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("vinay")
                .password(bcryptPasswordEncoder().encode("adminuser"))
                .roles("USER","ADMIN")
                .and()
                .passwordEncoder(bcryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Primary
    @Bean
    public PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
```
### Setting up Authorization
1. Override the `configure(HttpSecurity httpSecurity)` method

```java
@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {...}
```
2. Setup the Authorization rules
```java
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // configure authorization rules here
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers(GET,"/api/v1/orders/**")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers(POST,"/api/v1/orders/**")
                .hasRole("ADMIN")
                .antMatchers(PUT,"/api/v1/orders/**")
                .hasRole("ADMIN")
                .antMatchers(DELETE,"/api/v1/orders/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
               .and()
                /*
                   Set the sessioncreation policy to avoid using cookies for authentication
                   https://stackoverflow.com/questions/50842258/spring-security-caching-my-authentication/50847571
                 */
                .sessionManagement().sessionCreationPolicy(STATELESS);
  
    }
```

## Configuring DAOAuthentication using SpringSecurity

Steps for DAO Authentication
1. Create User DTO class
```java
package com.classpath.ordermgmt.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import static javax.persistence.FetchType;
import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long userId;

    private String username;

    private String password;

    private String emailAddress;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Role> roles;

}
```

2. Create Role DTO class

```java
@Entity
@Setter
@Getter
@ToString(exclude = "users")
@EqualsAndHashCode(of = "roleId")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
}
```

3. Create `UserRepository` interface

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> { 
    Optional<User> findByUsername(String username);
 }

```

4. Create `data.sql` inside `src/main/resources` directory and insert the data
```sql

insert into user (user_id, email_address, password, username) values (1, 'kiran@gmail.com', '$2A$10$EYQ2LNWTBRKXDJG7CPFOX.KUW4I3QIJEU.ZHME3CAIWCK1U0UHIRM', 'kiran');
insert into user (user_id, email_address, password, username) values (2, 'vinay@gmail.com', '$2A$10$TN.AT/ISGMOASVWTK0SMOUEGMSEBUKLAJHHD2/GOVJYXV.6NY3KRQ', 'vinay');

insert into role (role_id, role_name) values (1, 'ROLE_USER');
insert into role (role_id, role_name) values (2, 'ROLE_ADMIN');

insert into user_roles(user_id, role_id) values (1, 1),(2, 1), (2,2);
```

5. Configure Spring to initialize the data 
```properties
spring.datasource.initialization-mode=always
```

6. Create a `DomainUserDetailsServer` class implementing `UserDetailsService` interface
```java
@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Invalid User"));
        System.out.println("User from the repository ");
        System.out.println(user);
        //convert into UserDetails object which Spring Security can understand
        return new DomainUserDetails(user);
    }
}
```
7. Create a `DomainUserDetails` class implementing `UserDetails` interface

```java
public class DomainUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public DomainUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Printing the authorities :: ");
        System.out.println(this.authorities);
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```
8. Configure the authentication manager in the `SecurityConfiguration` class
```java
 @Override
protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //configure users
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(bcryptPasswordEncoder());
    }
```
