package exceptionhandling.classroom.readfromconfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.resources") // put the path of folder you wish to scan
public class ExternalConfig {

    @Bean
    public Row row() {
        return new Row();// we own the creational resp.
    }

}

// MS1 : Beta => Gamma  => Prod
// MS2 : Beta => Gamma  => Prod
// env vars

