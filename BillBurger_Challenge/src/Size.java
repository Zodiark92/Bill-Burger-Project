public enum Size {

    M, L, XL;

    public static Size getSize(String size) {
        return switch(size) {
            case "M" -> M;
            case "L" -> L;
            case "XL" -> XL;
            default -> null;
        };
    }
}
