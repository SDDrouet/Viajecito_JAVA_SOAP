/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Drouet
 */
public class CompraBoletoRequest {
    private int idUsuario;
    private List<VueloCompra> vuelos;
    private boolean esCredito;
    private int numeroCuotas;
    private double tasaInteresAnual;

    public CompraBoletoRequest() {
    }

    public CompraBoletoRequest(int idUsuario, List<VueloCompra> vuelos, boolean esCredito, int numeroCuotas, double tasaInteresAnual) {
        this.idUsuario = idUsuario;
        this.vuelos = vuelos;
        this.esCredito = esCredito;
        this.numeroCuotas = numeroCuotas;
        this.tasaInteresAnual = tasaInteresAnual;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<VueloCompra> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<VueloCompra> vuelos) {
        this.vuelos = vuelos;
    }

    public boolean isEsCredito() {
        return esCredito;
    }

    public void setEsCredito(boolean esCredito) {
        this.esCredito = esCredito;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public double getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        this.tasaInteresAnual = tasaInteresAnual;
    }
    
    // Local → SOAP
    public static ec.edu.viajecito.client.CompraBoletoRequest toSoap(CompraBoletoRequest local) {
        if (local == null) return null;

        ec.edu.viajecito.client.CompraBoletoRequest soap = new ec.edu.viajecito.client.CompraBoletoRequest();
        soap.setIdUsuario(local.getIdUsuario());
        soap.setEsCredito(local.isEsCredito());
        soap.setNumeroCuotas(local.getNumeroCuotas());
        soap.setTasaInteresAnual(local.getTasaInteresAnual());

        if (local.getVuelos() != null) {
            for (VueloCompra vuelo : local.getVuelos()) {
                soap.getVuelos().add(VueloCompra.toSoap(vuelo));
            }
        }

        return soap;
    }

    // SOAP → Local
    public static CompraBoletoRequest fromSoap(ec.edu.viajecito.client.CompraBoletoRequest soap) {
        if (soap == null) return null;

        CompraBoletoRequest local = new CompraBoletoRequest();
        local.setIdUsuario(soap.getIdUsuario());
        local.setEsCredito(soap.isEsCredito());
        local.setNumeroCuotas(soap.getNumeroCuotas());
        local.setTasaInteresAnual(soap.getTasaInteresAnual());

        if (soap.getVuelos() != null) {
            List<VueloCompra> vuelosLocal = new ArrayList<>();
            for (ec.edu.viajecito.client.VueloCompra vueloSoap : soap.getVuelos()) {
                vuelosLocal.add(VueloCompra.fromSoap(vueloSoap));
            }
            local.setVuelos(vuelosLocal);
        }

        return local;
    }
}

