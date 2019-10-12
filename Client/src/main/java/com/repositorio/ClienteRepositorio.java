package com.repositorio;

import com.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

        Cliente findByNumeroIdentificacion (String numeroCliente );

        Cliente findByUsuarioAndPassword (String user, String password);

}
