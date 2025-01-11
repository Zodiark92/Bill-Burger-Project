import javax.naming.InvalidNameException;

public class Hamburger {

    private int hamburgerCode;
    private String hamburgerDesc;
    private String meat;
    private String size;
    private boolean deluxe;
    private double burgerPrice;
    protected String firstTopping;
    protected String secondTopping;
    protected String thirdTopping;
    protected String firstDeluxeTopping;
    protected String secondDeluxeTopping;
    private double addingToppingPrice;
    private double removingToppingPrice;
    private String sauce;
    protected Toppings toppings;

    public Hamburger(int hamburgerCode, String hamburgerDesc, String meat, String size, String sauce, boolean deluxe, double burgerPrice) {
        this.hamburgerCode = hamburgerCode;
        this.hamburgerDesc = hamburgerDesc;
        this.meat = meat;
        this.size = size;
        this.sauce = sauce;
        this.deluxe = deluxe;
        this.burgerPrice = burgerPrice;
        this.addingToppingPrice = 1.0;
        this.removingToppingPrice = 1.0;

        this.toppings = new Toppings();
    }


    public boolean addTopping(int numberTopping, String topping) throws InvalidNameException {

        if (!toppings.addIngredient(topping)) {
            System.out.printf("Topping %s already present %n", topping);
            return false;
        }

        switch (numberTopping) {
            case 0 -> this.firstTopping = topping;
            case 1 -> this.secondTopping = topping;
            case 2 -> this.thirdTopping = topping;
        }

        this.burgerPrice += addingToppingPrice;
        System.out.printf("[%s] Topping %s added%n", this.getClass().getSimpleName(), topping);
        System.out.printf("[%s] Hamburger price: %.2f%n", this.getClass().getSimpleName(), burgerPrice);

        return true;
    }

    public boolean removeTopping(String topping) throws InvalidNameException {

        if (!toppings.removeIngredient(topping)) {
            System.out.printf("Topping %s already not present %n", topping);
            return false;
        }

        if(firstTopping != null && firstTopping.equalsIgnoreCase(topping)){
            firstTopping = null;
        } else if (secondTopping != null && secondTopping.equalsIgnoreCase(topping)) {
            secondTopping = null;
        } else if (thirdTopping != null && thirdTopping.equalsIgnoreCase(topping)) {
            thirdTopping = null;
        }

        if(deluxe && firstDeluxeTopping != null && firstDeluxeTopping.equalsIgnoreCase(topping)) {
            firstDeluxeTopping = null;
        } else if (deluxe && secondDeluxeTopping != null && secondDeluxeTopping.equalsIgnoreCase(topping)) {
            secondDeluxeTopping = null;
        }

        System.out.printf("[%s] topping %s removed%n", this.getClass().getSimpleName(), topping);
        if(!deluxe){
            this.burgerPrice -= removingToppingPrice;
            System.out.printf("[%s] Hamburger price: %.2f%n", this.getClass().getSimpleName(), burgerPrice);
        }

        return true;
    }
    
    public double clearToppings() throws InvalidNameException {
        if(firstTopping != null){
            String topping = firstTopping;
            if(removeTopping(firstTopping)){
                System.out.println("[Hamburger] Topping " + topping + " removed");
            } else {
                return -1;
            }
        }

        if(secondTopping != null){
            String topping = secondTopping;
            if(removeTopping(secondTopping)){
                System.out.println("[Hamburger] Topping " + topping  + " removed");
            } else {
                return -1;
            }
        }

        if(thirdTopping != null){
            String topping = thirdTopping;
            if(removeTopping(thirdTopping)){
                System.out.println("[Hamburger] Topping " + topping  + " removed");
            } else {
                return -1;
            }
        }

        if(firstDeluxeTopping != null){
            String topping = firstDeluxeTopping;
            if(removeTopping(firstDeluxeTopping)){
                System.out.println("[Hamburger] Topping " + topping  + " removed");
            } else {
                return -1;
            }
        }

        if(secondDeluxeTopping != null){
            String topping = secondDeluxeTopping;
            if(removeTopping(secondDeluxeTopping)){
                System.out.println("[Hamburger] Topping " + topping  + " removed");
            } else {
                return -1;
            }
        }
        
        
        return burgerPrice;
    }

