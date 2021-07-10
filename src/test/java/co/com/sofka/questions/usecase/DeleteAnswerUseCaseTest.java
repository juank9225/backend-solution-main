package co.com.sofka.questions.usecase;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecasebusiness.DeleteAnswerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest
class DeleteAnswerUseCaseTest {

    @SpyBean
    private DeleteAnswerUseCase deleteAnswerUseCase;

    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("Borrar respuesta por id usuario, con datos validos")
    public void deleteAnswerTest(){

        var answerDTO = new AnswerDTO("1","1A","1BC","es un framework",false,0);

        var answer = new Answer();
        answer.setId("1");
        answer.setUserId("1A");
        answer.setQuestionId("1BC");
        answer.setAnswer("Por que es Jueves");
        answer.setModificada(false);
        answer.setVecesModificada(0);

        var question= new Question();
        question.setId("12");
        question.setQuestion("que fue primero");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("xxx");
        question.setAnswerDelete(0);

        when(answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())).thenReturn(Mono.just(answer));
        when(questionRepository.findById("1BC")).thenReturn(Mono.just(question));
        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));
        when(answerRepository.deleteById("1")).thenReturn(Mono.empty());

        var spy = deleteAnswerUseCase.apply(answerDTO);

        Assertions.assertEquals(spy.block(),null);

    }

}