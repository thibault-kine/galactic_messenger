package galactic_messenger.app;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    /*@Value("${key.something}")
    private String injectedProperty;

    @Autowired
    private Environment env;

    public String getSomeKey(){
        return env.getProperty("key.something");
    }*/


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}