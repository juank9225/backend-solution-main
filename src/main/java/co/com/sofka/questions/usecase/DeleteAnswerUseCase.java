package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.mapper.AnswerSumarQuestionMapper;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteAnswerUseCase{
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public DeleteAnswerUseCase(AnswerRepository answerRepository, QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }


    public Mono<Void> apply(AnswerDTO answerDTO) {
        return   answerRepository.findByIdAndUserId(answerDTO.getId(),answerDTO.getUserId())
                .flatMap(answer->answerRepository.deleteById(answerDTO.getId())).and(sumar(answerDTO));
    }

    public Mono<QuestionDTO> sumar(AnswerDTO answerDTO){
        return questionRepository.findById(answerDTO.getQuestionId())
                .flatMap(question -> {
                    question.setAnswerDelete(question.getAnswerDelete()+1);
                    return questionRepository.save(question);
                }).map(questionMapper.mapQuestionToDTO());
    }
}