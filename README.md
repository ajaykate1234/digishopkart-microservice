# digishopkart-microservice
<<<<<<< HEAD
- This is an e-commerce Java spring boot application
- added several microservices and communicated with each other using feignClient
=======
- This is an E-commerce java spring boot application
- added several microservices and communcated with each other using feignClient
>>>>>>> ad47ad6e468dc2ea33727c96d81d40b7bf80d2bb
- added Registry-service to register all client-services into eureka-server
- added to load balancer to balance the service instances load
- added API-Gateway service to communicate clients with all microservices


# customer-service
- All CRUD APIs for table manipulation
<<<<<<< HEAD
- 
=======
>>>>>>> ad47ad6e468dc2ea33727c96d81d40b7bf80d2bb
# product-service
- All CRUD APIs for table manipulation
- Custom exception handler added
- JWT security added in product-service
- Quartz Scheduler added (feature independent to service)
- Kart Entity added to add product Items
<<<<<<< HEAD


# discount-service
- All CRUD APIs for table manipulation
- 
=======

# discount-service
- All CRUD APIs for table manipulation
  
>>>>>>> ad47ad6e468dc2ea33727c96d81d40b7bf80d2bb
# order-service
- All CRUD APIs for table manipulation
- FeignClient added to communicate with other services
- load balancer added in order-service while calling external endpoints using feignClient
<<<<<<< HEAD
  
# payment-service

# ApiGateway-service
- ApiGateway service to communicate with all microservices with single post
- 
# Registry-service
- all Eureka clients(services) services with Registry-service
- 
# Subscription-service

# digiShopKart-frontEnd
=======
>>>>>>> ad47ad6e468dc2ea33727c96d81d40b7bf80d2bb

# payment-service
- added independent microservice for payment API integration (not working)

  
# ApiGateway-service
- piGateway service to communicate with all microservices with a single post

  
# Registry-service
- all Eureka clients(services) services with Registry-service

# Subscription-service

# digiShopKart-frontEnd
- Added UI for all services with nav-bar options
- Added searchQuery to find product by name or brand
