package com.controlador;

import com.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.node.TextNode;
import com.modelo.*;
import com.repositorio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ClienteControlador{

    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    private Cliente cliente;

    @Autowired
    ClienteRepositorio clienteRepositorio;

    private Proveedor proveedor;

    @Autowired
    ProveedorRepositorio proveedorRepositorio;

    private Servicio servicio;

    @Autowired
    ServicioRepositorio servicioRepositorio;

    private Producto producto;

    @Autowired
    ProductoRepositorio productoRepositorio;

    private RequestLoginCliente loginCliente;




    @GetMapping("/cliente")
    public List <Cliente> getCliente() {
        return clienteRepositorio.findAll();
    }

    @PostMapping("/putClient")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClienteById (@PathVariable(value = "id") Integer clienteId){
        return clienteRepositorio.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
    }

    @GetMapping("/numeroId/{id}")
    public Cliente getClienteByNumber (@PathVariable(value = "id") String numberIdentification){
        return clienteRepositorio.findByNumeroIdentificacion(numberIdentification);
    }

    @GetMapping("/proveedor")
    public List <Proveedor> getProveedor() {
        return proveedorRepositorio.findAll();
    }

    @GetMapping("/proveedor/{id}")
    public Proveedor getproveedorByNumber (@PathVariable(value = "id") String numberIdentification){
        return proveedorRepositorio.findByNumeroIdentificacion(Integer.parseInt(numberIdentification) );
    }

    @PostMapping("/crearProveedor")
    public Proveedor createProveedor(@Valid @RequestBody Proveedor proveedor){
        return proveedorRepositorio.save(proveedor);
    }

    @GetMapping("/servicio")
    public List <Servicio> getServicio() {
        return servicioRepositorio.findAll();
    }

    @PostMapping("/crearServicio/proveedor/{id}")
    public Servicio createServicio(@PathVariable(value = "id") Integer identificadorProveedor, @Valid @RequestBody Servicio servicio){
       this.servicio = servicio;
       proveedorRepositorio.findById(identificadorProveedor).map( idproveedor -> {
           this.servicio = servicio;
           this.servicio.setIdproveedor(idproveedor);
           return this.servicio;
               }).orElseThrow(() -> new ResourceNotFoundException("Proveedor No encontrado: ","id",identificadorProveedor));
        return servicioRepositorio.save(servicio);
    }


    @GetMapping("/producto")
    public List <Producto> getProducto() {
        return productoRepositorio.findAll();
    }

    @PostMapping("/crearProducto/proveedor/{id}")
    public Producto createProducto(@PathVariable(value = "id") Integer identificadorProveedor,@Valid @RequestBody Producto producto){
        this.producto = producto;
        proveedorRepositorio.findById(identificadorProveedor).map( idproveedor -> {
            this.producto = producto;
            this.producto.setIdproveedor(idproveedor);
            return this.producto;
        }).orElseThrow(() -> new ResourceNotFoundException("Proveedor No encontrado: ","id",identificadorProveedor));

        return productoRepositorio.save(producto);
    }

    @PostMapping(value = "/loginCliente")
    public Cliente loginCliente(@Valid @RequestBody RequestLoginCliente requestLoginCliente ){
        Cliente cliente;
        cliente = clienteRepositorio.findByUsuarioAndPassword(requestLoginCliente.getUser(),requestLoginCliente.getPassword());
        if (cliente == null){
            throw new ResourceNotFoundException("Usuario:  ","id",requestLoginCliente.getUser());
        }
        return cliente;
    }



}
