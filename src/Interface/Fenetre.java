package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.graphstream.graph.Graph;

public class Fenetre extends JFrame {
    JLabel lbInsertVol = new JLabel("Insérez une liste :");
    JTextField txtInsertVol = new JTextField("Chemin du/des fichiers...");
    JLabel lbKmax = new JLabel("Valeur de Kmax :");
    JTextField txtKmax = new JTextField("Entrez un entier...");
    JTextArea txtConsole = new JTextArea();
    JButton btParcourir = new JButton("Parcourir");
    JButton btTraiter = new JButton("Traiter");
    JButton btExporter = new JButton("Exporter");
    JButton btCarte = new JButton("Afficher la carte");
    JButton btParcourirListeAeroport = new JButton("Parcourir");

    JPanel mainPanel = new JPanel(new GridBagLayout());
    JDesktopPane graphPanel = new JDesktopPane();

    private int currentGraphIndex = 0;
    private List<Graph> graphes;

    public Fenetre() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialisationVue();
        ajouterActionListeners();
    }

    private void initialisationVue() {
        this.setTitle("Projet_Crash");

        GridBagConstraints cont = new GridBagConstraints();
        cont.insets = new Insets(5, 5, 5, 5);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Label Insert List
        cont.fill = GridBagConstraints.HORIZONTAL;
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 2;
        mainPanel.add(lbInsertVol, cont);

        // Label Kmax
        cont.gridx = 5;
        cont.gridy = 0;
        cont.gridwidth = 1;
        mainPanel.add(lbKmax, cont);

        // Text Insert List
        cont.gridx = 0;
        cont.gridy = 1;
        cont.gridwidth = 3;
        mainPanel.add(txtInsertVol, cont);

        // Bouton Parcourir
        cont.gridx = 3;
        cont.gridy = 1;
        cont.gridwidth = 1;
        mainPanel.add(btParcourir, cont);

        // Text Kmax
        cont.gridx = 5;
        cont.gridy = 1;
        cont.gridwidth = 1;
        mainPanel.add(txtKmax, cont);

        // Label Insertion Liste Aeroport
        JLabel lbInsertionListeAeroport = new JLabel("Insertion Liste Aeroport :");
        cont.gridx = 4;
        cont.gridy = 0;
        cont.gridwidth = 1;
        mainPanel.add(lbInsertionListeAeroport, cont);

        // Deuxième bouton Parcourir pour la sélection d'une liste d'aéroports
        cont.gridx = 4;
        cont.gridy = 1;
        cont.gridwidth = 1;
        cont.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btParcourirListeAeroport, cont);

        // Graph Panel
        cont.gridx = 0;
        cont.gridy = 2;
        cont.gridwidth = 6;
        cont.weightx = 1;
        cont.weighty = 1;
        cont.fill = GridBagConstraints.BOTH;
        mainPanel.add(graphPanel, cont);

        // Console
        JScrollPane consoleScrollPane = new JScrollPane(txtConsole);
        cont.gridx = 0;
        cont.gridy = 4;
        cont.gridwidth = 6;
        cont.weightx = 1;
        cont.weighty = 0.1;
        cont.fill = GridBagConstraints.BOTH;
        mainPanel.add(consoleScrollPane, cont);

        // Bouton Afficher Carte
        cont.gridx = 0;
        cont.gridy = 6;
        cont.gridwidth = 2;
        cont.weighty = 0;
        mainPanel.add(btCarte, cont);

        // Bouton Traiter
        cont.gridx = 3;
        cont.gridy = 6;
        cont.gridwidth = 2;
        cont.weighty = 0;
        mainPanel.add(btTraiter, cont);

        // Bouton Exporter
        cont.gridx = 5;
        cont.gridy = 6;
        cont.gridwidth = 1;
        cont.weighty = 0;
        mainPanel.add(btExporter, cont);

        this.setContentPane(mainPanel);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
    }

    private void ajouterActionListeners() {
        btParcourir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                int option = fileChooser.showOpenDialog(Fenetre.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    StringBuilder filePaths = new StringBuilder();
                    for (File file : files) {
                        filePaths.append(file.getAbsolutePath()).append(";");
                    }
                    txtInsertVol.setText(filePaths.toString());
                }
            }
        });

        btParcourirListeAeroport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(Fenetre.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    txtInsertVol.setText(file.getAbsolutePath());
                }
            }
        });

        // Ajout d'action listeners pour les autres boutons
        btCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher la carte
                // Cette fonctionnalité reste à implémenter
            }
        });

        btTraiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Traiter les fichiers et utiliser kmax si spécifié
                // Cette fonctionnalité reste à implémenter
            }
        });

        btExporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(Fenetre.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Exporter les résultats dans le fichier
                    // Cette fonctionnalité reste à implémenter
                }
            }
        });
    }

    public void afficherGraphes(List<Graph> graphes) {
        this.graphes = graphes;
        afficherGraphiqueCourant();
    }

    private void afficherGraphiqueCourant() {
        graphPanel.removeAll();
        FenetreGraph frame = new FenetreGraph(graphes.get(currentGraphIndex));
        graphPanel.add(frame);
        frame.setVisible(true);
        try {
            frame.setMaximum(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        graphPanel.revalidate();
        graphPanel.repaint();

        frame.addPreviousButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentGraphIndex > 0) {
                    currentGraphIndex--;
                    afficherGraphiqueCourant();
                }
            }
        });

        frame.addNextButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentGraphIndex < graphes.size() - 1) {
                    currentGraphIndex++;
                    afficherGraphiqueCourant();
                }
            }
        });
    }
}
