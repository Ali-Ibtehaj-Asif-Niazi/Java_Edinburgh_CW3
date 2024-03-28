import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private Map<Flight, FlightPanel> flightPanelsMap;
	private Map<AirportSimulation.CheckInDesk, DeskPanel> deskPanelsMap;
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
    	flightPanelsMap = new HashMap<Flight, FlightPanel>();
    	deskPanelsMap = new HashMap<AirportSimulation.CheckInDesk, DeskPanel>();
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
    class FlightPanel extends JPanel{
    	JLabel flightLabel;
    	FlightPanel(){
    		flightLabel = new JLabel();
    		this.add(flightLabel);
    		this.setBorder(BorderFactory.createLineBorder(Color.black));
    	}
    	void Update(Flight flight) {
    		long weight = Math.round((flight.currentBaggageWeight/flight.maxBaggageWeight)*100);
			long volume = Math.round((flight.currentBaggageVolume/flight.maxBaggageVolume)*100);
			String displayText =
			"<html>" +
			flight.flightCode + 
			" " +
			flight.getDestinationAirport() +
			"<br>" +
			flight.getCurrentCapacity() +
			" checked in out of " +
			flight.getCapacity() +
			"<br> Current weight: " +
			Long.toString(weight) +
			"%<br> Current volume: " +
			Long.toString(volume) +
			"%</html>";
			flightLabel.setText(displayText);
    	}
    }
    class DeskPanel extends JPanel{
    	JLabel deskLabel;
    	DeskPanel(){
    		deskLabel = new JLabel();
    		this.add(deskLabel);
    		this.setBorder(BorderFactory.createLineBorder(Color.black));
    	}
    	void Update(AirportSimulation.CheckInDesk desk) {
    		String displayText ="<html>No Passengers in Queue, Desk is idle</html>";
    		if(desk.getCurrentPassenger() != null){
    		displayText ="<html>Desk " + 
    				Integer.toString(desk.getDeskNumber()) + 
    				"<br>Passenger " + 
    				desk.getCurrentPassenger().getLastName() + 
    				" has a bag weighing " + 
    				Math.round(desk.getCurrentPassenger().getBaggageVolume()*100)/100.0 +
    				"kg" +
    				"<br>A baggage fee of £" +
    				desk.getCurrentPassenger().getExcessBaggageFee() + 
    				" is due</html>";
    		}
    		deskLabel.setText(displayText);
    	}
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
	public void addDesks(List<AirportSimulation.CheckInDesk> desks) {
    	deskPanelsMap = new HashMap<AirportSimulation.CheckInDesk, DeskPanel>();
    	desksPanel.removeAll();
		for (AirportSimulation.CheckInDesk desk : desks) {
			DeskPanel newPanel = new DeskPanel();
			deskPanelsMap.put(desk,newPanel);
			desksPanel.add(newPanel);
		}
	}
	public void updateDesks(List<AirportSimulation.CheckInDesk> desks) {
		desksNumLabel.setText(Integer.toString(desks.size()) + " desks currently open");
		for (Map.Entry<AirportSimulation.CheckInDesk, DeskPanel> entry : deskPanelsMap.entrySet()) {
			AirportSimulation.CheckInDesk desk = entry.getKey();
			DeskPanel panel = entry.getValue();
			panel.Update(desk);
			if (desk.getCurrentPassenger() != null) {
				feesCollected += desk.getCurrentPassenger().getExcessBaggageFee();
				feeLabel.setText("Baggage fees so far: £" + Double.toString(feesCollected));
			}
		}
	}
	public void addFlights(HashMap<String, Flight> flights) {
		flightPanelsMap = new HashMap<Flight, FlightPanel>();
		flightsPanel.removeAll();
		for (Map.Entry<String, Flight> entry : flights.entrySet()) {
			FlightPanel newPanel = new FlightPanel();
			Flight flight = entry.getValue();
			flightPanelsMap.put(flight, newPanel);
			flightsPanel.add(newPanel);
		}
	}
	public void updateFlights(HashMap<String, Flight> flights) {
		for (Map.Entry<Flight, FlightPanel> entry : flightPanelsMap.entrySet()) {
			FlightPanel panel = entry.getValue();
			Flight flight = entry.getKey();
			panel.Update(flight);
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
