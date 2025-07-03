package com.bytegenius.server.controller;

import com.bytegenius.server.model.Servicio;
import com.bytegenius.server.model.Usuario;
import com.bytegenius.server.serviceImpl.ServicioServiceImpl;
import com.bytegenius.server.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "servicios")
public class ServiceController {
    @Autowired
    private ServicioServiceImpl servicioService;
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> createService(@RequestBody Servicio service){
        servicioService.createService(service);
        return ResponseEntity.status(HttpStatus.OK).body("Servicio Creado"+service);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getServicios(@PathVariable long id){
        List<Servicio> servicesFinal = new ArrayList<>();
        Optional<Usuario> user = usuarioService.getUsuarioById(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        List<Servicio> servicios = servicioService.getServicios();
        for(Servicio service: servicios){

            if(service.getUser().getIdUser() == id){
                servicesFinal.add(service);
            }
        }
        if(servicesFinal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El usuario no tiene ningún servidor asociado, crear un servidor");
        }
        return ResponseEntity.status(HttpStatus.OK).body(servicesFinal);
    }

    @GetMapping("/oneservice")
    public ResponseEntity<?> getServiceById(@RequestParam long id){
        Optional<Servicio> service = servicioService.getServiceById(id);
        if(service.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Servicio no existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteService(@RequestParam long id){
        try{
            servicioService.eliminarService(id);
            return ResponseEntity.status(HttpStatus.OK).body("Service Eliminado Correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocurrio un error, no se pudo eliminar");
        }
    }


}
