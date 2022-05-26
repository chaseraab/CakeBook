
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
    favorite BOOLEAN,
    created_at DATE NOT NULL,
    updated_at DATE
);
CREATE TABLE Mealplans(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    weekOf DATE
);
CREATE TABLE Recipes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    cook_time VARCHAR(50),
    prep_time VARCHAR(50),
    favorite BOOLEAN,
    is_public BOOLEAN,
    author VARCHAR(50)
);
CREATE TABLE Ingredients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    quantity FLOAT NOT NULL,
    measurement measurements,
    recipe_id bigint REFERENCES Recipes(id)
);
CREATE TABLE Instructions(
    id SERIAL PRIMARY KEY,
    instruction VARCHAR(50),
    recipe_id bigint REFERENCES Recipes(id)
);
CREATE TABLE Users_Cookbooks(
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id),
    cookbook_id INT REFERENCES Cookbooks(id)

);
CREATE TABLE Users_Mealplans(
    id SERIAL PRIMARY KEY,
    userId INT REFERENCES Users(id),
    mealplanId INT REFERENCES Mealplans(id)
);
CREATE TABLE Users_Recipes(
    id SERIAL PRIMARY KEY,
    userId INT REFERENCES Users(id),
    recipeId INT REFERENCES Recipes(id)
);
CREATE TABLE Cookbooks_Recipes(
    id SERIAL PRIMARY KEY,
    cookbook_id INT REFERENCES Cookbooks(id),
    recipe_id INT REFERENCES Recipes(id)
);
CREATE TABLE Recipes_Instructions(
    id SERIAL PRIMARY KEY,
    instruction_id INT REFERENCES Instructions(id),
    recipe_id INT REFERENCES Recipes(id)
);