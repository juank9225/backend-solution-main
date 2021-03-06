package co.com.sofka.questions.routerbusiness;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.routerbusiness.DeleteAnswerRouter;
import co.com.sofka.questions.usecasebusiness.DeleteAnswerUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeleteAnswerRouter.class})
class DeleteAnswerRouterTest {

    @MockBean
    private DeleteAnswerUseCase deleteAnswerUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Borrar usuario por id usuario")
    public void deleteAnswerTest(){
        var answerDTO = new AnswerDTO("1", "1A", "1BC", "Por que es Jueves", false, 0);

        Mockito.when(deleteAnswerUseCase.apply(answerDTO)).thenReturn(Mono.empty());

        webTestClient.delete().uri("/deleteanswer")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .value(response->{
                    Assertions.assertThat(response).isEqualTo(null);
                });

    }


}