package FinalProject;

import ShapesProject.Circle;
import ShapesProject.Label;
import ShapesProject.Line;
import ShapesProject.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnimationPlayer {
    private Color backgroundColor = new Color(231, 249, 212);
    private ShapesProject.Shape[] elements;
    private int frames;
    private int speed;
    private ArrayList<Group> groups = new ArrayList<>();
    public static int sleepTime;
    public static int frameCounter = 1;

    public void loadAnimationFromFile(String textFile) {
        int n = 0;
        int G = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(textFile));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                int r = 0, g = 0, b = 0;
                int br = 0, bg = 0, bb = 0, border = 0;
                String[] effects = new String[100];
                int x = 0, y = 0, radius = 0;
                int length = 0, width = 0, xLU = 0, yLU = 0;
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                String text = null;
                String font = null;
                int fontSize = 0;
                int xLabel = 0, yLabel = 0;
                String[] groupEffects = new String[100];
                if (line.startsWith("frames:")) {
                    setFrames(Integer.parseInt(line.split(" ")[1]));
                } else if (line.startsWith("speed:")) {
                    line = line.replaceAll("fps", "");
                    setSpeed(Integer.parseInt(line.split(" ")[1]));
                    line = bufferedReader.readLine();
                    int numberOfShapes = Integer.parseInt(line);
                    this.elements = new ShapesProject.Shape[numberOfShapes];
                    line = bufferedReader.readLine();
                    sleepTime = getSleepTime();
                } else {
                    switch (line) {
                        case "Circle":
                            while (line.length() != 0) {
                                line = bufferedReader.readLine();
                                if (line.startsWith("r: ")) {
                                    radius = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("x: ")) {
                                    x = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("y: ")) {
                                    y = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("border: ")) {
                                    border = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("color:")) {
                                    String c = line.split(" ")[1];
                                    r = Integer.parseInt(c.split(",")[0]);
                                    g = Integer.parseInt(c.split(",")[1]);
                                    b = Integer.parseInt(c.split(",")[2]);
                                } else if (line.startsWith("color border:")) {
                                    String bc = line.split(" ")[2];
                                    br = Integer.parseInt(bc.split(",")[0]);
                                    bg = Integer.parseInt(bc.split(",")[1]);
                                    bb = Integer.parseInt(bc.split(",")[2]);
                                } else if (line.startsWith("effect")) {
                                    int count = 0;
                                    line = bufferedReader.readLine();
                                    while (line.length() != 0) {
                                        if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color")
                                                || line.equalsIgnoreCase("colorFull") || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("jump")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("effect")) {
                                            line = bufferedReader.readLine();
                                        }
                                    }

                                }
                            }
                            Dimension circleDimension = new Dimension(x, y);
                            elements[n] = new Circle(radius, circleDimension, border, new Color(br, bg, bb));
                            elements[n].setEffects(effects);
                            elements[n].setObjectColor(new Color(r, g, b));
                            n++;
                            effects = null;
                            break;
                        case "Rect":
                            while (line.length() != 0) {
                                line = bufferedReader.readLine();
                                if (line.startsWith("length: ")) {
                                    length = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("width: ")) {
                                    width = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("x: ")) {
                                    xLU = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("y: ")) {
                                    yLU = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("color:")) {
                                    String c = line.split(" ")[1];
                                    r = Integer.parseInt(c.split(",")[0]);
                                    g = Integer.parseInt(c.split(",")[1]);
                                    b = Integer.parseInt(c.split(",")[2]);
                                } else if (line.startsWith("border:")) {
                                    border = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("color border:")) {
                                    String bc = line.split(" ")[2];
                                    br = Integer.parseInt(bc.split(",")[0]);
                                    bg = Integer.parseInt(bc.split(",")[1]);
                                    bb = Integer.parseInt(bc.split(",")[2]);
                                } else if (line.startsWith("effect")) {
                                    int count = 0;
                                    line = bufferedReader.readLine();
                                    while (line.length() != 0) {
                                        if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color") || line.equalsIgnoreCase("colorFull")
                                                || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("jump")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("effect")) {
                                            line = bufferedReader.readLine();
                                        }
                                    }
                                }
                            }
                            Dimension rectDimension = new Dimension(xLU, yLU);
                            elements[n] = new Shape.Rect(width, length, rectDimension, border, new Color(br, bg, bb));
                            elements[n].setEffects(effects);
                            elements[n].setObjectColor(new Color(r, g, b));
                            n++;
                            effects = null;
                            break;
                        case "Line":
                            while (line.length() != 0) {
                                line = bufferedReader.readLine();
                                if (line.startsWith("x1:")) {
                                    x1 = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("x2")) {
                                    x2 = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("y1")) {
                                    y1 = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("y2")) {
                                    y2 = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("color:")) {
                                    String c = line.split(" ")[1];
                                    r = Integer.parseInt(c.split(",")[0]);
                                    g = Integer.parseInt(c.split(",")[1]);
                                    b = Integer.parseInt(c.split(",")[2]);
                                } else if (line.startsWith("border")) {
                                    border = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("effect")) {
                                    int count = 0;
                                    line = bufferedReader.readLine();
                                    while (line.length() != 0) {
                                        if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color") || line.equalsIgnoreCase("colorFull")
                                                || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("jump")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("effect")) {
                                            line = bufferedReader.readLine();
                                        }
                                    }
                                }
                            }
                            Dimension lineStartDimension = new Dimension(x1, y1);
                            Dimension lineEndDimension = new Dimension(x2, y2);
                            elements[n] = new Line(lineStartDimension, lineEndDimension, border);
                            elements[n].setEffects(effects);
                            elements[n].setObjectColor(new Color(r, g, b));
                            n++;
                            effects = null;
                            break;
                        case "Label":
                            while (line.length() != 0) {
                                line = bufferedReader.readLine();
                                if (line.startsWith("x: ")) {
                                    xLabel = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("y: ")) {
                                    yLabel = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("text: ")) {
                                    text = line.split(" ")[1];
                                } else if (line.startsWith("color: ")) {
                                    String c = line.split(" ")[1];
                                    r = Integer.parseInt(c.split(",")[0]);
                                    g = Integer.parseInt(c.split(",")[1]);
                                    b = Integer.parseInt(c.split(",")[2]);
                                } else if (line.startsWith("font: ")) {
                                    font = line.split(" ")[1].toUpperCase();
                                } else if (line.startsWith("fontSize: ")) {
                                    fontSize = Integer.parseInt(line.split(" ")[1]);
                                } else if (line.startsWith("effect")) {
                                    int count = 0;
                                    line = bufferedReader.readLine();
                                    while (line.length() != 0) {
                                        if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color") || line.equalsIgnoreCase("colorFull")
                                                || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("jump")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                            String es = line;
                                            line = bufferedReader.readLine();
                                            es = es + "_" + line.split(" ")[1];
                                            effects[count++] = es;
                                            line = bufferedReader.readLine();
                                        } else if (line.equalsIgnoreCase("effect")) {
                                            line = bufferedReader.readLine();
                                        }
                                    }
                                }
                            }
                            Dimension labelPosition = new Dimension(xLabel, yLabel);
                            elements[n] = new ShapesProject.Label(text, labelPosition, font, fontSize);
                            elements[n].setEffects(effects);
                            elements[n].setObjectColor(new Color(r, b, g));
                            n++;
                            effects = null;
                            break;
                        case "Group Begin":
                            int shapesOfEachGroup = 0;
                            ArrayList<ShapesProject.Shape> shapesOfGroup = new ArrayList<>();
                            line = bufferedReader.readLine();
                            while (true) {
                                switch (line) {
                                  /*case "Group Begin":
                                        readGroupsFromFile(bufferedReader , G);
                                        if(line.length() == 0){
                                            line = bufferedReader.readLine();
                                        }
                                        break;*/
                                    case "Circle":
                                        while (line.length() != 0) {
                                            line = bufferedReader.readLine();
                                            if (line.startsWith("r: ")) {
                                                radius = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("x: ")) {
                                                x = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("y: ")) {
                                                y = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("border: ")) {
                                                border = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("color:")) {
                                                String c = line.split(" ")[1];
                                                r = Integer.parseInt(c.split(",")[0]);
                                                g = Integer.parseInt(c.split(",")[1]);
                                                b = Integer.parseInt(c.split(",")[2]);
                                            } else if (line.startsWith("color border:")) {
                                                String bc = line.split(" ")[2];
                                                br = Integer.parseInt(bc.split(",")[0]);
                                                bg = Integer.parseInt(bc.split(",")[1]);
                                                bb = Integer.parseInt(bc.split(",")[2]);
                                            }
                                        }
                                        circleDimension = new Dimension(x, y);
                                        Circle circle = new Circle(radius, circleDimension, border, new Color(br, bg, bb));
                                        shapesOfGroup.add(circle);
                                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                                        shapesOfEachGroup++;
                                        break;
                                    case "Rect":
                                        while (line.length() != 0) {
                                            line = bufferedReader.readLine();
                                            if (line.startsWith("length: ")) {
                                                length = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("width: ")) {
                                                width = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("x: ")) {
                                                xLU = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("y: ")) {
                                                yLU = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("color:")) {
                                                String c = line.split(" ")[1];
                                                r = Integer.parseInt(c.split(",")[0]);
                                                g = Integer.parseInt(c.split(",")[1]);
                                                b = Integer.parseInt(c.split(",")[2]);
                                            } else if (line.startsWith("border:")) {
                                                border = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("color border:")) {
                                                String bc = line.split(" ")[2];
                                                br = Integer.parseInt(bc.split(",")[0]);
                                                bg = Integer.parseInt(bc.split(",")[1]);
                                                bb = Integer.parseInt(bc.split(",")[2]);
                                            }
                                        }
                                        rectDimension = new Dimension(xLU, yLU);
                                        Shape.Rect rect = new Shape.Rect(width, length, rectDimension, border, new Color(br, bg, bb));
                                        shapesOfGroup.add(rect);
                                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                                        shapesOfEachGroup++;
                                        break;
                                    case "Line":
                                        while (line.length() != 0) {
                                            line = bufferedReader.readLine();
                                            if (line.startsWith("x1: ")) {
                                                x1 = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("x2")) {
                                                x2 = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("y1")) {
                                                y1 = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("y2")) {
                                                y2 = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("color:")) {
                                                String c = line.split(" ")[1];
                                                r = Integer.parseInt(c.split(",")[0]);
                                                g = Integer.parseInt(c.split(",")[1]);
                                                b = Integer.parseInt(c.split(",")[2]);
                                            } else if (line.startsWith("border")) {
                                                border = Integer.parseInt(line.split(" ")[1]);
                                            }
                                        }
                                        lineStartDimension = new Dimension(x1, y1);
                                        lineEndDimension = new Dimension(x2, y2);
                                        Line l = new Line(lineStartDimension, lineEndDimension, border);
                                        shapesOfGroup.add(l);
                                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                                        shapesOfEachGroup++;
                                        break;
                                    case "Label":
                                        while (line.length() != 0) {
                                            line = bufferedReader.readLine();
                                            if (line.startsWith("x: ")) {
                                                xLabel = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("y: ")) {
                                                yLabel = Integer.parseInt(line.split(" ")[1]);
                                            } else if (line.startsWith("text: ")) {
                                                text = line.split(" ")[1];
                                            } else if (line.startsWith("color:")) {
                                                String c = line.split(" ")[1];
                                                r = Integer.parseInt(c.split(",")[0]);
                                                g = Integer.parseInt(c.split(",")[1]);
                                                b = Integer.parseInt(c.split(",")[2]);
                                            } else if (line.startsWith("font:")) {
                                                font = line.split(" ")[1];
                                            } else if (line.startsWith("fontSize:")) {
                                                fontSize = Integer.parseInt(line.split(" ")[1]);
                                            }
                                        }
                                        labelPosition = new Dimension(xLabel, yLabel);
                                        ShapesProject.Label label = new ShapesProject.Label(text, labelPosition, font, fontSize);
                                        shapesOfGroup.add(label);
                                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                                        shapesOfEachGroup++;
                                        break;
                                    case "effect":
                                        int count = 0;
                                        line = bufferedReader.readLine();
                                        while (line != "Group End") {
                                            if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color")
                                                    || line.equalsIgnoreCase("colorFull") || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                                String es = line;
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                groupEffects[count++] = es;
                                                line = bufferedReader.readLine();
                                            } else if (line.equalsIgnoreCase("jump")) {
                                                String es = line;
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                groupEffects[count++] = es;
                                                line = bufferedReader.readLine();
                                            } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                                String es = line;
                                                line = bufferedReader.readLine();
                                                es = es + "_" + line.split(" ")[1];
                                                groupEffects[count++] = es;
                                                line = bufferedReader.readLine();
                                            } else if (line.equalsIgnoreCase("effect")) {
                                                line = bufferedReader.readLine();
                                            } else {
                                                break;
                                            }
                                        }
                                        break;
                                }
                                if (line.equalsIgnoreCase("Group End") || line == null) {
                                    break;
                                }
                                line = bufferedReader.readLine();
                            }
                            groups.add(G, new Group(shapesOfGroup, groupEffects, G));
                            for (int i = 0; i < groups.get(G).getShapes().size(); i++) {
                                groups.get(G).getShapes().get(i).setGroupNumber(G);
                                groups.get(G).getShapes().get(i).setIsElmentOfaGroup(true);
                            }
                            groupEffects = null;
                            G++;
                            break;
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found ! Please try again.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File is empty.");
            e.printStackTrace();
        }
    }

    public int getSleepTime() {
        return 1000 / speed;
    }

    public void run() {
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setBackground(this.backgroundColor);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setSize(screenSize.width, screenSize.height);
        jFrame.setTitle("Circle Project :)");
        JMenuItem pause, resume;
        pause = new JMenuItem("pause");
        resume = new JMenuItem("resume");
        JMenu jMenu = new JMenu("Menu");
        JMenuBar menu = new JMenuBar();
        jMenu.add(pause);
        jMenu.add(resume);
        menu.add(jMenu);
        jFrame.setJMenuBar(menu);
        JSlider seekBar = new JSlider(1, frames, 1);
        seekBar.setPaintLabels(true);
        seekBar.setMajorTickSpacing(frames / 50);
        seekBar.setVisible(true);
        jFrame.add(seekBar, BorderLayout.PAGE_END);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\ASUS\\Desktop\\download.png");
        jFrame.setIconImage(imageIcon.getImage());
        JPanel frameCounterPlanel = new JPanel();
        frameCounterPlanel.setBounds(1350, 50, 110, 35);
        frameCounterPlanel.setVisible(true);
        frameCounterPlanel.setBackground(new Color(212, 234, 190));
        JLabel frameCounterLabel = new JLabel();
        frameCounterLabel.setForeground(Color.BLACK);
        frameCounterPlanel.add(frameCounterLabel);
        JComponent[] jComponents = new JComponent[elements.length];
        JComponent[][] groupJcomponents = null;
        if (groups.size() > 0) {
            groupJcomponents = new JComponent[groups.size()][100];
            for (int i = 0; i < groups.size(); i++) {
                for (int j = 0; j < groups.get(i).getShapes().size(); j++) {
                    groupJcomponents[i][j] = groups.get(i).getShapes().get(j).drawObject();
                    jFrame.getContentPane().add(groupJcomponents[i][j]);
                }
            }
        }
        for (int i = 0; elements[i] != null; i++) {
            jComponents[i] = elements[i].drawObject();
            jFrame.getContentPane().add(jComponents[i]);
        }
        while (AnimationPlayer.frameCounter < frames) {
            for (int i = 0; elements[i] != null; i++) {
                int start, stop;
                String effectName;
                for (int j = 0; elements[i].getEffects()[j] != null; j++) {
                    effectName = elements[i].getEffects()[j].split("_")[0];
                    start = Integer.parseInt(elements[i].getEffects()[j].split("_")[1]);
                    if (effectName.equalsIgnoreCase("blink")) {
                        stop = Integer.parseInt(elements[i].getEffects()[j].split("_")[2]);
                        elements[i].blink(start, stop, jComponents[i]);
                    } else if (effectName.equalsIgnoreCase("colorFull")) {
                        stop = Integer.parseInt(elements[i].getEffects()[j].split("_")[2]);
                        elements[i].colorFull(start, stop, jComponents[i]);
                    } else if (effectName.equalsIgnoreCase("zigzag")) {
                        stop = Integer.parseInt(elements[i].getEffects()[j].split("_")[2]);
                        elements[i].zigzag(jComponents[i], start, stop);
                    } else {
                        if (effectName.equalsIgnoreCase("change color")) {
                            int r, g, b;
                            r = Integer.parseInt(elements[i].getEffects()[j].split("_")[2].split(",")[0]);
                            g = Integer.parseInt(elements[i].getEffects()[j].split("_")[2].split(",")[1]);
                            b = Integer.parseInt(elements[i].getEffects()[j].split("_")[2].split(",")[2]);
                            elements[i].changeColor(start, new Color(r, g, b), jComponents[i]);
                        } else if (effectName.equalsIgnoreCase("show")) {
                            elements[i].Show(start, jComponents[i]);
                        } else if (effectName.equalsIgnoreCase("hide")) {
                            elements[i].Hide(start, jComponents[i]);
                        } else if (effectName.equalsIgnoreCase("jump")) {
                            int x = Integer.parseInt(elements[i].getEffects()[j].split("_")[2]);
                            int y = Integer.parseInt(elements[i].getEffects()[j].split("_")[3]);
                            elements[i].jump(jComponents[i], start, x, y);
                        } else if (effectName.equalsIgnoreCase("resize")) {
                            double size = Double.parseDouble(elements[i].getEffects()[j].split("_")[2]);
                            elements[i].resize(jComponents[i], start, size);
                        }
                    }
                }
            }
            for (int i = 0; i < groups.size(); i++) {
                int start, stop;
                String effectName;
                for (int j = 0; j < groups.get(i).getShapes().size(); j++) {
                    if (groups.get(i).getShapes().get(j) != null) {
                        for (int k = 0; groups.get(i).getEffects()[k] != null; k++) {
                            effectName = groups.get(i).getEffects()[k].split("_")[0];
                            start = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[1]);
                            if (effectName.equalsIgnoreCase("blink")) {
                                stop = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2]);
                                groups.get(i).getShapes().get(j).blink(start, stop, groupJcomponents[i][j]);
                            } else if (effectName.equalsIgnoreCase("zigzag")) {
                                stop = Integer.parseInt(elements[i].getEffects()[j].split("_")[2]);
                                elements[i].zigzag(jComponents[i], start, stop);
                            } else {
                                if (effectName.equalsIgnoreCase("change color")) {
                                    int r, g, b;
                                    r = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2].split(",")[0]);
                                    g = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2].split(",")[1]);
                                    b = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2].split(",")[2]);
                                    groups.get(i).getShapes().get(j).changeColor(start, new Color(r, g, b), groupJcomponents[i][j]);
                                } else if (effectName.equalsIgnoreCase("colorFull")) {
                                    stop = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2]);
                                    groups.get(i).getShapes().get(j).colorFull(start, stop, groupJcomponents[i][j]);
                                } else if (effectName.equalsIgnoreCase("resize")) {
                                    double size;

                                    size = Double.parseDouble(groups.get(i).getEffects()[k].split("_")[2]);

                                    groups.get(i).getShapes().get(j).resize(groupJcomponents[i][j], start, size);
                                } else if (effectName.equalsIgnoreCase("show")) {
                                    groups.get(i).getShapes().get(j).Show(start, groupJcomponents[i][j]);
                                } else if (effectName.equalsIgnoreCase("hide")) {
                                    groups.get(i).getShapes().get(j).Hide(start, groupJcomponents[i][j]);
                                } else if (effectName.equalsIgnoreCase("jump")) {
                                    int x = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[2]);
                                    int y = Integer.parseInt(groups.get(i).getEffects()[k].split("_")[3]);
                                    groups.get(i).getShapes().get(j).jump(groupJcomponents[i][j], start, x, y);
                                }
                            }
                        }
                    }
                }
            }
            try {
                seekBar.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        frameCounter = seekBar.getValue();
                    }
                });
                seekBar.setValue(frameCounter);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AnimationPlayer.frameCounter++; //TODO gir mikone too shomaresh
            while (pause.isArmed()) {
                pause.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameCounter--;
                    }
                });
                while (!resume.isArmed()) {
                    System.out.print("");
                }
                if (resume.isArmed()) {
                    resume.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frameCounter++;
                        }
                    });
                }
            }
            frameCounterLabel.setText("Frame Count : " + String.valueOf(frameCounter));
            jFrame.getContentPane().add(frameCounterPlanel);
            frameCounterLabel.setVisible(true);
        }
    }

    public BufferedReader readGroupsFromFile(BufferedReader bufferedReader, int G) {
        int r = 0, g = 0, b = 0;
        int br = 0, bg = 0, bb = 0, border = 0;
        int x = 0, y = 0, radius = 0;
        int length = 0, width = 0, xLU = 0, yLU = 0;
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        String text = null;
        String font = null;
        int fontSize = 0;
        int xLabel = 0, yLabel = 0;
        String[] groupEffects = new String[100];
        int shapesOfEachGroup = 0;
        ArrayList<Shape> shapesOfGroup = new ArrayList<>();
        try {
            String line = bufferedReader.readLine();
            while (true) {
                switch (line) {
                   /* case "Group Begin":
                        readGroupsFromFile(bufferedReader, G);
                        break;*/
                    case "Circle":
                        while (line.length() != 0) {
                            line = bufferedReader.readLine();
                            if (line.startsWith("r: ")) {
                                radius = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("x: ")) {
                                x = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("y: ")) {
                                y = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("border: ")) {
                                border = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("color:")) {
                                String c = line.split(" ")[1];
                                r = Integer.parseInt(c.split(",")[0]);
                                g = Integer.parseInt(c.split(",")[1]);
                                b = Integer.parseInt(c.split(",")[2]);
                            } else if (line.startsWith("color border:")) {
                                String bc = line.split(" ")[2];
                                br = Integer.parseInt(bc.split(",")[0]);
                                bg = Integer.parseInt(bc.split(",")[1]);
                                bb = Integer.parseInt(bc.split(",")[2]);
                            }
                        }
                        Circle circle = new Circle(radius, new Dimension(x, y), border, new Color(br, bg, bb));
                        shapesOfGroup.add(circle);
                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                        shapesOfEachGroup++;
                        break;
                    case "Rect":
                        while (line.length() != 0) {
                            line = bufferedReader.readLine();
                            if (line.startsWith("length: ")) {
                                length = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("width: ")) {
                                width = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("x: ")) {
                                xLU = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("y: ")) {
                                yLU = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("color:")) {
                                String c = line.split(" ")[1];
                                r = Integer.parseInt(c.split(",")[0]);
                                g = Integer.parseInt(c.split(",")[1]);
                                b = Integer.parseInt(c.split(",")[2]);
                            } else if (line.startsWith("border:")) {
                                border = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("color border:")) {
                                String bc = line.split(" ")[2];
                                br = Integer.parseInt(bc.split(",")[0]);
                                bg = Integer.parseInt(bc.split(",")[1]);
                                bb = Integer.parseInt(bc.split(",")[2]);
                            }
                        }
                        Shape.Rect rect = new Shape.Rect(width, length, new Dimension(xLU, yLU), border, new Color(br, bg, bb));
                        shapesOfGroup.add(rect);
                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                        shapesOfEachGroup++;
                        break;
                    case "Line":
                        while (line.length() != 0) {
                            line = bufferedReader.readLine();
                            if (line.startsWith("x1: ")) {
                                x1 = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("x2")) {
                                x2 = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("y1")) {
                                y1 = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("y2")) {
                                y2 = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("color:")) {
                                String c = line.split(" ")[1];
                                r = Integer.parseInt(c.split(",")[0]);
                                g = Integer.parseInt(c.split(",")[1]);
                                b = Integer.parseInt(c.split(",")[2]);
                            } else if (line.startsWith("border")) {
                                border = Integer.parseInt(line.split(" ")[1]);
                            }
                        }
                        Line l = new Line(new Dimension(x1, y1), new Dimension(x2, y2), border);
                        shapesOfGroup.add(l);
                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                        shapesOfEachGroup++;
                        break;
                    case "Label":
                        while (line.length() != 0) {
                            line = bufferedReader.readLine();
                            if (line.startsWith("x: ")) {
                                xLabel = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("y: ")) {
                                yLabel = Integer.parseInt(line.split(" ")[1]);
                            } else if (line.startsWith("text: ")) {
                                text = line.split(" ")[1];
                            } else if (line.startsWith("color:")) {
                                String c = line.split(" ")[1];
                                r = Integer.parseInt(c.split(",")[0]);
                                g = Integer.parseInt(c.split(",")[1]);
                                b = Integer.parseInt(c.split(",")[2]);
                            } else if (line.startsWith("font:")) {
                                font = line.split(" ")[1];
                            } else if (line.startsWith("fontSize:")) {
                                fontSize = Integer.parseInt(line.split(" ")[1]);
                            }
                        }
                        ShapesProject.Label label = new Label(text, new Dimension(xLabel, yLabel), font, fontSize);
                        shapesOfGroup.add(label);
                        shapesOfGroup.get(shapesOfEachGroup).setObjectColor(new Color(r, g, b));
                        shapesOfEachGroup++;
                        break;
                    case "effect":
                        int count = 0;
                        line = bufferedReader.readLine();
                        while (line != "Group End") {
                            if (line.equalsIgnoreCase("blink") || line.equalsIgnoreCase("change color")
                                    || line.equalsIgnoreCase("colorFull") || line.equalsIgnoreCase("resize") || line.equalsIgnoreCase("zigzag")) {
                                String es = line;
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                groupEffects[count++] = es;
                                line = bufferedReader.readLine();
                            } else if (line.equalsIgnoreCase("jump")) {
                                String es = line;
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                groupEffects[count++] = es;
                                line = bufferedReader.readLine();
                            } else if (line.equalsIgnoreCase("show") || line.equalsIgnoreCase("hide")) {
                                String es = line;
                                line = bufferedReader.readLine();
                                es = es + "_" + line.split(" ")[1];
                                groupEffects[count++] = es;
                                line = bufferedReader.readLine();
                            } else if (line.equalsIgnoreCase("effect")) {
                                line = bufferedReader.readLine();
                            } else {
                                break;
                            }
                        }
                        break;
                }
                if (line.equalsIgnoreCase("Group End") || line == null) {
                    break;
                }
                line = bufferedReader.readLine();
                break;
            }
            groups.add(G, new Group(shapesOfGroup, groupEffects, G));
            for (int i = 0; i < groups.get(G).getShapes().size(); i++) {
                groups.get(G).getShapes().get(i).setGroupNumber(G);
                groups.get(G).getShapes().get(i).setIsElmentOfaGroup(true);
            }
            groupEffects = null;
            G++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    public void setFrames(int frames) {
        if (frames > 0) this.frames = frames;
        else {
            this.frames = 0;
            throw new InvalidIntegerException("frame number is invalid" + frames);
        }
    }

    public void setSpeed(int speed) {
        if (speed > 0) this.speed = speed;
        else {
            this.speed = 0;
            throw new InvalidIntegerException("speed number is invalid" + speed);
        }
    }

}