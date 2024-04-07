
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP WITH TIME ZONE,
    product_name VARCHAR(255) NOT NULL,
    product_description VARCHAR(2000),
    unit_price DECIMAL(19,2) NOT NULL,
    image_url VARCHAR(255),
    units_in_stock INT
);

TRUNCATE TABLE products RESTART IDENTITY;
