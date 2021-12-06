package util;

import gameCommons.IFrog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferedImage;

public class SpriteBank {
    public BufferedImage frogToDown;
    public BufferedImage frogToUp;
    public BufferedImage frogToRight;
    public BufferedImage frogToLeft;
    public BufferedImage carSize1toRight;
    public BufferedImage carSize1toLeft;
    public BufferedImage carSize2toRight;
    public BufferedImage carSize2toLeft;
    public BufferedImage dirtP;
    public BufferedImage waterP;
    public BufferedImage logP;
    public BufferedImage roadP;

    public SpriteBank(){
         frogToDown = compileImg("frogToDown");
         frogToUp = compileImg("frogToUp");
         frogToRight = compileImg("frogToRight");
         frogToLeft = compileImg("frogToLeft");
         carSize1toRight = compileImg("carSize1toRight");
         carSize1toLeft = compileImg("carSize1toLeft");
         carSize2toRight = compileImg("carSize2toRight");
         carSize2toLeft = compileImg("carSize2toLeft");
         dirtP = compileImg("dirt");
         waterP = compileImg("water");
         logP = compileImg("log");
         roadP = compileImg("road");
    }

    public static BufferedImage compileImg(String s) {
            try {
                return ImageIO.read(new File("src/sprites/"+s+".png"));
            } catch (IOException e) {
                System.out.println(s);
                e.printStackTrace();
            }
        return null;
    }
}
