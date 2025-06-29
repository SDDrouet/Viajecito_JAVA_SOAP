package ec.edu.viajecito.controller;

import ec.edu.viajecito.client.AeroCondorClient;
import ec.edu.viajecito.model.Usuario;

public class UsuariosController {
    public boolean crearUsuario(Usuario usuario) {
        ec.edu.viajecito.client.Usuarios soapUser = Usuario.toSoap(usuario);
        return AeroCondorClient.crearUsuario(soapUser);
    }

    public Usuario login(String username, String password) {
        ec.edu.viajecito.client.Usuarios soap = AeroCondorClient.login(username, password);
        return Usuario.fromSoap(soap);
    }
}
