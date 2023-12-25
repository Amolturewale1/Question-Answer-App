package questionAnswerApplication;

public class Question {
    private int question_id;
    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String ans;

    public Question() {

    }



    public Question(String question, String op1, String op2, String op3, String ans) {
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.ans = ans;
    }

    public Question(int question_id, String question, String op1, String op2, String op3, String ans) {
        this.question_id = question_id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.ans = ans;
    }

    public int getQuestion_id() {
        return question_id;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
