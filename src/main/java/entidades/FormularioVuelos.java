package entidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioVuelos extends JFrame {
    private JTable tablaVuelos;
    private DefaultTableModel modelo;
    private JTextField txtDestino = new JTextField(10);
    private JTextField txtOrigen = new JTextField(10);
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Todos", "Nacional", "Internacional"});
    private VueloDAO dao = new VueloDAO();

    public FormularioVuelos() {
        setTitle("Panel de Ventas - AeroViajes");
        setSize(850, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlNorte = new JPanel();
        pnlNorte.add(new JLabel("Origen:"));
        pnlNorte.add(txtOrigen);
        pnlNorte.add(new JLabel("Destino:"));
        pnlNorte.add(txtDestino);
        pnlNorte.add(new JLabel("Tipo:"));
        pnlNorte.add(comboTipo);

        JButton btnBuscar = new JButton("Buscar");
        pnlNorte.add(btnBuscar);

        modelo = new DefaultTableModel(new String[]{"ID", "Origen", "Destino", "Precio", "Asientos"}, 0);
        tablaVuelos = new JTable(modelo);
        JButton btnComprar = new JButton("REALIZAR COMPRA");

        add(pnlNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaVuelos), BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);

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
                JOptionPane.showMessageDialog(this, "Seleccione un vuelo de la tabla");
            }
        });

        cargarDatos();
    } // AQUÍ CIERRA EL CONSTRUCTOR

    private void cargarDatos() {
        modelo.setRowCount(0);
        for (Vuelo v : dao.filtrarVuelos(txtOrigen.getText(), txtDestino.getText(), comboTipo.getSelectedItem().toString())) {
            modelo.addRow(new Object[]{v.getId(), v.getOrigen(), v.getDestino(), v.getPrecio(), v.getAsientos()});
        }
    }
}