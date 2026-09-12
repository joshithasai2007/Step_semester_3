class AccessChecker {

    // Extended to handle 5 accessor contexts
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // Protected allows cross-package access ONLY via the subclass's OWN reference type
                if ("SAME_CLASS".equals(accessorContext) 
                        || "SAME_PACKAGE".equals(accessorContext) 
                        || "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                // SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE is strictly DENIED
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }
}

public class Problem2 {
    public static void main(String[] args) {
        // Test 1: Subclass access via own instance type
        System.out.println("protected via OWN_TYPE: " + 
            AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")); 
        // Output: ALLOWED

        // Test 2: Subclass access via parent reference type
        System.out.println("protected via PARENT_TYPE: " + 
            AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); 
        // Output: DENIED
    }
}