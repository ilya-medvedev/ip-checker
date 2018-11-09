package medvedev.ilya.checker.ip;

import medvedev.ilya.checker.ip.service.checker.CheckerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args)
                .getBean(CheckerService.class)
                .run();
    }
}
