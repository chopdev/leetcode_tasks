import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * My solution. A bit changed from version 1, includes loops detections in dependency graph (Topological Sort).
 * Idea inspired here: https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/discuss/1646597/Topological-Sort-or-DFS-or-Java
 *
 * R - count of recipes
 * S - count of supplies
 * O(R + S) - space and time
 * */
public class Solution2 {

    public enum Status {
        NOT_VISITED,
        VISITING,
        CAN_COOK
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Status> canCookMemo = new HashMap<>();
        Map<String, List<String>> ingredientsMap = new HashMap<>();

        for (String recipe : recipes) canCookMemo.put(recipe, Status.NOT_VISITED);
        for (String supply : supplies) canCookMemo.put(supply, Status.CAN_COOK);
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
                           Map<String, Status> canCookMemo,
                           Map<String, List<String>> recipeToIngredientsNeeded) {

        if (!canCookMemo.containsKey(current))
            return false; // not known recipe or product

        if (canCookMemo.get(current) == Status.CAN_COOK)
            return true;

        if (canCookMemo.get(current) == Status.VISITING)
            return false; // circular dependency on recipes


        canCookMemo.put(current, Status.VISITING);
        for (String ingredient : recipeToIngredientsNeeded.get(current)) {
            boolean canCookChildRecipe = canCook(ingredient, canCookMemo, recipeToIngredientsNeeded);
            if (!canCookChildRecipe) return false;
        }

        canCookMemo.put(current, Status.CAN_COOK);
        return true;
    }
}
