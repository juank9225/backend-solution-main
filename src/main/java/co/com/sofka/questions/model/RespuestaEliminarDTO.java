package co.com.sofka.questions.model;

import java.util.Objects;

public class RespuestaEliminarDTO {
    private String idAnswer;
    private String idUser;
    private String mensaje;

    public RespuestaEliminarDTO() {

    }

    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespuestaEliminarDTO that = (RespuestaEliminarDTO) o;
        return Objects.equals(idAnswer, that.idAnswer) && Objects.equals(idUser, that.idUser) && Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer, idUser, mensaje);
    }
}
