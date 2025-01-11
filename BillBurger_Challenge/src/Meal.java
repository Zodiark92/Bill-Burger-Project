import javax.naming.InvalidNameException;

public class Meal {

    private Hamburger hamburger;
    private Drink drink;
    private SideItem sideItem;
    private double totalPrice;

    public Meal(String size) throws InvalidNameException {
        this(0, size);
        switch (size) {
            case "M" -> this.totalPrice = 9.90;
            case "L" -> this.totalPrice = 11.30;
            case "XL" -> this.totalPrice = 12.50;
            default -> throw new InvalidNameException("[Meal] Size not correct");
        }
    }

    public Meal(int hamburgerCode, String size) throws InvalidNameException {
        this.hamburger = Hamburger.getHamburger(hamburgerCode, size);
        switch (hamburger.getSize()) {
            case "M" -> this.totalPrice = hamburger.getBurgerPrice();
            case "L" -> this.totalPrice = hamburger.getBurgerPrice() + 1.50;
            case "XL" -> this.totalPrice = hamburger.getBurgerPrice() + 2.50;
            default -> throw new InvalidNameException("[Meal] Size not correct");
        }
    }

    public boolean addTopping(int numberTopping, int toppingCode) throws InvalidNameException {
        boolean toppingAdded;
        switch (toppingCode) {
            case 0 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.BACON);
            case 1 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.CHEDDAR);
            case 2 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.TOMATO);
            case 3 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.SALAD);
            case 4 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.CRISPY_ONIONS);
            case 5 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.PARMIGIANO_REGGIANO);
            case 6 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.ROCKET);
            case 7 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.SCAMORZA);
            case 8 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.OREGANO);
            case 9 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.CRISPY_ONION_RINGS);
            case 10 -> toppingAdded = hamburger.addTopping(numberTopping, Constants.CUCUMBERS);
            default -> throw new InvalidNameException();
        }
        if(toppingAdded && !hamburger.isDeluxe()){
            this.totalPrice += hamburger.getAddingToppingPrice();
        }
        return toppingAdded;
    }

    public boolean removeTopping(int toppingCode) throws InvalidNameException {
        boolean toppingRemoved;
         switch (toppingCode) {
            case 0 -> toppingRemoved = hamburger.removeTopping(Constants.BACON);
            case 1 -> toppingRemoved = hamburger.removeTopping(Constants.CHEDDAR);
            case 2 -> toppingRemoved = hamburger.removeTopping(Constants.TOMATO);
            case 3 -> toppingRemoved = hamburger.removeTopping(Constants.SALAD);
            case 4 -> toppingRemoved = hamburger.removeTopping(Constants.CRISPY_ONIONS);
            case 5 -> toppingRemoved = hamburger.removeTopping(Constants.PARMIGIANO_REGGIANO);
            case 6 -> toppingRemoved = hamburger.removeTopping(Constants.ROCKET);
            case 7 -> toppingRemoved = hamburger.removeTopping(Constants.SCAMORZA);
            case 8 -> toppingRemoved = hamburger.removeTopping(Constants.OREGANO);
            case 9 -> toppingRemoved = hamburger.removeTopping(Constants.CRISPY_ONION_RINGS);
            case 10 -> toppingRemoved = hamburger.removeTopping(Constants.CUCUMBERS);
            default -> throw new InvalidNameException();
        }
        if(toppingRemoved){
            this.totalPrice -= hamburger.getRemovingToppingPrice();
        }
        return toppingRemoved;
    }

    public void clearToppings() throws InvalidNameException {
        if (hamburger.clearToppings() < 0) {
            throw new InvalidNameException("[Meal] Clear topping failed");
        } else {
            this.totalPrice = computeMealPrice(this.getHamburger().getHamburgerCode(), this.getHamburger().getSize());
        }
    }

    public double computeMealPrice(int hamburgerCode, String size) throws InvalidNameException
    {
            return switch(size){
                    case "M" ->  (hamburgerCode == 0) ? 9.90 : hamburger.getBurgerPrice();
                    case "L" ->  (hamburgerCode == 0) ?  11.30 : hamburger.getBurgerPrice() + 1.50 ;
                    case "XL" ->  (hamburgerCode == 0) ?  12.50 : hamburger.getBurgerPrice() + 2.50;
                    default -> throw new InvalidNameException("[Meal] Size not correct");
                };
    }

    public boolean addDrink(int drinkCode) throws InvalidNameException {
        this.drink = Drink.getDrink(drinkCode, hamburger.getSize().toUpperCase());
        this.totalPrice += drink.getPrice();
        return this.drink != null;
    }

    public void addSideItem (String type, boolean isMenu) {
        this.sideItem = new SideItem(type, isMenu ? 0.0 : 2.50);
        this.totalPrice += this.sideItem.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Hamburger getHamburger() {
        return hamburger;
    }

    public Drink getDrink() {
        return drink;
    }

    public SideItem getSideItem() {
        return sideItem;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "hamburger=" + hamburger +
                ", drink=" + drink +
                ", sideItem=" + sideItem +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
