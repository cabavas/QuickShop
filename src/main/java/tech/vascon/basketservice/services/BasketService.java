package tech.vascon.basketservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.vascon.basketservice.client.response.PlatziProductResponse;
import tech.vascon.basketservice.controllers.request.BasketRequest;
import tech.vascon.basketservice.controllers.request.PaymentRequest;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.entity.Product;
import tech.vascon.basketservice.entity.Status;
import tech.vascon.basketservice.exceptions.BusinessException;
import tech.vascon.basketservice.exceptions.DataNotFoundException;
import tech.vascon.basketservice.repository.BasketRepository;

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
        throw new BusinessException("There is already an open basket for this client.");
      });

    List<Product> products = getProducts(request);

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

  public Basket findById(String id) {
    return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Basket not found"));
  }

  public Basket update(String id, BasketRequest request) {
    if (repository.findById(id).isPresent()) {
      Basket savedBasket = repository.findById(id).get();


      List<Product> products = getProducts(request);

      savedBasket.setProducts(products);
      savedBasket.calculateTotalPrice();
      return repository.save(savedBasket);
    }
    return null;
  }

  private List<Product> getProducts(BasketRequest request) {
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
    return products;
  }

  public void delete(String id) {
    repository.deleteById(id);
  }

  public Basket payment(String id, PaymentRequest request) {
    if (repository.findById(id).isPresent()) {
      Basket savedBasket = repository.findById(id).get();
      savedBasket.setPaymentMethod(request.getPaymentMethod());
      savedBasket.setStatus(Status.SOLD);

      return repository.save(savedBasket);
    }
    return null;
  }
}