    public static Hamburger getHamburger(int code, String size) throws InvalidNameException {
        return switch (code){
            case 0 -> new BaseHamburger(Constants.BEEF, size);
            case 1 -> new BaseHamburger(Constants.CHICKEN, size);
            case 2 -> new BillBurger(size);
            case 3 -> new CrazyCheeseBBQ(size);
            case 4 -> new PecorinoRomanoScamorzaBurger(size);
            case 5 -> new BaconKing(size);
            case 6 -> new BaconKingTriple(size);
            case 7 -> new BaconKingTripleOnion(size);
            case 8 -> new ChickenBaconKing(size);
            case 9 -> new Crunchicken(size);
            case 10 -> new CrazyCheeseChickenBBQ(size);
            case 11 -> new ParmigianoReggianoBurger(size);
            case 12 -> new Whopper(size);
            case 13 -> new DeluxeHamburger(Constants.BEEF, size);
            case 14 -> new DeluxeHamburger(Constants.CHICKEN, size);
            default -> throw new InvalidNameException("Hamburger not found");
        };
    }
    
    public void printToppings(){
        if(firstTopping != null){
            System.out.println("- " + firstTopping);
        }

        if(secondTopping != null){
            System.out.println("- " + secondTopping);
        }

        if(thirdTopping != null){
            System.out.println("- " + thirdTopping);
        }
    }

    @Override
    public String toString() {
        return "Hamburger{" +
                "hamburgerCode=" + hamburgerCode +
                ", hamburgerDesc='" + hamburgerDesc + '\'' +
                ", meat='" + meat + '\'' +
                ", size='" + size + '\'' +
                ", deluxe=" + deluxe +
                ", burgerPrice=" + burgerPrice +
                ", firstTopping='" + firstTopping + '\'' +
                ", secondTopping='" + secondTopping + '\'' +
                ", thirdTopping='" + thirdTopping + '\'' +
                ", sauce='" + sauce + '\'' +
                ", toppings=" + toppings +
                '}' + toppings.toString();
    }

    protected double getBurgerPrice() {
        return burgerPrice;
    }

    public double getAddingToppingPrice() {
        return addingToppingPrice;
    }

    public double getRemovingToppingPrice() {
        return removingToppingPrice;
    }

    public String getSize() {
        return size;
    }

    public boolean isDeluxe() {
        return deluxe;
    }

    public int getHamburgerCode() {
        return hamburgerCode;
    }
}

class BaseHamburger extends Hamburger {
    public BaseHamburger(String meat, String size) {
        super(0, Constants.BASE_HAMBURGER, meat, size, Constants.BILLSAUCE, false, (meat.equals(Constants.BEEF) ? 8.90 : 7.70));
    }
}

class BillBurger extends Hamburger {

    public BillBurger(String size) throws InvalidNameException {
        super(1, Constants.BILL_BURGER, Constants.BEEF, size, Constants.BILLSAUCE, false, 10.20);
        toppings.addIngredient(Constants.DOUBLE_BACON);
        toppings.addIngredient(Constants.CHEDDAR);
        toppings.addIngredient(Constants.TOMATO);
        toppings.addIngredient(Constants.SALAD);
    }

}

class CrazyCheeseBBQ extends Hamburger {

    public CrazyCheeseBBQ(String size) throws InvalidNameException {
        this(2, Constants.CRAZY_CHEESE_BBQ, Constants.BEEF, size, 9.40);
        toppings.addIngredient(Constants.BACON);
        toppings.addIngredient(Constants.CHEDDAR);
        toppings.addIngredient(Constants.CRISPY_ONIONS);
    }

    public CrazyCheeseBBQ(int code, String description, String meat, String size, double price) {
        super(code, description, meat, size, Constants.BULLS_EYE_SAUCE, false, price);
    }

}

class PecorinoRomanoScamorzaBurger extends Hamburger {

    public PecorinoRomanoScamorzaBurger(String size) throws InvalidNameException {
        super(3, Constants.PECORINO_ROMANO_SCAMORZA, Constants.BEEF, size, Constants.MAYO, false, 10.50);
        toppings.addIngredient(Constants.BACON);
        toppings.addIngredient(Constants.PARMIGIANO_REGGIANO);
        toppings.addIngredient(Constants.CRISPY_ONIONS);
        toppings.addIngredient(Constants.ROCKET);
        toppings.addIngredient(Constants.SCAMORZA);
        toppings.addIngredient(Constants.OREGANO);
    }

}

class BaconKing extends Hamburger {

    public BaconKing(String size) throws InvalidNameException {
        this(4, Constants.BACON_KING, Constants.BEEF, size, 9.90);
        toppings.addIngredient(Constants.BACON);
        toppings.addIngredient(Constants.CHEDDAR);
    }

