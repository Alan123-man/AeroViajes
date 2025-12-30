package entidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new BorderLayout(10, 10)); // Espacio entre componentes

        // Panel superior con búsqueda
        JPanel pnlNorte = new JPanel(new GridBagLayout());
        pnlNorte.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        pnlNorte.add(new JLabel("Destino:"), gbc);
        gbc.gridx = 1;
        pnlNorte.add(txtBuscar, gbc);

        gbc.gridx = 2;
        pnlNorte.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 3;
        pnlNorte.add(comboTipo, gbc);

        gbc.gridx = 4;
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 123, 255));
        btnBuscar.setForeground(Color.WHITE);
        pnlNorte.add(btnBuscar, gbc);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Origen", "Destino", "Precio", "Asientos"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; } // Evitar edición
        };
        tablaVuelos = new JTable(modelo);
        tablaVuelos.setRowHeight(25);
        tablaVuelos.setFillsViewportHeight(true);

        // Color alternado en filas
        tablaVuelos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE);
                }
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(tablaVuelos);
        scroll.setBorder(BorderFactory.createTitledBorder("Vuelos Disponibles"));

        // Botón comprar
        JButton btnComprar = new JButton("REALIZAR COMPRA");
