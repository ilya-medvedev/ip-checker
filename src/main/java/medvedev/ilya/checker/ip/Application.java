package medvedev.ilya.checker.ip;

import medvedev.ilya.checker.ip.service.checker.CheckerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
            context.getBean(CheckerService.class).run();
        }
    }
}
