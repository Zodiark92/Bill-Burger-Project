import javax.naming.InvalidNameException;

enum Item {

    FRIES_WITH_KETCHUP("Fries With Ketchup"),
    BILL_NUGGETS("Bill Nuggets"),
    FRIES_WITH_MAYO("Fries With Mayo"),
    ONION_RINGS("Onion Rings");

    private String description;

    Item(java.lang.String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return description;
    }
}

public class SideItem {
    private Item type;
    private double price;

    public SideItem(Item type, double price) {
        this.type = type;
        this.price = price;
    }

    public static SideItem getSideItem(int sideItemCode, boolean isMenu) throws InvalidNameException {
        return switch (sideItemCode) {
            case 0 -> new SideItem(Item.FRIES_WITH_KETCHUP, isMenu ? 0.0 : 2.5);
            case 1 -> new SideItem(Item.FRIES_WITH_MAYO, isMenu ? 0.0 : 2.5);
            case 2 -> new SideItem(Item.ONION_RINGS, isMenu ? 0.0 : 2.5);
            case 3 -> new SideItem(Item.BILL_NUGGETS, isMenu ? 0.0 : 2.5);
            default -> throw new InvalidNameException("Unexpected value: " + sideItemCode);
        };
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
