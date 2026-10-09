
class FileValidator {
    String validateFileExtension(String filename) {
        int dot = filename.lastIndexOf('.');

        if (dot == -1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dot + 1);

        if (extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("docx")
                || extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }
}

public class Problem3 {
    public static void main(String[] args) {
        FileValidator f = new FileValidator();

        System.out.println(f.validateFileExtension("Assignment1.PDF"));
        System.out.println(f.validateFileExtension("notes.txt"));
    }
}