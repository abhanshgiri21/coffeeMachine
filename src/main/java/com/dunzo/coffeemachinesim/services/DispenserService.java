/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.services;


import com.dunzo.coffeemachinesim.models.Drink;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author abhanshgiri
 */
public class DispenserService implements Runnable {
    private final Map<String, Integer> sharedIngredientsMap;
    private final DrinkService drinkService;
    private String drinkName;


    public DispenserService(Map<String, Integer> sharedIngredientsMap, DrinkService drinkService,
                            String drinkName) {
        this.sharedIngredientsMap = sharedIngredientsMap;
        this.drinkService = drinkService;
        this.drinkName = drinkName;
    }

    @Override
    public void run() {
        Drink drink = drinkService.getDrink(drinkName);
        boolean allIngredientsAvailable = true;
        HashMap<String, Integer> blockedIngredientMap = new HashMap<>();
        for (Map.Entry<String, Integer> drinkIngredients : drink.getIngredients().entrySet()) {

            if (sharedIngredientsMap.containsKey(drinkIngredients.getKey())) {
                int totalAvailableCount = sharedIngredientsMap.get(drinkIngredients.getKey());
                int requiredCount = drinkIngredients.getValue();
                if (totalAvailableCount - requiredCount >= 0) {
                    sharedIngredientsMap.compute(drinkIngredients.getKey(), (k, v) -> v - requiredCount);
                    blockedIngredientMap.put(drinkIngredients.getKey(), requiredCount);
                } else { // If any of ingredient is short in supply we cant prepare beverage
                    allIngredientsAvailable = false;
                    System.out.println(String.format("%s cannot be prepared because item %s is not sufficient", drinkName, drinkIngredients.getKey()));
                    break;
                }
            } else { // If Ingredient itself is unavailable print error and return
                allIngredientsAvailable = false;
                System.out.println(String.format("%s cannot be prepared because %s is not available", drinkName, drinkIngredients.getKey()));
                break;
            }
        }
        //If all ingredients are available we can dispense beverage
        // else we need to add back the ingredients that we have subtracted
        if (allIngredientsAvailable) {
            System.out.println(drinkName + " is prepared");
            //MapUtil.print(sharedIngredientsMap);
        } else {
            for (Map.Entry<String, Integer> entry : blockedIngredientMap.entrySet()) {
                sharedIngredientsMap.compute(entry.getKey(), (k, v) -> v + entry.getValue());
            }
            // we can add retry logic here
        }
    }

}
