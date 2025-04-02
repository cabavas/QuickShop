package tech.vascon.basketservice.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI getOpenAPI() {

    Contact contact = new Contact();
    contact.setUrl("https://github.com/cabavas");
    contact.setName("Caio Vasconcelos");
    contact.setEmail("cabavas@gmail.com");

    Info info = new Info();
    info.title("QuickShop");
    info.version("1.0");
    info.description("API para consumo de produtos de uma API externa e criação de um carrinho de compras");
    info.contact(contact);

    return new OpenAPI().info(info);
  }
}
