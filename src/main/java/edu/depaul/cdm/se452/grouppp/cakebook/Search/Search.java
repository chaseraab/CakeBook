package edu.depaul.cdm.se452.grouppp.cakebook.Search;

import java.security.Key;
import java.util.ArrayList;

import antlr.collections.List;
import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;
import edu.depaul.cdm.se452.grouppp.cakebook.User.User;

public abstract class Search {
    
    public static Recipe searchRecipeForName (Recipe recipe, ArrayList<String> values){
        String recipeName = recipe.getName().toLowerCase().replace(" ", "");
        for (String name : values) {
            String newName = name.toLowerCase().replace(" ", "");
            if ((newName.equals(recipeName)) || (recipeName.contains(name))) {
                return recipe;
            }
        }
        return null;
    }

    public static Recipe searchRecipeForAuthor (Recipe recipe, ArrayList<String> values){
        String recipeName = recipe.getAuthor().toLowerCase().replace(" ", "");
        for (String name : values) {
            String newName = name.toLowerCase().replace(" ", "");
            if ((newName.equals(recipeName)) || (recipeName.contains(name))) {
                return recipe;
            }
        }
        return null;
    }

    public static Recipe searchRecipeForIngredient (Recipe recipe, ArrayList<String> values){
        for (Ingredient i : recipe.getIngredients()) {
            String recipeIngredient = i.getName().toLowerCase().replace(" ", "");
            for (String name : values) {
                String newName = name.toLowerCase().replace(" ", "");
                if ((newName.equals(recipeIngredient)) || (recipeIngredient.contains(newName))) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static Recipe searchRecipeForCookTime (Recipe recipe, ArrayList<String> values){
        System.out.println("Entering cookTime");
        int recipeCT = recipe.getCookTime();
        System.out.println("Recipe CT: " + recipeCT);
        for (String i : values) {
            int newTime = Integer.parseInt(i);
            System.out.println("Comparing: " + recipeCT + " and " + newTime);
            if (newTime >= recipeCT) {
                System.out.println("returning the recipe");
                return recipe;
            }
        }
        System.out.println("returning nothing");
        return null;
    }

    public static Recipe searchRecipeForPrepTime (Recipe recipe, ArrayList<String> values){
        int recipeCT = recipe.getPrepTime();
        for (String i : values) {
            int newTime = Integer.parseInt(i);
            if (newTime >= recipeCT) {
                return recipe;
            }
        }
        return null;
    }

    public static ArrayList<KeyValue> parseSearchString(String searchString) {
        ArrayList<KeyValue> kvList = new ArrayList<>();
        String[] arr1 = searchString.split("&");
        for (int i = 0; i < arr1.length; i++) {
            String[] arr2 = arr1[i].split("=");
            if ((arr2.length & 1) == 1) {
                System.out.println("Bad input: " + searchString); return null;
            }
            for (int k = 0; k < arr2.length; k = k + 2) {
                int flag = 0;
                for (KeyValue j : kvList) {
                    if (j.key.equals(arr2[k])) {
                        if (!j.value.contains(arr2[k + 1])) {j.value.add(arr2[k+1]);}
                    }
                }
                if (flag == 0) {
                    KeyValue temp = new KeyValue(arr2[k], arr2[k+1]);
                    kvList.add(temp);
                }
            }
        }
        return kvList;
    }

    public static ArrayList<Recipe> searchByCriteria(ArrayList<KeyValue> kvList, ArrayList<Recipe> allRecipes) {
        ArrayList<ArrayList<Recipe>> searchResults = new ArrayList<>();
        for (KeyValue kv : kvList) {
            ArrayList<Recipe> tempList = new ArrayList<>();
            for (Recipe r : allRecipes) {
                Recipe temp = null;
                switch (kv.key) {
                    case "name":
                        temp = searchRecipeForName(r, kv.value);
                        if (temp != null) {
                        tempList.add(temp);
                        temp = null;
                         }
                        break;
                    case "cookTime":
                        System.out.println("CookTime case");
                        temp = searchRecipeForCookTime(r, kv.value);
                        if (temp != null) {
                            System.out.println("Adding the recipe");
                            tempList.add(temp);
                            temp = null;
                        }
                        break;
                    case "prepTime":
                        temp = searchRecipeForPrepTime(r, kv.value);
                        if (temp != null) {
                            tempList.add(temp);
                            temp = null;
                        }
                        break;
                    case "author":
                        temp = searchRecipeForAuthor(r, kv.value);
                        if (temp != null) {
                            tempList.add(temp);
                            temp = null;
                        }
                        break;
                    case "ingredient":
                        temp = searchRecipeForIngredient(r, kv.value);
                        if (temp != null) {
                            tempList.add(temp);
                            temp = null;
                        }
                        break;
                }
            }
            if (tempList.size() > 0) {searchResults.add(tempList);}
        }
        if (searchResults.size() > 1) {
            while (searchResults.size() != 1) {
                ArrayList<ArrayList<Recipe>> tempOuter = new ArrayList<>();
                for (int i = 0; i < searchResults.size() - 1; i++) {
                    ArrayList<Recipe> tempInner = new ArrayList<>();
                    for (int j = 0; j < searchResults.get(i).size(); j++) {
                        if (searchResults.get(i + 1).contains(searchResults.get(i).get(j))) {
                            tempInner.add(searchResults.get(i).get(j));
                        }
                    }
                    tempOuter.add(tempInner);
                }
                searchResults = tempOuter;
            }
        }
        return searchResults.get(0);
    }

}
