package questionAnswerApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionImp implements QuestionService {
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questiondb?user=root&password=tiger");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public int addQuestion(Question question) {
        int n=0;
        String addQuery="insert into question_info (question ,option1,option2,option3,answer) values (?,?,?,?,?)";
        try {
            PreparedStatement pstmt=conn.prepareStatement(addQuery);
            pstmt.setString(1,question.getQuestion());
            pstmt.setString(2,question.getOp1());
            pstmt.setString(3,question.getOp2());
            pstmt.setString(4,question.getOp3());
            pstmt.setString(5,question.getAns());
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid Input");
        }
        return n;
    }

    @Override
    public int removeQuestion(int questionId) {
        int n=0;
        String removeQuery="delete from question_info where question_id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(removeQuery);
            pstmt.setInt(1,questionId);
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid Input !!");
        }
        return n;
    }

    @Override
    public int modifyQuestion(int questionId,String ques) {
        int n=0;
        String updateQuery="update question_info set question=? where question_id=? ";
        try {
            PreparedStatement pstmt= conn.prepareStatement(updateQuery);
            pstmt.setString(1,ques);
            pstmt.setInt(2,questionId);
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid Input !!");
        }
        return n;
    }

    @Override
    public List<Question> getQuestionById(int questionId) {
        String displayQuery="select * from question_info where question_id=?";
        List<Question> questionList=new ArrayList<>();
        try {
            PreparedStatement pstmt= conn.prepareStatement(displayQuery);
            pstmt.setInt(1,questionId);
            ResultSet rs=pstmt.executeQuery();

            while (rs.next()){
            Question question=new Question(
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                questionList.add(question);
            }
        } catch (SQLException e) {
            System.err.println("Invalid Display Query ");
        }
        return questionList;
    }

    @Override
    public int modifyOption(Question newQuestion, int questionId) {
        String modifyOptQuery="update question_info set option1=?,option2=?,option3=?,answer=? where question_id=?";
        try {
            PreparedStatement pstmt= conn.prepareStatement(modifyOptQuery);
            pstmt.setString(1,newQuestion.getOp1());
            pstmt.setString(2,newQuestion.getOp2());
            pstmt.setString(3,newQuestion.getOp3());
            pstmt.setString(4,newQuestion.getAns());
            pstmt.setInt(5,questionId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid Input !!");
        }

        return 0;
    }

    @Override
    public List<Question> displayAllQuestions() {
        String displayAllQuery="select * from question_info";
        List<Question> allQuestionList=new ArrayList<>();

        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(displayAllQuery);

            while (rs.next()){
                Question allQuestion=new Question(
                        rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));

                allQuestionList.add(allQuestion);
            }
        } catch (SQLException e) {
            System.err.println("Invalid Input !!");
        }
        return allQuestionList;
    }

    @Override
    public List<Login> getLogin(String id, String pass) {
        List<Login> login=new ArrayList<>();
        String getQuery="Select * from login";
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs=stmt.executeQuery(getQuery);

            while (rs.next()){
                Login l=new Login(rs.getString(1),rs.getString(2));
                login.add(l);
            }
        } catch (SQLException e) {
            System.err.println("Invalid Input");
        }

        return login;
    }
}
