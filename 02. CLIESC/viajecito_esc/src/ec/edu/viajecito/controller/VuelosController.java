package ec.edu.viajecito.controller;

import ec.edu.viajecito.client.AeroCondorClient;
import ec.edu.viajecito.model.Vuelo;
import java.util.ArrayList;
import java.util.List;

public class VuelosController {

    public List<Vuelo> obtenerTodosVuelos() {
        List<ec.edu.viajecito.client.Vuelos> soapList = AeroCondorClient.getVuelos();
        List<Vuelo> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Vuelos v : soapList) {
            localList.add(Vuelo.fromSoap(v));
        }
        return localList;
    }

    public Vuelo obtenerVueloPorId(String idVuelo) {
        ec.edu.viajecito.client.Vuelos soap = AeroCondorClient.obtenerVueloPorId(Integer.parseInt(idVuelo));
        return Vuelo.fromSoap(soap);
    }

    public List<Vuelo> obtenerVuelosPorCiudad(String origen, String destino, String horaSalida) {
        List<ec.edu.viajecito.client.Vuelos> soapList = AeroCondorClient.buscarVuelos(origen, destino, horaSalida);
        List<Vuelo> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Vuelos v : soapList) {
            localList.add(Vuelo.fromSoap(v));
        }
        return localList;
    }
}
