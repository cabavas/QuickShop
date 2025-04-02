package tech.vascon.basketservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vascon.basketservice.client.response.PlatziProductResponse;
import tech.vascon.basketservice.entity.Product;
import tech.vascon.basketservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Endpoints para gerenciamento de produtos")
public class ProductController {

  private final ProductService service;

  @Operation(summary = "Buscar todos os produtos", description = "Retorna uma lista de todos os produtos disponíveis.")
  @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso",
  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
  @GetMapping
  public ResponseEntity<List<PlatziProductResponse>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @Operation(summary = "Buscar um produto por ID", description = "Retorna um produto pelo ID.")
  @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
    content = @Content(schema = @Schema(implementation = Product.class)))
  @ApiResponse(responseCode = "404", description = "Produto não encontrado",
  content = @Content())
  @GetMapping("/{id}")
  public ResponseEntity<PlatziProductResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }
}
