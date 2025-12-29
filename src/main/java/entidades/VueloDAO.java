package entidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloDAO {
    public List<Vuelo> filtrarVuelos(String destino, String tipo) {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE destino LIKE ? AND tipo LIKE ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + destino + "%");
            ps.setString(2, tipo.equals("Todos") ? "%" : tipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Vuelo(rs.getInt("id"), rs.getString("origen"),
                        rs.getString("destino"), rs.getDouble("precio"),
                        rs.getInt("asientos_disponibles")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean reducirAsiento(int id) {
        String sql = "UPDATE vuelos SET asientos_disponibles = asientos_disponibles - 1 WHERE id = ? AND asientos_disponibles > 0";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
