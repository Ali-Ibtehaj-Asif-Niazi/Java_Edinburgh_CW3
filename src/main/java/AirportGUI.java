import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AirportGUI implements ActionListener {
	private JFrame frame;
    private JPanel mainPanel;
    public AirportGUI(){
    	frame = new JFrame("Airport GUI");
    	frame.setSize(480, 480);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        frame.setLayout(new BorderLayout());
    	mainPanel = new JPanel(new CardLayout());
        mainPanel.setOpaque(false); // Make mainPanel transparent
        frame.add(mainPanel);
    }
	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}

}
