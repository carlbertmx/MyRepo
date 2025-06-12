package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// 1. App Principal Inicia la App Spring Boot
@SpringBootApplication
public class AppInterceptor {

    public static void main(String[] args) {
        SpringApplication.run(AppInterceptor.class, args);
    }
}


// 2. Controlador : Responde a peticiones HTTP
@RestController
class Controlador {

    @GetMapping("/hola")
    public String hola() {
        return "Hola desde Controller";
    }
}

// 4
@Configuration
class Config implements WebMvcConfigurer {
    private final myClaseInterceptora myObjClaseInterceptora;

    // En el constructor inyecto
    public Config(myClaseInterceptora interceptor) {
        this.myObjClaseInterceptora = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myObjClaseInterceptora);
    }
}


// 3 Interceptor : realiza acciones antes y depsues de c/peticion
@Component
class myClaseInterceptora implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        System.out.println("Interceptor antes del controller");
        System.out.println(">>> Interceptor: URL = " + req.getRequestURI());
        return true; // deja continuar la petición
        // Si es 'false', la petición se detiene aquí.
    }

    // se ejecuta DESPUÉS  que la petición ha sido completada (incluso si hubo un error).
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
        System.out.println("Interceptor después del controller");
    }
}
