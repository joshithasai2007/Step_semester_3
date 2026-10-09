
class ISBN {
    String normalizeCode(String raw) {
        String code = raw.trim();

        if (code.length() < 3) {
            return code.toUpperCase();
        }

        return code.substring(0, 3).toUpperCase()
                + code.substring(3);
    }

    String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("[")
              .append(code.substring(0, 3))
              .append("] YEAR: 20")
              .append(code.substring(3, 5))
              .append(" | CATALOG: ")
              .append(code.substring(7, 13));

        return result.toString();
    }
}

public class Problem4 {
    public static void main(String[] args) {
        ISBN isbn = new ISBN();

        String code1 = isbn.normalizeCode(" pen2026004251 ");
        System.out.println(isbn.validateAndFormat(code1));

        String code2 = isbn.normalizeCode("12N2026004251");
        System.out.println(isbn.validateAndFormat(code2));
    }
}