import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

enum ToppingItem {

    DOUBLE_BACON("Double Bacon"),
    BACON("Bacon"),
    BACON_TRIPLE("Triple Bacon"),
    CHEDDAR_TRIPLE("Triple Cheddar"),
    CHEDDAR("Cheddar"),
    TOMATO("Tomato"),
    SALAD("Salad"),
    CRISPY_ONIONS("Crispy Onions"),
    ROCKET("Rocket"),
    PARMIGIANO_REGGIANO("Parmigiano Reggiano"),
    SCAMORZA("Scamorza"),
    OREGANO("Oregano"),
    CRISPY_ONION_RINGS("Crispy Onion Rings"),
    CUCUMBERS("Cucumbers");

    private String description;

    ToppingItem(java.lang.String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

public class Topping {

    private List<ToppingItem> hamburgerToppings = new ArrayList<>();

    public boolean addIngredient(ToppingItem topping) {

        ToppingItem tempTopping = topping;
        if(topping == ToppingItem.DOUBLE_BACON || topping == ToppingItem.BACON_TRIPLE){
            tempTopping = ToppingItem.BACON;
        } else if (topping == ToppingItem.CHEDDAR_TRIPLE) {
            tempTopping = ToppingItem.CHEDDAR;
        }

        if(!hamburgerToppings.contains(tempTopping) || !hamburgerToppings.contains(topping)) {
            hamburgerToppings.add(topping);
            hamburgerToppings.add(tempTopping);
            return true;
        }

        return false;
    }

    public boolean removeIngredient(ToppingItem topping) throws InvalidNameException {

        ToppingItem tempTopping = topping;
        if(topping == ToppingItem.DOUBLE_BACON || topping == ToppingItem.BACON_TRIPLE){
            tempTopping = ToppingItem.BACON;
        } else if (topping == ToppingItem.CHEDDAR_TRIPLE) {
            tempTopping = ToppingItem.CHEDDAR;
        }

        if(hamburgerToppings.contains(tempTopping) || hamburgerToppings.contains(topping)) {
            hamburgerToppings.remove(topping);
            hamburgerToppings.remove(tempTopping);
            return true;
        }

        return false;
    }

}


