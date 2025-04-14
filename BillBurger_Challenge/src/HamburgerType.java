public enum HamburgerType {

     BASE_HAMBURGER ( "Base Hamburger"),
     BILL_BURGER ( "Bill's Burger"),
     CRAZY_CHEESE_BBQ ( "Crazy Cheese BBQ"),
     CRAZY_CHEESE_CHICKEN_BBQ ( "Crazy Cheese Chicken BBQ"),
     PECORINO_ROMANO_SCAMORZA ( "The Pecorino Romano & Scamorza Burger"),
     BACON_KING ( "Bacon King"),
     BACON_KING_TRIPLE ( "Bacon King 3.0"),
     BACON_KING_TRIPLE_ONION ( "Bacon King 3.0 con Anelli di Cipolla Croccante"),
     CHICKEN_BACON_KING ( "Chicken Bacon King"),
     CRUNCHICKEN ( "Crunchicken"),
     PARMIGIANO_REGGIANO_BURGER ( "The Parmigiano Reggiano Burger"),
     WHOPPER ( "Whopper"),
     DELUXE_HAMBURGER ( "Deluxe Hamburger");

    private String description;

    HamburgerType(java.lang.String description) {
        this.description = description;
    }
    
    @Override
    public java.lang.String toString() {
        return description;
    }
}

enum Sauce {

    BILL_SAUCE ("Bill's Sauce"),
    BULLS_EYE_SAUCE ("Bull's Eye"),
    MAYO ("Mayonnaise"),
    MAYO_KETCHUP ("Mayonnaise & Ketchup");

    private String description;

    Sauce(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}


