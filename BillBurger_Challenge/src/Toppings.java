import javax.naming.InvalidNameException;

public class Toppings {
    private boolean baconDouble;
    private boolean bacon;
    private boolean cheddar;
    private boolean tomato;
    private boolean salad;
    private boolean crispyOnions;
    private boolean parmisanCheese;
    private boolean rocket;
    private boolean scamorza;
    private boolean oregano;
    private boolean baconTriple;
    private boolean cheddarTriple;
    private boolean onionRings;
    private boolean cucumbers;

    public boolean addIngredient(String description) throws InvalidNameException {

        return switch (description.toUpperCase()) {

            case "DOUBLE BACON" -> {
                if (!baconDouble && !bacon) {
                    this.baconDouble = true;
                    this.bacon = true;
                    yield true;
                }
                yield false;
            }
            case "BACON" -> {
                if (!bacon) {
                    this.bacon = true;
                    yield true;
                }
                yield false;
            }
            case "CHEDDAR" -> {
                if (!cheddar) {
                    this.cheddar = true;
                    yield true;
                }
                yield false;
            }
            case "SALAD" -> {
                if (!salad) {
                    this.salad = true;
                    yield true;
                }
                yield false;
            }
            case "TOMATO" -> {
                if (!tomato) {
                    this.tomato = true;
                    yield true;
                }
                yield false;
            }
            case "ROCKET" -> {
                if (!rocket) {
                    this.rocket = true;
                    yield true;
                }
                yield false;
            }
            case "CRISPY_ONIONS" -> {
                if (!crispyOnions) {
                    this.crispyOnions = true;
                    yield true;
                }
                yield false;
            }
            case "CRISPY_ONION_RINGS" -> {
                if (!onionRings) {
                    this.onionRings = true;
                    yield true;
                }
                yield false;
            }
            case "BACON_TRIPLE" -> {
                if (!baconTriple && !bacon) {
                    this.baconTriple = true;
                    this.bacon = true;
                    yield true;
                }
                yield false;
            }
            case "CHEDDAR_TRIPLE" -> {
                if (!cheddarTriple && !cheddar) {
                    this.cheddarTriple = true;
                    this.cheddar = true;
                    yield true;
                }
                yield false;
            }
            case "PARMIGIANO REGGIANO" -> {
                if (!parmisanCheese) {
                    this.parmisanCheese = true;
                    yield true;
                }
                yield false;
            }
            case "SCAMORZA" -> {
                if (!scamorza) {
                    this.scamorza = true;
                    yield true;
                }
                yield false;
            }
            case "OREGANO" -> {
                if (!oregano) {
                    this.oregano = true;
                    yield true;
                }
                yield false;
            }

            case "CUCUMBERS" -> {
                if (!cucumbers) {
                    this.cucumbers = true;
                    yield true;
                }
                yield false;
            }

            default -> {
                System.out.println("[Toppings] Topping not found");
                throw new InvalidNameException("Topping not found in the " + this.getClass().getSimpleName() + " class");
            }
        };
    }

    public boolean removeIngredient(String description) throws InvalidNameException {

       return switch (description.toUpperCase()) {
           case "DOUBLE BACON" -> {
               if (baconDouble && bacon) {
                   this.baconDouble = false;
                   this.bacon = false;
                   yield true;
               }
               yield false;
           }
           case "BACON" -> {
               if (bacon) {
                   this.bacon = false;
                   yield true;
               }
               yield false;
           }
           case "CHEDDAR" -> {
               if (cheddar) {
                   this.cheddar = false;
                   yield true;
               }
               yield false;
           }
           case "SALAD" -> {
               if (salad) {
                   this.salad = false;
                   yield true;
               }
               yield false;
           }
           case "TOMATO" -> {
               if (tomato) {
                   this.tomato = false;
                   yield true;
               }
               yield false;
           }
           case "ROCKET" -> {
               if (rocket) {
                   this.rocket = false;
                   yield true;
               }
               yield false;
           }
           case "CRISPY_ONIONS" -> {
               if (crispyOnions) {
                   this.crispyOnions = false;
                   yield true;
               }
               yield false;
           }
           case "CRISPY_ONION_RINGS" -> {
               if (onionRings) {
                   this.onionRings = false;
                   yield true;
               }
               yield false;
           }
           case "BACON_TRIPLE" -> {
               if (baconTriple && bacon) {
                   this.baconTriple = false;
                   this.bacon = false;
                   yield true;
               }
               yield false;
           }
           case "CHEDDAR_TRIPLE" -> {
               if (cheddarTriple && cheddar) {
                   this.cheddarTriple = false;
                   this.cheddar = false;
                   yield true;
               }
               yield false;
           }
           case "PARMIGIANO REGGIANO" -> {
               if (parmisanCheese) {
                   this.parmisanCheese = false;
                   yield true;
               }
               yield false;
           }
           case "SCAMORZA" -> {
               if (scamorza) {
                   this.scamorza = false;
                   yield true;
               }
               yield false;
           }
           case "OREGANO" -> {
               if (oregano) {
                   this.oregano = false;
                   yield true;
               }
               yield false;
           }

           case "CUCUMBERS" -> {
               if (cucumbers) {
                   this.cucumbers = false;
                   yield true;
               }
               yield false;
           }
            default -> {
                System.out.println("Topping not found");
                throw new InvalidNameException("Topping not found in the " + this.getClass().getSimpleName() + " class");
            }
        };
    }

    @Override
    public String toString() {
        return "Toppings{" +
                "baconDouble=" + baconDouble +
                ", bacon=" + bacon +
                ", cheddar=" + cheddar +
                ", tomato=" + tomato +
                ", salad=" + salad +
                ", crispyOnions=" + crispyOnions +
                ", parmiggianoReggiano=" + parmisanCheese +
                ", rocket=" + rocket +
                ", scamorza=" + scamorza +
                ", oregano=" + oregano +
                ", baconTriple=" + baconTriple +
                ", cheddarTriple=" + cheddarTriple +
                ", onionRings=" + onionRings +
                ", cucumbers=" + cucumbers +
                '}';
    }
}
