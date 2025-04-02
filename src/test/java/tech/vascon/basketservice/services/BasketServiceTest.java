package tech.vascon.basketservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.vascon.basketservice.client.response.PlatziProductResponse;
import tech.vascon.basketservice.controllers.request.BasketRequest;
import tech.vascon.basketservice.controllers.request.PaymentRequest;
import tech.vascon.basketservice.controllers.request.ProductRequest;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.entity.PaymentMethod;
import tech.vascon.basketservice.entity.Product;
import tech.vascon.basketservice.entity.Status;
import tech.vascon.basketservice.exceptions.BusinessException;
import tech.vascon.basketservice.repository.BasketRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

  @Mock
  private BasketRepository repository;

  @Mock
  private ProductService productService;

  @InjectMocks
  private BasketService basketService;

  private Basket basket;
  private BasketRequest request;

  @BeforeEach
  void setUp() {
    Product product = Product.builder()
      .id(1L)
      .title("Test Product")
      .price(BigDecimal.valueOf(125))
      .quantity(2)
      .build();

    ProductRequest productRequest = new ProductRequest(product.getId(), product.getQuantity());

    request = new  BasketRequest(1L, List.of(productRequest));

    basket = Basket.builder()
      .id("basket-123")
      .client(1L)
      .status(Status.OPEN)
      .products(List.of(product))
      .build();

  }

  @Test
  void shouldCreateBasketSuccessfully() {
    when(repository.findByClientAndStatus(anyLong(), eq(Status.OPEN))).thenReturn(Optional.empty());
    when(productService.findById(anyLong())).thenReturn(new PlatziProductResponse(1L, "Test Product", BigDecimal.valueOf(125)));
    when(repository.save(any(Basket.class))).thenReturn(basket);

    Basket createdBasket = basketService.create(request);
    assertNotNull(createdBasket);
    assertEquals(1L, createdBasket.getClient());
    assertEquals(Status.OPEN, createdBasket.getStatus());
    verify(repository, times(1)).save(any(Basket.class));
  }

  @Test
  void shouldNotCreateBasketIfClientAlreadyHasOpenBasket() {
    when(repository.findByClientAndStatus(anyLong(), eq(Status.OPEN))).thenReturn(Optional.of(basket));

    BusinessException thrown = assertThrows(BusinessException.class, () -> basketService.create(request));

    assertEquals("There is already an open basket for this client.", thrown.getMessage());
    verify(repository, never()).save(any(Basket.class));
  }

  @Test
  void shouldFindBasketById() {
    when(repository.findById("basket-123")).thenReturn(Optional.of(basket));

    Basket foundBasket = basketService.findById("basket-123");

    assertNotNull(foundBasket);
    assertEquals(1L, foundBasket.getClient());
    verify(repository, times(1)).findById("basket-123");
  }

  @Test
  void shouldThrowExceptionWhenBasketNotFound() {
    when(repository.findById("invalid-id")).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> basketService.findById("invalid-id"));
  }

  @Test
  void shouldUpdateBasketSuccessfully() {
    when(repository.findById("basket-123")).thenReturn(Optional.of(basket));
    when(productService.findById(anyLong())).thenReturn(new PlatziProductResponse(1L, "New Product", BigDecimal.valueOf(50)));
    when(repository.save(any(Basket.class))).thenReturn(basket);

    Basket updatedBasket = basketService.update("basket-123", request);

    assertNotNull(updatedBasket);
    assertEquals(1L, updatedBasket.getClient());
    verify(repository, times(1)).save(any(Basket.class));
  }

  @Test
  void shouldDeleteBasket() {
    doNothing().when(repository).deleteById("basket-123");

    basketService.delete("basket-123");

    verify(repository, times(1)).deleteById("basket-123");
  }

  @Test
  void shouldProcessPayment() {
    PaymentRequest paymentRequest = new PaymentRequest(PaymentMethod.CREDIT);
    when(repository.findById("basket-123")).thenReturn(Optional.of(basket));
    when(repository.save(any(Basket.class))).thenReturn(basket);

    Basket paidBasket = basketService.payment("basket-123", paymentRequest);

    assertNotNull(paidBasket);
    assertEquals(Status.SOLD, paidBasket.getStatus());
    assertEquals(PaymentMethod.CREDIT, paidBasket.getPaymentMethod());
    verify(repository, times(1)).save(any(Basket.class));
  }
}
