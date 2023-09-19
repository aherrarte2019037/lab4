/* Crear base de datos */
CREATE DATABASE RecipeApp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/* Crear tabla Users */
CREATE TABLE Users (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
)