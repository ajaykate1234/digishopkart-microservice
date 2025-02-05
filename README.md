# digishopkart-microservice
- This is an E-commerce java spring boot application
- added several microservices and communcated with each other using feignClient
- added Registry-service to register all client-services into eureka-server
- addded to loadbalancer to balance the service instances load
- added API-Gateway service to communicate client with all microservices


# customer-service
- All CRUD APIs for table manipulation
- Added User entity and LoginController to login and registration endpoints
- Added OAuth 2.0 for LoginWithGoogle feature to Login user

# product-service
- All CRUD APIs for table manipulation
- Custom exception handler added
- JWT security added in product-service
- Quartz Scheduler added (feature independent to service)
- Kart Entity added to add product Items

# discount-service
- All CRUD APIs for table manipulation
  
# order-service
- All CRUD APIs for table manipulation
- FeignClient added to communicate with other services
- load balancer added in order-service while calling external endpoints using feignClient

# payment-service
- added independent microservice for payment API integration (not working)
- Added create-order and verify-payment endpoints with working credentials 

  
# ApiGateway-service
- piGateway service to communicate with all microservices with a single post

  
# Registry-service
- all Eureka clients(services) services with Registry-service

# Subscription-service

# digiShopKart-frontEnd
- Added UI for all services with nav-bar options
- Added searchQuery to find product by name or brand
- Added pages for Login, Registration and various components
