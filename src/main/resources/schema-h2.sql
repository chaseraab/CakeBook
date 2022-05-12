DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Cookbooks;
DROP TABLE IF EXISTS Recipes;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Mealplans;
DROP TABLE IF EXISTS Users_Cookbooks;
DROP TABLE IF EXISTS Users_Recipes;
DROP TABLE IF EXISTS Users_Mealplans;
DROP TABLE IF EXISTS Cookbooks_Recipes;
DROP TABLE IF EXISTS Recipes_Ingredients;

CREATE TABLE Users (
    id INT AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    pword VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Users_Cookbooks(
    FOREIGN KEY (userId) REFERENCES Users(id),
    FOREIGN KEY (cookbookId) REFERENCES Cookbooks(id)
);

CREATE TABLE Users_Mealplans(
    FOREIGN KEY (userId) REFERNCES Users(id),
    FOREIGN KEY (meanplanId) REFERENCES Mealplans(id)
);

CREATE TABLE Users_Recipes(
    FOREIGN KEY (userId) REFERENCES Users(id),
    FOREIGN KEY (recipeId) REFERENCES Recipes(id)
);

CREATE TABLE Cookbooks(
    id INT AUTO_INCREMENT,
    favorite BOOLEAN,
    createdAt DATE NOT NULL,
    updatedAt DATE,
    PRIMARY KEY (id)
);

CREATE TABLE Cookbooks_Recipes(
    FOREIGN KEY (cookbookId) REFERENCES Cookbooks(id),
    FOREIGN KEY (recipeId) REFERENCES Recipes(id)
);

CREATE TABLE Mealplans(
    id INT AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    weekOf DATE,
    PRIMARY KEY (id)
);

create type measurements as enum('cup','tablespoon','teaspoon');

CREATE TABLE Ingredients(
    id INT AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    quantity FLOAT NOT NULL,
    measurement measurements,
    PRIMARY KEY (id)
);

CREATE TABLE Recipes(
    id INT AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    instructions text[],
    cookTime TIME,
    prepTime TIME,
    favorite BOOLEAN,
    public, BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE Recipes_Ingredients(
    FOREIGN KEY (recipeId) REFERENCES Recipes(id),
    FOREIGN KEY (ingredientId) REFERENCES Ingredients(id)
);