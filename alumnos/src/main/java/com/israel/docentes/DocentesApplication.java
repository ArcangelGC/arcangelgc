package com.israel.docentes;
import com.israel.alumnos.AlumnosApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Esta clase nos ayuda a heredar de la interfaz genérica de Spring Data JPA, proporcionando los metodos crud
@SpringBootApplication
public class DocentesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocentesApplication.class, args);
    }
}
