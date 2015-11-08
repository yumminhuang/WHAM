package eventbrite.model;

/**
 * @Author: yummin
 * Date: 15/11/7
 */
public enum SubCategory {

    BEER(10001, "Beer", Category.FOOD_DRINK),
    WINE(10002, "Wine", Category.FOOD_DRINK);

    private final long id;
    private final String name;
    private final Category type;

    private SubCategory(long id, String name, Category type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the category name as used by the Eventbrite APIs.
     *
     * @return A String representing the category name.
     */
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return type;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return type.toString() + ": " + name;
    }
}
