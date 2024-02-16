package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Persona;
import org.iesvdm.pruebarecuud3.domain.Profesor;

import java.util.List;

public interface PersonaDAO {
    public void create(Persona persona);
    public List<Persona> getAll();
}
