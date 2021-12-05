package ShapesProject;

import FinalProject.AnimationPlayer;

import javax.swing.*;
import java.awt.*;

public class Line extends Shape {
    private Dimension endPosition;
    private Dimension startPosition;
    private Color borderColor;
    private int border = 0;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // use for Zigzag method

    public Line(Dimension endPosition, Dimension startPosition, int border) {
        setEndPosition(endPosition);
        setStartPosition(startPosition);
        setBorder(border);
    }

    @Override
    public JComponent drawObject() {
        JComponent jComponent = new JComponent() {

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setStroke(new BasicStroke(border));
                g2.setColor(getObjectColor());
                g2.drawLine(startPosition.width, startPosition.height, endPosition.width, endPosition.height);
            }
        };
        jComponent.setBounds(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        jComponent.setVisible(false);
        if (this.isElementOfaGroup()) {
            JComponent label = getLabel();
            label.setVisible(true);
            jComponent.add(label);
        }
        return jComponent;
    }

    // methods
    @Override
    public void resize(JComponent jc, int start, double size) {
        if (AnimationPlayer.frameCounter == start) {
            int deltaX = getEndPosition().width - getStartPosition().width;
            int deltaY = getEndPosition().height - getStartPosition().height;
            double x2 = this.endPosition.width + (deltaX * (size - 1)) / 2;
            double y2 = this.endPosition.height + (deltaY * (size - 1)) / 2;
            double x1 = this.startPosition.width - (deltaX * (size - 1)) / 2;
            double y1 = this.startPosition.height - (deltaY * (size - 1)) / 2;
            this.setEndPosition(new Dimension((int) x2, (int) y2));
            this.setStartPosition(new Dimension((int) x1, (int) y1));
        }
    }

    @Override
    public void jump(JComponent jComponent, int start, int x, int y) {
        if (AnimationPlayer.frameCounter == start) {
            jComponent.setVisible(false);
            int deltaX = getEndPosition().width - getStartPosition().width;
            int deltaY = getEndPosition().height - getStartPosition().height;
            int x2 = x + deltaX;
            int y2 = y + deltaY;
            setEndPosition(new Dimension(x, y));
            setStartPosition(new Dimension(x2, y2));
            if (isElementOfaGroup()) {
                getGroupNumberLabel().setLuPosition(new Dimension(x, y));
            }
            if (isShow()) {
                jComponent.setVisible(true);
            }
            if (!isShow()) {
                jComponent.setVisible(false);
            }
        }
    }

    @Override
    public void zigzag(JComponent jc, int start, int stop) {
        if (endPosition.width + 50 <= screenSize.width) {
            if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 0 && AnimationPlayer.frameCounter < stop) {
                jc.setVisible(false);
                setStartPosition(new Dimension(getStartPosition().width + 50, getStartPosition().height + 100));
                setEndPosition(new Dimension(getEndPosition().width + 50, getEndPosition().height + 100));
                jc.setVisible(true);
            } else if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 1 && AnimationPlayer.frameCounter < stop) {
                jc.setVisible(false);
                setStartPosition(new Dimension(getStartPosition().width + 50, getStartPosition().height - 100));
                setEndPosition(new Dimension(getEndPosition().width + 50, getEndPosition().height - 100));
                jc.setVisible(true);
            }
        }
    }
    // just for line of a Group
    public JComponent getLabel() {
        if (this.isElementOfaGroup()) {
            Label label = new Label(String.valueOf(this.getGroupNumber() + 1), new Dimension(getStartPosition().width, getStartPosition().height), "BOLD", 10);
            label.setObjectColor(Color.darkGray);
            this.setGroupNumberLabel(label);
            JComponent l = label.drawObject();
            return l;
        } else {
            System.out.println("NO label becouse this shape is not an element of a group");
            //TODO exception throw kone
            return null;
        }
    }
    // Setters and Getters
    public Dimension getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Dimension startPosition) {
        if (startPosition.height > 0 && startPosition.width > 0)
            this.startPosition = startPosition;
    }

    public Dimension getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Dimension endPosition) {
        if (endPosition.width > 0 && endPosition.height > 0)
            this.endPosition = endPosition;
    }

    public void setBorder(int border) {
        if( border > -1)
            this.border = border;
    }
}

