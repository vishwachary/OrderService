Order-Service Microservices Project

This project demonstrates an end-to-end microservices workflow with synchronous and asynchronous communication using Feign client and Kafka.

Create an Order

Endpoint:

POST http://localhost:8081/api/v1/shop/orders
Content-Type: application/json

Request Body

{
  "orderTotal": 192000,
  "items": [
    {
      "productName": "headphone",
      "price": 182000.00,
      "quantity": 1
    },
    {
      "productName": "speakers",
      "price": 10000,
      "quantity": 2
    }
  ]
}
Description:

This creates a new order in the Orders table.

Each order contains a list of OrderItems with product details and quantity.


Synchronous Payment Processing

Once the order is created, the Order Service makes a synchronous call to the Payment Service using Feign Client:

@FeignClient(name = "payment-service", url = "http://localhost:8084")
public interface PaymentClient {
    @PostMapping("/api/v1/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
Payment Service Endpoint:
POST http://localhost:8084/api/v1/payments/process

rocesses the payment for the order.

Returns a PaymentResponse with orderId and payment status.

Asynchronous Kafka Event

The Payment Service publishes a Kafka message to notify other services about the payment status:
public void sendPaymentEvent(PaymentResponse response) {
    kafkaTemplate.send("payment-topic", response);
    System.out.println("Payment event published to Kafka: " + response);
}
Kafka Topic: payment-topic

This decouples payment processing from downstream services.

Ensures that services consuming payment events can scale independently.

Notification Service

The Notification Service listens to the Kafka topic and triggers notifications (currently prints a message to the console):

public class NotificationListener {

    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void handlePaymentEvent(PaymentResponse response) {
        System.out.println("Email sent: Order " + response.getOrderId() +
                " payment " + response.getStatus());
    }
}
Example Output:   Email sent: Order 5 payment SUCCESS

Architecture Overview

UI / Client
   │
   ▼
Order Service (POST /api/v1/shop/orders)
   │
   ├─> Feign Client (synchronous)
   ▼
Payment Service (POST /api/v1/payments/process)
   │
   ├─> Kafka Producer
   ▼
Kafka Topic: payment-topic
   │
   └─> Notification Service (Kafka Listener)
        └─> Sends Email Notification (Console for now)

        
Key Features

Synchronous communication: Feign client between Order Service and Payment Service.

Asynchronous communication: Kafka messaging between Payment Service and Notification Service.

Decoupled services: Notification Service can scale independently without impacting payment processing.

Database persistence: Orders and order items stored in a relational database.
