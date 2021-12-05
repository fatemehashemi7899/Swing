package FinalProject;

import ShapesProject.Shape;

import java.util.ArrayList;

public class Group {
    private ArrayList<Shape> Shapes;
    private String[] effects;
    private int groupNumber; // use for showing a number on each shape of group

    public Group(ArrayList<Shape> shapes, String[] effects , int groupNumber) {
        setShapes(shapes);
        setEffects(effects);
        setGroupNumber(groupNumber);
    }
    //setters and getters
    public void setShapes(ArrayList shapesOfCount) {
        this.Shapes = shapesOfCount;
    }
    public void setEffects(String[] effects ){
        this.effects = effects;
    }

    public ArrayList<Shape> getShapes() {
        return Shapes;
    }

    public String[] getEffects() {
        return effects;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }
}

