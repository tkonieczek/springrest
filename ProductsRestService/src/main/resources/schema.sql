create table products (
      id INT NOT NULL AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      description VARCHAR(300) NOT NULL,
      image VARCHAR(100) NOT NULL,
      price DECIMAL(20, 2) NOT NULL,
      PRIMARY KEY (id)
  );
