public class SideItem {
    private String type; //da sostituire con un enum
    private double price;

    public SideItem(String type, double price) {
        this.type = type;
        this.price = price;
    }

    //da aggiungere il get con lo switch

    public double getPrice() {
        return price;
    }
}
