import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

enum Meat {
    BEEF,
    CHICKEN,
    BEEF_TRIPLE
}

public class Hamburger {

    private int hamburgerCode;
    private HamburgerType hamburger;
    private Meat meat;
    private Size size;
    private boolean deluxe;
    private double burgerPrice;
    private List<ToppingItem> additionalToppings = new ArrayList<>();
    private double addingToppingPrice;
    private double removingToppingPrice;
    private Sauce sauce;
    protected Topping topping;

    public Hamburger(int hamburgerCode, HamburgerType hamburger, Meat meat, Size size, Sauce sauce, boolean deluxe, double burgerPrice) {
        this.hamburgerCode = hamburgerCode;
        this.hamburger = hamburger;
        this.meat = meat;
        this.size = size;
        this.sauce = sauce;
        this.deluxe = deluxe;
        this.burgerPrice = burgerPrice;
        this.addingToppingPrice = 1.0;
        this.removingToppingPrice = 1.0;

        this.topping = new Topping();
    }


    public boolean addTopping(ToppingItem topping) throws InvalidNameException {

        if (!this.topping.addIngredient(topping)) {
            System.out.printf("Topping %s already present %n", topping);
            return false;
        }

        additionalToppings.add(topping);
        if(!deluxe){
            this.burgerPrice += addingToppingPrice;
        }

        System.out.printf("[%s] Topping %s added%n", this.getClass().getSimpleName(), topping);

        if(deluxe) {
            System.out.printf("[%s] Topping %s added in the Deluxe Burger%n", this.getClass().getSimpleName(), topping);
        } else {
            System.out.printf("[%s] Hamburger price: %.2f%n", this.getClass().getSimpleName(), burgerPrice);
        }

        return true;
    }

    public boolean removeTopping(ToppingItem topping) throws InvalidNameException {

        if (!this.topping.removeIngredient(topping)) {
            System.out.printf("Topping %s already not present %n", topping);
            return false;
        }

        additionalToppings.remove(topping);

        System.out.printf("[%s] topping %s removed%n", this.getClass().getSimpleName(), topping);
        if(!deluxe){
            this.burgerPrice -= removingToppingPrice;
            System.out.printf("[%s] Hamburger price: %.2f%n", this.getClass().getSimpleName(), burgerPrice);
        }

        return true;
    }
    
    public double clearToppings() throws InvalidNameException {

        List<ToppingItem> itemsToRemove = new ArrayList<>(additionalToppings);

        for (ToppingItem item : itemsToRemove) {
            removeTopping(item);
        }

        return burgerPrice;
    }

    public static Hamburger getHamburger(int code, Size size) throws InvalidNameException {
        return switch (code){
            case 0 -> new BaseHamburger(Meat.BEEF, size);
            case 1 -> new BaseHamburger(Meat.CHICKEN, size);
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
            case 13 -> new DeluxeHamburger(Meat.BEEF, size);
            case 14 -> new DeluxeHamburger(Meat.CHICKEN, size);
            default -> throw new InvalidNameException("Hamburger not found");
        };
    }
    
    public void printToppings(){
        for(ToppingItem topping : additionalToppings) {
            System.out.println("- " + topping);
        }
    }

    @Override
    public String toString() {

        String hamburgerDesc = this.hamburger + "\n";
        for(ToppingItem topping : additionalToppings) {
            hamburgerDesc =  hamburgerDesc.concat("Extra Topping: " + topping + "\n");
        }
        return hamburgerDesc;
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

    public Size getSize() {
        return size;
    }

    public boolean isDeluxe() {
        return deluxe;
    }

    public int getHamburgerCode() {
        return hamburgerCode;
    }

    public List<ToppingItem> getAdditionalToppings() {
        return additionalToppings;
    }
}

class BaseHamburger extends Hamburger {
    public BaseHamburger(Meat meat, Size size) {
        super(0, HamburgerType.BASE_HAMBURGER, meat, size, Sauce.BILL_SAUCE, false, meat == Meat.BEEF ? 8.90 : 7.70);
    }
}

class BillBurger extends Hamburger {

    public BillBurger(Size size) throws InvalidNameException {
        super(1, HamburgerType.BILL_BURGER, Meat.BEEF, size, Sauce.BILL_SAUCE, false, 10.20);
        topping.addIngredient(ToppingItem.DOUBLE_BACON);
        topping.addIngredient(ToppingItem.CHEDDAR);
        topping.addIngredient(ToppingItem.TOMATO);
        topping.addIngredient(ToppingItem.SALAD);
    }

}

class CrazyCheeseBBQ extends Hamburger {

    public CrazyCheeseBBQ(Size size) throws InvalidNameException {
        this(2, HamburgerType.CRAZY_CHEESE_BBQ, Meat.BEEF, size, 9.40);
        topping.addIngredient(ToppingItem.BACON);
        topping.addIngredient(ToppingItem.CHEDDAR);
        topping.addIngredient(ToppingItem.CRISPY_ONIONS);
    }

