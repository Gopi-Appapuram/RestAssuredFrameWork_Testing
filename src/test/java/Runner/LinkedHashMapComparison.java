package Runner;

import java.util.LinkedHashMap;
import java.util.Map;


public class LinkedHashMapComparison {
    public static void main(String[] args) {

        // Create two LinkedHashMap instances
        LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
        LinkedHashMap<String, String> map2 = new LinkedHashMap<>();

        // Populate map1
        map1.put("Key1", "Value1");
        map1.put("Key2", "Value2");

        // Populate map2
        map2.put("Key1", "Value1");
        map2.put("Key2", "Value3"); // Different value for comparison

        System.out.println(map1);
        System.out.println();

        // Compare the LinkedHashMap instances
        boolean areEqual = compareLinkedHashMaps(map1, map2);

        // Print the comparison result
        if (areEqual) {
            System.out.println("The LinkedHashMap instances are equal.");
        } else {
            System.out.println("The LinkedHashMap instances are not equal.");
        }


    }
    // Method to compare two LinkedHashMap instances
    public static boolean compareLinkedHashMaps(LinkedHashMap<String, String> map1, LinkedHashMap<String, String> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value1 = entry.getValue();
            String value2 = map2.get(key);

            if (value2 == null || !value1.equals(value2)) {
                return false;
            }
        }

        return true;
    }
}

