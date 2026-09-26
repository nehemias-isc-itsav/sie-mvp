package org.itsav.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.itsav.UsuariosService;

import java.util.ArrayList;
import java.util.List;

@Path("sie")
public class SIEController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("lista-tec")
    @Produces(MediaType.APPLICATION_JSON)
        public List<String> listaNombres(){
            List<String> nombres = new ArrayList<>();
            nombres.add("Quarkus");
            nombres.add("Angular");
            nombres.add("Spring");
            nombres.add("MySQL");
            return nombres;
        }

    @Inject
    UsuariosService usuariosService;

    @GET
    @Path("lista-alumnos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaAlumnos(){
        var listaUsuarios = usuariosService.getInfoUsuario();
        if(listaUsuarios.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(listaUsuarios).build();
    }
}