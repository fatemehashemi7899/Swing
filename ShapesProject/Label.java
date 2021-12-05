package ShapesProject;

import FinalProject.AnimationPlayer;

import javax.swing.*;
import java.awt.*;

public class Label extends Shape {
    private String text = "JLabel";
    private Dimension luPosition = new Dimension(0,0);
    private int fontSize = 10;
    private String font = "BOLD";

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // use for Zigzag method

    public Label(String text, Dimension luPosition , String font , int fontSize) {
        setFont(font);
        setLuPosition(luPosition);
        setText(text);
        setFontSize(fontSize);
    }
    // override methods
    @Override
    public JComponent drawObject() {
        JComponent jComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics graphics) {
                graphics.setColor(getObjectColor());
                graphics.setFont(new Font(font,Font.BOLD,fontSize));
                graphics.drawString(text,luPosition.width,luPosition.height);
            }
        };
        jComponent.setBounds(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
        jComponent.setVisible(false);
        if(this.isElementOfaGroup()){
            JComponent label=getLabel();
            label.setVisible(true);
            jComponent.add(label);
        }
        return jComponent;
    }

    @Override
    public void zigzag(JComponent jc, int start, int stop) {
        if(luPosition.width+50 <= screenSize.width){
            if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 0 && AnimationPlayer.frameCounter < stop){
                jc.setVisible(false);
                setLuPosition(new Dimension(luPosition.width+50,luPosition.height+100));
                jc.setVisible(true);
            }else if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 1 && AnimationPlayer.frameCounter < stop){
                jc.setVisible(false);
                setLuPosition(new Dimension(luPosition.width+50,luPosition.height-100));
                jc.setVisible(true);
            }
        }
    }

    @Override
    public void resize(JComponent jc, int start, double size) {
        if(AnimationPlayer.frameCounter == start){
            double sizeFont;
            sizeFont=this.fontSize * size;
            this.fontSize = (int) sizeFont;
        }
    }

    @Override
    public void jump(JComponent jComponent, int start , int x , int y) {
        if(AnimationPlayer.frameCounter == start){
            jComponent.setVisible(false);
            setLuPosition(new Dimension(x,y));
            if(isElementOfaGroup()) {
                getGroupNumberLabel().setLuPosition(new Dimension(x, y));
            }
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
    //use for Groups
    public JComponent getLabel(){
        if(this.isElementOfaGroup()){
            Label label=new Label(String.valueOf(this.getGroupNumber()+1),new Dimension(getLuPosition().width,getLuPosition().height),"BOLD",10);
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
    public Dimension getLuPosition() {
        return luPosition;
    }

    public void setLuPosition(Dimension luPosition) {
        if(luPosition.width > 0 && luPosition.height > 0){
            this.luPosition = luPosition;
        }
    }

    public void setFontSize(int fontSize) {
        if(fontSize > 0)
            this.fontSize = fontSize;
    }

    public void setText(String text) {
        if( text != null)
            this.text = text;
    }
    public void setFont(String font){
        if(font != null){
            this.font = font;
        }
    }
}
