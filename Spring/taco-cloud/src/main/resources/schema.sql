CREATE TABLE IF NOT EXISTS Taco_Order (
  id IDENTITY ,
  delivery_name VARCHAR (50) NOT NULL,
  delivery_street VARCHAR(50) NOT NULL,
  delivery_city VARCHAR(50) NOT NULL,
  delivery_state VARCHAR(2) NOT NULL,
  delivery_zip VARCHAR(10) NOT NULL,
  cc_number VARCHAR(16) NOT NULL,
  cc_expiration VARCHAR(5) NOT NULL,
  cc_cvv VARCHAR(3) NOT NULL,
  placed_at TIMESTAMP NOT NULL,
  user VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS Taco (
  id identity,
  name VARCHAR(50) NOT NULL,
  taco_order BIGINT NOT NULL,
  taco_order_key BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
  ingredient VARCHAR(4) NOT NULL,
  taco BIGINT NOT NULL,
  taco_key BIGINT NOT NULL
);


CREATE TABLE IF NOT EXISTS Ingredient (
  id VARCHAR(4) NOT NULL,
  name VARCHAR(25) NOT NULL,
  type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS User (
  id IDENTITY,
  username VARCHAR(25) NOT NULL,
  password VARCHAR(120) NOT NULL,
  full_name VARCHAR(25) NOT NULL,
  street VARCHAR(25) NOT NULL,
  city VARCHAR(25) NOT NULL,
  state VARCHAR(25) NOT NULL,
  zip VARCHAR(25) NOT NULL,
  phone_number VARCHAR(25) NOT NULL
);

ALTER TABLE Taco
    ADD FOREIGN KEY (taco_order) REFERENCES Taco_Order(id);
ALTER TABLE Ingredient_Ref
    ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);
