package tech.vascon.basketservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.vascon.basketservice.entity.Basket;
import tech.vascon.basketservice.entity.Status;

import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {

    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
