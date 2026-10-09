
class TextCounter {
    void countVowelsAndConsonants(String text) {
        int vowels = 0, consonants = 0;

        text = text.toLowerCase();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels
                + " | Consonants: " + consonants);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        TextCounter t = new TextCounter();
        t.countVowelsAndConsonants("Java Programming");
    }
}