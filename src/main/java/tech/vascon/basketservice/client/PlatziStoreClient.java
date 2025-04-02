package tech.vascon.basketservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.vascon.basketservice.client.response.PlatziProductResponse;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> findAll();

    @GetMapping("/products/{id}")
    PlatziProductResponse findById(@PathVariable Long id);
}
