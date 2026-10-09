import java.util.*;

public class Main {
    static int[] findTopper(int[][] marks) {
        int bestIndex = 0, bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++)
                total += marks[i][j];

            if (total > bestTotal) {
                bestTotal = total;
                bestIndex = i;
            }
        }
        return new int[]{bestIndex, bestTotal};
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        System.out.println(Arrays.toString(findTopper(marks)));
    }
}