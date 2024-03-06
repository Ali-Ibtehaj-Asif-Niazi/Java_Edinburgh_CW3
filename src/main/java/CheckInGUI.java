package src.main.java;
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
    private JLabel baggageWeightLabel;
    private JLabel excessBaggageFeeLabel;
    private JLabel textWelcome;
    private JLabel baggageWelcome;
    private JLabel baggageLengthLabel;
    private JFormattedTextField baggageLengthTextField;
    private JLabel baggageWidthLabel;
    private JFormattedTextField baggageWidthTextField;
    private JLabel baggageHeightLabel;
    private JFormattedTextField baggageHeightTextField;
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
    private double baggageLength;
    private double baggageWidth;
    private double baggageHeight;

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

        baggageWelcome = new JLabel("Please enter your Baggage details");
        baggageWelcome.setFont(largerFont);
        baggageWelcome.setForeground(myTextColor);

        baggageLengthLabel = new JLabel("Length (Inch): ");
        baggageLengthLabel.setFont(largerFont);
        baggageLengthLabel.setForeground(myTextColor);

        baggageLengthTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageLengthTextField.setPreferredSize(new Dimension(50, 35));
        baggageLengthTextField.setFont(largerFont);

        baggageWidthLabel = new JLabel("Width (Inch): ");
        baggageWidthLabel.setFont(largerFont);
        baggageWidthLabel.setForeground(myTextColor);

        baggageWidthTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageWidthTextField.setPreferredSize(new Dimension(50, 35));
        baggageWidthTextField.setFont(largerFont);

        baggageHeightLabel = new JLabel("Height (Inch): ");
        baggageHeightLabel.setFont(largerFont);
        baggageHeightLabel.setForeground(myTextColor);

        baggageHeightTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageHeightTextField.setPreferredSize(new Dimension(50, 35));
        baggageHeightTextField.setFont(largerFont);

        baggageWeightLabel = new JLabel("Baggage Weight: ");
        baggageWeightLabel.setFont(largerFont);
        baggageWeightLabel.setForeground(myTextColor);

        baggageWeightTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageWeightTextField.setPreferredSize(new Dimension(50, 35));
        baggageWeightTextField.setFont(largerFont);

        confirmBaggageInfoButton = new JButton("Confirm Baggage Info");
        confirmBaggageInfoButton.addActionListener(this);

        // Add components to baggage info panel
        baggageInfoPanel.add(baggageWelcome);
        baggageInfoPanel.add(baggageWeightLabel);
        baggageInfoPanel.add(baggageWeightTextField);
        baggageInfoPanel.add(baggageLengthLabel);
        baggageInfoPanel.add(baggageLengthTextField);
        baggageInfoPanel.add(baggageWidthLabel);
        baggageInfoPanel.add(baggageWidthTextField);
        baggageInfoPanel.add(baggageHeightLabel);
        baggageInfoPanel.add(baggageHeightTextField);
        baggageInfoPanel.add(confirmBaggageInfoButton);

        // Set automatic creation of gaps
        baggageInfoLayout.setAutoCreateGaps(true);
        baggageInfoLayout.setAutoCreateContainerGaps(true);

        // Set horizontal group
        baggageInfoLayout.setHorizontalGroup(baggageInfoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(baggageInfoLayout.createSequentialGroup()
                .addComponent(baggageWelcome))
            .addGroup(baggageInfoLayout.createSequentialGroup()
                .addGroup(baggageInfoLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(baggageWeightLabel)
                    .addComponent(baggageLengthLabel)
                    .addComponent(baggageWidthLabel)
                    .addComponent(baggageHeightLabel))
                .addGroup(baggageInfoLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(baggageWeightTextField)
                    .addComponent(baggageLengthTextField)
                    .addComponent(baggageWidthTextField)
                    .addComponent(baggageHeightTextField)))
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
                .addComponent(baggageLengthLabel)
                .addComponent(baggageLengthTextField))
            .addGroup(baggageInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(baggageWidthLabel)
                .addComponent(baggageWidthTextField))
            .addGroup(baggageInfoLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(baggageHeightLabel)
                .addComponent(baggageHeightTextField))
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
        // Check if the confirmPersonalInfoButton is clicked
        boolean isBaggageInputValid = false;
        if (ae.getSource() == confirmPersonalInfoButton) {
            // Retrieve last name and booking reference entered by the user
            String lastName = lastNameTextField.getText();
            String bookingRef = bookingRefTextField.getText();

            // Validate booking reference
            if (!bookings.containsKey(bookingRef)) {
                // Display error message if booking reference is not found
                JOptionPane.showMessageDialog(frame, "Booking reference not found. Please re-enter your details.");
                return;
            }

            // Retrieve booking details
            Booking booking = bookings.get(bookingRef);

            // Validate last name against booking reference
            if (!booking.getPassengerName().equalsIgnoreCase(lastName)) {
                // Display error message if last name does not match booking reference
                JOptionPane.showMessageDialog(frame, "Last name does not match the booking reference. Please re-enter your details.");
                return;
            }

            // Check if passenger is already checked in
            if (booking.isCheckedIn()) {
                // Display message if passenger is already checked in
                JOptionPane.showMessageDialog(frame, "You are already checked in. Have a safe flight.");
                return;
            }

            // Set passenger as checked in
            booking.setCheckedIn(true);
            JOptionPane.showMessageDialog(frame, "Check-in successful!");

            // Switch to baggage info panel
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "baggage");
        } 
        // Check if the confirmBaggageInfoButton is clicked
        else if (ae.getSource() == confirmBaggageInfoButton) {
            try {
                // Retrieve baggage dimensions entered by the user
                String baggageWeightString = baggageWeightTextField.getText();
                String baggageLengthString = baggageLengthTextField.getText();
                String baggageWidthString = baggageWidthTextField.getText();
                String baggageHeightString = baggageHeightTextField.getText();

                // Parse baggage dimensions and store in class-level variables
                baggageWeight = Double.parseDouble(baggageWeightString);
                baggageLength = Double.parseDouble(baggageLengthString);
                baggageWidth = Double.parseDouble(baggageWidthString);
                baggageHeight = Double.parseDouble(baggageHeightString);
                isBaggageInputValid = true;
            } catch (NumberFormatException e) {
                // Show a popup dialog to inform the user to enter a numerical value
                JOptionPane.showMessageDialog(frame, "Please enter a numerical value for baggage dimensions.", "Input Error", JOptionPane.ERROR_MESSAGE);
                isBaggageInputValid = false;
            }
            if (isBaggageInputValid) {
                // Calculate baggage volume
                // Volume = Length * Width * Height
                baggageVolume = baggageLength * baggageWidth * baggageHeight;

                // Check if weight and volume are within limits
                if (baggageWeight <= 40.0 && baggageVolume <= 6000.0) {
                    passengers.add(new Passenger(lastNameTextField.getText(), bookingRefTextField.getText(), baggageWeight, baggageVolume, 0.0));
                    JOptionPane.showMessageDialog(frame, "Thank you for providing baggage information.");
                    lastNameTextField.setText("");
                    bookingRefTextField.setText("");
                    baggageLengthTextField.setText("");
                    baggageWidthTextField.setText("");
                    baggageHeightTextField.setText("");
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
            baggageLengthTextField.setText("");
            baggageWidthTextField.setText("");
            baggageHeightTextField.setText("");
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

