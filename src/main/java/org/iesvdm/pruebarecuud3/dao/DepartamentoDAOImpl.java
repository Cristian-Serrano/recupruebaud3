package org.iesvdm.pruebarecuud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class DepartamentoDAOImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Departamento> getAll(){
        List<Departamento> listDepartamento = jdbcTemplate.query(
                "SELECT * FROM departamento",
                (rs, rowNum) -> new Departamento(
                        rs.getInt("id"),
                        rs.getString("nombre")
                )
        );

        log.info("Devueltos {} registros.", listDepartamento.size());

        return listDepartamento;
    }
}
