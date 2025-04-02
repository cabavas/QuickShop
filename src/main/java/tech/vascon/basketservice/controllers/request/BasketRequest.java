package tech.vascon.basketservice.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tech.vascon.basketservice.entity.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BasketRequest {

    private Long clientId;

    private List<ProductRequest> products;

}
