import javax.naming.InvalidNameException;

enum Drink {
    
     WATER ( "Water"),
     COCA_COLA ( "Coca Cola"),
     ORANGE_SODA ( "Orange Soda"),
     BEER ( "Beer");

     private String description;

    Drink(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}


public class DrinkItem {

    private Drink drink;
    private Size size;
    protected double price;

    public DrinkItem(Drink drink, Size size) {
        this.drink = drink;
        this.size = size;
    }

    public static DrinkItem getDrink(int code, Size size) throws InvalidNameException {
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

    @Override
    public String toString() {
        return drink.toString();
    }
}

class Water extends DrinkItem {

    public Water() {
        super(Drink.WATER, Size.M);
        this.price = 1.5;
    }
}

class CocaCola extends DrinkItem {

    public CocaCola(Size size) {
        super( Drink.COCA_COLA, size);
        switch(size){
            case M -> this.price = 2.80;
            case L -> this.price = 3.50;
            case XL -> this.price = 4.20;
        }
    }
}

class OrangeSoda extends DrinkItem {
    public OrangeSoda(Size size) {
        super (Drink.ORANGE_SODA, size);
        switch(size){
            case M -> this.price = 2.80;
            case L -> this.price = 3.50;
            case XL -> this.price = 4.20;
        }
    }
}

class Beer extends DrinkItem {
    public Beer(Size size) {
        super(Drink.BEER, size);
        switch(size){
            case M -> this.price = 3.50;
            case L -> this.price = 4.20;
            case XL -> this.price = 5.50;
        }
    }
}


