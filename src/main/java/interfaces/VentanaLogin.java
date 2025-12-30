package interfaces;

import entidades.FormularioVuelos;
import entidades.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private JTextField txtUser = new JTextField(15);
    private JPasswordField txtPass = new JPasswordField(15);

    public VentanaLogin() {
        setTitle("Login AeroViajes");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        add(new JLabel("Usuario:", JLabel.CENTER)); add(txtUser);
        add(new JLabel("Clave:", JLabel.CENTER)); add(txtPass);

        // Configuración de botones
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(46, 204, 113)); // Color Verde
        btnEntrar.setForeground(Color.WHITE); // Texto blanco
        btnEntrar.setFocusPainted(false);

        JButton btnReg = new JButton("Registrarse");
        btnReg.setBackground(new Color(52, 152, 219)); // Color Azul
        btnReg.setForeground(Color.WHITE);
        btnReg.setFocusPainted(false);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(231, 76, 60)); // Color Rojo
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);

        JPanel pnlBotones = new JPanel();
        pnlBotones.add(btnEntrar);
        pnlBotones.add(btnReg);
        pnlBotones.add(btnSalir);
        add(pnlBotones);

        // Acción para entrar al sistema
        btnEntrar.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());
            if (new UsuarioDAO().validarLogin(user, pass)) {
                new FormularioVuelos().setVisible(true); // Abre la app principal
                this.dispose(); // Cierra el login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectas");
            }
        });

        // Acción para abrir la ventana de registro
        btnReg.addActionListener(e -> {
            new VentanaRegistro().setVisible(true);
        });

        // Acción para salir de la aplicación
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
