package co.com.sofka.questions.router;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecase.ModifyAnswerUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ModifyAnswerRouter.class})
class ModifyAnswerRouterTest {

    @MockBean
    private ModifyAnswerUseCase modifyAnswerUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void modifyAnswerRouter() {
    var answerDTO = new AnswerDTO("1", "1A", "1BC", "Por que es Jueves", false, 0);
    Mockito.when(modifyAnswerUseCase.apply(answerDTO)).thenReturn(Mono.just(answerDTO));

    webTestClient.put().uri("/modifyanswer")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(answerDTO), AnswerDTO.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(AnswerDTO.class)
            .value(request -> {
                Assertions.assertThat(request.getUserId()).isEqualTo(answerDTO.getUserId());
            });
    }
}