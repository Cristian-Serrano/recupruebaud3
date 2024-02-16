package org.iesvdm.pruebarecuud3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {
    private int id_profesor;
    private String nombre_departamento;
    private int id;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private String direccion;
    private String telefono;
    private Date fecha_nacimiento;
    private String tipo;
}
