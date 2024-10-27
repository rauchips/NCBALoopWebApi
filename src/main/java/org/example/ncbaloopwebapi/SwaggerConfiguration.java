package org.example.ncbaloopwebapi;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .displayName("NCBA Loop Web API")
                .group("User")
                .group("Account")
                .group("Card")
                .pathsToMatch("/api/**")
                .build();
    }
}