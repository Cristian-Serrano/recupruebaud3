package org.iesvdm.pruebarecuud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebarecuud3.domain.Persona;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PersonaDAOImpl implements PersonaDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Persona persona){
        /*SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("persona")
                .usingGeneratedKeyColumns("id_pelicula");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("titulo",profesor.getTitulo())
                .addValue("fecha_lanzamiento",pelicula.getFecha_lanzamiento())
                .addValue("id_idioma",pelicula.getId_idioma())
                .addValue("duracion_alquiler",pelicula.getDuracion_alquiler())
                .addValue("rental_rate",pelicula.getRental_rate())
                .addValue("duracion",pelicula.getDuracion())
                .addValue("replacement_cost",pelicula.getReplacement_cost())
                .addValue("ultima_actualizacion",pelicula.getUltima_actualizacion());

        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        profesor.setId_pelicula(number.intValue());

        log.info("Insertados {} registros.", number.intValue());*/
    }

    public List<Persona> getAll(){
        List<Persona> listPersona = jdbcTemplate.query(
                "SELECT * FROM persona",
                (rs, rowNum) -> new Persona(
                        rs.getInt("id"),
                        rs.getString("nif"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("tipo")
                )
        );

        log.info("Devueltos {} registros.", listPersona.size());

        return listPersona;
    }
}
