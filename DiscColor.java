public enum DiscColor{
    EMPTY("--"),
    RED("R"),
    YELLOW("Y");

    private String symbol;

    DiscColor(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
