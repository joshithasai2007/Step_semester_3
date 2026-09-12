import java.util.Arrays;

class MovieTicket {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public static void main(String[] args) {
        MovieTicket ticket = new MovieTicket("A1", "SCR-1", 12.50, "Inception");
        System.out.println("MovieTicket created for: " + ticket.movieTitle);
    }
}

class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) return "Allowed: 0 | Denied: 0";

        long allowed = Arrays.stream(attempts)
                .filter(attempt -> "ALLOWED".equals(classifyAccess(attempt[0], attempt[1])))
                .count();

        long denied = attempts.length - allowed;

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS")); // ALLOWED
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] batch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch)); // Allowed: 2 | Denied: 1
    }
}