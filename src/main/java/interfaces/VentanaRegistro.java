package interfaces;

import entidades.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtNuevoUser = new JTextField(15);
    private JPasswordField txtNuevaPass = new JPasswordField(15);
    private JButton btnGuardar = new JButton("Crear Cuenta");

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Importante: solo cierra esta ventana
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel p1 = new JPanel(); p1.add(new JLabel("Usuario:")); p1.add(txtNuevoUser);
        JPanel p2 = new JPanel(); p2.add(new JLabel("Clave:")); p2.add(txtNuevaPass);

        add(p1); add(p2); add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            String u = txtNuevoUser.getText();
            String p = new String(txtNuevaPass.getPassword());
            if (new UsuarioDAO().registrarUsuario(u, p)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar");
            }
        });
    }
}
