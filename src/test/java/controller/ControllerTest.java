package controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import model.*;
import view.*;

class ControllerTest {

	@Test
	void test() {
		Controller c = new Controller();  
		Model m = new Model();  
		View v = new View();
		m.registerView(v);
		c.setModel(m);
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos.add(1);
		pos.add(1);
		c.setRequest(pos);
		
		// check if the requested operation updated the model successfully
		assertEquals ('X', m.getBoard()[1][1]);
	}

}
