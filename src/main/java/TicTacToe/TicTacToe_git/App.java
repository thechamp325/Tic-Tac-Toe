package TicTacToe.TicTacToe_git;

/**
 * Hello world!
 *
 */
import model.*;
import view.*;
import controller.*;
import computer.*;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
		Controller c = new Controller();  
		View v = new View();   
	    Model m = new Model(); 
	    Computer comp=new Computer();
	    
	    // Use aggregation to put the components together
	    m.registerView(v);
	    c.setModel(m);
	    v.setActionListener(c);
	    comp.setModel(m);
	    v.setComp(comp);
	    
    }
}
