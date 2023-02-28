import java.util.ArrayList;
import java.util.Scanner;

public class OnlineExamination {
    private static ArrayList<User> userList = new ArrayList<User>();
    private static User currentUser;
    private static int timer = 0;

    public static void main(String[] args) {
        userList.add(new User("Alice", "alice123", "Alice Smith"));
        userList.add(new User("Bob", "bob123", "Bob Johnson"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.out.println("Exiting.........Exiting.........Exiting...................");
                    System.exit(0);
                    break;
                default:
                    System.out.println("INVALID CHOICE!!!!!!!");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                examMenu(scanner);
                break;
            }
        }

        System.out.println("=============INVALID USERNAME OR PASSWORD!!!============");
    }

    private static void examMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Update Profile");
            System.out.println("2. Select Answers for MCQs");
            System.out.println("3. Submit");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    updateProfile(scanner);
                    break;
                case 2:
                    selectAnswers(scanner);
                    break;
                case 3:
                    submit();
                    break;
                case 4:
                    System.out.println("..............Logging out................");
                    currentUser = null;
                    return;
                default:
                    System.out.println("INVALID CHOICE!!!!!!!");
            }
        }
    }

    private static void updateProfile(Scanner scanner) {
        System.out.println("Enter new name:");
        String name = scanner.next();
        System.out.println("Enter new password:");
        String password = scanner.next();

        currentUser.setName(name);
        currentUser.setPassword(password);

        System.out.println("..............Profile updated successfully!................");
    }

    private static void selectAnswers(Scanner scanner) {
        System.out.println("=======================Select the answer for each question:======================");

        for (Question question : currentUser.getQuestions()) {
            System.out.println(question.getText());

            for (int i = 0; i < question.getAnswers().length; i++) {
                System.out.println((i + 1) + ". " + question.getAnswers()[i]);
            }

            int answer = scanner.nextInt();
            question.setSelectedAnswer(answer - 1);
        }
    }

    private static void submit() {
        int score = 0;

        for (Question question : currentUser.getQuestions()) {
            if (question.getSelectedAnswer() == question.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("******************You scored " + score + " out of " + currentUser.getQuestions().size() + "!****************");
        currentUser = null;
    }

    private static class User {
        private String username;
        private String password;
        private String name;
        private ArrayList<Question> questions = new ArrayList<Question>();

        public User(String username, String password, String name) {
            this.username = username;
            this.password = password;
            this.name = name;

            // populate questions
            questions.add(new Question("----------What is the capital of France?----------", new String[]{"London", "Paris", "Berlin", "Madrid"}, 1));
            questions.add(new Question("----------What is the largest planet in our solar system?--------", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 2));
            questions.add(new Question("----------What is the tallest mammal?---------", new String[]{"Hippopotamus", "Elephant", "Giraffe", "Lion"}, 2));
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public ArrayList<Question> getQuestions() {
            return questions;
        }
    }

    private static class Question {
        private String text;
        private String[] answers;
        private int correctAnswer;
        private int selectedAnswer = -1;

        public Question(String text, String[] answers, int correctAnswer) {
            this.text = text;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        public String getText() {
            return text;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public int getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(int selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }
    }
}

