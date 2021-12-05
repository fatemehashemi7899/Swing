package ShapesProject;

import FinalProject.AnimationPlayer;
import FinalProject.InvalidIntegerException;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Shape {
    private String[] effects;
    private Color objectColor = new Color(114, 140, 91);
    private Graphics graphics;
    private boolean isShow;
    private boolean isElementOfaGroup;
    private Label groupNumberLabel;
    private int groupNumber = 0;

    //abstract methods
    public abstract JComponent drawObject();

    public abstract void jump(JComponent jComponent, int start, int x, int y);

    public abstract void resize(JComponent jc, int start, double size);

    public abstract void zigzag(JComponent jc, int start, int stop);

    // methods for all shapes
    public void Hide(int start, JComponent jc) {

        if (AnimationPlayer.frameCounter == start) {
            jc.setVisible(false);
            isShow = false;
        }
    }

    public void Show(int start, JComponent jc) {
        if (AnimationPlayer.frameCounter == start) {
            jc.setVisible(true);
            isShow = true;
        }
    }

    public void blink(int start, int stop, JComponent jc) {
        if (isShow) {
            if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 0 && AnimationPlayer.frameCounter < stop) {
                jc.setVisible(true);
            } else if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 1 && AnimationPlayer.frameCounter < stop) {
                jc.setVisible(false);
            }
            if (AnimationPlayer.frameCounter == stop) {
                jc.setVisible(true);
            }
        }
    }

    public void changeColor(int start, Color color, JComponent jc) {
        if (AnimationPlayer.frameCounter == start)
            this.setObjectColor(color);
    }

    public void colorFull(int start, int stop, JComponent jc) {
        if (AnimationPlayer.frameCounter > start && AnimationPlayer.frameCounter <= stop) {
            Random randomColor = new Random();
            int r = randomColor.nextInt(255);
            int g = randomColor.nextInt(255);
            int b = randomColor.nextInt(255);
            this.setObjectColor(new Color(r, g, b));
            if (isShow()) {
                jc.setVisible(false);
                jc.setVisible(true);
            }
        }

    }

    // setters and getters
    public String[] getEffects() {

        return effects;
    }

    public Color getObjectColor() {
        return objectColor;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setEffects(String[] effects) {
        this.effects = effects;
    }

    public void setObjectColor(Color objectColor) {
        if (objectColor.getBlue() < 256 && objectColor.getBlue() > -1 && objectColor.getGreen() < 256 && objectColor.getGreen() > -1 && objectColor.getRed() < 256 && objectColor.getRed() > -1)
            this.objectColor = objectColor;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }


    public boolean isShow() {
        return isShow;
    }

    public boolean isElementOfaGroup() {
        return isElementOfaGroup;
    }

    public void setIsElmentOfaGroup(boolean isElmentOfaGroup) {
        this.isElementOfaGroup = isElmentOfaGroup;
    }

    public Label getGroupNumberLabel() {
        return groupNumberLabel;
    }

    public void setGroupNumberLabel(Label groupNumberLabel) {
        this.groupNumberLabel = groupNumberLabel;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public static class Rect extends Shape {
        private int width = 10;
        private int length = 10;
        private Color borderColor = new Color(0,0,0);
        private Dimension luPosition = new Dimension(0,0);
        private int border = 0;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // use for Zigzag method

        public Rect(int width,int length,Dimension luPosition, int border , Color borderColor){
            setWidth(width);
            setLength(length);
            setLuPosition(luPosition);
            setBorderColor(borderColor);
            setBorder(border);
        }

        @Override
        public JComponent drawObject() {
            JComponent jComponent = new JComponent() {
                @Override
                protected void paintComponent(Graphics graphics) {


                    graphics.setColor(borderColor);
                    graphics.fillRect(luPosition.width-border,luPosition.height-border,width+2*border,length+2*border);
                    graphics.setColor(getObjectColor());
                    graphics.fillRect(luPosition.width,luPosition.height,width,length);


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

        public JComponent getLabel(){
            if(this.isElementOfaGroup()){
                Label label=new Label(String.valueOf(this.getGroupNumber()+1), new Dimension(getLuPosition().width+(width/2),getLuPosition().height+(length/2))
                        ,"BOLD",10);
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
        } // use for Groups
        // methods
        @Override
        public void zigzag(JComponent jc, int start, int stop) {
            if(getLuPosition().width+width+50 <= screenSize.width){
                if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 0 && AnimationPlayer.frameCounter < stop){
                    jc.setVisible(false);
                    setLuPosition(new Dimension(getLuPosition().width+50,getLuPosition().height+100));

                    jc.setVisible(true);
                }else if (AnimationPlayer.frameCounter >= start && (AnimationPlayer.frameCounter - start) % 2 == 1 && AnimationPlayer.frameCounter < stop){
                    jc.setVisible(false);
                    setLuPosition(new Dimension(getLuPosition().width+50,getLuPosition().height-100));
                    jc.setVisible(true);
                }
            }
        }

        @Override
        public void resize(JComponent jc, int start, double size) {
            if (AnimationPlayer.frameCounter == start){
                double x,y;
                double w,l;
                w=this.width * size;
                l=this.length * size;
                x=getLuPosition().width-((size-1)*width/2);
                y= getLuPosition().height-((size-1)*length/2);
                setLuPosition(new Dimension((int)x,(int)y));
                setLength((int)l);
                setWidth((int)w);
                this.length = (int) l;
                this.width = (int) w;
            }
        }

        @Override
        public void jump(JComponent jComponent,int start , int x , int y) {

            if(AnimationPlayer.frameCounter == start){
                jComponent.setVisible(false);
                setLuPosition(new Dimension(x,y));
                if(isElementOfaGroup()) {
                    getGroupNumberLabel().setLuPosition(new Dimension(x+(width/2), y+(length/2)));
                }
                if(isShow()) {
                    jComponent.setVisible(true);
                }
                if(!isShow()){
                    jComponent.setVisible(false);
                }
            }
        }
        //setters and Getters
        public void setWidth(int width) {
            if(width > 0){
                this.width = width;
            }
        }

        public void setLength(int length) {
            if(length > 0){
                this.length = length;
            }
        }

        public void setLuPosition(Dimension luPosition) {
            if(luPosition.width > 0 && luPosition.height > 0)
                this.luPosition = luPosition;
        }

        public Dimension getLuPosition() {
            return luPosition;
        }

        public void setBorderColor(Color borderColor) {
            if (borderColor.getBlue()<256 && borderColor.getBlue()> -1 && borderColor.getGreen()<256 && borderColor.getGreen()>-1 && borderColor.getRed()<256 && borderColor.getRed()>-1)
                this.borderColor = borderColor;
        }

        public void setBorder(int border) {
            if (border >= 0)
                this.border = border;
            else
                throw new InvalidIntegerException("invalid border");
        }

    }
}
