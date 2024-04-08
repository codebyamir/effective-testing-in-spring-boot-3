-- Products table
DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP WITH TIME ZONE,
    product_name VARCHAR(255) NOT NULL,
    product_description VARCHAR(2000),
    unit_price DECIMAL(19,2) NOT NULL,
    image_url VARCHAR(255),
    units_in_stock INT
);

-- Users Table
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash CHAR(64) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Carts Table
DROP TABLE IF EXISTS carts CASCADE;

CREATE TABLE IF NOT EXISTS carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Cart_Items Table

DROP TABLE IF EXISTS cart_items CASCADE;

CREATE TABLE cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT,
  --  added_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Orders Table
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    cart_id INT,
    total_price DECIMAL(10, 2),
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (cart_id) REFERENCES carts(id)
);
