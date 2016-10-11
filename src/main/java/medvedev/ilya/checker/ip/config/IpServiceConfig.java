package medvedev.ilya.checker.ip.config;

import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.ip.http.HttpIpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class IpServiceConfig {
    @Bean
    public IpService ipService(@Value("${ip.service.url}") final URL url) {
        return new HttpIpService(url);
    }
}
