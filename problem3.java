import java.util.*;

public class problem3 {
    static String[] mostPopular(String[] orders) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String item : orders)
            map.put(item, map.getOrDefault(item, 0) + 1);

        String best = orders[0];
        int max = 0;

        for (String item : orders) {
            if (map.get(item) > max) {
                max = map.get(item);
                best = item;
            }
        }

        return new String[]{best, String.valueOf(max)};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        System.out.println(Arrays.toString(mostPopular(orders)));
    }
}