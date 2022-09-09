import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2115. Find All Possible Recipes from Given Supplies
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i],
 * and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e.,
 * ingredients[i] may contain a string that is in recipes.
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 * Note that two recipes may contain each other in their ingredients.
 *
 * Constraints:
 *
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 *
 * */
public class Solution {
    /**
     * My not working solution, I missed the point that recipe can depend on each other in circular manner.
     * So recipe graph is not a tree but rather graph with loops! IMPORTANT to clarify that on interviews
     *
     * simple recipe - contains only products mentioned in supplies
     * complex recipe - may contain simple recipes and products from supplies
     *
     * */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Boolean> canCookMemo = new HashMap<>();
        Map<String, List<String>> ingredientsMap = new HashMap<>();

        for (String supply : supplies) canCookMemo.put(supply, true);
        for (int i = 0; i < ingredients.size(); i ++) ingredientsMap.put(recipes[i], ingredients.get(i));

        List<String> resultRecipes = new ArrayList<>();
        for (String recipe : recipes) {
            if (canCook(recipe, canCookMemo, ingredientsMap)) {
                resultRecipes.add(recipe);
            }
        }

        return resultRecipes;
    }

    // current - current recipe we want to check. If we can cook it
    // canCookMemo - hashmap recipes we can cook or not
    public boolean canCook(String current,
                           Map<String, Boolean> canCookMemo,
                           Map<String, List<String>> recipeToIngredientsNeeded) {

        if (canCookMemo.containsKey(current))
            return canCookMemo.get(current);

        for (String ingredient : recipeToIngredientsNeeded.get(current)) {

            if (canCookMemo.containsKey(ingredient) && canCookMemo.get(ingredient)) {
                continue; // can cook the ingredient
            }

            if (canCookMemo.containsKey(ingredient) && !canCookMemo.get(ingredient)) {
                canCookMemo.put(current, false);
                return false;
            }

            if (!recipeToIngredientsNeeded.containsKey(ingredient)) {
                // not known ingredient
                canCookMemo.put(ingredient, false);
                canCookMemo.put(current, false);
                return false;
            }

            boolean canCookChildRecipe = canCook(ingredient, canCookMemo, recipeToIngredientsNeeded);

            if (!canCookChildRecipe) {
                canCookMemo.put(current, false);
                return false; // no need to look on other ingredients
            }
        }

        canCookMemo.put(current, true);
        return true;
    }
}
