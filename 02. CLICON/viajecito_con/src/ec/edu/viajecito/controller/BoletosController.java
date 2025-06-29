/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.controller;

import ec.edu.viajecito.client.AeroCondorClient;
import ec.edu.viajecito.model.Boleto;
import ec.edu.viajecito.model.CompraBoletoRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Drouet
 */
public class BoletosController {
    public List<Boleto> obtenerBoletosPorUsuario(String idUsuario) {
        List<ec.edu.viajecito.client.Boletos> soapList = AeroCondorClient.obtenerBoletosPorUsuario(Integer.parseInt(idUsuario));
        List<Boleto> localList = new ArrayList<>();
        for (ec.edu.viajecito.client.Boletos b : soapList) {
            localList.add(Boleto.fromSoap(b));
        }
        return localList;
    }

    public boolean comprarBoletos(CompraBoletoRequest compraBoletoRequest) {
        ec.edu.viajecito.client.CompraBoletoRequest soapRequest = CompraBoletoRequest.toSoap(compraBoletoRequest);
        return AeroCondorClient.comprar(soapRequest);
    }
}
