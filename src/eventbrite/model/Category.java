package eventbrite.model;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public enum Category {
    MUSIC(103, "Music"),

    BUSINESS(101, "Business"),

    FOOD_DRINK(110, "Food & Drink"),

    COMMUNITY(113, "Community"),

    ARTS(105, "Arts"),

    FILM_MEDIA(104, "Film & Media"),

    SPORTS_FITNESS(108, "Sports & Fitness"),

    HEALTH(107, "Health"),

    SCIENCE_TECH(102, "Science & Tech"),

    TRAVEL_OUTDOOR(109, "Travel & Outdoor"),

    CHARITY_CAUSES(111, "Charity & Causes"),

    SPIRITUALITY(114, "Spirituality"),

    FAMILY_EDUCATION(115, "Family & Education"),

    HOLIDAY(116, "Holiday"),

    GOVERNMENT(112, "Government"),

    FASHION(106, "Fashion"),

    HOME_LIFESTYLE(117, "Home & Lifestyle"),

    AUTO_BOAT_AIR(118, "Auto, Boat & Air"),

    HOBBIES(119, "Hobbies"),

    OTHER(199, "Other");


    private final long id;
    private final String name;

    private Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the category name as used by the Eventbrite APIs.
     *
     * @return A String representing the category name.
     */
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public static Category findIdByCategory(String keyword) {
        for(Category v : values()){
            if( v.name.equals(keyword)){
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
