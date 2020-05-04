package com.cygnacom.raytracingweekend;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PpmViewer extends JFrame implements KeyListener {

    private StaticImage staticImage;
    private BufferedImage originalImage;
    double zoomFactor = 1.0;

    PpmViewer(File ppmFile) throws IOException {
        this(ImageUtil.readPpm(ppmFile), ppmFile);
    }

    public PpmViewer(BufferedImage img) {
        this(img, null);
    }

    public PpmViewer(BufferedImage img, File ppmFile) {
        originalImage = img;
        staticImage = new StaticImage(
                ImageUtil.scale(originalImage, zoomFactor, zoomFactor), true);
        staticImage.setToolTipText("<html>"
                + (ppmFile != null ? ppmFile.getAbsolutePath() : " Image")
                + "<br>" + originalImage.getWidth() + "x"
                + originalImage.getHeight() + "</html>");
        add(new JScrollPane(staticImage), BorderLayout.CENTER);
        pack();
        addKeyListener(this);
    }

    public static void main(String[] args) throws IOException {
        var frame = new PpmViewer(new File(args[0]));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void zoom(boolean zoomIn) {
        if (zoomIn)
            zoomFactor += .2;
        else
            zoomFactor = Math.max(.2, zoomFactor - .2);

        staticImage.setImage(
                ImageUtil.scale(originalImage, zoomFactor, zoomFactor));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '+' || e.getKeyChar() == '=')
            zoom(true);
        else if (e.getKeyChar() == '-')
            zoom(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    static void view(File ppmFile) throws IOException {
        setLookAndFeel();
        var frame = new PpmViewer(ppmFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void view(BufferedImage img) {
        setLookAndFeel();
        var frame = new PpmViewer(img);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
