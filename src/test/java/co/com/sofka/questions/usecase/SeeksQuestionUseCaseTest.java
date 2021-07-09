package co.com.sofka.questions.usecase;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SeeksQuestionUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private SeeksQuestionUseCase seeksQuestionUseCase;

    @Test
    public void seeksQuestionTest(){

        var questionDTO = new QuestionDTO("1","1A","Que es SpringBoot?","OPEN","Programming",0);

        var question = new Question();
        question.setId("1");
        question.setUserId("1A");
        question.setQuestion("Ques es SpringBoot");
        question.setType("OPEN");
        question.setCategory("Programming");
        question.setAnswerDelete(0);

        when(questionRepository.findByquestionLike(Mockito.any(String.class))).thenReturn(Flux.just(question));

        var resultado = seeksQuestionUseCase.seek("Que");

        Assertions.assertEquals(resultado.blockFirst().getQuestion(),"Ques es SpringBoot");
        Assertions.assertEquals(resultado.blockFirst().getId(),"1");
        Assertions.assertEquals(resultado.blockFirst().getUserId(),"1A");

    }

}