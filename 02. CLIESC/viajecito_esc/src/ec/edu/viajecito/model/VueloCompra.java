/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

/**
 *
 * @author Drouet
 */
public class VueloCompra {
    private int idVuelo;
    private int cantidad;

    public VueloCompra() {
    }

    public VueloCompra(int idVuelo, int cantidad) {
        this.idVuelo = idVuelo;
        this.cantidad = cantidad;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
     // Local → SOAP
    public static ec.edu.viajecito.client.VueloCompra toSoap(VueloCompra local) {
        if (local == null) return null;

        ec.edu.viajecito.client.VueloCompra soap = new ec.edu.viajecito.client.VueloCompra();
        soap.setIdVuelo(local.getIdVuelo());
        soap.setCantidad(local.getCantidad());

        return soap;
    }

    // SOAP → Local
    public static VueloCompra fromSoap(ec.edu.viajecito.client.VueloCompra soap) {
        if (soap == null) return null;

        VueloCompra local = new VueloCompra();
        local.setIdVuelo(soap.getIdVuelo());
        local.setCantidad(soap.getCantidad());

        return local;
    }
}
