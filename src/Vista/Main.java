package vista;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JMenuBar barraMenu;
    private JMenu menuGestion;
    private JMenuItem itemEstudiantes, itemDocentes, itemCursos, itemMatricula, itemReporte;

    public Main() {
        setTitle("Gestión Academica");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        

        initMenu();
    }

    private void initMenu() {
        barraMenu = new JMenuBar();
        menuGestion = new JMenu("Gestión");

        itemEstudiantes = new JMenuItem("Estudiantes");
        itemDocentes = new JMenuItem("Docentes");
        itemCursos = new JMenuItem("Cursos");
        itemMatricula = new JMenuItem("Matrícula");
        itemReporte = new JMenuItem("Reporte");

        menuGestion.add(itemEstudiantes);
        menuGestion.add(itemDocentes);
        menuGestion.add(itemCursos);
        menuGestion.add(itemMatricula);
        menuGestion.add(itemReporte);

        barraMenu.add(menuGestion);
        setJMenuBar(barraMenu);

        itemEstudiantes.addActionListener(e -> cambiarPanel(new EstudianteVista()));
        itemDocentes.addActionListener(e -> cambiarPanel(new DocenteVista()));
        itemCursos.addActionListener(e -> cambiarPanel(new VistaCurso()));
        itemMatricula.addActionListener(e -> cambiarPanel(new VistaMatricula()));
        itemReporte.addActionListener(e -> cambiarPanel(new ReporteVista()));
    }

    private void cambiarPanel(JPanel panel1) {
        getContentPane().removeAll();
        getContentPane().add(panel1, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
