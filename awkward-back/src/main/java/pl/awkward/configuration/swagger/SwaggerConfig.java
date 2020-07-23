package pl.awkward.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.awkward.configuration.profiles.DevProfile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@DevProfile
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("pl.awkward"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Awkward",
                "Fighting with awkwardness",
                "1.0.0",
                "awkward.pl",
                new Contact("AdiRey", "https://github.com/AdiRey", "kowal.adrian@interia.pl"),
                "My license",
                "https://github.com/AdiRey/awkward",
                Collections.emptyList()
        );
    }

}
