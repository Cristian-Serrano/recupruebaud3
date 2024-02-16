package org.iesvdm.pruebarecuud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProfesorDAOImpl implements ProfesorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Profesor profesor){

        /*insert de*/
        /*personas*/
        SimpleJdbcInsert simpleJdbcInsert2 = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert2
                .withTableName("persona")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params2 = new MapSqlParameterSource()
                .addValue("nif",profesor.getNif())
                .addValue("nombre",profesor.getNombre())
                .addValue("apellido1",profesor.getApellido1())
                .addValue("apellido2",profesor.getApellido2())
                .addValue("ciudad",profesor.getCiudad())
                .addValue("direccion",profesor.getDireccion())
                .addValue("telefono",profesor.getTelefono())
                .addValue("fecha_nacimiento",profesor.getFecha_nacimiento())
                .addValue("tipo",profesor.getTipo());

        Number number2 = simpleJdbcInsert2.executeAndReturnKey(params2);

        profesor.setId(number2.intValue());
        profesor.setId_profesor(number2.intValue());

        /*departamentos*/
        SimpleJdbcInsert simpleJdbcInsert3 = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert3
                .withTableName("departamento")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params3 = new MapSqlParameterSource()
                .addValue("nombre",profesor.getNombre_departamento());

        Number number3 = simpleJdbcInsert3.executeAndReturnKey(params3);

        profesor.setId_departamento(number3.intValue());

        /*profesores*/

        jdbcTemplate.update("""
                           INSERT INTO profesor
                           VALUE
                           (?, ?)
                           """
                , profesor.getId_profesor()
                , profesor.getId_departamento());

        log.info("Insertados {} registros.", number2.intValue());
    }

    public Optional<Profesor> find(int id){
        Profesor profesor =  jdbcTemplate
                .queryForObject("SELECT * FROM profesor pr JOIN persona pe ON pr.id_profesor = pe.id JOIN departamento d ON d.id = pr.id_departamento WHERE id = ?"
                        , (rs, rowNum) -> new Profesor(rs.getInt("pr.id_profesor"),
                                rs.getInt("pr.id_departamento"),
                                rs.getString("d.nombre"),
                                rs.getInt("pe.id"),
                                rs.getString("pe.nif"),
                                rs.getString("pe.nombre"),
                                rs.getString("pe.apellido1"),
                                rs.getString("pe.apellido2"),
                                rs.getString("pe.ciudad"),
                                rs.getString("pe.direccion"),
                                rs.getString("pe.telefono"),
                                rs.getDate("pe.fecha_nacimiento"),
                                rs.getString("pe.tipo"))
                        , id
                );

        if (profesor != null) {
            return Optional.of(profesor);}
        else {
            log.info("Profesor no encontrado.");
            return Optional.empty(); }
    }

    /**
     * Filtro por tipo por si acaso se ha creado alguna persona de tipo alumno con el mismo id que profesor.id_profesor
     * @return
     */
    public List<Profesor> getAll(){
        List<Profesor> listProfesor = jdbcTemplate.query(
                "SELECT * FROM profesor pr JOIN persona pe ON pr.id_profesor = pe.id JOIN departamento d ON d.id = pr.id_departamento WHERE pe.tipo = \"catedratico\" OR pe.tipo = \"profesor\"",
                (rs, rowNum) -> new Profesor(
                        rs.getInt("pr.id_profesor"),
                        rs.getInt("pr.id_departamento"),
                        rs.getString("d.nombre"),
                        rs.getInt("pe.id"),
                        rs.getString("pe.nif"),
                        rs.getString("pe.nombre"),
                        rs.getString("pe.apellido1"),
                        rs.getString("pe.apellido2"),
                        rs.getString("pe.ciudad"),
                        rs.getString("pe.direccion"),
                        rs.getString("pe.telefono"),
                        rs.getDate("pe.fecha_nacimiento"),
                        rs.getString("pe.tipo")
                )
        );

        log.info("Devueltos {} registros.", listProfesor.size());

        return listProfesor;
    }

}
