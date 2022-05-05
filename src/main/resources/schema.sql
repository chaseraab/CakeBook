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

CREATE TABLE Cookbooks(
    id SERIAL PRIMARY KEY,
    favorite BOOLEAN,
    createdAt DATE NOT NULL,
    updatedAt DATE
);

CREATE TABLE Mealplans(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    weekOf DATE
);

create type measurements as enum('cup','tablespoon','teaspoon');

CREATE TABLE Ingredients(
    name VARCHAR(50) UNIQUE NOT NULL,
    quantity FLOAT NOT NULL,
    measurement measurements 
);

CREATE TABLE Recipes(
    name VARCHAR(50) UNIQUE NOT NULL,
    cookTime TIME,
    prepTime TIME,
    favorite BOOLEAN,
    public, BOOLEAN
);