    public CrazyCheeseBBQ(int code, HamburgerType hamburger, Meat meat, Size size, double price) {
        super(code, hamburger, meat, size, Sauce.BULLS_EYE_SAUCE, false, price);
    }

}

class PecorinoRomanoScamorzaBurger extends Hamburger {

    public PecorinoRomanoScamorzaBurger(Size size) throws InvalidNameException {
        super(3, HamburgerType.PECORINO_ROMANO_SCAMORZA, Meat.BEEF, size, Sauce.MAYO, false, 10.50);
        topping.addIngredient(ToppingItem.BACON);
        topping.addIngredient(ToppingItem.PARMIGIANO_REGGIANO);
        topping.addIngredient(ToppingItem.CRISPY_ONIONS);
        topping.addIngredient(ToppingItem.ROCKET);
        topping.addIngredient(ToppingItem.SCAMORZA);
        topping.addIngredient(ToppingItem.OREGANO);
    }

}

class BaconKing extends Hamburger {

    public BaconKing(Size size) throws InvalidNameException {
        this(4, HamburgerType.BACON_KING, Meat.BEEF, size, 9.90);
        topping.addIngredient(ToppingItem.BACON);
        topping.addIngredient(ToppingItem.CHEDDAR);
    }

    public BaconKing(int code, HamburgerType hamburger, Meat meat, Size size, double price) {
        super(code, hamburger, meat, size, Sauce.MAYO_KETCHUP, false, price);
    }

}

class BaconKingTriple extends BaconKing {

    public BaconKingTriple(Size size) throws InvalidNameException {
        super(5, HamburgerType.BACON_KING_TRIPLE, Meat.BEEF_TRIPLE, size, 12.20);
        topping.addIngredient(ToppingItem.BACON_TRIPLE);
        topping.addIngredient(ToppingItem.CHEDDAR_TRIPLE);
    }

}

class BaconKingTripleOnion extends BaconKing {

    public BaconKingTripleOnion(Size size) throws InvalidNameException {
        super(6, HamburgerType.BACON_KING_TRIPLE_ONION, Meat.BEEF_TRIPLE, size, 13.90);
        topping.addIngredient(ToppingItem.BACON_TRIPLE);
        topping.addIngredient(ToppingItem.CHEDDAR_TRIPLE);
        topping.addIngredient(ToppingItem.CRISPY_ONION_RINGS);
    }

}

class ChickenBaconKing extends BaconKing {

    public ChickenBaconKing(Size size) throws InvalidNameException {
        super(7, HamburgerType.CHICKEN_BACON_KING, Meat.CHICKEN, size, 11.90);
        topping.addIngredient(ToppingItem.BACON_TRIPLE);
        topping.addIngredient(ToppingItem.CHEDDAR_TRIPLE);
    }

}

class Crunchicken extends Hamburger {

    public Crunchicken(Size size) throws InvalidNameException {
        super(8, HamburgerType.CRUNCHICKEN, Meat.CHICKEN, size, Sauce.MAYO, false, 8.90);
        topping.addIngredient(ToppingItem.SALAD);
        topping.addIngredient(ToppingItem.TOMATO);
        topping.addIngredient(ToppingItem.CRISPY_ONIONS);
    }

}

class CrazyCheeseChickenBBQ extends CrazyCheeseBBQ {

    public CrazyCheeseChickenBBQ(Size size) {
        super(9, HamburgerType.CRAZY_CHEESE_CHICKEN_BBQ, Meat.CHICKEN, size, 8.90);
    }
}

class ParmigianoReggianoBurger extends Hamburger {

    public ParmigianoReggianoBurger(Size size) throws InvalidNameException {
        super(10, HamburgerType.PARMIGIANO_REGGIANO_BURGER, Meat.BEEF, size, Sauce.MAYO, false, 10.50);
        topping.addIngredient(ToppingItem.PARMIGIANO_REGGIANO);
        topping.addIngredient(ToppingItem.CRISPY_ONIONS);
        topping.addIngredient(ToppingItem.ROCKET);
        topping.addIngredient(ToppingItem.SALAD);
    }

}

class Whopper extends Hamburger {

    public Whopper(Size size) throws InvalidNameException {
        super(11, HamburgerType.WHOPPER, Meat.BEEF, size, Sauce.MAYO_KETCHUP, false, 9.90);
        topping.addIngredient(ToppingItem.TOMATO);
        topping.addIngredient(ToppingItem.CRISPY_ONIONS);
        topping.addIngredient(ToppingItem.CUCUMBERS);
        topping.addIngredient(ToppingItem.SALAD);
    }

}

class DeluxeHamburger extends Hamburger {

    public DeluxeHamburger(Meat meat, Size size) {
        super(12, HamburgerType.DELUXE_HAMBURGER, meat, size, Sauce.BILL_SAUCE, true, meat == Meat.BEEF ? 12.50 : 11.20);
    }

}





