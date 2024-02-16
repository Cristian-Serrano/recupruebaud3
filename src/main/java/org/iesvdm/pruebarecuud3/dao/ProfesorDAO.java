package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProfesorDAO {
    public void create(Profesor profesor);
    public List<Profesor> getAll();
    public Optional<Profesor> find(int id);
}
