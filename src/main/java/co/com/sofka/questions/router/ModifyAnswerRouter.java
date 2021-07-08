package co.com.sofka.questions.router;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecase.ModifyAnswerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ModifyAnswerRouter {

    @Bean
    public RouterFunction<ServerResponse> modifyAnswers(ModifyAnswerUseCase modifyAnswerUseCase){
        return route(PUT("/modifyanswer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> modifyAnswerUseCase.apply(answerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }
}
