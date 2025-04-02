package tech.vascon.basketservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "basket")
public class Basket {
  @Schema(type = "string", description = "ID do carrinho")
  @NotBlank
  @Id
  private String id;

  @Schema(type = "long", description = "ID do cliente")
  private Long client;
  @Schema(type = "number", description = "Valor total do carrinho")
  private BigDecimal totalPrice;
  @ArraySchema(schema = @Schema(implementation = Product.class))
  @Schema(description = "Lista dos produtos incluídos no carrinho")
  private List<Product> products;
  @Schema(type = "string", description = "Status do pedido")
  private Status status;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(type = "string", description = "Método de pagamento utilizado para pagar o carrinho")
  private PaymentMethod paymentMethod;

  public void calculateTotalPrice() {
    this.totalPrice = products.stream()
      .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
