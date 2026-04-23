# BackendCapstoneProject

Problem Statement:
The agricultural supply chain often faces inefficiencies and waste, from overproduction to
food spoilage. Farmers and consumers are often disconnected, which leads to food
shortages, overpricing, and significant waste. There is a critical need for a real-time system
that connects local farmers directly to consumers and retailers, enabling better
optimization of the supply chain, reducing waste, and ensuring fair pricing for all stakeholders.

Overview
This project is a Spring Boot-based backend application demonstrating REST APIs, layered
architecture, and database integration.

Features
MicroServices, RESTful APIs- Layered Architecture- CRUD, Operations- Exception Handling- Database Integration

Tech Stack
Java, Spring Boot, Maven, JPA/Hibernate, MySQL

Architecture
Client
Spring Security 
Gateway
Controller/FeignClient→DTO→ Service → Repository → Database

Setup Instructions
1. git clone https://github.com/jabdulla1/BackendCapstoneProject.git
2.all microservices
- Run 'mvn clean install'
- Run 'mvn spring-boot:run'


API Endpoints

InventoryTracking
GET /stockapi/resources
GET /stockapi/all
GET /stockapi/id/{id}
GET /stockapi/sku/{sku}
GET /stockapi/alerts

POST /stockapi/add
POST /stockapi/decrease/sku/{sku}
POST /stockapi/increase/sku/{sku}

PUT /stockapi/id/{id}
PUT /stockapi/sku/{sku}

DELETE /stockapi/id/{id}
DELETE /stockapi/sku/{sku}




OrderManager
GET /alerts/
GET /orders
GET /orders/{orderId}

POST /orders/place

PUT /orders/{orderId}

DELETE /orders/{orderId}

ProductManager
GET /api/v1/inventory/products/all
GET /api/v1/inventory/products/{id}
GET /api/v1/inventory/products/sku/{sku}
GET /api/v1/inventory/alerts

POST /api/v1/inventory/products/add
POST /api/v1/inventory/orders/place

PUT /api/v1/inventory/products/update/{sku}

DELETE /api/v1/inventory/products/{id}
DELETE /api/v1/inventory/products/sku/{sku}

Supplier
GET /supplier
GET /supplier/id/{id}
GET /supplier/name/{name}

POST /supplier/insert

PUT /supplier/update/{id}

DELETE /supplier/delete/{id}

UserAuth
GET /security/validate

POST /security/register
POST /security/login

Author
Jihad I Abdul-Laitf
