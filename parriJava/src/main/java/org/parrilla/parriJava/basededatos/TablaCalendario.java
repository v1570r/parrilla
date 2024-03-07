package org.parrilla.parriJava.basededatos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class TablaCalendario {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime momento_inicial;
    private String identificador_video;
    private List<String> identificadores_de_lista;
    private List<String> identificadores_de_canal;
    private String orden;
    private String repetir;
    private Integer repetir_cada;
    private String dias_de_la_semana;
    private String coincidencia;
    private String duracion;
    private Integer numero_de_repeticiones;
    private LocalDate caducidad;
    private List<LocalDate> excepciones;
    private List<String> omitidos;

    public TablaCalendario() {
    }

    public TablaCalendario(Integer id, LocalDateTime momento_inicial, String identificador_video, List<String> identificadores_de_lista, List<String> identificadores_de_canal, String orden, String repetir, Integer repetir_cada, String dias_de_la_semana, String coincidencia, String duracion, Integer numero_de_repeticiones, LocalDate caducidad, List<LocalDate> excepciones, List<String> omitidos) {
        this.id = id;
        this.momento_inicial = momento_inicial;
        this.identificador_video = identificador_video;
        this.identificadores_de_lista = identificadores_de_lista;
        this.identificadores_de_canal = identificadores_de_canal;
        this.orden = orden;
        this.repetir = repetir;
        this.repetir_cada = repetir_cada;
        this.dias_de_la_semana = dias_de_la_semana;
        this.coincidencia = coincidencia;
        this.duracion = duracion;
        this.numero_de_repeticiones = numero_de_repeticiones;
        this.caducidad = caducidad;
        this.excepciones = excepciones;
        this.omitidos = omitidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getMomento_inicial() {
        return momento_inicial;
    }

    public void setMomento_inicial(LocalDateTime momento_inicial) {
        this.momento_inicial = momento_inicial;
    }

    public String getIdentificador_video() {
        return identificador_video;
    }

    public void setIdentificador_video(String identificador_video) {
        this.identificador_video = identificador_video;
    }

    public List<String> getIdentificadores_de_lista() {
        return identificadores_de_lista;
    }

    public void setIdentificadores_de_lista(List<String> identificadores_de_lista) {
        this.identificadores_de_lista = identificadores_de_lista;
    }

    public List<String> getIdentificadores_de_canal() {
        return identificadores_de_canal;
    }

    public void setIdentificadores_de_canal(List<String> identificadores_de_canal) {
        this.identificadores_de_canal = identificadores_de_canal;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }

    public Integer getRepetir_cada() {
        return repetir_cada;
    }

    public void setRepetir_cada(Integer repetir_cada) {
        this.repetir_cada = repetir_cada;
    }

    public String getDias_de_la_semana() {
        return dias_de_la_semana;
    }

    public void setDias_de_la_semana(String dias_de_la_semana) {
        this.dias_de_la_semana = dias_de_la_semana;
    }

    public String getCoincidencia() {
        return coincidencia;
    }

    public void setCoincidencia(String coincidencia) {
        this.coincidencia = coincidencia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getNumero_de_repeticiones() {
        return numero_de_repeticiones;
    }

    public void setNumero_de_repeticiones(Integer numero_de_repeticiones) {
        this.numero_de_repeticiones = numero_de_repeticiones;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }

    public List<LocalDate> getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(List<LocalDate> excepciones) {
        this.excepciones = excepciones;
    }

    public List<String> getOmitidos() {
        return omitidos;
    }

    public void setOmitidos(List<String> omitidos) {
        this.omitidos = omitidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TablaCalendario that = (TablaCalendario) o;
        return id == that.id && Objects.equals(momento_inicial, that.momento_inicial) && Objects.equals(identificador_video, that.identificador_video) && Objects.equals(identificadores_de_lista, that.identificadores_de_lista) && Objects.equals(identificadores_de_canal, that.identificadores_de_canal) && Objects.equals(orden, that.orden) && Objects.equals(repetir, that.repetir) && Objects.equals(repetir_cada, that.repetir_cada) && Objects.equals(dias_de_la_semana, that.dias_de_la_semana) && Objects.equals(coincidencia, that.coincidencia) && Objects.equals(duracion, that.duracion) && Objects.equals(numero_de_repeticiones, that.numero_de_repeticiones) && Objects.equals(caducidad, that.caducidad) && Objects.equals(excepciones, that.excepciones) && Objects.equals(omitidos, that.omitidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, momento_inicial, identificador_video, identificadores_de_lista, identificadores_de_canal, orden, repetir, repetir_cada, dias_de_la_semana, coincidencia, duracion, numero_de_repeticiones, caducidad, excepciones, omitidos);
    }

    @Override
    public String toString() {
        return "TablaCalendario{" +
                "id=" + id +
                ", momento_inicial=" + momento_inicial +
                ", identificador_video='" + identificador_video + '\'' +
                ", identificadores_de_lista=" + identificadores_de_lista +
                ", identificadores_de_canal=" + identificadores_de_canal +
                ", orden='" + orden + '\'' +
                ", repetir='" + repetir + '\'' +
                ", repetir_cada=" + repetir_cada +
                ", dias_de_la_semana='" + dias_de_la_semana + '\'' +
                ", coincidencia='" + coincidencia + '\'' +
                ", duracion='" + duracion + '\'' +
                ", numero_de_repeticiones=" + numero_de_repeticiones +
                ", caducidad=" + caducidad +
                ", excepciones=" + excepciones +
                ", omitidos=" + omitidos +
                '}';
    }
}
