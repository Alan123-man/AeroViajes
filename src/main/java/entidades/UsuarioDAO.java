package entidades;

import java.sql.*;

public class UsuarioDAO {
    public boolean validarLogin(String user, String pass) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) { return false; }
    }

    public boolean registrarUsuario(String user, String pass) {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
