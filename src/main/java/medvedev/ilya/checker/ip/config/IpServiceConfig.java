package medvedev.ilya.checker.ip.config;

import lombok.Setter;
import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.ip.http.HttpIpService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.net.URL;

@Configuration
@ConfigurationProperties(prefix = "ip.service")
@Setter
@Validated
public class IpServiceConfig {
    @NotNull
    private URL url;

    @Bean
    public IpService ipService() {
        return new HttpIpService(url);
    }
}
