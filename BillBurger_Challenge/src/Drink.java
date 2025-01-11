import javax.naming.InvalidNameException;

public class Drink {
    private int drinkCode;
    private String drinkDesc;
    private String size;
    protected double price;

    public Drink(int drinkCode, String drinkDesc, String size) {
        this.drinkCode = drinkCode;
        this.drinkDesc = drinkDesc;
        this.size = size;
    }

    public static Drink getDrink(int code, String size) throws InvalidNameException {
        return switch (code){
            case 0 -> new Water();
            case 1 -> new CocaCola(size);
            case 2 -> new OrangeSoda(size);
            case 3 -> new Beer(size);
            default -> throw new InvalidNameException("Hamburger not found");
        };
    }

    public double getPrice() {
        return price;
    }

    public String getDrinkDesc() {
        return drinkDesc;
    }
}

class Water extends Drink{

    public Water() {
        super(0, Constants.WATER, "0.5L");
        this.price = 1.5;
    }
}

class CocaCola extends Drink{

    public CocaCola(String size) {
        super(1, Constants.COCA_COLA, size);
        switch(size){
            case "M" -> this.price = 2.80;
            case "L" -> this.price = 3.50;
            case "XL" -> this.price = 4.20;
        }
    }
}

class OrangeSoda extends Drink {
    public OrangeSoda(String size) {
        super(2, Constants.ORANGE_SODA, size);
        switch(size){
            case "M" -> this.price = 2.80;
            case "L" -> this.price = 3.50;
            case "XL" -> this.price = 4.20;
        }
    }
}

class Beer extends Drink {
    public Beer(String size) {
        super(3, Constants.BEER, size);
        switch(size){
            case "M" -> this.price = 3.50;
            case "L" -> this.price = 4.20;
            case "XL" -> this.price = 5.50;
        }
    }
}


