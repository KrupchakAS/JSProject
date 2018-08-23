SET FOREIGN_KEY_CHECKS=0;
-- Table: users
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  surName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(10) NOT NULL,
  email VARCHAR(255) NOT NULL,
  birthDate DATE
)ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  UNIQUE (user_id, role_id)
) ENGINE = InnoDB;
-- Insert data
INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG','Андрей','Крупчак','9650024321','krupchakas@yandex.ru','1989/02/23');
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');
INSERT INTO user_roles VALUES (1, 1);