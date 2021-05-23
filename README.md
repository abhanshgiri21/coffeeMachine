### Coffee Machine Simulator

#### Project outline

The MainClass is the main entry point for the project.

The ingredient list is being picked up from `src/main/java/resources/ingredients.txt`

>This is the list of all the ingredients that are pre fed into the machine
------
 All the Drinks are being picked up from `src/main/java/resources/drinks.txt`

>This is the list of drinks with their names and ingredients required to prepare them.

-----------

In this flow, according to the input file given [here](https://www.npoint.io/docs/e8cd5a9bbd1331de326a), the following things are initialized in the project

- drinks.txt contains the drinks config from `beverages` key.
- ingredients.txt contains the initial ingredients with their quantities picked up from `total_items_quantity`
- Outlet count define din MainClass as `NUM_OUTLET` is picked up from `outlets.count_n` key.


When the project is run, it tries to prepare the following drinks, atmost 3 in parallel

```
 - hot_tea
 - hot_coffee
 - green_tea
 - black_tea
 ```

`black_tea` cannot be prepared since `hot_water` and `sugar_syrup` are low in quantity. So we call the `refill` method to refill these two ingredients and then try to prepare `black_tea` again. This time it's successful since all the ingredients are available.