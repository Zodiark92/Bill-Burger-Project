import javax.naming.InvalidNameException;
import java.io.InvalidClassException;
import java.util.Scanner;

public class OrderManager {
    private Meal meal;

    private int checkHamburgerCode = 14;

    public boolean createMenu(Scanner scanner, boolean isMenu)  {

        while (true) {
            try {
                System.out.println("*** Choose the Hamburger ***");
                boolean hamburgerCreated = editHamburger(scanner, false, isMenu);
                if(hamburgerCreated) {
                    System.out.println("[Order Manager] Total Hamburger Price: " + meal.getTotalPrice());
                } else {
                    throw new InvalidNameException("Invalid state exception. Hamburger not created");
                }

                if(isMenu){
                System.out.println("*** Add the drink ***");
                editDrink(scanner);

                System.out.println("*** Add the Side Item ***");
                addSideItem(scanner, isMenu);
                System.out.printf("[Order Manager] Final price after the menu discount: %.2f%n ",  meal.computeMealPrice(true));
               }

                return true;

            } catch (InvalidNameException e){
                System.out.println("[Order Manager] " + e.getMessage());
                System.out.println("[Order Manager] Incorrect input. Please retry");
            } catch(InvalidClassException e){
                System.out.println("[Order Manager] Something went wrong. The Hamburger was not created");
            } catch(NumberFormatException e){
                System.out.println("[Order Manager] Invalid input. Please Retry");
            }
        }

    }

    private boolean editHamburger(Scanner scanner, boolean isEdit, boolean isMenu) throws InvalidNameException, InvalidClassException {

        boolean addHamburger = addHamburger(scanner, isEdit, isMenu);
        if(!addHamburger){
            throw new InvalidClassException("[Order Manager] Hamburger not created");
        }

        boolean isDeluxe = meal.getHamburger().isDeluxe();

        if(!isDeluxe){
            System.out.print("Do you want to add/remove toppings from the burger? (y/n): ");
            String addingToppingInput = scanner.nextLine();

            if (addingToppingInput.equalsIgnoreCase("y")) {
                addRemoveTopping(scanner, false);
            } else if (!addingToppingInput.equalsIgnoreCase("n")) {
                throw new InvalidNameException("[Order Manager] Invalid adding topping input");
            }
        } else {
            System.out.println("*** Add the toppings to the deluxe hamburger ***");
            addRemoveTopping(scanner, true);
        }

        return true;
    }

    //Choose the Hamburger and add it to the meal
    public boolean addHamburger(Scanner scanner, boolean isEdit, boolean isMenu) throws InvalidNameException {

        printHamburgerMenu();
        System.out.print("Insert the hamburger code: ");
        int hamburgerCode = Integer.parseInt(scanner.nextLine());

        if (hamburgerCode < 0 || hamburgerCode > checkHamburgerCode) {
            throw new InvalidNameException("Hamburger not found");
        }

        String sizeInput = (!isMenu) ? "M" : null;

        if(!isEdit && isMenu) {
            System.out.print("Insert the size (M/L/XL): ");
            sizeInput = scanner.nextLine().toUpperCase();

            if (!sizeInput.equalsIgnoreCase("M") && !sizeInput.equalsIgnoreCase("L") &&
                    !sizeInput.equalsIgnoreCase("XL")) {
                throw new InvalidNameException("Not valid size");
            }
        }


        if (hamburgerCode == 0 && meal == null) {
            this.meal = new Meal(sizeInput);
            System.out.println("[Order Manager] *** Standard Menu created ***");
        } else if(meal == null) {
            this.meal = new Meal(hamburgerCode, sizeInput);
            System.out.println("[Order Manager] *** Menu created ***");
        } else {
            meal.editHamburger(hamburgerCode);
        }

        return true;
    }

