import javax.swing.*;
import javax.swing.GroupLayout.SequentialGroup;

import java.awt.event.*;
import java.text.DecimalFormat;

class CheckInGUI implements ActionListener{
	private static JLabel lastNameLabel;
	private static JLabel bookingRefLabel;
	private static JTextField lastNameTextField;
	private static JTextField bookingRefTextField;
	private static JLabel baggageVolumeLabel;
	private static JLabel baggageWeightLabel;
	private static JFormattedTextField baggageVolumeTextField;
	private static JFormattedTextField baggageWeightTextField;
	private static JButton confirmButton;
	private static JPanel detailsPanel;
	CheckInGUI (){ 
		JFrame f = new JFrame("Airport Check-In"); //creating instance of JFrame  
		
		confirmButton = new JButton("Confirm Check-In");
		
		lastNameLabel = new JLabel("Last Name: ");
		lastNameTextField = new JTextField(20);
		
		bookingRefLabel = new JLabel("Booking Reference: ");
		bookingRefTextField= new JTextField(20);
		
		DecimalFormat baggageValuesFormat = new DecimalFormat("#0.00");
		baggageVolumeLabel = new JLabel("Baggage Volume: ");
		baggageVolumeTextField= new JFormattedTextField(baggageValuesFormat);
		baggageVolumeTextField.setColumns(10);
		
		baggageWeightLabel = new JLabel("Baggage Weight: ");
		baggageWeightTextField = new JFormattedTextField(baggageValuesFormat);
		baggageWeightTextField.setColumns(10);
		
		confirmButton.addActionListener(this);
		
		detailsPanel = new JPanel();
		GroupLayout layout = new GroupLayout(detailsPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(lastNameLabel)
							.addComponent(bookingRefLabel)
							.addComponent(baggageVolumeLabel)
							.addComponent(baggageWeightLabel)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(lastNameTextField)
							.addComponent(bookingRefTextField)
							.addComponent(baggageVolumeTextField)
							.addComponent(baggageWeightTextField)
							.addComponent(confirmButton)
					)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lastNameLabel)
							.addComponent(lastNameTextField)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookingRefLabel)
							.addComponent(bookingRefTextField)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(baggageVolumeLabel)
							.addComponent(baggageVolumeTextField)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(baggageWeightLabel)
							.addComponent(baggageWeightTextField)
					)
					.addComponent(confirmButton)
		);
		detailsPanel.setLayout(layout);
		f.add(detailsPanel);
		
		f.setSize(500,200); //width, height
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		
	}
	public void actionPerformed(ActionEvent ae) {
		String lastName = lastNameTextField.getText();
		String bookingRef = bookingRefTextField.getText();
		String baggageVolumeString = baggageVolumeTextField.getText();
		String baggageWeightString = baggageWeightTextField.getText();
		double baggageVolume = 0.0;
		double baggageWeight = 0.0;
		try {
			baggageVolume = Double.parseDouble(baggageVolumeString);
			baggageWeight = Double.parseDouble(baggageVolumeString);
		}
		catch (NumberFormatException e){
			throw new IllegalStateException("Invalid baggage parameters have been entered");
		}
	}
}
