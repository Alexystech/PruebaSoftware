package com.welldex.PruebaSoftware.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
@Table(name = "contenedores")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Contenedor {

    @Id
    @Column(name = "folio", length = 10)
    private String folio;

    @Column(name = "tipo_contenedor")
    private String tipoContenedor;

    private String dimensiones;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus_contenedor", nullable = false)
    private EstatusContenedor estatusContenedor;

    @Column(name = "fecha_descargo")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaDescargo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_operaciones_folio", referencedColumnName = "folio", nullable = false)
    private Operacion operacion;

}
