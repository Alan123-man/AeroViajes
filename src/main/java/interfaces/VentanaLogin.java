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
        setResizable(false);

        // Panel principal con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 62, 80)); // Fondo azul oscuro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Etiqueta Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setForeground(Color.WHITE);
        panel.add(lblUser, gbc);

        // Campo Usuario
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUser, gbc);

        // Etiqueta Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblPass = new JLabel("Clave:");
        lblPass.setForeground(Color.WHITE);
        panel.add(lblPass, gbc);

        // Campo Contraseña
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPass, gbc);

        // Panel botones
        JPanel pnlBotones = new JPanel();
        pnlBotones.setBackground(new Color(45, 62, 80));
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(52, 152, 219));
        btnEntrar.setForeground(Color.BLACK);
        JButton btnReg = new JButton("Registrarse");
        btnReg.setBackground(new Color(231, 76, 60));
        btnReg.setForeground(Color.BLACK);
        pnlBotones.add(btnEntrar);
        pnlBotones.add(btnReg);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(pnlBotones, gbc);

        add(panel);

        // Acciones botones
        btnEntrar.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());
            if (new UsuarioDAO().validarLogin(user, pass)) {
                new FormularioVuelos().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectas");
            }
        });

        btnReg.addActionListener(e -> {
            new VentanaRegistro().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
