package Quarter2.Cytophobia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Puzzle implements MouseListener {
    JFrame frame;
    ImageIcon wall;
    ImageIcon tile;
    ImageIcon playerIcon;
    ImageIcon playerIcon2;
    ImageIcon finishTile;
    ImageIcon questionTile;
    JLabel tiles[];
    JLabel character[];
    int mapLayout[];
    int characterPlace[];
    int mapWidth;
    int mapHeight;
    int frameWidth;
    int frameHeight;
    int characterPosition;
    int characterMode;
    int questionLocation;
    int finishLocation;
    int blankLocation;
    
    public Puzzle(){
        mapWidth=3;
        mapHeight=3;
        frameWidth=500;
        frameHeight=500;
        characterPosition=-1;
        characterMode=0;
        blankLocation=8;
        
        frame=new JFrame();
        
        wall=new ImageIcon("Images/brickwall.png");
        tile=new ImageIcon("Images/tile1.png");
        playerIcon=new ImageIcon("Images/boy3.png");
        playerIcon2=new ImageIcon("Images/boy4.png");
        finishTile=new ImageIcon("Images/bluetile.png");
        questionTile=new ImageIcon("Images/questionTile.png");
        
        wall=new ImageIcon(wall.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        tile=new ImageIcon(tile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        playerIcon=new ImageIcon(playerIcon.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        playerIcon2=new ImageIcon(playerIcon2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        finishTile=new ImageIcon(finishTile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        questionTile=new ImageIcon(questionTile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        character=new JLabel[mapWidth*mapHeight];
        character[0]=new JLabel(wall);
        character[1]=new JLabel(tile);
        character[2]=new JLabel(playerIcon);
        character[3]=new JLabel(tile);
        character[4]=new JLabel(playerIcon);
        character[5]=new JLabel(wall);
        character[6]=new JLabel(wall);
        character[7]=new JLabel(playerIcon);
        character[8]=new JLabel();
    }
    
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));
                
        int x=0, y=0, w=1, h=1;
        for(int i=0;i<character.length;i++){
            frame.add(character[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }
        
        frame.setSize(frameWidth,frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
                
        for(int i=0;i<mapWidth*mapHeight;i++){
            character[i].addMouseListener(this);
        }
    }
    
    public static void main(String[] args) {
        Puzzle x=new Puzzle();
        x.setFrame();
    }
    
    public void checkWin(){
        int count=0;
        if(character[0].getIcon()==wall) count++;
        if(character[1].getIcon()==wall) count++;
        if(character[2].getIcon()==wall) count++;
        if(character[3].getIcon()==playerIcon) count++;
        if(character[4].getIcon()==playerIcon) count++;
        if(character[5].getIcon()==playerIcon) count++;
        if(character[6].getIcon()==tile) count++;
        if(character[7].getIcon()==tile) count++;
        if(count==8){
            character[8].setText("Congrats! You win!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int clicked;
        if(e.getSource()==character[0]) clicked=0;
        else if(e.getSource()==character[1]) clicked=1;
        else if(e.getSource()==character[2]) clicked=2;
        else if(e.getSource()==character[3]) clicked=3;
        else if(e.getSource()==character[4]) clicked=4;
        else if(e.getSource()==character[5]) clicked=5;
        else if(e.getSource()==character[6]) clicked=6;
        else if(e.getSource()==character[7]) clicked=7;
        else clicked=8;
        //System.out.println(Math.abs(clicked-blankLocation));
        if(Math.abs(clicked-blankLocation)==1||Math.abs(clicked-blankLocation)==3){
            character[blankLocation].setIcon(character[clicked].getIcon());
            character[clicked].setIcon(null);
            blankLocation=clicked;
            checkWin();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
