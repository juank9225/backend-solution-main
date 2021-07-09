package co.com.sofka.questions.usecase;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


@SpringBootTest
class ModifyAnswerUseCaseTest {

    @SpyBean
    private ModifyAnswerUseCase modifyAnswerUseCase;
    @MockBean
    private AnswerRepository answerRepository;

    @Test
    public void modifyAnswer() {
        var answerDTO = new AnswerDTO("1","1A","1BC","Por que es Jueves",false,0);
        var answer = new Answer();
        answer.setId("1");
        answer.setUserId("1A");
        answer.setQuestionId("1BC");
        answer.setAnswer("Por que es Jueves");
        answer.setModificada(false);
        answer.setVecesModificada(0);

        when(answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())).thenReturn(Mono.just(answer));
        when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));

        var spy = modifyAnswerUseCase.apply(answerDTO);

        Assertions.assertEquals(spy.block().getUserId(),"1A");
        Assertions.assertEquals(spy.block().getModificada(),true);
    }
}