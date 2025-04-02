package tech.vascon.basketservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.vascon.basketservice.client.PlatziStoreClient;
import tech.vascon.basketservice.client.response.PlatziProductResponse;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> findAll() {
        log.info("Finding all products");
        return platziStoreClient.findAll();
    }

    @Cacheable(value = "product", key = "#id")
    public PlatziProductResponse findById(Long id) {
        log.info("Finding product with id: {}", id);
        return platziStoreClient.findById(id);
    }

}
