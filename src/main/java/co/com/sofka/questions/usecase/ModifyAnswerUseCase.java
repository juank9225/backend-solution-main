package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.AnswerModificadaMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class ModifyAnswerUseCase /*implements Function<AnswerDTO, Mono<AnswerDTO>>*/ {
    private final AnswerRepository answerRepository;
    private final AnswerModificadaMapper answerModificadaMapper;

    @Autowired
    public ModifyAnswerUseCase(AnswerRepository answerRepository, AnswerModificadaMapper answerModificadaMapper) {
        this.answerRepository = answerRepository;
        this.answerModificadaMapper = answerModificadaMapper;
    }


   // @Override
    public Mono<AnswerDTO> apply(AnswerDTO answerDTO) {
        return answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())
                .flatMap(
                        answer -> {
                            answer.setAnswer(answerDTO.getAnswer());
                            answer.setModificada(true);
                            answer.setVecesModificada(answer.getVecesModificada() + 1);
                            return answerRepository.save(answer);
                        }
                ).map(answerModificadaMapper.answerToAnswerDTOModificada())
                .switchIfEmpty(Mono.error(new IllegalAccessError()));
    }
}