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
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    pword VARCHAR(50) NOT NULL
);

CREATE TABLE Users_Cookbooks(
    userId INT REFERENCES Users(id),
    cookbookId INT REFERENCES Cookbooks(id)

);

CREATE TABLE Users_Mealplans(
    userId INT REFERENCES Users(id),
    mealplanId INT REFERNECES Mealplans(id)
);

CREATE TABLE Users_Recipes(
    userId INT REFERENCES Users(id),
    recipeId INT REFERENCES Recipes(id)
);

CREATE TABLE Cookbooks(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    favorite BOOLEAN,
    createdAt DATE NOT NULL,
    updatedAt DATE
);

CREATE TABLE Cookbooks_Recipes(
    cookbookId INT REFERENCES Cookbooks(id),
    recipeId INT REFERENCES Recipes(id)
);

CREATE TABLE Mealplans(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    weekOf DATE
);

create type measurements as enum('cup','tablespoon','teaspoon');

CREATE TABLE Ingredients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    quantity FLOAT NOT NULL,
    measurement measurements 
);

CREATE TABLE Recipes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    instructions text[],
    cookTime TIME,
    prepTime TIME,
    favorite BOOLEAN,
    public, BOOLEAN
);

CREATE TABLE Recipes_Ingredients(
    recipeId INT REFERENCES Recipes(id),
    ingredientId INT REFERENCES Ingredients(id)
);




