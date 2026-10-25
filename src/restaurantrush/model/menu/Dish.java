package restaurantrush.model.menu;

/** A single food item, e.g. Salad ($9, 1 unit), Burger ($15, 1 unit), Pasta ($18, 2 units). */
public class Dish extends MenuItem {
    private final int priceCents;
    private final int prepUnits;

    public Dish(String name, int priceCents, int prepUnits) {
        super(name);
        if (priceCents < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (prepUnits < 1) throw new IllegalArgumentException("Prep units must be at least 1");
        this.priceCents = priceCents;
        this.prepUnits = prepUnits;
    }

    @Override
    public int getPriceCents() {
        return priceCents;
    }

    @Override
    public int getPrepUnits() {
        return prepUnits;
    }
}
