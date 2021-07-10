package co.com.sofka.questions.usecasebusiness;

import co.com.sofka.questions.mapper.RespuestaEliminarMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.RespuestaEliminarDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteAnswerUseCase{
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final RespuestaEliminarMapper respuestaEliminarMapper;

    @Autowired
    public DeleteAnswerUseCase(AnswerRepository answerRepository, QuestionRepository questionRepository, RespuestaEliminarMapper respuestaEliminarMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.respuestaEliminarMapper = respuestaEliminarMapper;
    }


    public Mono<Void> apply(AnswerDTO answerDTO) {
        return answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())
                .map(respuestaEliminarMapper.answerToRespuesta()).switchIfEmpty(Mono.error(new IllegalAccessError()))
                .flatMap(answer-> sumar(answerDTO).flatMap(delete->answerRepository.deleteById(answerDTO.getId())));
    }

    public Mono<RespuestaEliminarDTO> sumar(AnswerDTO answerDTO){
        return questionRepository.findById(answerDTO.getQuestionId())
                .flatMap(question -> {
                    question.setAnswerDelete(question.getAnswerDelete()+1);
                    return questionRepository.save(question);
                }).map(respuestaEliminarMapper.questionToRespuesta());
    }
}
