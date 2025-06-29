/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Drouet
 */
public class Factura {
    private Integer idFactura;
    private String numeroFactura;
    private BigDecimal precioSinIva;
    private BigDecimal precioConIva;
    private Usuario idUsuario;
    private String fechaFactura;
    private Collection<Boleto> boletosCollection;
    private Collection<Amortizacion> amortizacionBoletosCollection;
    

    public Factura() {
    }

    public Factura(Integer idFactura, String numeroFactura, BigDecimal precioSinIva, BigDecimal precioConIva, Usuario idUsuario, String fechaFactura, Collection<Boleto> boletosCollection, Collection<Amortizacion> amortizacionBoletosCollection) {
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.precioSinIva = precioSinIva;
        this.precioConIva = precioConIva;
        this.idUsuario = idUsuario;
        this.fechaFactura = fechaFactura;
        this.boletosCollection = boletosCollection;
        this.amortizacionBoletosCollection = amortizacionBoletosCollection;
    }

    public Collection<Amortizacion> getAmortizacionBoletosCollection() {
        return amortizacionBoletosCollection;
    }

    public void setAmortizacionBoletosCollection(Collection<Amortizacion> amortizacionBoletosCollection) {
        this.amortizacionBoletosCollection = amortizacionBoletosCollection;
    }

    

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    

    public Collection<Boleto> getBoletosCollection() {
        return boletosCollection;
    }

    public void setBoletosCollection(Collection<Boleto> boletosCollection) {
        this.boletosCollection = boletosCollection;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(BigDecimal precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public BigDecimal getPrecioConIva() {
        return precioConIva;
    }

    public void setPrecioConIva(BigDecimal precioConIva) {
        this.precioConIva = precioConIva;
    }

    public Date getFechaFactura() {
        if (fechaFactura == null || fechaFactura.isBlank()) return null;

        try {
            // Eliminar el sufijo [UTC] para que el parser no falle
            String cleaned = fechaFactura.replace("[UTC]", "");

            // Parsear con formato compatible
            Instant instant = Instant.parse(cleaned);
            return Date.from(instant);
        } catch (Exception e) {
            System.err.println("Error al parsear fechaFactura: " + e.getMessage());
            return null;
        }
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    
    // Local → SOAP
    public static ec.edu.viajecito.client.Facturas toSoap(Factura local) {
        if (local == null) return null;

        ec.edu.viajecito.client.Facturas soap = new ec.edu.viajecito.client.Facturas();
        soap.setIdFactura(local.getIdFactura());
        soap.setNumeroFactura(local.getNumeroFactura());
        soap.setPrecioSinIva(local.getPrecioSinIva());
        soap.setPrecioConIva(local.getPrecioConIva());
        soap.setIdUsuario(Usuario.toSoap(local.getIdUsuario()));

        // Convertir fecha a XMLGregorianCalendar
        try {
            if (local.getFechaFactura() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = sdf.parse(local.getFechaFactura().toString());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(parsedDate);
                XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                soap.setFechaFactura(xmlCal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lista de boletos
        if (local.getBoletosCollection() != null) {
            for (Boleto b : local.getBoletosCollection()) {
                soap.getBoletosCollection().add(Boleto.toSoap(b));
            }
        }

        // Lista de amortizaciones
        if (local.getAmortizacionBoletosCollection() != null) {
            for (Amortizacion a : local.getAmortizacionBoletosCollection()) {
                soap.getAmortizacionBoletosCollection().add(Amortizacion.toSoap(a));
            }
        }

        return soap;
    }

    // SOAP → Local
    public static Factura fromSoap(ec.edu.viajecito.client.Facturas soap) {
        if (soap == null) return null;

        Factura local = new Factura();
        local.setIdFactura(soap.getIdFactura());
        local.setNumeroFactura(soap.getNumeroFactura());
        local.setPrecioSinIva(soap.getPrecioSinIva());
        local.setPrecioConIva(soap.getPrecioConIva());
        local.setIdUsuario(Usuario.fromSoap(soap.getIdUsuario()));

        // Convertir fecha a String
        if (soap.getFechaFactura() != null) {
            local.setFechaFactura(soap.getFechaFactura().toGregorianCalendar().getTime().toInstant().toString());
        }

        // Lista de boletos
        if (soap.getBoletosCollection() != null) {
            Collection<Boleto> boletos = new ArrayList<>();
            for (ec.edu.viajecito.client.Boletos b : soap.getBoletosCollection()) {
                boletos.add(Boleto.fromSoap(b));
            }
            local.setBoletosCollection(boletos);
        }

        // Lista de amortizaciones
        if (soap.getAmortizacionBoletosCollection() != null) {
            Collection<Amortizacion> amortizaciones = new ArrayList<>();
            for (ec.edu.viajecito.client.Amortizacion a : soap.getAmortizacionBoletosCollection()) {
                amortizaciones.add(Amortizacion.fromSoap(a));
            }
            local.setAmortizacionBoletosCollection(amortizaciones);
        }

        return local;
    }
    
}
