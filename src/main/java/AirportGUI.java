import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class AirportGUI implements ActionListener {
	private JFrame frame;
    private JPanel mainPanel;
    private JPanel desksPanel;
    private JPanel flightsPanel;
    private JPanel controlPanel;
    private JButton increaseProcessingTimeButton;
    private JButton decreaseProcessingTimeButton;
    private JLabel processingTimeLabel;
    private JLabel processingTimeLabelDisplay;
    private JPanel passengerQueuePanel;
    private JScrollPane passengerQueueScrollPane;
    private JLabel passengerQueueAmountLabel; 
    private JList<String> scrollPaneList;
    private JButton deskOpenButton;
    private JLabel desksNumLabel;
    private JLabel desksDisplay;
    private JPanel infoReadoutPanel;
    private JLabel feeLabel;
    private int processingTime;
	private int desksToOpen;
	private double feesCollected;
    public AirportGUI(){
    	processingTime = 5000;
    	desksToOpen = 0;
    	feesCollected = 0;
    	
    	frame = new JFrame("Airport GUI");
    	frame.setSize(700,700);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        frame.setLayout(new BorderLayout());
    	mainPanel = new JPanel();
    	desksPanel = new JPanel();
    	desksPanel.setLayout(new BoxLayout(desksPanel, 0));
    	flightsPanel = new JPanel();
    	flightsPanel.setLayout(new BoxLayout(flightsPanel, 0));
    	passengerQueuePanel = new JPanel();
    	passengerQueueAmountLabel = new JLabel();
    	controlPanel = new JPanel();
    	infoReadoutPanel = new JPanel();
    	feeLabel = new JLabel();
    	increaseProcessingTimeButton = new JButton(">");
    	processingTimeLabelDisplay = new JLabel(Integer.toString(processingTime));
    	processingTimeLabel = new JLabel("Processing Time (ms): ");
    	decreaseProcessingTimeButton = new JButton("<");
    	deskOpenButton = new JButton("Open another Desk");
    	desksDisplay = new JLabel();
    	desksNumLabel = new JLabel();
    	decreaseProcessingTimeButton.addActionListener(this);
    	increaseProcessingTimeButton.addActionListener(this);
    	deskOpenButton.addActionListener(this);
    	
    	
    	scrollPaneList = new JList<String>();
    	passengerQueueScrollPane = new JScrollPane();
    	passengerQueueScrollPane.setPreferredSize(new Dimension(450,110));
    	
    	passengerQueueScrollPane.setViewportView(scrollPaneList);
    	
        mainPanel.setOpaque(false);
        GroupLayout mainLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainLayout);
        mainLayout.setAutoCreateContainerGaps(true);
        mainLayout.setAutoCreateGaps(true);
        mainLayout.setHorizontalGroup(mainLayout.createParallelGroup(Alignment.CENTER)
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(passengerQueuePanel))
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(desksNumLabel))
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(desksPanel))
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(flightsPanel))
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(controlPanel))
        		.addGroup(mainLayout.createParallelGroup()
        				.addComponent(infoReadoutPanel))
        );
        mainLayout.setVerticalGroup(mainLayout.createSequentialGroup()
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(passengerQueuePanel))
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(desksNumLabel))
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(desksPanel))
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(flightsPanel))
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(controlPanel))
        		.addGroup(mainLayout.createSequentialGroup()
        				.addComponent(infoReadoutPanel))
        );
        controlPanel.add(processingTimeLabel);
        controlPanel.add(decreaseProcessingTimeButton);
        controlPanel.add(processingTimeLabelDisplay);
        controlPanel.add(increaseProcessingTimeButton);
        controlPanel.add(deskOpenButton);
        controlPanel.add(desksDisplay);
        infoReadoutPanel.add(feeLabel);
        passengerQueuePanel.add(passengerQueueAmountLabel);
        passengerQueuePanel.add(passengerQueueScrollPane);
        mainPanel.add(desksNumLabel);
        mainPanel.add(passengerQueuePanel);
        mainPanel.add(desksPanel);
        mainPanel.add(flightsPanel);
        mainPanel.add(controlPanel);
        mainPanel.add(infoReadoutPanel);
        frame.add(mainPanel);
    }
	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
	private Vector<String> QueueToString(BlockingQueue<Passenger> queue) {
		Vector<String> out = new Vector<String>();
		for (Passenger passenger : queue) {
			 out.add(
					passenger.getBookingRef() + 
					" " +
					passenger.getLastName() + 
					" " + 
					Math.round(passenger.getBaggageWeight()*100)/100.0 + 
					"kg " + 
					Math.round(passenger.getBaggageHeight()) +
			 		"x" +
			 		Math.round(passenger.getBaggageLength()) +
			 		"x" +
			 		Math.round(passenger.getBaggageWidth()));
		}
		return out;
	}
	public void updateQueue(BlockingQueue<Passenger> passengerQueue) {
		Vector<String> passengers = QueueToString(passengerQueue);
		scrollPaneList.setListData(passengers);
		passengerQueueAmountLabel.setText("There are currently " + passengerQueue.size()+" waiting");
	}
	public void updateDesks(List<AirportSimulation.CheckInDesk> desks) {
		desksPanel.removeAll();
		desksNumLabel.setText(Integer.toString(desks.size()) + " desks currently open");
		for (AirportSimulation.CheckInDesk desk : desks) {
			if(desk.getCurrentPassenger()==null){
				JPanel deskPanel = new JPanel();
				JLabel deskLabel = new JLabel();
				deskPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				String displayText ="<html>No Passengers in Queue, Desk is idle</html>";
				deskLabel.setText(displayText);
				deskPanel.add(deskLabel);
				desksPanel.add(deskPanel);
			}
			else{
				JPanel deskPanel = new JPanel();
				JLabel deskLabel = new JLabel();
				deskPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				String displayText ="<html>Desk " + 
				Integer.toString(desk.getDeskNumber()) + 
				"<br>Passenger " + 
				desk.getCurrentPassenger().getLastName() + 
				" has a bag weighing " + 
				Math.round(desk.getCurrentPassenger().getBaggageVolume()*100)/100.0 +
				"kg" +
				"<br>A baggage fee of £" +
				desk.getCurrentPassenger().getExcessBaggageFee() + 
				" is due</html>";
				deskLabel.setText(displayText);
				deskPanel.add(deskLabel);
				desksPanel.add(deskPanel);
				feesCollected += desk.getCurrentPassenger().getExcessBaggageFee();
				feeLabel.setText("Baggage fees so far: £" + Double.toString(feesCollected));
			}
		}
	}
	public void updateFlights(HashMap<String, Flight> flights) {
		flightsPanel.removeAll();
		for (Map.Entry<String, Flight> flight : flights.entrySet()) {
			JPanel flightPanel = new JPanel();
			JLabel flightLabel = new JLabel();
			flightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			long weight = Math.round((flight.getValue().currentBaggageWeight/flight.getValue().maxBaggageWeight)*100);
			long volume = Math.round((flight.getValue().currentBaggageVolume/flight.getValue().maxBaggageVolume)*100);
			String displayText =
			"<html>" +
			flight.getKey() + 
			" " +
			flight.getValue().getDestinationAirport() +
			"<br>" +
			flight.getValue().getCurrentCapacity() +
			" checked in out of " +
			flight.getValue().getCapacity() +
			"<br> Current weight: " +
			Long.toString(weight) +
			"%<br> Current volume: " +
			Long.toString(volume) +
			"%</html>";
			flightLabel.setText(displayText);
			flightPanel.add(flightLabel);
			flightsPanel.add(flightPanel);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == increaseProcessingTimeButton) {
			processingTime += 100;
		}
		else if (e.getSource() == decreaseProcessingTimeButton) {
			processingTime -= 100;
		}
		else if (e.getSource() == deskOpenButton) {
			desksToOpen +=1;
			desksDisplay.setText("Opening " + Integer.toString(desksToOpen) + " Desks...");
		}
		if (processingTime < 100) {
			processingTime = 100;
		}
		else if (processingTime > 10000){
			processingTime = 10000;
		}
		processingTimeLabelDisplay.setText(Integer.toString(processingTime));
	}
	public int checkDesksToOpen() {
		int t = desksToOpen;
		desksToOpen = 0;
		desksDisplay.setText("");
		return t;
	}
	public int getProcessingTime() {
		return processingTime;
	}

}
