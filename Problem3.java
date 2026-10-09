
public class Problem3 {
    static void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) {
            System.out.println("No signal readings");
            return;
        }

        char current = signalLog.charAt(0);
        char longestColor = current;

        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == current) {
                count++;
            } else {
                current = signalLog.charAt(i);
                count = 1;
            }

            if (count > maxCount) {
                maxCount = count;
                longestColor = current;
            }
        }

        System.out.println("Longest Streak: '" + longestColor
                + "' repeated " + maxCount + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}