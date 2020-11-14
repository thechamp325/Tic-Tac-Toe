package view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import controller.*;

class ViewTest {

	@Test
	void test() {
		Controller c = new Controller();  
		View v = new View();  
		v.setActionListener(c);
		v.update(1,1,'x',"'O':  Player 2");
		assertEquals ("x", v.getButtonText(1,1));
    	}

}
