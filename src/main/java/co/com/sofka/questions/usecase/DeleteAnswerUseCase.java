package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class DeleteAnswerUseCase implements Function<AnswerDTO,Mono<QuestionDTO>>{
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public DeleteAnswerUseCase(AnswerRepository answerRepository, QuestionRepository questionRepository, AnswerMapper answerMapper, QuestionMapper questionMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.answerMapper = answerMapper;
        this.questionMapper = questionMapper;
    }

    @Override
    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
       answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())
                .flatMap(answer -> answerRepository.deleteById(answer.getId()));
                 return questionRepository.findById(answerDTO.getQuestionId())
                .flatMap(question -> {
                    question.setAnswerDelete(question.getAnswerDelete()+1);
                    return questionRepository.save(question);
                }).map(questionMapper.mapQuestionToDTO());
    }
}
