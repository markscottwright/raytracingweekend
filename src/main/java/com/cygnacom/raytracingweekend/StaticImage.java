package com.cygnacom.raytracingweekend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class StaticImage extends JComponent {
    private BufferedImage image;
    private boolean center;

    public StaticImage(BufferedImage image, boolean center) {
        this.image = image;
        this.center = center;
        setPreferredSize(
                new Dimension(this.image.getWidth(), this.image.getHeight()));
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        setPreferredSize(
                new Dimension(this.image.getWidth(), this.image.getHeight()));
        setSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
        invalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var imageWidth = image.getWidth();
        var imageHeight = image.getHeight();
        int x = 0;
        int y = 0;
        if (center) {
            if (imageWidth < getSize().width)
                x += (getSize().width - imageWidth) / 2;
            if (imageHeight < getSize().height)
                y += (getSize().height - imageHeight) / 2;
        }
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, x, y, this);
    }
}