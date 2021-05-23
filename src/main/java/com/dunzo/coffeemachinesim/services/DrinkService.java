/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.services;

import com.dunzo.coffeemachinesim.models.Drink;
import com.dunzo.coffeemachinesim.exception.DrinkNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abhanshgiri
 */
public class DrinkService {
    public static final String drinksFileName = "drinks.txt";
    public FileService fileService;
    private Map<String, Drink> drinksMap;
    
    public DrinkService(FileService fileService) {
        this.fileService = fileService;
        this.drinksMap = new HashMap<>();
        loadDrinksIngredients();
    }
    
    public void loadDrinksIngredients() {
        List<String> lines = fileService.readData(drinksFileName);
        
        for(int i=0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            String drinkName = data[0].trim().toLowerCase();
            Map<String, Integer> ingredients = new HashMap<>();
            for (int k = 1; k < data.length; k = k + 2) {
                String ingredientName = data[k].trim().toLowerCase();
                int ingredientAmount = Integer.parseInt(data[k + 1].trim());
                ingredients.put(ingredientName, ingredientAmount);
            }
            drinksMap.put(drinkName, new Drink(drinkName, ingredients));
        }
    }

    public Drink getDrink(String drinkName) {
        if (drinksMap.containsKey(drinkName)) {
            return drinksMap.get(drinkName);
        }
        
        throw new DrinkNotFoundException("Beverage " + drinkName + " not found.");
    }
}
