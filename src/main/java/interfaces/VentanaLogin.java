package interfaces;

import entidades.FormularioVuelos;
import entidades.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;

    public VentanaLogin() {
        setTitle("Login - AeroViajes");
        setSize(380, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(230, 240, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(panelPrincipal);

        // ===== TÍTULO =====
        JLabel lblTitulo = new JLabel("Ingreso al Sistema", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 70, 140));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // ===== PANEL FORMULARIO =====
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(230, 240, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtUser = new JTextField(15);
        txtPass = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(txtUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(txtPass, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        // ===== PANEL BOTONES =====
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(new Color(230, 240, 250));

        JButton btnEntrar = new JButton("Entrar");
        JButton btnRegistrar = new JButton("Registrarse");

        btnEntrar.setBackground(new Color(0, 120, 215));
        btnEntrar.setForeground(Color.BLACK);
        btnEntrar.setFocusPainted(false);

        btnRegistrar.setBackground(new Color(100, 160, 200));
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFocusPainted(false);

        panelBotones.add(btnEntrar);
        panelBotones.add(btnRegistrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // ===== ACCIONES =====
        btnEntrar.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (new UsuarioDAO().validarLogin(user, pass)) {
                new FormularioVuelos().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o contraseña incorrectos",
                        "Error de acceso",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnRegistrar.addActionListener(e -> {
            new VentanaRegistro().setVisible(true);
        });
    }
}
