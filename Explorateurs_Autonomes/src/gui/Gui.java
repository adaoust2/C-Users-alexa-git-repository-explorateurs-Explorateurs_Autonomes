package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GParameter;
import data.Monster;
/* @author DeAntoine
 * @author bjmeo
 * this class is for grafical display 
 */
public class Gui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	// constant of color 
	public Color DARKGREEN = new Color(0,100,0);
	public Color BLUE = new Color(0,0,255);
	public Color GOLDSAND = new Color(255,215,0);
	public Color STEELGREY = new Color(175, 175, 175); // new color 
	public Color IMPERIALGREEN = new Color(0, 86, 27);
	public Color DARKCHOCOLATE = new Color(90,58,34);
	private double sizeX;
	private double sizeY;
	BufferedImage bimg;
	
	public double tab[][];
	
	private Dimension dimension;
	
	private int screenHeight;
	private int screenWidth;
	
	private DashbordPanel dashbordPanel;
	private JPanel container;
	private ArrayList<Monster> listOfMonster ;
 
	
	public Gui(double tab[][],double sizeX, double sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tab = tab;
		
	    //allow to get the computer screen dimension 	
	    dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    screenHeight = (int)dimension.getHeight();
	    screenWidth  = (int)dimension.getWidth();
	    
	    dashbordPanel = new DashbordPanel(screenWidth);
	    container = new JPanel();

		setLayout();
	    add();
	}

	public Gui(double tab[][],double sizeX, double sizeY,int test,ArrayList<Monster> listOfMonster) { // don't delete, this constructor is for @DeAntoine Test
		this.listOfMonster = listOfMonster;
		this.tab = tab;
	    this.sizeX = sizeX;
	    this.sizeY = sizeY;

	}
	
	public void setLayout() {
		//For the mainFrame
		setTitle(" Explorateur ");
	    setSize((int)(sizeX*1.04), (int)(sizeY*1.07));
	    setLayout(null);
	    
	    container.setLayout(null);
	    container.setBounds(100, 100, 100, 100);
	    container.setBackground(new Color(255,255,255));
	}
	
	//method for display the map
	public void Display(){
		setSize((int)(sizeX*1.1),(int)(sizeY*1.1));
	    JPanel panneau = new JPanel(){
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g){
	            super.paintComponent(g);
	            g.setColor(Color.black);
	            int y,x;
	            for (y=0; y<sizeY; y++){  
	            	for (x=0; x<sizeX; x++){
	            		if(tab[x][y]==5) { // mountain 
	            			g.setColor(DARKCHOCOLATE);
	     				}
	            		else if(tab[x][y]==4) { // mountain 
	            			g.setColor(STEELGREY);
	     				}
	            		else if(tab[x][y]==3) { // forest 
	            			g.setColor(IMPERIALGREEN);
	     				}
	            		else if(tab[x][y]==2) { // sand 
	            			g.setColor(GOLDSAND);
	     				}
	            		else if(tab[x][y]==1){ //land
	            			g.setColor(DARKGREEN);
	            		}
	     				else if (tab[x][y]==0){ //water
	            			g.setColor(BLUE);
	     				}
	     				else{ // if the value is not recognized 
	     					System.out.println("Error ocured in Gui.java: \n Unknow value of map box :" + tab[x][y] );
	     					g.setColor(Color.PINK);
	     				}
	     	  			g.fillRect(x, y, 1, 1);

	     			}

	     		}
	            g.setColor(Color.RED);
	            for(int z = 0;z < listOfMonster.size() ; z++) {
	            	g.drawImage("/")
	            	g.fillOval((int)(listOfMonster.get(z).getPosX())-10,(int)(listOfMonster.get(z).getPosY())-10, 20, 20);
	            }
	            
	            
	        }
	    };
	    
	    //to center the map regardless of computer screen resolution
	    //panneau.setBounds((int)((screenWidth/2)-sizeX/2),95,GParameter.mapWidth,GParameter.mapHeight);
	    //container.add(panneau);
	    add(panneau);
	    setVisible(true);
	    show(); //affiche la fenetre
	}
	
	public void add(){
	    
	    container.add(dashbordPanel);
	    this.setContentPane(container);
	    setVisible(true);
	}
	
}
