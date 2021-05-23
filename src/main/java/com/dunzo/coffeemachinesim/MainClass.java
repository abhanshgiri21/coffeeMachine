/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim;

import com.dunzo.coffeemachinesim.services.DispenserService;
import com.dunzo.coffeemachinesim.services.DrinkService;
import com.dunzo.coffeemachinesim.services.FileService;
import com.dunzo.coffeemachinesim.services.InitializerService;
import static java.lang.Thread.sleep;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author abhanshgiri
 */
public class MainClass {
    private static final int NUM_OUTLETS = 3;
    
    public static void main(String[] args) throws InterruptedException {
        FileService fileService = new FileService();
        DrinkService drinkService = new DrinkService(fileService);
        InitializerService initializerService = new InitializerService(fileService);
        
        Map<String, Integer> sharedMap = initializerService.getIngredientMap();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OUTLETS);
        
        executorService.submit(new DispenserService(sharedMap, drinkService, "hot_tea"));
        executorService.submit(new DispenserService(sharedMap, drinkService, "hot_coffee"));
        executorService.submit(new DispenserService(sharedMap, drinkService, "green_tea"));
        executorService.submit(new DispenserService(sharedMap, drinkService, "black_tea"));

        // this will stop the threads and give time for lowIndicator to run
        sleep(1000);
        initializerService.lowIndicator();
        
        // refilling low ingredients for black tea
        initializerService.refill("sugar_syrup", 200);
        initializerService.refill("hot_water", 300);
        executorService.submit(new DispenserService(sharedMap, drinkService, "black_tea"));

        executorService.shutdown();
    }
}
