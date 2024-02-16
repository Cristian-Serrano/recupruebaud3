package org.iesvdm.pruebarecuud3.mapstruct;

import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {
    public ProfesorDTO profesorToProfesorDTO(Profesor profesor, String nombre_departamento);
    public Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO);
}
