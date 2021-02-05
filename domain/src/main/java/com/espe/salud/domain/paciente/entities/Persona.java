package com.espe.salud.domain.paciente.entities;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MZSTPER", schema = "SALUD")
@EntityListeners(AuditingEntityListener.class)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MZSTPER_CODIGO", updatable = false, nullable = false)
    private Long codigo;

    @Column(name = "MZSTPER_APELLIDO_PATERNO")
    private String apellidoPaterno;

}
