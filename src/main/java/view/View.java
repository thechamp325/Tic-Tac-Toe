package view;

/** The View class is responsible for setting up the gui and 
 * displaying the state of the game on the gui as informed by 
 * the model.
 */

import controller.*;
import computer.*;
import adapter.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class View {
	private Adapter adapter;
	private JFrame gui;
    private JButton[][] blocks;
    private JButton reset,hnh,hnc;
    private JTextArea playerturn;
    private Computer comp=null;
    
    // default constructor to initialize the gui as JFrame
    public View() {
    		this.gui = new JFrame("Tic Tac Toe");
    		this.blocks = new JButton[3][3];
    		this.reset = new JButton("Reset");
    		this.hnh = new JButton("vs Human");
    		this.hnc = new JButton("vs Computer");
    		this.playerturn = new JTextArea();
    		// call the initialize method to set up the layout and initialize buttons
    		initialize();
    }
    
    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
    		// adapter needs reference of controller and view class
		this.adapter = new Adapter(c,this);
		for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		blocks[row][column].addActionListener(adapter);
	        }
		}
	    reset.addActionListener(adapter);
	    hnh.addActionListener(adapter);
	    hnc.addActionListener(adapter);
    }
    
    public void setComp(Computer comp) {
    	this.comp=comp;
    }
    
    // function to initialize layout and buttons
    public void initialize () {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gui.setSize(new Dimension(500, 400));
	    gui.setResizable(true);
	    
	    JPanel gamePanel = new JPanel(new FlowLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));
	    gamePanel.add(game, BorderLayout.CENTER);
	    JPanel options = new JPanel(new FlowLayout());
	    options.add(reset);
	    options.add(hnh);
	    options.add(hnc);
	    JPanel messages = new JPanel(new FlowLayout());
	    messages.setBackground(Color.white);
	    gui.add(gamePanel, BorderLayout.NORTH);
	    gui.add(options, BorderLayout.CENTER);
	    gui.add(messages, BorderLayout.SOUTH);
	    messages.add(playerturn);
	    playerturn.setText("Player 1 to play 'X'");
	    
	    for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	            blocks[row][column] = new JButton();
	            blocks[row][column].setPreferredSize(new Dimension(75,75));
	            blocks[row][column].setText("");
	            game.add(blocks[row][column]);
	            
		    }
		}
	    
	    // make the gui visible as the final step
	    gui.setVisible(true);

    }
    
    // function to check if the action event was generated because of reset key
    public boolean isReset(ActionEvent e) {
    		if(e.getSource() == reset)
    			return true;
    		return false;
    }
    
    public boolean isHnh(ActionEvent e) {
    	if(e.getSource()==hnh) {
    		return true;
    	}
		return false;
    }
    
    public boolean isHnc(ActionEvent e) {
    	if(e.getSource()==hnc) {
    		return true;
    	}
		return false;
    }
    // function to find the x,y-coordinates of the pressed button
    public ArrayList<Integer> getPosition(ActionEvent e) {
    	ArrayList<Integer> position = new ArrayList<Integer>();
    	for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		if(e.getSource() == blocks[row][column]) {
	        			position.add(row);
	        			position.add(column);
	        		}
	        }
    		}
    		return position;
    }
    
    // function to update the view with the correct mark and message
    public void update(int row, int column, char symbol, String message) {
    		blocks[row][column].setText(Character.toString(symbol));
    		blocks[row][column].setEnabled(false);
    		playerturn.setText(message);
    }
    
    public void compPlay() {
    	ArrayList<Integer> l= comp.getMove();
    	blocks[l.get(0)][l.get(1)].doClick();
    	
    }
    
    // function to freeze the view if there is a winner or game is tied
    public void isWinner(int row, int column, char symbol, String message) {
		blocks[row][column].setText(Character.toString(symbol));
		blocks[row][column].setEnabled(false);
		for(int i = 0; i<3 ;i++) {
	        for(int j = 0; j<3 ;j++) {
	        	blocks[i][j].setEnabled(false);
	        }
		}
		playerturn.setText(message);

    }
    
    // function to clear the view and reset it for a new game
    public void resetGame() {
    	for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        playerturn.setText("Player 1 to play 'X'");
    }
    
    // mock getter function for checking the value of a button on the grid
    public String getButtonText(int i, int j) {
    		return blocks[i][j].getText();
    }

}
