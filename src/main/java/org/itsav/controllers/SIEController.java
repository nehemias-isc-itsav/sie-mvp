package org.itsav.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.itsav.domain.entities.*;
import org.itsav.domain.records.PaisRequest;
import org.itsav.service.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<String> listaNombres() {
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
    public Response listaAlumnos() {
        var listaUsuarios = usuariosService.getInfoUsuario();
        if (listaUsuarios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(listaUsuarios).build();
    }

    //END-POINT alergias
    @Inject
    AlergiasService alergiasService;

    @GET
    @Path("alergias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlergias(){
        return Response.ok(alergiasService.findAll()).build();
    }

    @GET
    @Path("alergias/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlergiaById(@PathParam("id") int id){
        return Response.ok(alergiasService.findById(id)).build();
    }

    //END-POINT alumnos
    @Inject
    AlumnosService alumnosService;

    @GET
    @Path("alumnos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlumnos(){
        return Response.ok(alumnosService.findAll()).build();
    }

    @GET
    @Path("alumnos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlumnoById(@PathParam("id") UUID id){
        Alumnos alumno = alumnosService.findById(id);
        if (alumno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(alumno).build();
    }

    //END-POINT paises
    @Inject
    PaisesService paisesService;

    @GET
    @Path("paises")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response obtenerPaises(){
        return Response.ok(paisesService.findAll()).build();
    }

    @GET
    @Path("paises/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPaisById(@PathParam("id")Long id){
        Paises pais = paisesService.findById(id);
        if(pais == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pais).build();
    }

    // Agregar paises desde la API
    @POST
    @Path("paises")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarPais(PaisRequest reqDTO){
        Paises nuevoPais = paisesService.addCountry(reqDTO);
        return Response.status(Response.Status.CREATED).entity(nuevoPais).build();
    }

    //END-POINT estados
    @Inject
    EstadosService estadosService;

    @GET
    @Path("estados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstados(){
        return Response.ok(estadosService.findAll()).build();
    }

    @GET
    @Path("estados/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstadoById(@PathParam("id") Long id) {
        Estados estados = estadosService.findById(id);
        if (estados == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(estados).build();
    }

    //END-POINT ciudades
    @Inject
    CiudadesService ciudadesService;

    @GET
    @Path("ciudades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCiudades(){
        return Response.ok(ciudadesService.findAll()).build();
    }

    @GET
    @Path("ciudades/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCiudadById(@PathParam("id")Long id){
        Ciudades ciudad = ciudadesService.findById(id);
        if (ciudad == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ciudad).build();
    }

    //END-POINT datos_medicos
    @Inject
    DatosMedicosService datosMedicosService;

    @GET
    @Path("datos_medicos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosMedicos(){
        return Response.ok(datosMedicosService.findAll()).build();
    }

    @GET
    @Path("datos_medicos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatoMedicoById(@PathParam("id")UUID id){
        DatosMedicos datoMedico = datosMedicosService.findById(id);
        if(datoMedico == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(datoMedico).build();
    }

    //END-POINT direcciones
    @Inject
    DireccionesService direccionesService;

    @GET
    @Path("direcciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDirecciones(){
        return Response.ok(direccionesService.findAll()).build();
    }

    @GET
    @Path("direcciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDireccionById(@PathParam("id")UUID id){
        Direcciones direccion = direccionesService.findById(id);
        if(direccion == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(direccion).build();
    }

    //END-POINT discapacidades_medicas
    @Inject
    DiscapacidadesMedicasService discapacidadesMedicasService;

    @GET
    @Path("discapacidades_medicas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDiscapacidadesMedicas(){
        return Response.ok(discapacidadesMedicasService.findAll()).build();
    }

    @GET
    @Path("discapacidades_medicas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDiscapacidadMedicaById(@PathParam("id")Integer id){
        DiscapacidadesMedicas discapacidadMedica = discapacidadesMedicasService.findById(id);
        if(discapacidadMedica == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(discapacidadMedica).build();
    }

    //END-POINT rel_alergias_datos_medicos
    @Inject
    RelAlergiasDatosMedicosService relAlergiasDatosMedicosService;

    @GET
    @Path("rel_alergias_datos_medicos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelAlergiasDatosMedicos(){
        return Response.ok(relAlergiasDatosMedicosService.findAll()).build();
    }

    @GET
    @Path("rel_alergias_datos_medicos/{alergia}/{datoMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelAlergiaDatoMedico(@PathParam("alergia") Integer alergia, @PathParam("datoMedico") UUID datoMedico){
        // El objeto id que se pasara como parametro al metodo findById()
        RelAlergiasDatosMedicosId id = new RelAlergiasDatosMedicosId(alergia, datoMedico);

        RelAlergiasDatosMedicos relAlergiaDatoMedico = relAlergiasDatosMedicosService.findById(id);
        if(relAlergiaDatoMedico == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(relAlergiaDatoMedico).build();
    }

    //END-POINT rel_alumnos_direcciones
    @Inject
    RelAlumnosDireccionesService relAlumnosDireccionesService;

    @GET
    @Path("rel_alumnos_direcciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelAlumnosDirecciones(){
        return Response.ok(relAlumnosDireccionesService.findAll()).build();
    }

    @GET
    @Path("rel_alumnos_direcciones/{direccion}/{alumno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelAlumnoDireccion(@PathParam("direccion") UUID direccion, @PathParam("alumno") UUID alumno){

        RelAlumnosDireccionesId id= new RelAlumnosDireccionesId(direccion, alumno);

        RelAlumnosDirecciones relAlumnoDireccion= relAlumnosDireccionesService.findById(id);
        if(relAlumnoDireccion == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(relAlumnoDireccion).build();
    }

    //END-POINT rel_discapacidades_datos_medicos
    @Inject
    RelDiscapacidadesDatosMedicosService relDiscapacidadesDatosMedicosService;

    @GET
    @Path("rel_discapacidades_datos_medicos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelDiscapacidadesDatosMedicos(){
        return Response.ok(relDiscapacidadesDatosMedicosService.findAll()).build();
    }

    @GET
    @Path("rel_discapacidades_datos_medicos/{discapacidad}/{datoMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRelDiscapacidadDatoMedico(@PathParam("discapacidad") Integer discapacidad, @PathParam("datoMedico") UUID datoMedico){

        RelDiscapacidadesDatosMedicosId id= new RelDiscapacidadesDatosMedicosId(discapacidad,datoMedico);

        RelDiscapacidadesDatosMedicos relDiscapacidadesDatosMedicos= relDiscapacidadesDatosMedicosService.findById(id);
        if(relDiscapacidadesDatosMedicos == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(relDiscapacidadesDatosMedicos).build();
    }
}