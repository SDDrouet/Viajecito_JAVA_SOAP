/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.viajecito.model;

/**
 *
 * @author Drouet
 */
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String username;
    private String password;
    private String telefono;
    private String cedula;
    private String correo;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String username, String password, String telefono, String cedula, String correo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.cedula = cedula;
        this.correo = correo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
    // Local → SOAP
    public static ec.edu.viajecito.client.Usuarios  toSoap(Usuario usuario) {
        if (usuario == null) return null;

        ec.edu.viajecito.client.Usuarios  soapUsuario = new ec.edu.viajecito.client.Usuarios ();
        soapUsuario.setIdUsuario(usuario.getIdUsuario());
        soapUsuario.setNombre(usuario.getNombre());
        soapUsuario.setUsername(usuario.getUsername());
        soapUsuario.setPassword(usuario.getPassword());
        soapUsuario.setTelefono(usuario.getTelefono());
        soapUsuario.setCedula(usuario.getCedula());
        soapUsuario.setCorreo(usuario.getCorreo());

        return soapUsuario;
    }

    // SOAP → Local
    public static Usuario fromSoap(ec.edu.viajecito.client.Usuarios  soapUsuario) {
        if (soapUsuario == null) return null;

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(soapUsuario.getIdUsuario());
        usuario.setNombre(soapUsuario.getNombre());
        usuario.setUsername(soapUsuario.getUsername());
        usuario.setPassword(soapUsuario.getPassword());
        usuario.setTelefono(soapUsuario.getTelefono());
        usuario.setCedula(soapUsuario.getCedula());
        usuario.setCorreo(soapUsuario.getCorreo());

        return usuario;
    }
}
