/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.exception;

/**
 *
 * @author abhanshgiri
 */
public class DrinkNotFoundException extends RuntimeException{
    public DrinkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrinkNotFoundException(String message) {
        super(message);
    }
}
