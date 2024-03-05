import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Color;

class CheckInGUI implements ActionListener {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel personalInfoPanel;
    private JPanel baggageInfoPanel;
    private JPanel excessBaggagePanel;
    private JLabel lastNameLabel;
    private JLabel bookingRefLabel;
    private JTextField lastNameTextField;
    private JTextField bookingRefTextField;
    private JLabel baggageVolumeLabel;
    private JLabel baggageWeightLabel;
    private JLabel excessBaggageFeeLabel;
    private JLabel textWelcome;
    private JLabel baggageWelcome;
    private JFormattedTextField baggageVolumeTextField;
    private JFormattedTextField baggageWeightTextField;
    private JFormattedTextField excessBaggageFeeTextField;
    private JButton confirmPersonalInfoButton;
    private JButton confirmBaggageInfoButton;
    private JButton payExcessBaggageButton;
    private HashMap<String, Booking> bookings;
    private ArrayList<Passenger> passengers;

    // Declare variables for baggage info
    private double baggageWeight;
    private double baggageVolume;

    public CheckInGUI(HashMap<String, Booking> bookings) {
        this.bookings = bookings;
        this.passengers = new ArrayList<>();

        frame = new JFrame("Airport Check-In");

        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("giphy1.gif"); // .gif added to make animated background
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new BorderLayout());

        // Main panel to hold personal and baggage info panels
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setOpaque(false); // Make mainPanel transparent
        frame.add(mainPanel);