    public boolean editMenu(Scanner scanner, boolean isMenu) {
        while (true) {
            try {
                printOrder();
                System.out.println("""
                    0 - Confirm edit
                    1 - Edit hamburger
                    2 - Add or Edit Drink
                    3 - Add or Edit Side Item
                    """);
                System.out.print("Enter option: ");
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("0")){
                    System.out.println("[Order manager] Order completed");
                    System.out.printf("[Order Manager] Price : %.2f%n ",  meal.getTotalPrice());
                    return true;
                } else if (input.equalsIgnoreCase("1")) {
                    editHamburger(scanner, true, isMenu);
                } else if (input.equalsIgnoreCase("2")) {
                    editDrink(scanner);
                } else if(input.equalsIgnoreCase("3")) {
                    addSideItem(scanner, false);
                } else {
                    System.out.println("[Order Manager] Invalid input");
                }

                System.out.printf("[Order Manager] Price updated: %.2f%n ",  meal.computeMealPrice(isMenu));

            } catch (InvalidNameException e){
                System.out.println("[Order Manager] " + e.getMessage());
                System.out.println("[Order Manager] Incorrect input. Please retry");
            } catch(NumberFormatException e){
                System.out.println("[Order Manager] Invalid input. Please Retry");
            } catch (InvalidClassException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void addRemoveTopping(Scanner scanner, boolean isDeluxe) throws InvalidNameException {

        boolean canConfirmToppings = !isDeluxe;
        String codeOption = "0";

        // For the deluxe Hamburger you have to choose at least 2 toppings
        while (true) {
            try {
                System.out.println(printToppingOptions(canConfirmToppings, isDeluxe));
                System.out.print("Enter the option: ");
                codeOption = scanner.nextLine();

                if (canConfirmToppings && codeOption.equalsIgnoreCase("Q")) {
                    break;
                }

                int numberOfToppings = isDeluxe ? 5 : 3;
                if (codeOption.equalsIgnoreCase("0")) {
                   printToppingMenu();

                    System.out.printf("You can add up to %d toppings (enter n to select none).%n" +
                            "If the hamburger has already other toppings, these will be overridden.%n" +
                            "If the hamburger is deluxe, you have to choice from 2 up to 5 toppings%n", numberOfToppings);
                    meal.clearToppings();
                    if(isDeluxe){
                        canConfirmToppings = false;
                    }

                    for (int i = 0; i < numberOfToppings; i++) {
                        System.out.print("Insert the topping code (n to select no other toppings): ");
                        String toppingCode = scanner.nextLine();

                        if (toppingCode.equalsIgnoreCase("n") && (!isDeluxe || i > 1)) {
                            System.out.println("[OrderManager] Toppings selected:");
                            canConfirmToppings = true;
                            this.meal.getHamburger().printToppings();
                            break;
                        } else if(toppingCode.equalsIgnoreCase("n") && isDeluxe && i <= 1){
                            System.out.println("[OrderManager] You have to choice at least 2 toppings in the deluxe hamburger.");
                            i--;
                            continue;
                        } else

                        if (meal.addTopping(i, Integer.parseInt(toppingCode))) {
                            System.out.println("Topping added");
                        } else {
                            System.out.println("The topping is already present. Select another topping");
                            i--;
                        }

                        if(i == numberOfToppings -1){
                            canConfirmToppings = true;
                            System.out.println("[OrderManager] Toppings selected:");
                            this.meal.getHamburger().printToppings();
                        }


                    }
                } else if (codeOption.equalsIgnoreCase("1")) {
                    printToppingMenu();
                    System.out.print("Insert the topping code: ");
                    String toppingCode = scanner.nextLine();
                    if (meal.removeTopping(Integer.parseInt(toppingCode))) {
                        System.out.println("Topping removed");
                    } else {
                        System.out.println("The topping is already not present in the hamburger");
                    }
                } else {
                    throw new NumberFormatException("[Order Manager] Invalid input");
                }

            } catch (InvalidNameException e){
                System.out.println("[Order Manager] Topping not found. Please retry");
            } catch (NumberFormatException e){
                System.out.println("[Order Manager] Invalid input. Please retry");
            }
        }
    }

    private String printToppingOptions(boolean canConfirm, boolean isDeluxe){
        if(canConfirm && isDeluxe){
            return """
                  Selecting "Add Topping", the previous toppings will be deleted.
                  Q - Confirm toppings
                  0 - Add toppings (it will delete the toppings selected)
                """;
        } else if (isDeluxe) {
            return """    
                  You have to choice from 2 up to 5 toppings. If you have selected other toppings previously, these will be deleted.
                  0 - Add toppings (it will delete the toppings selected)
                """;
        } else {
            return """
                  You can add up to 3 toppings. If you have selected other toppings previously, they will be deleted.
                  Q - Confirm toppings
                  0 - Add toppings (it will override the toppings selected)
                  1 - Remove Topping
                """;
        }
    }

    public void editDrink(Scanner scanner) {
        while (true) {
            try {
                printDrinks();
                System.out.print("Enter the drink code: ");
                int drinkCodeInput = Integer.parseInt(scanner.nextLine());
                if(drinkCodeInput >= 0 && drinkCodeInput <= 3){
                    meal.addDrink(drinkCodeInput);
                    System.out.printf("[Order Manager] Drink %s added to the menu.%n" +
                            "Meal price: %.2f%n ", meal.getDrink().getDrinkDesc(), meal.getTotalPrice());
                    break;
                } else {
                    throw new InvalidNameException();
                }
            } catch (InvalidNameException e) {
                System.out.println("[Order Manager] Invalid code. Please insert a valid code for the drink");
            } catch(NumberFormatException e){
                System.out.println("[Order Manager] Invalid input. Please retry inserting the correct drink code.");
            }
        }
    }

    public void addSideItem(Scanner scanner, boolean isMenu) throws InvalidNameException {

        while(true) {
            try {
                printSideItems();
                System.out.print("Enter the side item code: ");
                int sideItemCode = Integer.parseInt(scanner.nextLine());

                if (sideItemCode >= 0 && sideItemCode <= 3) {
                    //add the side item to the burger
                    meal.addSideItem(sideItemCode, isMenu);
                    break;
                } else {
                    throw new InvalidNameException();
                }

            } catch (InvalidNameException e) {
                System.out.println("[Order Manager] Invalid code. Please insert a valid code for the side item.");
            } catch(NumberFormatException e){
                System.out.println("[Order Manager] Invalid input. Please retry inserting the correct side item code.");
            }

        }
    }

    public boolean clearOrder(Scanner scanner) {
        while(true){
            System.out.print("Are you sure you want to delete the order? (Y/N): ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("y")){
                clearOrder();
                System.out.println("[Order Manager] Order has been cleared.");
                return true;
            } else if(input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("[Order Manager] Invalid input. Please retry inserting the correct input.");
            }
        }

    }

    public void clearOrder() {
        this.meal = null;
    }

    public void printOrder() {
        System.out.println(this.meal);
    }

    public void confirmAndPay() {
        System.out.printf("Total price: %.2f dollars%n", this.meal.getTotalPrice());
    }

    public static void printHamburgerMenu() {
        System.out.println("""
                0 - Base Hamburger Beef
                1 - Base Hamburger With Chicken
                2 - Bill's Burger
                3 - Crazy Cheese BBQ
                4 - Pecorino Romano with Scamorza Burger
                5 - Bacon King
                6 - Bacon King Triple
                7 - Bacon King Triple Onion
                8 - Chicken Bacon King
                9 - Crunchicken
                10 - Crazy Cheese Chicken BBQ
                11 - Parmiggiano Reggiano Burger
                12 - Whopper
                13 - Deluxe Burger (with Beef)
                14 - Deluxe Burger (With Chicken)
                """);
    }

    public void printToppingMenu() {
        System.out.println("""
                0 - Bacon
                1 - Cheddar
                2 - Tomato;
                3 - Salad;
                4 - Crispy Onions
                5 - Parmisan Cheese
                6 - Rocket
                7 - Scamorza
                8 - Oregano
                9 - Onion Rings
                10 - Cucumbers
                """);


    }

    public void printDrinks() {
        System.out.println("""
                0 - Water
                1 - Coca cola
                2 - Orange Soda
                3 - Beer
                """);
    }

    public void printSideItems() {
        System.out.println("""
                0 - Fries with Ketchup
                1 - Fries with Mayo
                2 - Onion Rings
                3 - Bill Nuggets
                """);
    }
}
