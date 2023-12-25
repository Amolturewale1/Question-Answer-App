package questionAnswerApplication;

import java.util.List;

public interface QuestionService {

    int addQuestion(Question question);

    int removeQuestion(int questionId);

    int modifyQuestion(int questionId,String ques);

    List<Question> getQuestionById(int questionId);

    int modifyOption(Question newQuestion, int questionId);

    List<Question> displayAllQuestions();

    List<Login> getLogin(String id, String pass);
}
