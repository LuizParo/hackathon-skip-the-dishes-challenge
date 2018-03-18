

This is the project develped during the hackathon with SkipTheDishes.

It simulates a microservices architecture and uses mainly the following technologies:

<ul>
    <li>Spring Boot</li>
    <li>Spring Cloud</li>
</ul>

Find below a short description about each service:

<b>service-discovery</b>

This is the service discovery where all services register themselves. All applications uses fetch the URLs from other services from here.

<b>api-gateway</b>

This is the gateway that proxes all the requests to the underliyng services. The only thing it does now is delegate the calls the these services, but the intention here was to trace all the requests and log them, so we could trace where a fail would have happened, when it happens (probably would use Zipkin here). Also, the authenticathion would be placed here, delegating the credentials to the calls to underliyng services.

<b>customer-service</b>

This is where the customer related operations are centralized, it uses its own database. The intention here was to cache the results avoing unnecessary hits on database.

<b>product-service</b>

This is where the products related operations are centralized, it uses its own database. The intention here was to cache the results avoing unnecessary hits on database.

<b>order-service</b>

This is where the orders related operations are centralized, it uses its own database. It makes calls to customer-service and product-service thru a combination of Ribbon (client-side load-balancer) and Feign.