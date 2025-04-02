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

    @PostMapping
    public ResponseEntity<Basket> create(@RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
}
