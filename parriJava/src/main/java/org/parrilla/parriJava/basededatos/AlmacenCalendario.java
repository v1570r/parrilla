package org.parrilla.parriJava.basededatos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenCalendario extends JpaRepository<TablaCalendario, Long> {
}