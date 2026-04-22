# BackendCapstoneProject

Tracking Orders (Consumer)

1. Consumer Login:
○ Consumers log in to their accounts to view their order history and track
deliveries.

2. Order Tracking:
○ Consumers can monitor their orders, including real-time location tracking and
estimated delivery times.
○ The system sends updates via SMS or email regarding order status.

3. Order History:
○ Consumers can view past orders along with detailed delivery information.

4. Alerts and Notiﬁcations:

○ Weather-related delays and disruptions are communicated to users through
SMS or email alerts, ensuring smooth management of the supply chain.

Database Architecture
Tables Overview

1. Users Table

○ Description: Stores information about users, including their role (farmer, retailer,
consumer), authentication data, and personal details.

○ Columns:

■ user_id (PRIMARY KEY, AUTO_INCREMENT)

■ username (VARCHAR)

■ password (VARCHAR)

■ email (VARCHAR, UNIQUE)

■ role (ENUM('FARMER', 'RETAILER', 'CONSUMER'))

■ contact_info (VARCHAR)

■ created_at (TIMESTAMP)

■ updated_at (TIMESTAMP)

3. Produce Table

○ Description: Contains information about the produce listed by farmers, including
price, quantity, and delivery options.

○ Columns:

■ produce_id (PRIMARY KEY, AUTO_INCREMENT)

■ farmer_id (FOREIGN KEY referencing Users Table)

■ produce_name (VARCHAR)

■ price (DECIMAL)

■ quantity (INTEGER)

■ delivery_options (VARCHAR)

■ created_at (TIMESTAMP)

■ updated_at (TIMESTAMP)

5. Orders Table

○ Description: Manages orders placed by retailers, tracking order status and
payment details.

○ Columns:

■ order_id (PRIMARY KEY, AUTO_INCREMENT)

■ retailer_id (FOREIGN KEY referencing Users Table)

■ produce_id (FOREIGN KEY referencing Produce Table)

■ quantity_ordered (INTEGER)

■ total_price (DECIMAL)

■ status (ENUM('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED',
'CANCELLED'))

■ payment_status (ENUM('PAID', 'UNPAID'))

■ created_at (TIMESTAMP)

■ updated_at (TIMESTAMP)

7. Inventory Table

○ Description: Tracks the available stock of produce listed by farmers. Updates in
real-time based on orders.

○ Columns:

■ inventory_id (PRIMARY KEY, AUTO_INCREMENT)

■ produce_id (FOREIGN KEY referencing Produce Table)

■ stock_level (INTEGER)

■ last_updated (TIMESTAMP)

9. Delivery Table

○ Description: Manages logistics and delivery tracking, including shipment status
and geolocation.

○ Columns:

■ delivery_id (PRIMARY KEY, AUTO_INCREMENT)

■ order_id (FOREIGN KEY referencing Orders Table)

■ delivery_address (VARCHAR)

■ delivery_status (ENUM('IN_PROGRESS', 'OUT_FOR_DELIVERY',
'DELIVERED', 'FAILED'))

■ tracking_number (VARCHAR)

■ current_location (VARCHAR)

■ delivery_time_estimate (TIMESTAMP)

■ created_at (TIMESTAMP)

■ updated_at (TIMESTAMP)

Relationships

1. Users → Produce:

○ One-to-Many relationship between Users (farmers) and Produce.

3. Users → Orders:

○ One-to-Many relationship between Users (retailers) and Orders.

5. Orders → Delivery:

○ One-to-One relationship between Orders and Delivery, allowing tracking of
shipments for each order.

7. Inventory → Produce:

○ One-to-One relationship between Inventory and Produce for real-time stock
management.
Key Features of Database Architecture

● Real-time Updates: Ensures immediate synchronization of inventory, orders, and
deliveries.

● Role-Based Access Control: Securely manages access for farmers, retailers, and
consumers based on roles.

● Integration with APIs: Weather data and geolocation features enhance logistics and
delivery tracking.

● Efficient Relationships: Optimizes the management of supply chain entities and their
interdependencies.

Tool Chain
Competency Skill Skill Detail
Programming Languages Application Language Java
Products & Frameworks Presentation Console Application
Database & Storage MySQL
JDBC
Governance & Tooling Git
Business Requirements

1. User Registration and Authentication
Requirement:
As a user, I should be able to register and log in to the system based on my role (farmer,
retailer, consumer).
Acceptance Criteria:

● A user must provide a unique username and email during registration.

● After registration, the system must validate user credentials through email veriﬁcation
or OTP (One-Time Password).

● A logged-in user must be able to reset their password securely.

● Users should be able to log out of the system.

● The system must authenticate users based on their role and provide role-speciﬁc
functionalities (e.g., farmers can list produce, retailers can place orders, consumers can
track orders).

3. Produce Listing (Farmer)
Requirement:
As a farmer, I should be able to list my available produce, including price, quantity, and delivery
details.
Acceptance Criteria:

● Farmers must enter details such as produce name, price per unit, quantity, and delivery
options.

● The system should automatically update inventory levels in real-time based on the
availability and orders placed by retailers or consumers.

● Farmers should be able to edit or remove produce listings at any time.

● Produce listings should be categorized by type (e.g., fruits, vegetables, dairy) for easier
navigation and search.

5. Placing Orders (Retailer)
Requirement:
As a retailer, I should be able to browse listed produce and place orders for the products I need.
Acceptance Criteria:

● Retailers should be able to select produce from available listings and place orders.

● Orders should trigger automatic updates to the inventory and payment processing.

● Retailers must receive order conﬁrmation once the order is placed.

● The system should provide real-time stock levels to avoid over-ordering.

7. Tracking Orders (Consumer)
Requirement:
As a consumer, I should be able to track the status of my orders, including location tracking
and estimated delivery times.
Acceptance Criteria:

● Consumers should receive real-time updates on the status of their orders (e.g.,
conﬁrmed, shipped, delivered).

● The system must provide location tracking of deliveries, integrated with Google Maps
API, to show the current location and estimated delivery times.

● Consumers must be able to view their order history and access detailed delivery
information, including receipts and payment status.

9. Inventory Management
Requirement:
The system should provide real-time updates of inventory at various supply chain points.
Acceptance Criteria:

● The inventory must automatically deplete when orders are placed or produce is
delivered.

● Farmers and retailers should receive alerts for low stock or stock depletion.

● The system must handle backorder scenarios when stock levels are temporarily
unavailable.

10. Order Notiﬁcations
Requirement:
The system must send order conﬁrmation, shipment, and delivery notiﬁcations via email or
SMS.
Acceptance Criteria:

● Notiﬁcations should be sent instantly once an order is placed, shipped, or delivered.

● Users should be able to customize notiﬁcation preferences for order status updates.

11. Role-Based Access Control
Requirement:
The system should have role-based access control to provide distinct functionalities for
farmers, retailers, and consumers.
Acceptance Criteria:

● Each role (farmer, retailer, consumer) must have distinct permissions for accessing
relevant data and performing actions.

● The system must prevent unauthorized access to sensitive data or actions outside the
assigned roles.

Source Code: Core Java Code
Database Schema: Diagrams and structure for the MySQL database tables.

