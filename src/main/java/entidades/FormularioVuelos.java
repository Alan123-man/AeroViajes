package entidades;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioVuelos extends JFrame {
    private JTable tablaVuelos;
    private DefaultTableModel modelo;
    private JTextField txtBuscar = new JTextField(15);
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Todos", "Nacional", "Internacional"});
    private VueloDAO dao = new VueloDAO();

    public FormularioVuelos() {
        setTitle("Panel de Ventas - AeroViajes");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(236, 240, 241)); // Fondo gris claro

        // Panel norte: filtros
        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlNorte.setBackground(new Color(52, 152, 219)); // Azul moderno
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setForeground(Color.WHITE);
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setForeground(Color.WHITE);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(46, 204, 113));
        btnBuscar.setForeground(Color.WHITE);

        pnlNorte.add(lblDestino); pnlNorte.add(txtBuscar);
        pnlNorte.add(lblTipo); pnlNorte.add(comboTipo);
        pnlNorte.add(btnBuscar);

        // Tabla de vuelos
        modelo = new DefaultTableModel(new String[]{"ID", "Origen", "Destino", "Precio", "Asientos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita edición directa
            }
        };
        tablaVuelos = new JTable(modelo);
        tablaVuelos.setFillsViewportHeight(true);
        tablaVuelos.setRowHeight(25);
        tablaVuelos.setSelectionBackground(new Color(52, 152, 219));
        tablaVuelos.setSelectionForeground(Color.WHITE);

        // Centrar los encabezados
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaVuelos.getColumnCount(); i++) {
            tablaVuelos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        JScrollPane scroll = new JScrollPane(tablaVuelos);

        // Panel sur: botón comprar
        JButton btnComprar = new JButton("REALIZAR COMPRA");
        btnComprar.setBackground(new Color(231, 76, 60));
        btnComprar.setForeground(Color.WHITE);
        JPanel pnlSur = new JPanel();
        pnlSur.setBackground(new Color(236, 240, 241));
        pnlSur.add(btnComprar);

        // Añadir componentes al frame
        add(pnlNorte, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        // Acciones botones
        btnBuscar.addActionListener(e -> cargarDatos());
        btnComprar.addActionListener(e -> {
            int fila = tablaVuelos.getSelectedRow();
            if (fila != -1) {
                int id = (int) modelo.getValueAt(fila, 0);
                if (dao.reducirAsiento(id)) {
                    JOptionPane.showMessageDialog(this, "¡Ticket Generado!");
                    cargarDatos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un vuelo primero.");
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        for (Vuelo v : dao.filtrarVuelos(txtBuscar.getText(), comboTipo.getSelectedItem().toString())) {
            modelo.addRow(new Object[]{v.getId(), v.getOrigen(), v.getDestino(), v.getPrecio(), v.getAsientos()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioVuelos().setVisible(true));
    }
}

