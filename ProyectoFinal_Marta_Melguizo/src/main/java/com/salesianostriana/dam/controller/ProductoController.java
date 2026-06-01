package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {
 
    private final ProductoService productoService;
 
    // catalogo para user
    @GetMapping("/")
    public String listAll(Model model,
                          @RequestParam(required = false) String buscar,
                          @RequestParam(required = false) String tipo) {
        cargarFiltros(model, buscar, tipo);
        return "producto-list";
    }
 
    // catalogo para admin
    @GetMapping("/admin")
    public String listAdmin(Model model,
                            @RequestParam(required = false) String buscar,
                            @RequestParam(required = false) String tipo) {
        cargarFiltros(model, buscar, tipo);
        model.addAttribute("alertaStock", productoService.productosBajoStock(5));
        return "producto-list-admin";
    }
 
    // Admin y user
    private void cargarFiltros(Model model, String buscar, String tipo) {
        if (buscar != null && !buscar.isBlank()) {
            model.addAttribute("productos", productoService.buscarPorNombre(buscar));
            model.addAttribute("buscar", buscar);
        } else if (tipo != null && !tipo.isBlank()) {
            model.addAttribute("productos", productoService.porTipoMascota(tipo));
            model.addAttribute("tipoSeleccionado", tipo);
        } else {
            model.addAttribute("productos", productoService.findAll());
        }
        model.addAttribute("tipos", TipoMascota.values());
    }
 
    //Admin
    @GetMapping("/nuevo")
    public String showForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("tipos", TipoMascota.values());
        return "producto-form";
    }
 
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("producto") Producto p,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TipoMascota.values());
            return "producto-form";
        }
        productoService.save(p);
        return "redirect:/productos/admin";
    }
 
    //Admin
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable long id, Model model) {
        Optional<Producto> pEditar = productoService.findById(id);
        if (pEditar.isPresent()) {
            model.addAttribute("producto", pEditar.get());
            model.addAttribute("tipos", TipoMascota.values());
            return "producto-form";
        }
        return "redirect:/productos/admin";
    }
 
    @PostMapping("/editar/submit")
    public String update(@Valid @ModelAttribute("producto") Producto p,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TipoMascota.values());
            return "producto-form";
        }
        productoService.edit(p);
        return "redirect:/productos/admin";
    }
 
    //Admin 
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable long id) {
        productoService.findById(id).ifPresent(productoService::delete);
        return "redirect:/productos/admin";
    }
}