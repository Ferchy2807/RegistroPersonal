package org.fernando;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainRegistroPersonal extends JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    ArrayList<MainRegistroPersonal> listaRegistros = new ArrayList<MainRegistroPersonal>();
    private JPanel jPanelMain;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtNumero;
    private JRadioButton educacionBasicaCompletaRadioButton;
    private JRadioButton educacionSuperiorCompletaRadioButton;
    private JRadioButton educacionSuperiorIncompletaRadioButton;
    private JRadioButton educacionBasicaIncompletaRadioButton;
    private JButton REGISTRARButton;
    private JLabel labelNombres;
    private JLabel labelApellidos;
    private JLabel labelCorreo;
    private JLabel labelNumero;
    private JLabel labelNivEducativo;
    private JTable jtable_mostrarRegistro;
    private JComboBox cmbCorreo;
    private ButtonGroup buttonGroup_nEducativo = new ButtonGroup();
    public String nivelEducacional; 

    public MainRegistroPersonal() {
        setTitle("Registro de Personal");
        setContentPane(jPanelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Correo");
        modelo.addColumn("Numero");
        modelo.addColumn("Nivel Educativo");
        jtable_mostrarRegistro.setModel(modelo);
        refrescarTabla();
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarDatos();
                refrescarTabla();

            }
        });
        txtNombres.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < ' ' || c > ' ')) {
                    e.consume();
                }
            }
        });
        txtApellidos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < ' ' || c > ' ')) {
                    e.consume();
                }
            }
        });
        txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ((c < '0' || c > '9'))
                    e.consume();
            }
        });
        txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(txtNumero.getText().length() > 8){
                    e.consume();
                }
            }
        });
    }

    public void confirmarDatos() {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String correo = txtCorreo.getText();
        String numero = txtNumero.getText();


        if (nombres.equals("") || apellidos.equals("") || correo.equals("") || numero.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
        } else {
            if (educacionBasicaCompletaRadioButton.isSelected()) {
                nivelEducacional = educacionBasicaCompletaRadioButton.getText();
            } else if (educacionSuperiorCompletaRadioButton.isSelected()) {
                nivelEducacional = educacionSuperiorCompletaRadioButton.getText();
            } else if (educacionSuperiorIncompletaRadioButton.isSelected()) {
                nivelEducacional = educacionSuperiorIncompletaRadioButton.getText();
            } else if (educacionBasicaIncompletaRadioButton.isSelected()) {
                nivelEducacional = educacionBasicaIncompletaRadioButton.getText();
            }

            else  {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un nivel de educación");
                return;
            }
            if(numero.length()<9){
                JOptionPane.showMessageDialog(null, "Por favor , ingrese un numero de digitos validos (9)");
                return;
            }

            if(!correo.contains("@gmail.com")&& !correo.contains("@hotmail.com")&& !correo.contains("outlook.com") && !correo.contains("@yahoo.com")){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un dominio de correo valido(@gmail.com,@hotmail.com,@outlook.com,@yahoo.com)");
                return;
            }

            registrarTabla();
        }



    }

    public void registrarTabla() {

        buttonGroup_nEducativo.add(educacionBasicaCompletaRadioButton);
        buttonGroup_nEducativo.add(educacionSuperiorCompletaRadioButton);
        buttonGroup_nEducativo.add(educacionSuperiorIncompletaRadioButton);
        buttonGroup_nEducativo.add(educacionBasicaIncompletaRadioButton);
        String[] columnas = new String[5];
        columnas[0] = txtNombres.getText();
        columnas[1] = txtApellidos.getText();
        columnas[2] = txtCorreo.getText();
        columnas[3] = txtNumero.getText();
        columnas[4] = nivelEducacional;
        modelo.addRow(columnas);
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtNumero.setText("");
        buttonGroup_nEducativo.clearSelection();
        JOptionPane.showMessageDialog(null, "´Personal registrado exitosamente");

    }

    private void refrescarTabla() {
        jtable_mostrarRegistro.setModel(modelo);
    }

    public static void main(String[] args) {
        new MainRegistroPersonal();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
