package com.repositorio;

import com.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepositorio extends  JpaRepository <Proveedor, Integer> {

    Proveedor findByNumeroIdentificacion(int numeroId);

    Proveedor findByIdproveedorAndAndNumeroIdentificacion (int id, int numeroId);

}
