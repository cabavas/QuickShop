package tech.vascon.basketservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import tech.vascon.basketservice.controllers.request.BasketRequest;
import tech.vascon.basketservice.controllers.request.PaymentRequest;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.services.BasketService;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Tag(name = "Basket", description = "Recurso responsável pelo gerenciamento do carrinho de compras.")
public class BasketController {

  private final BasketService service;

  @Operation(summary = "Buscar todos os carrinhos", description = "Método responsável por buscar todos os carrinhos criados.")
  @ApiResponse(responseCode = "200", description = "Retorna todos os carrinhos criados",
  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Basket.class))))
  @GetMapping
  public ResponseEntity<List<Basket>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @Operation(summary = "Buscar carrinho por id", description = "Método responsável por buscar um carrinho pelo id dele.")
  @ApiResponse(responseCode = "200", description = "Carrinho encontrado",
    content = @Content(schema = @Schema(implementation = Basket.class)))
  @ApiResponse(responseCode = "404", description = "Carrinho não encontrado",
  content = @Content())
  @GetMapping("/{id}")
  public ResponseEntity<Basket> findById(@PathVariable String id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @Operation(summary = "Cria carrinho", description = "Método responsável por criar um carrinho")
  @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso.",
  content = @Content(schema = @Schema(implementation = Basket.class)))
  @PostMapping
  public ResponseEntity<Basket> create(@RequestBody BasketRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
  }

  @Operation(summary = "Altera carrinho", description = "Método responsável por alterar um carrinho")
  @ApiResponse(responseCode = "200", description = "Carrinho alterado com sucesso.",
    content = @Content(schema = @Schema(implementation = Basket.class)))
  @PutMapping("/{id}")
  public ResponseEntity<Basket> update(@RequestBody BasketRequest request, @PathVariable String id) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, request));
  }

  @Operation(summary = "Fecha carrinho", description = "Método responsável por adicionar o método de pagamento e alterar o status")
  @ApiResponse(responseCode = "200", description = "Carrinho alterado com sucesso.",
    content = @Content(schema = @Schema(implementation = Basket.class)))
  @PutMapping("/{id}/payment")
  public ResponseEntity<Basket> payment(@RequestBody PaymentRequest request, @PathVariable String id) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.payment(id, request));
  }

  @Operation(summary = "Deleta carrinho", description = "Método responsável por deletar um carrinho")
  @ApiResponse(responseCode = "204", description = "Carrinho deletado com sucesso.",
    content = @Content())
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
