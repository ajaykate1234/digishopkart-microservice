# digishopkart-microservice
- This is an e-commerce Java spring boot application
- added several microservices and communicated with each other using feignClient
- added Registry-service to register all client-services into eureka-server
- added to load balancer to balance the service instances load
- added API-Gateway service to communicate clients with all microservices


# customer-service
- All CRUD APIs for table manipulation
- 
# product-service
- All CRUD APIs for table manipulation
- Custom exception handler added
- JWT security added in product-service
- Quartz Scheduler added (feature independent to service)
- Kart Entity added to add product Items


# discount-service
- All CRUD APIs for table manipulation
- 
# order-service
- All CRUD APIs for table manipulation
- FeignClient added to communicate with other services
- load balancer added in order-service while calling external endpoints using feignClient
  
# payment-service

# ApiGateway-service
- ApiGateway service to communicate with all microservices with single post
- 
# Registry-service
- all Eureka clients(services) services with Registry-service
- 
# Subscription-service

# digiShopKart-frontEnd

