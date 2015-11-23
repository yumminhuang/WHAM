package eventbrite.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public class Category {
    private static final Map<String, Integer> CATEGORY_TO_ID;
    private static final Map<String, ArrayList<String>> CATEGORY_TO_SUBCATEGORY;

    static {
        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        tempMap.put("Music", 103);
        tempMap.put("Travel & Outdoor", 109);
        tempMap.put("Food & Drink", 110);
        tempMap.put("Science & Technology", 102);
        tempMap.put("Seasonal & Holiday", 116);
        CATEGORY_TO_ID = Collections.unmodifiableMap(tempMap);
    }

    static {
        Map<String, ArrayList<String>> tempMap = new HashMap<String, ArrayList<String>>();
        ArrayList<String> subcategory = new ArrayList<String>();

        subcategory.add("Folk");
        subcategory.add("Metal");
        subcategory.add("Opera");
        subcategory.add("Pop");
        subcategory.add("Rock");
        tempMap.put("Music", subcategory);

        subcategory = new ArrayList<String>();
        subcategory.add("Hiking");
        subcategory.add("Rafting");
        subcategory.add("Kayaking");
        subcategory.add("Canoeing");
        subcategory.add("Climbing");
        tempMap.put("Travel & Outdoor", subcategory);

        subcategory = new ArrayList<String>();
        subcategory.add("Beer");
        subcategory.add("Wine");
        subcategory.add("Food");
        subcategory.add("Spirits");
        subcategory.add("Other");
        tempMap.put("Food & Drink", subcategory);

        subcategory = new ArrayList<String>();
        subcategory.add("Medicine");
        subcategory.add("Biotech");
        subcategory.add("Mobile");
        subcategory.add("Robotics");
        subcategory.add("Science");
        tempMap.put("Science & Technology", subcategory);

        subcategory = new ArrayList<String>();
        subcategory.add("Easter");
        subcategory.add("Halloween/Haunt");
        subcategory.add("Thanksgiving");
        subcategory.add("Christmas");
        subcategory.add("Channukah");
        tempMap.put("Seasonal & Holiday", subcategory);

        CATEGORY_TO_SUBCATEGORY = Collections.unmodifiableMap(tempMap);
    }

    /**
     * Find id for the given category name or subcategory name
     * @param name
     * @return
     */
    public static int getCategoryID(String name) {
        return CATEGORY_TO_ID.get(name);
    }

    /**
     * Find category name or subcategory name for the given id
     * @param id
     * @return
     */
    public static String getCategoryName(int id) {
        for (Map.Entry<String, Integer> entry : CATEGORY_TO_ID.entrySet())
            if (entry.getValue() == id)
                // Found it
                return entry.getKey();
        // Not found
        return null;
    }

    /**
     * Find all subcategory of the given category
     * @param categoryName
     * @return
     */
    public static List<String> allSubCategories(String categoryName) {
        return CATEGORY_TO_SUBCATEGORY.get(categoryName);
    }

    /**
     * List all available categories
     * @return
     */
    public static List<String> allCategories() {
        return new ArrayList<String>(CATEGORY_TO_ID.keySet());
    }
}