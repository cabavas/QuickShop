package tech.vascon.basketservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vascon.basketservice.controllers.request.BasketRequest;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.services.BasketService;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService service;

    @GetMapping
    public ResponseEntity<List<Basket>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

  @GetMapping("/{id}")
  public ResponseEntity<Basket> findById(@PathVariable String id) {
    return ResponseEntity.ok(service.findById(id));
  }

    @PostMapping
    public ResponseEntity<Basket> create(@RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

  @PutMapping("/{id}")
  public ResponseEntity<Basket> update(@RequestBody BasketRequest request, @PathVariable String id) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, request));
  }
}
