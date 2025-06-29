/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.client;

/**
 *
 * @author Drouet
 */
public class AeroCondorClient {

    public static java.util.List<ec.edu.viajecito.client.Vuelos> buscarVuelos(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.buscarVuelos(arg0, arg1, arg2);
    }

    public static boolean crearUsuario(ec.edu.viajecito.client.Usuarios arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.crearUsuario(arg0);
    }

    public static java.util.List<ec.edu.viajecito.client.Ciudades> getCiudades() {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.getCiudades();
    }

    public static java.util.List<ec.edu.viajecito.client.Facturas> getFacturasPorUsuario(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.getFacturasPorUsuario(arg0);
    }

    public static java.util.List<ec.edu.viajecito.client.Facturas> getFacturasPorUsuario_1(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.getFacturasPorUsuario(arg0);
    }

    public static java.util.List<ec.edu.viajecito.client.Vuelos> getVuelos() {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.getVuelos();
    }

    public static Usuarios login(java.lang.String arg0, java.lang.String arg1) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.login(arg0, arg1);
    }

    public static java.util.List<ec.edu.viajecito.client.Amortizacion> obtenerAmortizacionPorFactura(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.obtenerAmortizacionPorFactura(arg0);
    }

    public static java.util.List<ec.edu.viajecito.client.Boletos> obtenerBoletosPorUsuario(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.obtenerBoletosPorUsuario(arg0);
    }

    public static Facturas obtenerFacturaPorId(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.obtenerFacturaPorId(arg0);
    }

    public static Vuelos obtenerVueloPorId(java.lang.Integer arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.obtenerVueloPorId(arg0);
    }

    public static boolean comprar(ec.edu.viajecito.client.CompraBoletoRequest arg0) {
        ec.edu.viajecito.client.AeroCondorController_Service service = new ec.edu.viajecito.client.AeroCondorController_Service();
        ec.edu.viajecito.client.AeroCondorController port = service.getAeroCondorControllerPort();
        return port.comprar(arg0);
    }
    
    
}
