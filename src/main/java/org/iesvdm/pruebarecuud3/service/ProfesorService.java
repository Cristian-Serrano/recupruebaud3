package org.iesvdm.pruebarecuud3.service;

import org.iesvdm.pruebarecuud3.dao.ProfesorDAO;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.iesvdm.pruebarecuud3.mapstruct.ProfesorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorDAO profesorDAO;

    /*@Autowired
    private ProfesorMapper profesorMapper;*/

    public List<Profesor> listAll() {

        return profesorDAO.getAll();

    }

    public void create(Profesor profesor){
        profesorDAO.create(profesor);
    }

    public int conteoProfesores(){
        List<Profesor> listPro = listAll();

        int conteo = (int)listPro.stream()
                .count();

        return conteo;
    }

    /*public Profesor one(Integer id) {
        Optional<Profesor> optPro = profesorDAO.find(id);
        if (optPro.isPresent())
            return optPro.get();
        else
            return null;
    }*/


/*
    public ProfesorDTO profesorDTO(int idProfesor){



        return profesorMapper.profesorToProfesorDTO(
                one(idCliente),
                comercialDAO.getListComercialByIdCliente(idCliente),
                pedidosTrimestre,
                pedidosSemestre,
                pedidosAnio,
                pedidosLustro
        );
    }*/
}
