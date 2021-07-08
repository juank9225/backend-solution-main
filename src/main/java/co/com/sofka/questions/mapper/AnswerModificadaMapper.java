package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AnswerModificadaMapper {

    public Function<AnswerDTO, Answer> AnswerDtoModificadaToAnswer(String id) {
        return updateAnswer -> {
            var answer = new Answer();
            answer.setId(id);
            answer.setUserId(updateAnswer.getUserId());
            answer.setQuestionId(updateAnswer.getQuestionId());
            answer.setAnswer(updateAnswer.getAnswer());
            answer.setModificada(updateAnswer.getModificada());
            answer.setVecesModificada(updateAnswer.getVecesModificada());
            return answer;
        };
    }

    public Function<Answer,AnswerDTO> answerToAnswerDTOModificada(){
        return entity -> new AnswerDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestionId(),
                entity.getAnswer(),
                entity.getModificada(),
                entity.getVecesModificada());
    }
}
