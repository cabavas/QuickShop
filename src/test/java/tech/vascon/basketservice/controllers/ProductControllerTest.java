package tech.vascon.basketservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tech.vascon.basketservice.client.response.PlatziProductResponse;
import tech.vascon.basketservice.services.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  @Mock
  private ProductService service;

  @InjectMocks
  private ProductController controller;

  private PlatziProductResponse product1;
  private PlatziProductResponse product2;

  @BeforeEach
  void setUp() {
    product1 = new PlatziProductResponse(1L, "Product 1", BigDecimal.valueOf(10));
    product2 = new PlatziProductResponse(1L, "Product 2", BigDecimal.valueOf(15));
  }

  @Test
  void shouldReturnAllProducts() {
    when(service.findAll()).thenReturn(Arrays.asList(product1, product2));

    ResponseEntity<List<PlatziProductResponse>> response = controller.findAll();

    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
    assertEquals("Product 1", response.getBody().get(0).title());
    verify(service, times(1)).findAll();
  }

  @Test
  void shouldReturnProductById() {
    when(service.findById(1L)).thenReturn(product1);

    ResponseEntity<PlatziProductResponse> response = controller.findById(1L);

    assertNotNull(response.getBody());
    assertEquals(1L, response.getBody().id());
    assertEquals("Product 1", response.getBody().title());
    verify(service, times(1)).findById(1L);
  }
}
