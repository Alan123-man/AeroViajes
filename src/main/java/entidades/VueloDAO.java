package entidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VueloDAO {

    // Método para obtener la conexión (Asegúrate que los datos coincidan con tu DB)
    private Connection obtenerConexion() {
        try {
            // Importante: El nombre de la DB debe ser sistema_vuelos como en tu error
            String url = "jdbc:mysql://localhost:3306/sistema_vuelos?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = ""; // Tu contraseña de MySQL
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public List<Vuelo> filtrarVuelos(String origen, String destino, String tipo) {
        List<Vuelo> lista = new ArrayList<>();
        // SQL dinámico: busca por origen Y destino.
        // La parte del 'tipo' permite que si es 'Todos', traiga cualquier categoría.
        String sql = "SELECT * FROM vuelos WHERE origen LIKE ? AND destino LIKE ? AND (tipo = ? OR ? = 'Todos')";

        Connection con = obtenerConexion();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            return lista;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + origen + "%");
            ps.setString(2, "%" + destino + "%");
            ps.setString(3, tipo);
            ps.setString(4, tipo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vuelo v = new Vuelo();
                v.setId(rs.getInt("id"));
                v.setOrigen(rs.getString("origen"));
                v.setDestino(rs.getString("destino"));
                v.setPrecio(rs.getDouble("precio"));
                v.setAsientos(rs.getInt("asientos"));
                v.setTipo(rs.getString("tipo"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { con.close(); } catch (SQLException e) { /* ignorar */ }
        }
        return lista;
    }

    public boolean reducirAsiento(int id) {
        String sql = "UPDATE vuelos SET asientos = asientos - 1 WHERE id = ? AND asientos > 0";
        Connection con = obtenerConexion();
        if (con == null) return false;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfec = ps.executeUpdate();
            return filasAfec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { /* ignorar */ }
        }
    }
}