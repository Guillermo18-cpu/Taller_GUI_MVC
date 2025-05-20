package vista;
import javax.swing.*;
import java.awt.*;
import vista.*;

import controlador.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Gestión Academica");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        initMenu();
    }

    private void initMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu("Gestión");

        JMenuItem itemEstudiante = new JMenuItem("Estudiantes");
        JMenuItem itemDocente = new JMenuItem("Docentes");
        JMenuItem itemCurso = new JMenuItem("Cursos");
        JMenuItem itemMatricula = new JMenuItem("Matrícula");
        JMenuItem itemReporte = new JMenuItem("Reporte");

        menu.add(itemEstudiante);
        menu.add(itemDocente);
        menu.add(itemCurso);
        menu.add(itemMatricula);
        menu.add(itemReporte);

        barraMenu.add(menu);
        setJMenuBar(barraMenu);

        itemEstudiante.addActionListener(e -> {
            EstudianteVista vista = new EstudianteVista();
            new EstudianteControlador(vista);
            setPanel(vista);
        });

        itemDocente.addActionListener(e -> {
            DocenteVista vista = new DocenteVista();
            new DocenteControlador(vista);
            setPanel(vista);
        });

        itemCurso.addActionListener(e -> {
            VistaCurso vista = new VistaCurso();
            new CursoControlador(vista);
            setPanel(vista);
        });

        itemMatricula.addActionListener(e -> {
            VistaMatricula vista = new VistaMatricula();
            new MatriculaControlador(vista);
            setPanel(vista);
        });

        itemReporte.addActionListener(e -> {
            ReporteVista vista = new ReporteVista();
            new ReporteControlador(vista);
            setPanel(vista);
        });
    }

    private void setPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}