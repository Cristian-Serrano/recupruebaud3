package org.iesvdm.pruebarecuud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.iesvdm.pruebarecuud3.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping({"/profesores"})
    public String listar(Model model){

        List<Profesor> listAllPro =  profesorService.listAll();
        model.addAttribute("listaProfesores", listAllPro);

        model.addAttribute("conteo",profesorService.conteoProfesores());

        return "profesores";
    }

    @GetMapping({"profesores/crear","professors/create"})
    public String crear(Model model){

        Profesor profesor = new Profesor();
        model.addAttribute("profesor", profesor);

        return "crearProfesor";
    }

    @PostMapping({"profesores/crear","professors/create"})
    public String submitCrear(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult bindingResult,  Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("profesor", profesor);

            return "crearProfesor";
        }
        profesorService.create(profesor);

        return "redirect:/comerciales";
    }
}
