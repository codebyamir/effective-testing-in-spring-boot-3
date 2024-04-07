CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP WITH TIME ZONE,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(19,2) NOT NULL,
    description VARCHAR(2000),
    image_url VARCHAR(255),
    units_in_stock INT
);
