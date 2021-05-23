/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.models;

import java.util.Map;

/**
 *
 * @author abhanshgiri
 */
public class Drink {
    public String name; //chai, coffee
    public Map<String, Integer> ingredients; // [ Ingredients{ name: quantity}... ]

    public Drink(String name, Map<String, Integer> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }
    public Map<String, Integer> getIngredients() {
            return ingredients;
    }
}
