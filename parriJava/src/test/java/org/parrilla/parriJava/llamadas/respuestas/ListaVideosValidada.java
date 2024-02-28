package org.parrilla.parriJava.llamadas.respuestas;

import org.parrilla.parriJava.validacion.VideoDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ListaVideosValidada {
    public ArrayList<VideoDTO> obtenerLista(){
        DayOfWeek dia_de_la_semana = LocalDate.now().getDayOfWeek();
        LocalDate ultimo_dia_de_la_semana_pasada = LocalDate.now().minusDays(
                dia_de_la_semana.getValue()
        );
        LocalDateTime momento_primer_video = LocalDateTime.of(
                ultimo_dia_de_la_semana_pasada,
                LocalTime.of(11,20)
        );

        ArrayList<VideoDTO> listado_videos_preparado = new ArrayList<>();

        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.minusWeeks(1).plusDays(DayOfWeek.SUNDAY.getValue()),
            "https://www.youtube.com/watch?v=WWYz71WOrPg",
            "https://i.ytimg.com/vi/WWYz71WOrPg/hqdefault.jpg"
            ));

        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.MONDAY.getValue()),
            "https://youtu.be/5TaLyHN591I",
            "https://i.ytimg.com/vi/5TaLyHN591I/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.TUESDAY.getValue()),
            "https://www.youtube.com/watch?v=yTcuazSy5Cs",
            "https://i.ytimg.com/vi/yTcuazSy5Cs/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.WEDNESDAY.getValue()),
            "https://youtu.be/Z6U8ohxR7QQ",
            "https://i.ytimg.com/vi/Z6U8ohxR7QQ/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.THURSDAY.getValue()),
            "https://youtu.be/9xp1XWmJ_Wo",
            "https://i.ytimg.com/vi/9xp1XWmJ_Wo/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.FRIDAY.getValue()),
            "https://youtu.be/gi6J_WjjNhE",
            "https://i.ytimg.com/vi/gi6J_WjjNhE/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.SATURDAY.getValue()),
            "https://youtu.be/_cLiUJol8ak",
            "https://i.ytimg.com/vi/_cLiUJol8ak/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusDays(DayOfWeek.SUNDAY.getValue()),
            "https://youtu.be/Zo0HnnhUpQQ",
            "https://i.ytimg.com/vi/Zo0HnnhUpQQ/hqdefault.jpg"
            ));

        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.MONDAY.getValue()),
            "https://youtu.be/ekFiZVuX8dM",
            "https://i.ytimg.com/vi/ekFiZVuX8dM/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.TUESDAY.getValue()),
            "https://www.youtube.com/watch?v=yTcuazSy5Cs",
            "https://i.ytimg.com/vi/yTcuazSy5Cs/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.WEDNESDAY.getValue()),
            "https://youtu.be/Z6U8ohxR7QQ",
            "https://i.ytimg.com/vi/Z6U8ohxR7QQ/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.THURSDAY.getValue()),
            "https://youtu.be/xJxzdqcGJQk",
            "https://i.ytimg.com/vi/xJxzdqcGJQk/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.FRIDAY.getValue()),
            "https://youtu.be/E2icOW6Ntfo",
            "https://i.ytimg.com/vi/E2icOW6Ntfo/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.SATURDAY.getValue()),
            "https://youtu.be/TFBmzuePT14",
            "https://i.ytimg.com/vi/TFBmzuePT14/hqdefault.jpg"
            ));
        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(1).plusDays(DayOfWeek.SUNDAY.getValue()),
            "https://www.youtube.com/watch?v=quiH1gVhhzQ",
            "https://i.ytimg.com/vi/quiH1gVhhzQ/hqdefault.jpg"
            ));

        listado_videos_preparado.add(
                new VideoDTO(
            momento_primer_video.plusWeeks(2).plusDays(DayOfWeek.MONDAY.getValue()),
            "https://youtu.be/c6k6gyyGShY",
            "https://i.ytimg.com/vi/c6k6gyyGShY/hqdefault.jpg"
            ));
        return listado_videos_preparado;
    }
}
