package ec.edu.viajecito.controller;

import ec.edu.viajecito.client.AeroCondorClient;
import ec.edu.viajecito.model.Amortizacion;
import ec.edu.viajecito.model.Factura;
import java.util.ArrayList;
import java.util.List;

public class FacturasController {

    public Factura obtenerFacturaPorId(int idFactura) {
        ec.edu.viajecito.client.Facturas soap = AeroCondorClient.obtenerFacturaPorId(idFactura);
        return Factura.fromSoap(soap);
    }

    public List<Factura> obtenerFacturasPorUsuario(int idUsuario) {
        List<ec.edu.viajecito.client.Facturas> soapList = AeroCondorClient.getFacturasPorUsuario(idUsuario);
        List<Factura> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Facturas f : soapList) {
            localList.add(Factura.fromSoap(f));
        }
        return localList;
    }

    public List<Amortizacion> obtenerAmortizacionPorFactura(int idFactura) {
        List<ec.edu.viajecito.client.Amortizacion> soapList = AeroCondorClient.obtenerAmortizacionPorFactura(idFactura);
        List<Amortizacion> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Amortizacion a : soapList) {
            localList.add(Amortizacion.fromSoap(a));
        }
        return localList;
    }

}
