import java.util.*;

public class ClassMarksAnalysis {
    public static void main(String[] args) {
        // Simulating multiple lines of student marks input
        String[] input = {
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        };

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        for (String line : input) {
            String name = line.substring(0, line.indexOf(" ["));
            String marksStr = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            String[] tokens = marksStr.split(", ");
            int[] marks = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                marks[i] = Integer.parseInt(tokens[i]);
            }
            names.add(name);
            marksList.add(marks);
        }

        int numStudents = names.size();
        int numSubjects = 3;
        int[] totals = new int[numStudents];
        double[] subjectSums = new double[numSubjects];

        for (int i = 0; i < numStudents; i++) {
            int[] marks = marksList.get(i);
            for (int j = 0; j < numSubjects; j++) {
                totals[i] += marks[j];
                subjectSums[j] += marks[j];
            }
        }

        // 1. Print totals
        System.out.print("Totals ");
        for (int i = 0; i < numStudents; i++) {
            System.out.print(names.get(i) + " " + totals[i] + (i == numStudents - 1 ? "" : ", "));
        }
        System.out.println();

        // 2. Print averages
        System.out.print("averages ");
        for (int j = 0; j < numSubjects; j++) {
            double avg = subjectSums[j] / numStudents;
            System.out.printf(Locale.US, "%.2f%s", avg, (j == numSubjects - 1 ? "" : ", "));
        }
        System.out.println();

        // 3. Find topper
        int maxTotal = -1;
        String topper = "";
        for (int i = 0; i < numStudents; i++) {
            if (totals[i] > maxTotal) {
                maxTotal = totals[i];
                topper = names.get(i);
            }
        }
        System.out.println("topper " + topper + " (" + maxTotal + ")");
    }
}