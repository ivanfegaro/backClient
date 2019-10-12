package com.repositorio;

import com.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepositorio extends JpaRepository<Servicio,Integer> {
}
