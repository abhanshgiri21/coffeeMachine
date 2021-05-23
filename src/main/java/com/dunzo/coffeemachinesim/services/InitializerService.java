/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author abhanshgiri
 */
public class InitializerService {
    private static final String INGREDIENT_FILE_NAME = "ingredients.txt";
    private final int LOW_INDICATOR = 50;
    private final Map<String, Integer> ingredientMap;
    private final FileService fileService;

    public InitializerService(FileService fileService) {
        this.fileService = fileService;
        this.ingredientMap = new ConcurrentHashMap<>();
        loadInitialIngredients();
    }

    public Map<String, Integer> getIngredientMap() {
        return ingredientMap;
    }

    private void loadInitialIngredients() {
        List<String> ingredientData = fileService.readData(INGREDIENT_FILE_NAME);
        for (String data : ingredientData) {
            String[] ingredients = data.split(",");
            String ingrtedientName = ingredients[0].trim().toLowerCase();
            int quantity = Integer.parseInt(ingredients[1].trim());
            ingredientMap.put(ingrtedientName, quantity);
        }
    }

    public void refill(String ingredientName, int quantity) {
        ingredientMap.computeIfPresent(ingredientName, (k, v) -> v + quantity);
    }

    public void lowIndicator() {
        for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {
            if (entry.getValue() < LOW_INDICATOR) {
                System.out.println("---------------------------");
                System.out.println("Inventory for " + entry.getKey() + " is getting low, Please refill");
                System.out.println("---------------------------");
            }
        }
    }
}
