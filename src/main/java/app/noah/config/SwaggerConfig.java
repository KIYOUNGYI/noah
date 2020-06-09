package app.noah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api() {

        String profile = System.getProperty("spring.profiles.active");
        String host = "localhost:8080";

//        if ("develop".equals(profile) || "staging".equals(profile)) {
//            host = "ec.glowpick.net:8099";
//        }
//        if ("production".equals(profile)) {
//            host = "admin-api.glowpick.com";
//        }

        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("app.noah"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("글로우픽 Noah API(Admin+Client)")
                .build();
    }
}
