# E-Commerce REST API

A robust backend REST API built with **Spring Boot** supporting secure user authentication, role-based authorization, comprehensive product management with image uploads, and an order processing system.

## Running with Docker

To run the application using Docker, follow these steps:

1. Download the source files.
2. Open your terminal in the project root directory and run:
```bash
docker compose up
```
## Features

### Authentication & Authorization (`/auth`)

* **Register**: Creates a new standard user account (`ROLE_USER`) with encrypted passwords.


* **Login**: Authenticates credentials and returns a secure JWT token for subsequent requests.



### Product Management (`/products`)

* **View Products**: Public endpoints to browse all catalog items or inspect a specific product.


* **Product Images)**: Fetch associated image metadata or retrieve image binary content.


* **Create Product**: Admin-only endpoint supporting `multipart/form-data` to upload product details alongside multiple image files.


* **Update Product**: Admin-only endpoint to modify product attributes.


* **Delete Product**: Admin-only endpoint that removes products and cleans up associated stored image files.



### Order System (`/order`)

* **Buy Now**: User-restricted endpoint that validates stock levels, automatically updates inventory, calculates totals, and logs the purchase.


* **User Orders**: Retrieves the order history for the currently authenticated user.


* **Delete Order**: Admin-only endpoint to remove an existing order record.

