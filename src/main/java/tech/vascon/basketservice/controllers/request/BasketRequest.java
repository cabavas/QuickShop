package tech.vascon.basketservice.controllers.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketRequest {

    private Long clientId;

    private List<ProductRequest> products;
}