    public BaconKing(int code, String description, String meat, String size, double price) {
        super(code, description, meat, size, Constants.MAYO_KETCHUP, false, price);
    }

}

class BaconKingTriple extends BaconKing {

    public BaconKingTriple(String size) throws InvalidNameException {
        super(5, Constants.BACON_KING_TRIPLE, Constants.BEEF_TRIPLE, size, 12.20);
        toppings.addIngredient(Constants.BACON_TRIPLE);
        toppings.addIngredient(Constants.CHEDDAR_TRIPLE);
    }

}

class BaconKingTripleOnion extends BaconKing {

    public BaconKingTripleOnion(String size) throws InvalidNameException {
        super(6, Constants.BACON_KING_TRIPLE_ONION, Constants.BEEF_TRIPLE, size, 13.90);
        toppings.addIngredient(Constants.BACON_TRIPLE);
        toppings.addIngredient(Constants.CHEDDAR_TRIPLE);
        toppings.addIngredient(Constants.CRISPY_ONION_RINGS);
    }

}

class ChickenBaconKing extends BaconKing {

    public ChickenBaconKing(String size) throws InvalidNameException {
        super(7, Constants.CHICKEN_BACON_KING, Constants.CHICKEN, size, 11.90);
        toppings.addIngredient(Constants.BACON_TRIPLE);
        toppings.addIngredient(Constants.CHEDDAR_TRIPLE);
    }

}

class Crunchicken extends Hamburger {

    public Crunchicken(String size) throws InvalidNameException {
        super(8, Constants.CRUNCHICKEN, Constants.CHICKEN, size, Constants.MAYO, false, 8.90);
        toppings.addIngredient(Constants.SALAD);
        toppings.addIngredient(Constants.TOMATO);
        toppings.addIngredient(Constants.CRISPY_ONIONS);
    }

}

class CrazyCheeseChickenBBQ extends CrazyCheeseBBQ {

    public CrazyCheeseChickenBBQ(String size) {
        super(9, Constants.CRAZY_CHEESE_CHICKEN_BBQ, Constants.CHICKEN, size, 8.90);
    }
}

class ParmigianoReggianoBurger extends Hamburger {

    public ParmigianoReggianoBurger(String size) throws InvalidNameException {
        super(10, Constants.PARMIGIANO_REGGIANO_BURGER, Constants.BEEF, size, Constants.MAYO, false, 10.50);
        toppings.addIngredient(Constants.PARMIGIANO_REGGIANO);
        toppings.addIngredient(Constants.CRISPY_ONIONS);
        toppings.addIngredient(Constants.ROCKET);
        toppings.addIngredient(Constants.SALAD);
    }

}

class Whopper extends Hamburger {

    public Whopper(String size) throws InvalidNameException {
        super(11, Constants.WHOPPER, Constants.BEEF, size, Constants.MAYO_KETCHUP, false, 9.90);
        toppings.addIngredient(Constants.TOMATO);
        toppings.addIngredient(Constants.CRISPY_ONIONS);
        toppings.addIngredient(Constants.CUCUMBERS);
        toppings.addIngredient(Constants.SALAD);
    }

}

class DeluxeHamburger extends Hamburger {

    public DeluxeHamburger(String meat, String size) {
        super(12, Constants.DELUXE_HAMBURGER, meat, size, Constants.BILLSAUCE, true, (meat.equals(Constants.BEEF) ? 12.50 : 11.20));
    }

    @Override
    public boolean addTopping(int numberTopping, String topping) throws InvalidNameException {

        if (!toppings.addIngredient(topping)) {
            System.out.printf("Topping %s already present %n", topping);
            return false;
        }

        switch (numberTopping) {
            case 0 -> this.firstTopping = topping;
            case 1 -> this.secondTopping = topping;
            case 2 -> this.thirdTopping = topping;
            case 3 -> this.firstDeluxeTopping = topping;
            case 4 -> this.secondDeluxeTopping = topping;
        }

        System.out.printf("[%s] Topping %s added in the Deluxe Burger%n", this.getClass().getSimpleName(), topping);
        return true;
    }

    @Override
    public void printToppings() {
        super.printToppings();

        if(firstDeluxeTopping != null){
            System.out.println("- " + firstDeluxeTopping);
        }
        if(secondDeluxeTopping != null){
            System.out.println("- " + secondDeluxeTopping);
        }
    }
}




