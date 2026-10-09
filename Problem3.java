
import java.util.*;

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

abstract class Question {
    String text;
    int marks;

    Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    abstract boolean checkAnswer(String answer);
}

class MCQ extends Question {
    String correct;

    MCQ(String text, int marks, String correct) {
        super(text, marks);
        this.correct = correct;
    }

    boolean checkAnswer(String answer) {
        return correct.equalsIgnoreCase(answer);
    }
}

class TrueFalse extends Question {
    String correct;

    TrueFalse(String text, int marks, String correct) {
        super(text, marks);
        this.correct = correct;
    }

    boolean checkAnswer(String answer) {
        return correct.equalsIgnoreCase(answer);
    }
}

class Examination {
    String title;
    List<Question> questions = new ArrayList<>();

    Examination(String title) {
        this.title = title;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    Student student;
    Examination exam;
    Map<Question, String> answers = new LinkedHashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        System.out.println(exam.title + " started by " + student.name);
    }

    void answer(int index, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        if (index < 0 || index >= exam.questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        answers.put(exam.questions.get(index), answer);
        System.out.println("Answer recorded for Question " + (index + 1));
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        int total = 0, score = 0;

        System.out.println(exam.title + " submitted by " + student.name);

        for (int i = 0; i < exam.questions.size(); i++) {
            Question q = exam.questions.get(i);
            total += q.marks;
            boolean correct = q.checkAnswer(
                    answers.getOrDefault(q, ""));

            if (correct) {
                score += q.marks;
            }

            System.out.println("Question " + (i + 1) + ": " +
                    (correct ? "Correct (" + q.marks + " points)"
                             : "Incorrect (0 points)"));
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Student s = new Student("Student 1");
        Examination exam = new Examination("Exam A");

        exam.addQuestion(new MCQ("Choose the correct option",
                5, "C"));
        exam.addQuestion(new TrueFalse("Java is platform independent",
                5, "False"));

        Attempt a = new Attempt(s, exam);
        a.answer(0, "C");
        a.answer(1, "True");
        a.submit();
        a.answer(0, "A");
    }
}