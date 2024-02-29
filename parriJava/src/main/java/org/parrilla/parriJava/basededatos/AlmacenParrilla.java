package org.parrilla.parriJava.basededatos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AlmacenParrilla extends JpaRepository<TablaParrilla, Long> {
    List<TablaParrilla> findByMomentoBetweenOrderByMomentoAsc(LocalDateTime startMomento, LocalDateTime endMomento);

    List<TablaParrilla> findByMomentoGreaterThanEqualOrderByMomentoAsc(LocalDateTime startMomento);
}