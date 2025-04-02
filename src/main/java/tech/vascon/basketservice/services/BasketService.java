package tech.vascon.basketservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.vascon.basketservice.controllers.request.BasketRequest;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.entity.Product;
import tech.vascon.basketservice.entity.Status;
import tech.vascon.basketservice.repository.BasketRepository;
import tech.vascon.basketservice.client.response.PlatziProductResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository repository;
    private final ProductService productService;

    public Basket create(BasketRequest request) {
        repository.findByClientAndStatus(request.getClientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalArgumentException("There is already an open basket for this client.");
                });

        List<Product> products = new ArrayList<>();
        request.getProducts().forEach(product -> {
            PlatziProductResponse platziProductResponse = productService.findById(product.getId());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(product.getQuantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(request.getClientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();
        return repository.save(basket);
    }

    public List<Basket> findAll() {
        return repository.findAll();
    }
}
