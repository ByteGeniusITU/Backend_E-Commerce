package com.bytegenius.server.service;

import com.bytegenius.server.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> getServicios();
    void createService(Servicio service);
    Optional<Servicio> getServiceById(long id);
    void eliminarService(long id);
}
