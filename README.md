# spring-boot-microservices

## Services:
* Product Service
* Order Service
* Inventory Service
* Notification Service

  Order, Inventory and Notification services interact with each other.
# Architectures
## Solution Architecture
<img width="1444" alt="image" src="https://github.com/Harshak777/spring-boot-microservices/assets/33751325/f024b1e0-83ae-412a-b62f-63ff382bae8e">

## Services Architecture
<img width="943" alt="image" src="https://github.com/Harshak777/spring-boot-microservices/assets/33751325/7a1f9f21-e93d-4897-aeca-823d1a797058">

## Get initial project data files [spring intilizr](https://start.spring.io)

## Databses used
* MongoDB - Product Service
* MySQL - Order & Inventory Services

## Inter Process Communication
### Available clients - RestTemplate(in maintainace mode), WebClient(recommended by SpringBoot)
* Synchronous communication: Inventory Service - Order Service
### Service Discovery

<img width="475" alt="image" src="https://github.com/Harshak777/spring-boot-microservices/assets/33751325/c0672892-6e77-4944-9f5d-5c792b153ccd"> ----- <img width="475" alt="image" src="https://github.com/Harshak777/spring-boot-microservices/assets/33751325/e8a5380f-dcc7-45ef-b390-efa4bac407b0">

<b>Note:</b>
The Discovery Server will also send back a local registery after the first request, just as a backup if the DS is busy/unavailable.
