package co.com.sofka.questions.model;

import java.util.Objects;

public class RespuestaEliminarDTO {
    private String idAnswer;
    private Boolean estado;
    private String mensaje;
    private String questionId;

    public RespuestaEliminarDTO() {

    }

    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespuestaEliminarDTO that = (RespuestaEliminarDTO) o;
        return Objects.equals(idAnswer, that.idAnswer) && Objects.equals(estado, that.estado) && Objects.equals(mensaje, that.mensaje) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer, estado, mensaje, questionId);
    }
}
