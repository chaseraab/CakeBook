
DROP TABLE IF EXISTS Users_Cookbooks;
DROP TABLE IF EXISTS Users_Recipes;
DROP TABLE IF EXISTS Users_Mealplans;
DROP TABLE IF EXISTS Cookbooks_Recipes;
DROP TABLE IF EXISTS Recipes_Ingredients;
DROP TABLE IF EXISTS Recipes_Instructions;
DROP TABLE IF EXISTS Mealplans;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Cookbooks;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Instructions;
DROP TABLE IF EXISTS Recipes;

create type measurements as enum('cup','tablespoon','teaspoon');

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    pword VARCHAR(50) NOT NULL
);
CREATE TABLE Cookbooks(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    favorite BOOLEAN
);
CREATE TABLE Mealplans(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    weekOf DATE
);
CREATE TABLE Ingredients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    quantity FLOAT NOT NULL,
    measurement measurements 
);
CREATE TABLE Recipes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    cookTime TIME,
    prepTime TIME,
    favorite BOOLEAN,
    isPublic BOOLEAN
);
CREATE TABLE Instructions(
    id SERIAL PRIMARY KEY,
    recipeId INT REFERENCES Recipes(id),
    instruction VARCHAR(50)
);

CREATE TABLE Users_Cookbooks(
    user_id INT NOT NULL,
    cookbook_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (cookbook_id) REFERENCES Cookbooks(id)
);
/*
CREATE TABLE Users_Mealplans(
    userId INT REFERENCES Users(id),
    mealplanId INT REFERENCES Mealplans(id)
);
CREATE TABLE Users_Recipes(
    userId INT REFERENCES Users(id),
    recipeId INT REFERENCES Recipes(id)
);
CREATE TABLE Cookbooks_Recipes(
    cookbookId INT REFERENCES Cookbooks(id),
    recipeId INT REFERENCES Recipes(id)
);
CREATE TABLE Recipes_Ingredients(
    recipeId INT REFERENCES Recipes(id),
    ingredientId INT REFERENCES Ingredients(id)
);
*/