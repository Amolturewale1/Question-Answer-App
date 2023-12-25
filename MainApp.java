package questionAnswerApplication;


import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static Scanner sc=new Scanner(System.in);
    public static QuestionService service=new QuestionImp();
    public static void main(String[] args) {
        System.out.println("Select Options");
        System.out.println("1.Add Question ");
        System.out.println("2.Remove Question ");
        System.out.println("3.Update Question");
        System.out.println("4.Display All Questions ");
        System.out.println("5.Take Test");
        System.out.println("6.Exit");
        int ch=sc.nextInt();

        if (ch<5){
            System.out.println("Enter Login Id ");
            String id= sc.next();
            System.out.println("Enter Password");
            String pass=sc.next();
            List<Login> loginInfo=service.getLogin(id,pass);

            for (Login l:loginInfo){
                if (!(l.getId().equals(id) && l.getPassword().equals(pass))){
                    System.out.println("Login Unsuccessful");
                    System.exit(0);
                }
            }
        }


        switch (ch){
            case 1:
                addQuestion();
                break;
            case 2:
                removeQuestion();
                break;
            case 3:
                updateQuestion();
                break;
            case 4:
                displayAllQuestions();
                break;
            case 5:
                takeTest();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
        }
        main(args);

    }


    private static void addQuestion() {
        System.out.println("Enter Question OR Enter 0 For Back");
        sc.nextLine();
        String ques= sc.nextLine();
        if (ques.equals("0")){
            main(null);
        }
        System.out.println("Enter option 1");
        String op1= sc.nextLine();
        System.out.println("Enter option 2");
        String op2= sc.nextLine();
        System.out.println("Enter Option 3");
        String op3= sc.nextLine();
        System.out.println("Enter Answer ");
        String ans= sc.nextLine();

        Question question=new Question(ques,op1,op2,op3,ans);
        int n=service.addQuestion(question);
        System.out.println(n+" Question Added !!");
        System.out.println("\t\t");
        addQuestion();


    }

    private static void removeQuestion() {
        System.out.println("Enter Question Id");
        int questionId= sc.nextInt();

        int n=service.removeQuestion(questionId);
        System.out.println(n+" Questions Removed !!");

    }
    private static void updateQuestion() {
        System.out.println("1.Modify Question ");
        System.out.println("2.Modify Option ");
        System.out.println("3.<--back");
        int ch= sc.nextInt();
        switch (ch){
            case 1:
                modifyQuestion();
                break;
            case 2:
                modifyOption();
                break;
            case 3:
                return;
        }
        updateQuestion();
    }

    private static void modifyQuestion() {
        System.out.println("Enter Question Id");
        int questionId= sc.nextInt();
        System.out.println("Enter Question");
        String ques= sc.nextLine();
        ques= sc.nextLine();

        int n=service.modifyQuestion(questionId,ques);
        System.out.println(n+" Question Updated ");
        System.out.println("\n\n");

    }

    private static void modifyOption() {
        System.out.println("Enter Question Id");
        int questionId= sc.nextInt();
        List<Question> displayQuesList=service.getQuestionById(questionId);
        System.out.println("Question_Id\t| Question \t| Option1 \t| Option2 \t| Option3 \t| Answer");
        for (Question q:displayQuesList) {
            System.out.println(
                    q.getQuestion_id() + " \t| " + q.getQuestion() + " \t| " + q.getOp1() + " \t| " + q.getOp2() + " \t| " + q.getOp3() + " \t| " + q.getAns());

            System.out.println("Enter Option1");
            String opt1 = sc.nextLine();
            opt1 = sc.nextLine();
            System.out.println("Enter Option2");
            String opt2 = sc.nextLine();
            System.out.println("Enter Option3");
            String opt3 = sc.nextLine();
            System.out.println("Enter Answer");
            String ans = sc.nextLine();
            Question newQuestion = new Question(q.getQuestion(),opt1,opt2,opt3,ans);

            int n=service.modifyOption(newQuestion,questionId);
            System.out.println(n+ " Options Modified !!");
            System.out.println("\t\t");
        }

    }
    private static void displayAllQuestions() {

        List<Question> displayAll=service.displayAllQuestions();

        for (Question q:displayAll){
            System.out.println("Q"+q.getQuestion_id()+". "+q.getQuestion());
            System.out.println("1. "+q.getOp1());
            System.out.println("2. "+q.getOp2());
            System.out.println("3. "+q.getOp3());
            System.out.println("--> "+q.getAns());
            System.out.println("---------------------------------------------------");
        }
        System.out.println("\t\t");

    }


    private static void takeTest() {
        List<Question> testList=service.displayAllQuestions();
        int marks=0;
        int count=0;

        System.out.println("Ready For Test");
        String ans= sc.nextLine();
        for (Question q:testList){
            System.out.println("Q"+q.getQuestion_id()+". "+q.getQuestion());
            System.out.println("1. "+q.getOp1());
            System.out.println("2. "+q.getOp2());
            System.out.println("3. "+q.getOp3());
            System.out.println("Enter Your Answer");
            ans= sc.nextLine();
            System.out.println("----------------------------------------------");
            if (ans.equals(q.getAns())){
                marks+=5;
                count++;
            }else {
                marks-=2;
            }
        }
        System.out.println("Total Correct Answers "+count);
        System.out.println("Total Marks Are "+marks+" Out Of 100");
        System.exit(0);
    }
}
