package tech.vascon.basketservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.vascon.basketservice.client.PlatziStoreClient;
import tech.vascon.basketservice.client.response.PlatziProductResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private PlatziStoreClient platziStoreClient;

  @InjectMocks
  private ProductService service;

  private PlatziProductResponse product1;
  private PlatziProductResponse product2;

  @BeforeEach
  void setUp() {
    product1 = new PlatziProductResponse(1L, "Product 1", BigDecimal.valueOf(10));
    product2 = new PlatziProductResponse(2L, "Product 2", BigDecimal.valueOf(15));
  }

  @Test
  void shouldReturnAllProduct() {
    when(platziStoreClient.findAll()).thenReturn(Arrays.asList(product1, product2));

    List<PlatziProductResponse> products = service.findAll();

    assertEquals(2, products.size());
    assertEquals(product1, products.get(0));
    verify(platziStoreClient, times(1)).findAll();
  }

  @Test
  void shouldReturnProductById() {
    when(platziStoreClient.findById(1L)).thenReturn(product1);

    PlatziProductResponse foundProduct = service.findById(1L);

    assertNotNull(foundProduct);
    assertEquals(1L, foundProduct.id());
    assertEquals("Product 1", foundProduct.title());
    verify(platziStoreClient, times(1)).findById(1L);
  }
}
