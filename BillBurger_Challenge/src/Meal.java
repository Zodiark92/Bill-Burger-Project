import javax.naming.InvalidNameException;

public class Meal {

    public static final int NUMBER_MAX_TOPPINGS = 3;
    public static final int NUMBER_MAX_DELUXE_TOPPINGS = 5;

    private Hamburger hamburger;
    private DrinkItem drinkItem;
    private SideItem sideItem;
    private double totalPrice;
    private double discount = 0.25;

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
        this.hamburger = Hamburger.getHamburger(hamburgerCode, Size.getSize(size));
        switch (hamburger.getSize()) {
            case M -> this.totalPrice = hamburger.getBurgerPrice();
            case L -> this.totalPrice = hamburger.getBurgerPrice() + 1.50;
            case XL -> this.totalPrice = hamburger.getBurgerPrice() + 2.50;
            default -> throw new InvalidNameException("[Meal] Size not correct");
        }
    }

    public Hamburger editHamburger(int hamburgerCode) throws InvalidNameException {
        this.hamburger = Hamburger.getHamburger(hamburgerCode, hamburger.getSize());
        return this.hamburger;
    }

    public boolean addTopping(int numberTopping, int toppingCode) throws InvalidNameException {
        boolean toppingAdded;
        switch (toppingCode) {
            case 0 -> toppingAdded = hamburger.addTopping(ToppingItem.BACON);
            case 1 -> toppingAdded = hamburger.addTopping(ToppingItem.CHEDDAR);
            case 2 -> toppingAdded = hamburger.addTopping(ToppingItem.TOMATO);
            case 3 -> toppingAdded = hamburger.addTopping(ToppingItem.SALAD);
            case 4 -> toppingAdded = hamburger.addTopping(ToppingItem.CRISPY_ONIONS);
            case 5 -> toppingAdded = hamburger.addTopping(ToppingItem.PARMIGIANO_REGGIANO);
            case 6 -> toppingAdded = hamburger.addTopping(ToppingItem.ROCKET);
            case 7 -> toppingAdded = hamburger.addTopping(ToppingItem.SCAMORZA);
            case 8 -> toppingAdded = hamburger.addTopping(ToppingItem.OREGANO);
            case 9 -> toppingAdded = hamburger.addTopping(ToppingItem.CRISPY_ONION_RINGS);
            case 10 -> toppingAdded = hamburger.addTopping(ToppingItem.CUCUMBERS);
            default -> throw new InvalidNameException();
        }
        if (toppingAdded && !hamburger.isDeluxe()) {
            this.totalPrice += hamburger.getAddingToppingPrice();
        }
        return toppingAdded;
    }

    public boolean removeTopping(int toppingCode) throws InvalidNameException {
        boolean toppingRemoved;
        switch (toppingCode) {
            case 0 -> toppingRemoved = hamburger.removeTopping(ToppingItem.BACON);
            case 1 -> toppingRemoved = hamburger.removeTopping(ToppingItem.CHEDDAR);
            case 2 -> toppingRemoved = hamburger.removeTopping(ToppingItem.TOMATO);
            case 3 -> toppingRemoved = hamburger.removeTopping(ToppingItem.SALAD);
            case 4 -> toppingRemoved = hamburger.removeTopping(ToppingItem.CRISPY_ONIONS);
            case 5 -> toppingRemoved = hamburger.removeTopping(ToppingItem.PARMIGIANO_REGGIANO);
            case 6 -> toppingRemoved = hamburger.removeTopping(ToppingItem.ROCKET);
            case 7 -> toppingRemoved = hamburger.removeTopping(ToppingItem.SCAMORZA);
            case 8 -> toppingRemoved = hamburger.removeTopping(ToppingItem.OREGANO);
            case 9 -> toppingRemoved = hamburger.removeTopping(ToppingItem.CRISPY_ONION_RINGS);
            case 10 -> toppingRemoved = hamburger.removeTopping(ToppingItem.CUCUMBERS);
            default -> throw new InvalidNameException();
        }
        if (toppingRemoved) {
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

    public double computeMealPrice(int hamburgerCode, Size size) throws InvalidNameException {
        return switch (size) {
            case M -> (hamburgerCode == 0) ? 9.90 : hamburger.getBurgerPrice();
            case L -> (hamburgerCode == 0) ? 11.30 : hamburger.getBurgerPrice() + 1.50;
            case XL -> (hamburgerCode == 0) ? 12.50 : hamburger.getBurgerPrice() + 2.50;
        };
    }

    public double computeMealPrice(boolean isMenu) throws InvalidNameException {
        this.totalPrice = computeMealPrice(hamburger.getHamburgerCode(), hamburger.getSize());
        boolean isDeluxe = hamburger.isDeluxe();

        for(ToppingItem ignored : hamburger.getAdditionalToppings()) {
            this.totalPrice += hamburger.getAddingToppingPrice();
        }

        if (drinkItem != null) {
            this.totalPrice += drinkItem.getPrice();
        }
        if (sideItem != null) {
            this.totalPrice += sideItem.getPrice();
        }

        if (isMenu) {
            this.totalPrice -= discount * totalPrice;
        }
        return this.totalPrice;
    }

    public boolean addDrink(int drinkCode) throws InvalidNameException {
        drinkItem = DrinkItem.getDrink(drinkCode, hamburger.getSize());
        totalPrice += drinkItem.getPrice();
        return drinkItem != null;
    }

    public void addSideItem(int code, boolean isMenu) throws InvalidNameException {
        this.sideItem = SideItem.getSideItem(code, isMenu);
        this.totalPrice += this.sideItem.getPrice();
        System.out.printf("Side item %s added. Total price: %.2f%n", sideItem, this.totalPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Hamburger getHamburger() {
        return hamburger;
    }

    public DrinkItem getDrink() {
        return drinkItem;
    }

    public SideItem getSideItem() {
        return sideItem;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        String descriptionMenu;
        descriptionMenu = "Meal: \n" +
                "Hamburger: " + hamburger;
        if (drinkItem != null) {
            descriptionMenu = descriptionMenu.concat("Drink: " + drinkItem + "\n");
        } else {
            descriptionMenu = descriptionMenu.concat("Drink: " + "none selected\n");
        }
        if (sideItem != null) {
            descriptionMenu = descriptionMenu.concat("Side Item: " + sideItem + "\n");
        } else {
            descriptionMenu = descriptionMenu.concat("Side Item: " + "none selected\n");
        }

        return descriptionMenu;
    }
}
