class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)
                        || "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                // Protected access via parent type in cross-package subclass is DENIED
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) return "None Denied";

        for (int i = 0; i < attempts.length; i++) {
            if (attempts[i] == null || attempts[i].length < 2) continue;
            String mod = attempts[i][0];
            String ctx = attempts[i][1];

            if ("DENIED".equals(classifyAccess(mod, ctx))) {
                // Early exit on the very first denial
                return mod + " via " + ctx + " (attempt #" + (i + 1) + ")";
            }
        }
        return "None Denied";
    }
}

public class Asg2 {
    public static void main(String[] args) {
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts1));
        // Output: protected via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE (attempt #2)

        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts2));
        // Output: None Denied
    }
}