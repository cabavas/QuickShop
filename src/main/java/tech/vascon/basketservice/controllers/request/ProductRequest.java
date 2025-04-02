package tech.vascon.basketservice.controllers.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private Long id;
    private Integer quantity;
}
