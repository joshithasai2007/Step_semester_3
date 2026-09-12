import java.util.*;

// LibraryMember field requirements setup
class LibraryMember {
    private String membershipPin; // Reachable only within LibraryMember
    String branchCode;            // Package-private: reachable only in same package
    protected double finesOwed;   // Reachable by same package (and subclasses cross-package)
    public String displayName;    // Reachable from anywhere
}

class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        // LinkedHashMap guarantees fixed order for all 4 Java access modifiers
        Map<String, int[]> map = new LinkedHashMap<>();
        map.put("private", new int[]{0, 0});   // [0] = allowed, [1] = denied
        map.put("default", new int[]{0, 0});
        map.put("protected", new int[]{0, 0});
        map.put("public", new int[]{0, 0});

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt == null || attempt.length < 2) continue;
                String mod = attempt[0];
                String ctx = attempt[1];

                if (map.containsKey(mod)) {
                    String status = classifyAccess(mod, ctx);
                    if ("ALLOWED".equals(status)) {
                        map.get(mod)[0]++;
                    } else {
                        map.get(mod)[1]++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            if (!first) {
                sb.append(" | ");
            }
            int[] counts = entry.getValue();
            sb.append(entry.getKey())
              .append(": ")
              .append(counts[0])
              .append(" allowed / ")
              .append(counts[1])
              .append(" denied");
            first = false;
        }

        return sb.toString();
    }
}

public class Asg1 {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));    // ALLOWED
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));
        // Output: private: 1 allowed / 1 denied | default: 1 allowed / 1 denied | protected: 2 allowed / 0 denied | public: 1 allowed / 0 denied
    }
}
    

