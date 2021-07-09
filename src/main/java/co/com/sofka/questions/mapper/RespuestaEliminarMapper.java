package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.RespuestaEliminarDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RespuestaEliminarMapper {

    public Function<AnswerDTO, RespuestaEliminarDTO> answerDTOToRespuestaEliminada(){
        return answerUpdate->{
           var respuestaEliminar = new RespuestaEliminarDTO();
           respuestaEliminar.setIdAnswer(answerUpdate.getId());
           respuestaEliminar.setEstado(true);
           respuestaEliminar.setMensaje("Se elimino una respuesta");
           respuestaEliminar.setQuestionId(answerUpdate.getQuestionId());
           return respuestaEliminar;
        };
    }

    public Function<Answer,RespuestaEliminarDTO> answerToRespuesta() {
        return ansUpdate -> {
            var respuestaEliminar = new RespuestaEliminarDTO();
            respuestaEliminar.setIdAnswer(ansUpdate.getId());
            respuestaEliminar.setEstado(true);
            respuestaEliminar.setMensaje("Se elimino una respuesta");
            respuestaEliminar.setQuestionId(ansUpdate.getQuestionId());
            return respuestaEliminar;
        };
    }

    public Function<Question,RespuestaEliminarDTO> questionToRespuesta() {
        return questionUpdate -> {
            var respuestaEliminar = new RespuestaEliminarDTO();
            respuestaEliminar.setIdAnswer(questionUpdate.getId());
            respuestaEliminar.setEstado(true);
            respuestaEliminar.setMensaje("Se ha sumado la eliminacion");
            respuestaEliminar.setQuestionId(questionUpdate.getId());
            return respuestaEliminar;
        };
    }

}
