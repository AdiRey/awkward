package pl.awkward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AwkwardBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwkwardBackApplication.class, args);
    }

}
