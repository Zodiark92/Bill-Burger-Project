import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("**** Hello!! Welcome to Bill's Burger ****");

        OrderManager order = new OrderManager();

        boolean orderCreated;
        boolean orderCompleted;

        while(true) {

            try{
                System.out.println("""
                    Choose an option:
                        Q - Quit
                        1 - Order a Meal Menu
                        2 - Order Only Burger
                    """);
                System.out.print("Input: ");
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("Q")){
                    break;
                } else if (input.equalsIgnoreCase("1")) {
                    orderCreated = order.createMenu(scanner, true);
                } else if (input.equalsIgnoreCase("2")) {
                    orderCreated = order.createMenu(scanner, false);
                } else {
                    throw new InputMismatchException("Invalid input");
                }

                if(orderCreated) {
                    System.out.print("""
                                    Order correctly inserted! :)
                                    Do you want to add other items? (y/n): """);
                    input = scanner.nextLine();
                    if(input.equalsIgnoreCase("Y")){
                        orderCompleted = order.editMenu(scanner);
                    } else if (input.equalsIgnoreCase("n")) {
                        orderCompleted = true;
                    } else {
                        throw new InputMismatchException("Invalid input");
                    }
                } else {
                    throw new Exception("Meal not created");
                }

                if(orderCompleted){
                    order.printOrder();
                    order.confirmAndPay();
                    break;
                }

            } catch(InputMismatchException e) {
                System.out.println("[Main] " + e.getMessage() + ". Please retry");
            } catch (Exception e) {
                System.out.println("""
                [Main] Something went wrong :(
                Please retry inserting a new order.
                """);
                order.clearOrder();
            }
        }

        System.out.println("**** Thank you! Goodbye :) ****");
    }

}