import javax.naming.InvalidNameException;

public class SideItem {
    private String type; //da sostituire con un enum
    private double price;

    public SideItem(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public static SideItem getSideItem (int sideItemCode, boolean isMenu) throws InvalidNameException {
        return switch(sideItemCode) {
            case 0 -> new SideItem(Constants.FRIES_WITH_KETCHUP, isMenu ? 0.0 : 2.5);
            case 1 -> new SideItem(Constants.FRIES_WITH_MAYO, isMenu ? 0.0 : 2.5);
            case 2 -> new SideItem(Constants.ONION_RINGS, isMenu ? 0.0 : 2.5);
            case 3 -> new SideItem(Constants.BILL_NUGGETS, isMenu ? 0.0 : 2.5);
            default -> throw new InvalidNameException("Unexpected value: " + sideItemCode);
        };
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type;
    }
}
