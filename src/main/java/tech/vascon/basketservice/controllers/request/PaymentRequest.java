package tech.vascon.basketservice.controllers.request;

import lombok.Getter;
import lombok.Setter;
import tech.vascon.basketservice.entity.PaymentMethod;

@Getter
@Setter
public class PaymentRequest {
  private PaymentMethod paymentMethod;
}
