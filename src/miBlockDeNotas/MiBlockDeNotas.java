/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miBlockDeNotas;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Gabriel
 */
public class MiBlockDeNotas extends javax.swing.JFrame {

    /**
     * Selector de archivo
     */
    private File selectedFile;

    /**
     * Path del archivo
     */
    private String archivo;

    /**
     * Cambio detectado en texto
     */
    private Boolean cambiado;

    /**
     * Creates new form MiBlockDeNotas
     */
    public MiBlockDeNotas() {
        initComponents();
        this.initFile();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (!verificarCambios()) {
                    return;
                }
                System.exit(0);
            }
        });
    }

    private void limpiarCambiado() {
        this.cambiado = false;
        this.textoTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!cambiado) {
                    setTitle(getTitle() + " - modificado");
                }
                cambiado = true;

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!cambiado) {
                    setTitle(getTitle() + " - modificado");
                }
                cambiado = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!cambiado) {
                    setTitle(getTitle() + " - modificado");
                }
                cambiado = true;
            }
        });
    }

    /**
     *
     * @return debe continuar
     */
    private Boolean verificarCambios() {
        if (this.cambiado) {
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            dialogButton = JOptionPane.showConfirmDialog(null, "Archivo modificado, desea guardar?", "Advertencia", dialogButton);
            if (dialogButton == JOptionPane.CANCEL_OPTION || dialogButton == JOptionPane.CLOSED_OPTION) {
                return false;
            }
            if (dialogButton == JOptionPane.OK_OPTION) {
                this.jMenuArchivoGuardarActionPerformed(null);
                if (this.cambiado) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initFile() {
        this.initFile(null);
    }

    private void initFile(File archivo) {

        this.selectedFile = archivo;

        if (this.selectedFile == null) {
            textoTextArea.setText("");
            this.setTitle("Notepad - Nuevo");
            this.limpiarCambiado();
            return;
        }

        FileReader file;
        try {
            file = new FileReader(this.selectedFile);
            this.textoTextArea.read(file, null);
            this.setTitle("Notepad - " + this.selectedFile.getPath());
            this.limpiarCambiado();
        } catch (Exception ex) {
            Logger.getLogger(MiBlockDeNotas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al abrir archivo.");
            return;
        }

    }

    private File seleccionarArchivo(String accion) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showDialog(this, accion);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private void escribirArchivo() {
        try {
            FileWriter writer;
            writer = new FileWriter(selectedFile);
            textoTextArea.write(writer);
            this.setTitle("Notepad - " + this.selectedFile.getPath());
            this.limpiarCambiado();
        } catch (Exception ex) {
            this.selectedFile = null;
            JOptionPane.showMessageDialog(null, "Error al guardar archivo.");
            Logger.getLogger(MiBlockDeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textoTextArea = new javax.swing.JTextArea();
        jMenu = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuArchivoNuevo = new javax.swing.JMenuItem();
        jMenuArchivoAbrir = new javax.swing.JMenuItem();
        jMenuArchivoGuardar = new javax.swing.JMenuItem();
        jMenuArchivoGuardarComo = new javax.swing.JMenuItem();
        jMenuArchivoSalir = new javax.swing.JMenuItem();
        jMenuEdicion = new javax.swing.JMenu();
        jMenuEdicionCortar = new javax.swing.JMenuItem();
        jMenuCopiar = new javax.swing.JMenuItem();
        jMenuEdicionPegar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Notepad");

        textoTextArea.setColumns(20);
        textoTextArea.setRows(5);
        textoTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTextAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textoTextArea);

        jMenu.setName("jMenu"); // NOI18N

        jMenuArchivo.setText("Archivo");
        jMenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoActionPerformed(evt);
            }
        });

        jMenuArchivoNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuArchivoNuevo.setText("Nuevo");
        jMenuArchivoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoNuevoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuArchivoNuevo);

        jMenuArchivoAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuArchivoAbrir.setText("Abrir...");
        jMenuArchivoAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoAbrirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuArchivoAbrir);

        jMenuArchivoGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuArchivoGuardar.setText("Guardar");
        jMenuArchivoGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoGuardarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuArchivoGuardar);

        jMenuArchivoGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuArchivoGuardarComo.setText("Guardar Como...");
        jMenuArchivoGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoGuardarComoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuArchivoGuardarComo);

        jMenuArchivoSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuArchivoSalir.setText("Salir");
        jMenuArchivoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuArchivoSalir);

        jMenu.add(jMenuArchivo);

        jMenuEdicion.setText("Edición");

        jMenuEdicionCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuEdicionCortar.setText("Cortar");
        jMenuEdicionCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEdicionCortarActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuEdicionCortar);

        jMenuCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuCopiar.setText("Copiar");
        jMenuCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCopiarActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuCopiar);

        jMenuEdicionPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuEdicionPegar.setText("Pegar");
        jMenuEdicionPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEdicionPegarActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuEdicionPegar);

        jMenu.add(jMenuEdicion);

        setJMenuBar(jMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoActionPerformed

    }//GEN-LAST:event_jMenuArchivoActionPerformed

    private void jMenuArchivoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoNuevoActionPerformed
        if (this.verificarCambios()) {
            this.initFile();
        }
    }//GEN-LAST:event_jMenuArchivoNuevoActionPerformed

    private void jMenuArchivoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoAbrirActionPerformed
        if (!this.verificarCambios()) {
            return;
        }
        File f = this.seleccionarArchivo("Abrir");
        if (f != null) {
            this.initFile(f);
        }
    }//GEN-LAST:event_jMenuArchivoAbrirActionPerformed

    private void jMenuArchivoGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoGuardarActionPerformed
        if (this.selectedFile == null) {
           this.jMenuArchivoGuardarComoActionPerformed(evt);
           return;
        }
        this.escribirArchivo();
    }//GEN-LAST:event_jMenuArchivoGuardarActionPerformed

    private void textoTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTextAreaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTextAreaKeyPressed

    private void jMenuArchivoGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoGuardarComoActionPerformed
        File f = this.seleccionarArchivo("Guardar");
        if (f == null) {
            return;
        }
        if(f.isFile() && !f.isDirectory()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            dialogButton = JOptionPane.showConfirmDialog(null, "El archivo ya existe, desea sobreescribir?", "Advertencia", dialogButton);
            if (dialogButton == JOptionPane.NO_OPTION || dialogButton == JOptionPane.CLOSED_OPTION) {
                return ;
            }
        }
        this.selectedFile = f;
        this.escribirArchivo();
    }//GEN-LAST:event_jMenuArchivoGuardarComoActionPerformed

    private void jMenuArchivoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoSalirActionPerformed
        if (!this.verificarCambios()) {
            return;
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuArchivoSalirActionPerformed


    private void jMenuCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCopiarActionPerformed
        StringSelection selection
                = new StringSelection(this.textoTextArea.getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }//GEN-LAST:event_jMenuCopiarActionPerformed

    private void jMenuEdicionPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEdicionPegarActionPerformed
        try {
            this.textoTextArea.replaceSelection((String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al pegar texto.");
            Logger.getLogger(MiBlockDeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuEdicionPegarActionPerformed

    private void jMenuEdicionCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEdicionCortarActionPerformed
        StringSelection selection
                = new StringSelection(this.textoTextArea.getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        this.textoTextArea.replaceSelection("");
    }//GEN-LAST:event_jMenuEdicionCortarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiBlockDeNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiBlockDeNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiBlockDeNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiBlockDeNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiBlockDeNotas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenu;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuItem jMenuArchivoAbrir;
    private javax.swing.JMenuItem jMenuArchivoGuardar;
    private javax.swing.JMenuItem jMenuArchivoGuardarComo;
    private javax.swing.JMenuItem jMenuArchivoNuevo;
    private javax.swing.JMenuItem jMenuArchivoSalir;
    private javax.swing.JMenuItem jMenuCopiar;
    private javax.swing.JMenu jMenuEdicion;
    private javax.swing.JMenuItem jMenuEdicionCortar;
    private javax.swing.JMenuItem jMenuEdicionPegar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textoTextArea;
    // End of variables declaration//GEN-END:variables
}
