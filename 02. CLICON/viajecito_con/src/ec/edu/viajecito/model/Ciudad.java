/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

/**
 *
 * @author Drouet
 */
public class Ciudad {
    private Integer idCiudad;
    private String codigoCiudad;
    private String nombreCiudad;

    // Constructor por defecto
    public Ciudad() {
    }

    // Constructor con solo id
    public Ciudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    // Constructor con todos los campos
    public Ciudad(Integer idCiudad, String codigoCiudad, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.codigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    // Getters y Setters
    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
    // Local → SOAP
    public static ec.edu.viajecito.client.Ciudades toSoap(Ciudad local) {
        if (local == null) return null;

        ec.edu.viajecito.client.Ciudades soap = new ec.edu.viajecito.client.Ciudades();
        soap.setIdCiudad(local.getIdCiudad());
        soap.setCodigoCiudad(local.getCodigoCiudad());
        soap.setNombreCiudad(local.getNombreCiudad());

        return soap;
    }

    // SOAP → Local
    public static Ciudad fromSoap(ec.edu.viajecito.client.Ciudades soap) {
        if (soap == null) return null;

        Ciudad local = new Ciudad();
        local.setIdCiudad(soap.getIdCiudad());
        local.setCodigoCiudad(soap.getCodigoCiudad());
        local.setNombreCiudad(soap.getNombreCiudad());

        return local;
    }
}
