package co.com.sofka.questions.router;


import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecase.DeleteAnswerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteAnswerRouter {

    @Bean
    public RouterFunction<ServerResponse> eliminarQuestions(DeleteAnswerUseCase deleteAnswerUseCase){
        return route(DELETE("/deleteanswer").and(accept(MediaType.APPLICATION_JSON)),
                request ->request.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> deleteAnswerUseCase.apply(answerDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
                        .onErrorResume(error -> {
                            if(error instanceof IllegalAccessError){
                                return ServerResponse.badRequest().bodyValue("Su registro no es valido, verifique el usuario");
                            }
                            return ServerResponse.badRequest().build();
                        })
        );
    }
}
