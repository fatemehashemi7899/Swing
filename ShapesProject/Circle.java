package ShapesProject;

import FinalProject.AnimationPlayer;
import FinalProject.InvalidIntegerException;

import javax.swing.*;
import java.awt.*;

public class Circle extends Shape {
    private int radius = 10;
    private Color borderColor = new Color(0,0,0);
    private Dimension centerPosition = new Dimension(10,10);
    private int border = 0;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // use for Zigzag method

    public Circle (int radius, Dimension centerPosition,int border,Color borderColor){
        setRadius(radius);
        this.borderColor=borderColor;
        this.centerPosition=centerPosition;
        setBorder(border);
    }
    // override methods
    @Override
    public JComponent drawObject(){
        JComponent jComponent = new JComponent() {

            @Override
            protected void paintComponent(Graphics graphics) {

                graphics.setColor(borderColor);
                graphics.fillOval(centerPosition.width-radius-border,centerPosition.height-radius-border,(radius+border)*2,(radius+border)*2);
                graphics.setColor(getObjectColor());
                graphics.fillOval(centerPosition.width-radius,centerPosition.height-radius,(radius)*2,(radius)*2);

            }

        };
        jComponent.setBounds(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
        jComponent.setVisible(false);
        if(this.isElementOfaGroup()){
            JComponent label=getLabel();
            label.setVisible(true);
            jComponent.add(label);
            //if(this.isShow()){
                //getLabel().setVisible(true);
            //}
        }
        return jComponent;
    }

    @Override
    public void jump(JComponent jComponent, int start , int x , int y) {
       if(AnimationPlayer.frameCounter == start){
           jComponent.setVisible(false);
           setCenterPosition(new Dimension(x,y));
           if(isElementOfaGroup()) {
               getGroupNumberLabel().setLuPosition(new Dimension(x, y));
           }
           if(isShow()) {
               jComponent.setVisible(true);
           }
           if(!isShow()){
               jComponent.setVisible(false);
           }
       }
    }

    @Override
    public void zigzag(JComponent jc, int start, int stop) {
        if (centerPosition.width+radius+border+50 < screenSize.width){
            if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 0 && AnimationPlayer.frameCounter < stop){
                jc.setVisible(false);
                setCenterPosition(new Dimension(getCenterPosition().width+50,getCenterPosition().height+100));
                jc.setVisible(true);
            }else if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 1 && AnimationPlayer.frameCounter < stop){
                jc.setVisible(false);
                setCenterPosition(new Dimension(getCenterPosition().width+50,getCenterPosition().height-100));
                jc.setVisible(true);
            }
        }
    }

    @Override
    public void resize(JComponent jc, int start, double size) {
        if(AnimationPlayer.frameCounter == start){
            double newRadius;
            newRadius= this.radius * size;
            this.radius = (int) newRadius;
        }
    }
    // use for Groups
    public JComponent getLabel(){
        if(this.isElementOfaGroup()){
            ShapesProject.Label label=new Label(String.valueOf(this.getGroupNumber()+1),new Dimension(getCenterPosition().width,getCenterPosition().height),"BOLD",10);
            label.setObjectColor(Color.darkGray);
            this.setGroupNumberLabel(label);
            JComponent l=label.drawObject();
            return l;
        }
        else{
            System.out.println("NO label becouse this shape is not an element of a group");
            //TODO exception throw kone
            return null;
        }
    }
    //setters and getters
    private void setRadius(int radius) {
        if (radius > 0) this.radius = radius;
        else {
            throw new InvalidIntegerException("radius is invalid." + radius);
        }
    }

    public void setBorder(int border) {
        if (border > 0 && border < radius) this.border = border;
        else
            throw new InvalidIntegerException("border is invalid");
    }

    public void setBorderColor(Color borderColor) {
        if (borderColor.getBlue()<256 && borderColor.getBlue()> -1 && borderColor.getGreen()<256 && borderColor.getGreen()>-1 && borderColor.getRed()<256 && borderColor.getRed()>-1)
            this.borderColor = borderColor;
    }

    public Dimension getCenterPosition() {
        return centerPosition;
    }

    public void setCenterPosition(Dimension centerPosition) {
        if(centerPosition.height > 0 && centerPosition.width >0){
            this.centerPosition = centerPosition;
        }
    }

}
