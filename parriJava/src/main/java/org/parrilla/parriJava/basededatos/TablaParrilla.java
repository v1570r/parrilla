package org.parrilla.parriJava.basededatos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TablaParrilla {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime momento;
    private String identificador_video;
    private String miniatura;

    public TablaParrilla() {
    }

    public TablaParrilla(long id, LocalDateTime momento, String identificador_video, String miniatura) {
        this.id = id;
        this.momento = momento;
        this.identificador_video = identificador_video;
        this.miniatura = miniatura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public String getIdentificador_video() {
        return identificador_video;
    }

    public void setIdentificador_video(String identificador_video) {
        this.identificador_video = identificador_video;
    }

    public String getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(String miniatura) {
        this.miniatura = miniatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TablaParrilla that = (TablaParrilla) o;
        return id == that.id && Objects.equals(momento, that.momento) && Objects.equals(identificador_video, that.identificador_video) && Objects.equals(miniatura, that.miniatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, momento, identificador_video, miniatura);
    }

    @Override
    public String toString() {
        return "TablaParrilla{" +
                "id=" + id +
                ", momento_inicial=" + momento +
                ", identificador_video='" + identificador_video + '\'' +
                ", miniatura='" + miniatura + '\'' +
                '}';
    }
}
