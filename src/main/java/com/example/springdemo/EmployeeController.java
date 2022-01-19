package com.example.springdemo;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository repository;

//    EmployeeController(EmployeeRepository repository){
//        this.repository = repository;
//    }

    @GetMapping("/employees")
    List<Employee> all() {
        logger.info("all method invoked");
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    Employee get(@PathVariable Long id) {
        logger.info("get method invoked");
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    Employee create(@RequestBody Employee employee) {
        logger.info("create method invoked");
        return repository.save(employee);
    }

    @PutMapping("/employees/{id}")
    Employee update(@RequestParam Long id, @RequestBody Employee employee) {
        logger.info("update method invoked");
        return repository.findById(id)
                .map(employee1 -> {
                    employee1.setFirstName(employee.getFirstName());
                    employee1.setLastName(employee.getLastName());
                    employee1.setSalary(employee.getSalary());
                    employee1.setRole(employee.getRole());
                    return repository.save(employee1);
                }).orElseGet(() -> {
                    employee.setId(id);
                    return repository.save(employee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void delete(@PathVariable Long id) {
        logger.info("delete method invoked");
        repository.deleteById(id);
    }
}

@ControllerAdvice
class EmployeeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFound(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}

class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id) {
        super("No Employee details found with id: "+id);
    }
}

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Value("${swagger.api.title}")
    private String swaggerApiTitle;

    @Bean
    public Docket springDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springdemo"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerApiTitle)
                .description("SpringDemo API reference for developers")
                .termsOfServiceUrl("http://SpringDemo.com").license("SpringDemo License")
                .licenseUrl("SpringDemo@gmail.com").version("1.0").build();
    }

}

@Configuration
class LoadDBConfiguration {
    private static final Logger log = LoggerFactory.getLogger(LoadDBConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(createEmployee("john", "deer", "manager", 100000)));
            log.info("Preloading " + repository.save(createEmployee("thomas", "edison", "employee", 80000)));
        };
    }

    private Employee createEmployee(String firstName, String lastName, String role, int salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setRole(role);
        employee.setSalary(salary);
        return employee;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {}

@Entity
@Data
class Employee {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;
    private int salary;
}

