package com.welldex.PruebaSoftware.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
@Table(name = "operaciones")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Operacion {

    @Id
    @Column(name = "folio", length = 10)
    private String folio;

    private String pedimento;
    private String cliente;
    private String aduana;
    private String patente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mercancia", nullable = false)
    private TipoMercancia tipoMercancia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacion", nullable = false)
    private TipoOperacion tipoOperacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @Column(name = "fecha_arribo")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaArribo;

    @Column(name = "fecha_zarpe")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaZarpe;

    @Column(name = "pais_origen")
    private String paisOrigen;

    @JsonIgnore
    @OneToOne(mappedBy = "operacion")
    private CSuelta cSuelta;

}
