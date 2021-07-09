package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.SeeksQuestionUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SeekQuestionRouter.class})
class SeekQuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SeeksQuestionUseCase seeksQuestionUseCase;

    @Test
    public void buscarQuestionTest(){

        QuestionDTO question = new QuestionDTO();
        question.setId("1111");
        question.setUserId("xxxx");
        question.setQuestion("cual es el sentido de la vida");
        question.setType("OPEN");
        question.setCategory("yyyy");

        Mockito.when(seeksQuestionUseCase.seek("cual")).thenReturn(Flux.just(question));

        webTestClient.get()
                .uri("/consultar/question/{question}","cual")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(response->{
                    Assertions.assertThat(response.get(0).getQuestion()).isEqualTo(question.getQuestion());
                    Assertions.assertThat(response.get(0).getId()).isEqualTo(question.getId());
                    Assertions.assertThat(response.get(0).getUserId()).isEqualTo(question.getUserId());
                });
    }

}