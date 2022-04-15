package hu.yokudlela.foodAndDrinks.spring;

import hu.yokudlela.foodAndDrinks.datamodel.Food;
import hu.yokudlela.foodAndDrinks.utils.request.RequestBean;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.jboss.logging.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SecurityScheme(
        type = SecuritySchemeType.OAUTH2,
        name = "oauth2",
        description = "KeyCloak Yokudlela",
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://172.17.0.1:6080/auth/realms/yokudlela/protocol/openid-connect/auth"
                        + "?client_id=account"
                        //+ "&redirect_uri=http://172.17.0.1:8080/food/swagger-ui/oauth2-redirect.html"
                        + "&redirect_uri=http://yokudlela:8080/food/swagger-ui/oauth2-redirect.html"
                        + "&response_type=code"
                        + "&scope=openid")
        )
)

@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "apikey",
        paramName = "Authorization",
        description = "KeyCloak Yokudlela",
        in = SecuritySchemeIn.HEADER)


@SecurityScheme(
        type = SecuritySchemeType.OPENIDCONNECT,
        name = "openid",
        description = "KeyCloak Yokudlela",
        //openIdConnectUrl = "http://172.17.0.1:6080/auth/realms/yokudlela/.well-known/openid-configuration"
        openIdConnectUrl = "http://yokudlela:6080/auth/realms/yokudlela/.well-known/openid-configuration"
)

@OpenAPIDefinition(
        servers = {
                //@Server(url = "http://172.17.0.1:8080/food", description = "local dev"),
                @Server(url = "http://yokudlela:8080/food", description = "local dev"),
                @Server(url = "https://www.yokudlela.hu:1980/food", description = "test") },

        info = @Info(
                title = "Yokudlela Table API",
                version = "v1",
                description = "description = \"Yokudlela Table API for Graphical User Interface .",
                license = @License(
                        name = "Custom 4D Soft",
                        url = "https://www.4dsoft.hu"),
                contact = @Contact(
                        url = "https://www.4dsoft.hu",
                        name = "Fodor Norbert", email = "dvkerf@4dsoft.hu")))
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "hu.yokudlela.foodAndDrinks.service",
        "hu.yokudlela.foodAndDrinks.rest",
        "hu.yokudlela.foodAndDrinks.utils.request",
        "hu.yokudlela.foodAndDrinks.utils.logging"
})
@EnableJpaRepositories("hu.yokudlela.foodAndDrinks.store")
@EntityScan("hu.yokudlela.foodAndDrinks.datamodel")
@SpringBootApplication
@Import(ValidationRestDataExceptionHandler.class)
public class FoodAndDrinksApplication {

    public static void main(String[] args){
       SpringApplication.run(FoodAndDrinksApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer (){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean("requestScopedBean")
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestBean requestBean() {
        MDC.put("application", "2");
        MDC.put("host", "3");
        return new RequestBean();
    }

    @Bean("applicationContextProvider")
    public ApplicationContextProvider createApplicationContextProvider() {

        return new ApplicationContextProvider();
    }
}
