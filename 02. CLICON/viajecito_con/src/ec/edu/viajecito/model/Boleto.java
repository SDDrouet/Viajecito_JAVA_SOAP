/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Drouet
 */
public class Boleto {

    private Integer idBoleto;
    private String numeroBoleto;
    private String fechaCompra;
    private BigDecimal precioCompra;
    private Usuario idUsuario;
    private Vuelo idVuelo;

    // Constructor por defecto
    public Boleto() {
    }

    // Constructor con todos los campos
    public Boleto(Integer idBoleto, String numeroBoleto, String fechaCompra, BigDecimal precioCompra, Usuario usuario, Vuelo vuelo) {
        this.idBoleto = idBoleto;
        this.numeroBoleto = numeroBoleto;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.idUsuario = usuario;
        this.idVuelo = vuelo;
    }

    // Getters y Setters
    public Integer getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(Integer idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(String numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public Date getFechaCompra() {
        if (fechaCompra == null || fechaCompra.isBlank()) return null;

        try {
            // Eliminar el sufijo [UTC] para que el parser no falle
            String cleaned = fechaCompra.replace("[UTC]", "");

            // Parsear con formato compatible
            Instant instant = Instant.parse(cleaned);
            return Date.from(instant);
        } catch (Exception e) {
            System.err.println("Error al parsear fechaFactura: " + e.getMessage());
            return null;
        }
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Vuelo getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Vuelo idVuelo) {
        this.idVuelo = idVuelo;
    }
    
    // Local → SOAP
    public static ec.edu.viajecito.client.Boletos toSoap(Boleto local) {
        if (local == null) return null;

        ec.edu.viajecito.client.Boletos soap = new ec.edu.viajecito.client.Boletos();
        soap.setIdBoleto(local.getIdBoleto());
        soap.setNumeroBoleto(local.getNumeroBoleto());
        soap.setPrecioCompra(local.getPrecioCompra());

        // fechaCompra: String → XMLGregorianCalendar
        if (local.getFechaCompra() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = sdf.parse(local.getFechaCompra().toString());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(parsedDate);
                XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                soap.setFechaCompra(xmlCal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        soap.setIdUsuario(Usuario.toSoap(local.getIdUsuario()));
        soap.setIdVuelo(Vuelo.toSoap(local.getIdVuelo()));

        return soap;
    }

    // SOAP → Local
    public static Boleto fromSoap(ec.edu.viajecito.client.Boletos soap) {
        if (soap == null) return null;

        Boleto local = new Boleto();
        local.setIdBoleto(soap.getIdBoleto());
        local.setNumeroBoleto(soap.getNumeroBoleto());
        local.setPrecioCompra(soap.getPrecioCompra());

        // fechaCompra: XMLGregorianCalendar → String
        if (soap.getFechaCompra() != null) {
            local.setFechaCompra(soap.getFechaCompra().toGregorianCalendar().getTime().toInstant().toString());
        }

        local.setIdUsuario(Usuario.fromSoap(soap.getIdUsuario()));
        local.setIdVuelo(Vuelo.fromSoap(soap.getIdVuelo()));

        return local;
    }
    
}