        // Personal Info Panel
        personalInfoPanel = new JPanel();
        personalInfoPanel.setOpaque(false); // Make personalInfoPanel transparent
        GroupLayout personalInfoLayout = new GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoLayout);

        Font largerFont = new Font("Arial", Font.BOLD, 20); // Set font name, style, and size
        Color myTextColor = new Color(0, 0, 127); // Creates a dark green color

        textWelcome = new JLabel("Welcome to the Airport CheckIn System");
        textWelcome.setFont(largerFont);
        textWelcome.setForeground(myTextColor);

        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setFont(largerFont);
        lastNameLabel.setForeground(myTextColor);

        lastNameTextField = new JTextField(20);
        lastNameTextField.setPreferredSize(new Dimension(50, 35));
        lastNameTextField.setFont(largerFont);

        bookingRefLabel = new JLabel("Booking Reference: ");
        bookingRefLabel.setFont(largerFont);
        bookingRefLabel.setForeground(myTextColor);

        bookingRefTextField = new JTextField(20);
        bookingRefTextField.setPreferredSize(new Dimension(50, 35));
        bookingRefTextField.setFont(largerFont);

        confirmPersonalInfoButton = new JButton("Confirm Booking Info");
        confirmPersonalInfoButton.addActionListener(this);
        personalInfoLayout.setAutoCreateGaps(true);
        personalInfoLayout.setAutoCreateContainerGaps(true);

        // Set horizontal group
        personalInfoLayout.setHorizontalGroup(personalInfoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(personalInfoLayout.createSequentialGroup()
                .addComponent(textWelcome))
            .addGroup(personalInfoLayout.createSequentialGroup()
                .addComponent(lastNameLabel)
                .addComponent(lastNameTextField))
            .addGroup(personalInfoLayout.createSequentialGroup()
                .addComponent(bookingRefLabel)
                .addComponent(bookingRefTextField))
            .addComponent(confirmPersonalInfoButton, Alignment.TRAILING)
        );

        // Set vertical group
        personalInfoLayout.setVerticalGroup(personalInfoLayout.createSequentialGroup()
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(personalInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(textWelcome))
            .addGroup(personalInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(lastNameLabel)
                .addComponent(lastNameTextField))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(personalInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(bookingRefLabel)
                .addComponent(bookingRefTextField))
            .addComponent(confirmPersonalInfoButton)
        );

        personalInfoPanel.add(lastNameLabel);
        personalInfoPanel.add(lastNameTextField);
        personalInfoPanel.add(bookingRefLabel);
        personalInfoPanel.add(bookingRefTextField);
        personalInfoPanel.add(confirmPersonalInfoButton);
        mainPanel.add(personalInfoPanel, "personal");

        // Baggage Info Panel
        baggageInfoPanel = new JPanel();
        baggageInfoPanel.setOpaque(false); // Make baggageInfoPanel transparent
        GroupLayout baggageInfoLayout = new GroupLayout(baggageInfoPanel);
        baggageInfoPanel.setLayout(baggageInfoLayout);

        baggageWelcome = new JLabel("Please enter you Baggage details");
        baggageWelcome.setFont(largerFont);
        baggageWelcome.setForeground(myTextColor);

        baggageVolumeLabel = new JLabel("Baggage Volume: ");
        baggageVolumeLabel.setFont(largerFont);
        baggageVolumeLabel.setForeground(myTextColor);

        baggageVolumeTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageVolumeTextField.setPreferredSize(new Dimension(50, 35));
        baggageVolumeTextField.setFont(largerFont);

        baggageWeightLabel = new JLabel("Baggage Weight: ");
        baggageWeightLabel.setFont(largerFont);
        baggageWeightLabel.setForeground(myTextColor);

        baggageWeightTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageWeightTextField.setPreferredSize(new Dimension(50, 35));
        baggageWeightTextField.setFont(largerFont);

        confirmBaggageInfoButton = new JButton("Confirm Baggage Info");
        confirmBaggageInfoButton.addActionListener(this);
        baggageInfoPanel.add(baggageWeightLabel);
        baggageInfoPanel.add(baggageWeightTextField);
        baggageInfoPanel.add(baggageVolumeLabel);
        baggageInfoPanel.add(baggageVolumeTextField);
        baggageInfoPanel.add(confirmBaggageInfoButton);
        baggageInfoLayout.setAutoCreateGaps(true);
        baggageInfoLayout.setAutoCreateContainerGaps(true);

        // Set horizontal group
        baggageInfoLayout.setHorizontalGroup(baggageInfoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(baggageInfoLayout.createSequentialGroup()
                .addComponent(baggageWelcome))
            .addGroup(baggageInfoLayout.createSequentialGroup()
                .addComponent(baggageWeightLabel)
                .addComponent(baggageWeightTextField))
            .addGroup(baggageInfoLayout.createSequentialGroup()
                .addComponent(baggageVolumeLabel)
                .addComponent(baggageVolumeTextField))
            .addComponent(confirmBaggageInfoButton, Alignment.TRAILING)
        );

        // Set vertical group
        baggageInfoLayout.setVerticalGroup(baggageInfoLayout.createSequentialGroup()
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(baggageInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(baggageWelcome))
            .addGroup(baggageInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(baggageWeightLabel)
                .addComponent(baggageWeightTextField))
            .addGroup(baggageInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(baggageVolumeLabel)
                .addComponent(baggageVolumeTextField))
            .addComponent(confirmBaggageInfoButton)
        );

        mainPanel.add(baggageInfoPanel, "baggage");

        // Excess Baggage Panel
        excessBaggagePanel = new JPanel();
        excessBaggagePanel.setOpaque(false); // Make excessBaggagePanel transparent
        GroupLayout excessBaggageLayout = new GroupLayout(excessBaggagePanel);
        excessBaggagePanel.setLayout(excessBaggageLayout);

        excessBaggageFeeLabel = new JLabel("Excess Baggage Fee: ");
        excessBaggageFeeLabel.setFont(largerFont);
        excessBaggageFeeLabel.setForeground(myTextColor);

        excessBaggageFeeTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        excessBaggageFeeTextField.setPreferredSize(new Dimension(50, 35));
        excessBaggageFeeTextField.setFont(largerFont);
        
        payExcessBaggageButton = new JButton("Pay Excess Baggage Fee");
        payExcessBaggageButton.addActionListener(this);
        excessBaggagePanel.add(excessBaggageFeeLabel);
        excessBaggagePanel.add(excessBaggageFeeTextField);
        excessBaggagePanel.add(payExcessBaggageButton);
        excessBaggageLayout.setAutoCreateGaps(true);
        excessBaggageLayout.setAutoCreateContainerGaps(true);
        // Set horizontal group
        excessBaggageLayout.setHorizontalGroup(excessBaggageLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(excessBaggageLayout.createSequentialGroup()
                .addComponent(excessBaggageFeeLabel)
                .addComponent(excessBaggageFeeTextField))
            .addComponent(payExcessBaggageButton, Alignment.TRAILING)
        );

        // Set vertical group
        excessBaggageLayout.setVerticalGroup(excessBaggageLayout.createSequentialGroup()
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(excessBaggageLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(excessBaggageFeeLabel)
                .addComponent(excessBaggageFeeTextField))
            .addComponent(payExcessBaggageButton)
        );
        mainPanel.add(excessBaggagePanel, "excessBaggage");

        // Set up frame
        frame.setSize(480, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Action event handling
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmPersonalInfoButton) {
            // Perform validation for personal info and switch to baggage info panel
            String lastName = lastNameTextField.getText();
            String bookingRef = bookingRefTextField.getText();

            if (!bookings.containsKey(bookingRef)) {
                JOptionPane.showMessageDialog(frame, "Booking reference not found. Please re-enter your details.");
                return;
            }

            Booking booking = bookings.get(bookingRef);

            if (!booking.getPassengerName().equalsIgnoreCase(lastName)) {
                JOptionPane.showMessageDialog(frame, "Last name does not match the booking reference. Please re-enter your details.");
                return;
            }

            if (booking.isCheckedIn()) {
                JOptionPane.showMessageDialog(frame, "You are already checked in. Have a safe flight.");
                return;
            }

            booking.setCheckedIn(true);
            JOptionPane.showMessageDialog(frame, "Check-in successful!");

            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "baggage");
        } else if (ae.getSource() == confirmBaggageInfoButton) {
            // Perform validation for baggage info
            String baggageVolumeString = baggageVolumeTextField.getText();
            String baggageWeightString = baggageWeightTextField.getText();
            baggageVolume = Double.parseDouble(baggageVolumeString); // Store in class-level variable
            baggageWeight = Double.parseDouble(baggageWeightString); // Store in class-level variable

            // Check if weight and volume are within limits
            if (baggageWeight <= 40.0 && baggageVolume <= 6000.0) {
                passengers.add(new Passenger(lastNameTextField.getText(), bookingRefTextField.getText(), baggageWeight, baggageVolume, 0.0));
                JOptionPane.showMessageDialog(frame, "Thank you for providing baggage information.");
                lastNameTextField.setText("");
                bookingRefTextField.setText("");
                baggageVolumeTextField.setText("");
                baggageWeightTextField.setText("");
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "personal");
            } else {
            // Determine excess baggage fee based on weight and volume limits
            double excessBaggageFee = 0.0;
            if (baggageWeight > 40.0 && baggageVolume > 6000.0) {
                excessBaggageFee = 50.0; // If both weight and volume exceed the limits
            } else if (baggageWeight > 40.0) {
                excessBaggageFee = 20.0; // If only weight exceeds the limit
            } else if (baggageVolume > 6000.0) {
                excessBaggageFee = 30.0; // If only volume exceeds the limit
            }

            String message = "";
            if (baggageWeight > 40.0 && baggageVolume > 6000.0) {
                message = "Baggage weight exceeds the limit. You have to pay £20. \nBaggage volume exceeds the limit. You have to pay £30. \nYour total excess baggage fee is: £50";
            }
            else if (baggageWeight > 40.0) {
                message = "Baggage weight exceeds the limit. You have to pay £20. ";
            }
            else if (baggageVolume > 6000.0) {
                message = "Baggage volume exceeds the limit. You have to pay £30. ";
            }
            JOptionPane.showMessageDialog(frame, message.trim());
            excessBaggageFeeTextField.setText(String.valueOf(excessBaggageFee));
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "excessBaggage");
        }
        } else if (ae.getSource() == payExcessBaggageButton) {
            // Process payment for excess baggage fee
            String excessBaggageFeeString = excessBaggageFeeTextField.getText();
            double excessBaggageFee = Double.parseDouble(excessBaggageFeeString);
            passengers.add(new Passenger(lastNameTextField.getText(), bookingRefTextField.getText(), baggageWeight, baggageVolume, excessBaggageFee));
            JOptionPane.showMessageDialog(frame, "Thank you for paying the excess baggage fee.");

            // Go back to personal info panel
            lastNameTextField.setText("");
            bookingRefTextField.setText("");
            baggageVolumeTextField.setText("");
            baggageWeightTextField.setText("");
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "personal");
        }
    }

    // Method to get passengers
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}

