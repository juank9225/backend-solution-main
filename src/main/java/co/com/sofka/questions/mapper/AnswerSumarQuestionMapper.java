package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AnswerSumarQuestionMapper {

    public Function<QuestionDTO, Question> answerDTOSumarQuestion(String id){
        return updateQuestion->{
            var question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setQuestion(updateQuestion.getQuestion());
            question.setType(updateQuestion.getType());
            question.setCategory(updateQuestion.getCategory());
            question.setAnswerDelete(updateQuestion.getAnswerDelete()+1);

            return question;
        };
    }
}
