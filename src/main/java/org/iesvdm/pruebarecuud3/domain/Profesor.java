package org.iesvdm.pruebarecuud3.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor extends Persona{

    private int id_profesor;
    private int id_departamento;
    private String nombre_departamento;
    private int id;

    @Pattern(regexp = "\\d((([X-Z])|([LM])){1}([-]?)((\\d){7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]))", message = "Formato de nif incorrecto")
    private String nif;

    @NotBlank
    @Size(min=3, message = "Minimo 3 caracteres para nombre")
    private String nombre;
    private String apellido1;
    private String apellido2;

    @Size(max=25, message = "Maximo de 25 caracteres para ciudad")
    private String ciudad;
    private String direccion;
    private String telefono;

    /*@DateTimeFormat(pattern="yyyy-MM-dd")*/
    private Date fecha_nacimiento;
    private String tipo;
}
