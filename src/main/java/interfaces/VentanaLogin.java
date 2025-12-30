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
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        add(new JLabel("Usuario:", JLabel.CENTER)); add(txtUser);
        add(new JLabel("Clave:", JLabel.CENTER)); add(txtPass);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnReg = new JButton("Registrarse");
        JButton btnSalir = new JButton("Salir");

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
            System.exit(0); // Cierra completamente el programa
        });
    }
}
