package ec.edu.viajecito.controller;

import ec.edu.viajecito.client.AeroCondorClient;
import ec.edu.viajecito.model.Ciudad;
import java.util.ArrayList;
import java.util.List;

public class CiudadesController {

    public List<Ciudad> obtenerTodasCiudades() {
        List<ec.edu.viajecito.client.Ciudades> soapList = AeroCondorClient.getCiudades();
        List<Ciudad> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Ciudades c : soapList) {
            localList.add(Ciudad.fromSoap(c));
        }
        return localList;
    }
}