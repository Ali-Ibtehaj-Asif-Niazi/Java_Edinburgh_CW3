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
    private JFormattedTextField baggageVolumeTextField;
    private JFormattedTextField baggageWeightTextField;
    private JFormattedTextField excessBaggageFeeTextField;
    private JButton confirmPersonalInfoButton;
    private JButton confirmBaggageInfoButton;
    private JButton payExcessBaggageButton;
    private HashMap<String, Booking> bookings;
    public ArrayList<Passenger> passengers;

    // Declare variables for baggage info
    private double baggageWeight;
    private double baggageVolume;

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    CheckInGUI(HashMap<String, Booking> bookings) {
        this.bookings = bookings;
        this.passengers = new ArrayList<>();

        frame = new JFrame("Airport Check-In");

        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("giphy1.gif"); // Change "background.jpg" to your image file
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

        Font largerFont = new Font("Arial", Font.BOLD, 18); // Set font name, style, and size
        Color myTextColor = new Color(0, 0, 0); // Creates a dark green color

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

        confirmPersonalInfoButton = new JButton("Confirm Personal Info");
        confirmPersonalInfoButton.addActionListener(this);
        personalInfoLayout.setAutoCreateGaps(true);
        personalInfoLayout.setAutoCreateContainerGaps(true);

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
        baggageVolumeLabel = new JLabel("Baggage Volume: ");
        baggageVolumeTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageVolumeTextField.setColumns(10);
        baggageWeightLabel = new JLabel("Baggage Weight: ");
        baggageWeightTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        baggageWeightTextField.setColumns(10);
        confirmBaggageInfoButton = new JButton("Confirm Baggage Info");
        confirmBaggageInfoButton.addActionListener(this);
        baggageInfoPanel.add(baggageVolumeLabel);
        baggageInfoPanel.add(baggageVolumeTextField);
        baggageInfoPanel.add(baggageWeightLabel);
        baggageInfoPanel.add(baggageWeightTextField);
        baggageInfoPanel.add(confirmBaggageInfoButton);
        mainPanel.add(baggageInfoPanel, "baggage");

        // Excess Baggage Panel
        excessBaggagePanel = new JPanel();
        excessBaggagePanel.setOpaque(false); // Make excessBaggagePanel transparent
        excessBaggageFeeLabel = new JLabel("Excess Baggage Fee: ");
        excessBaggageFeeTextField = new JFormattedTextField(new DecimalFormat("#0.00"));
        excessBaggageFeeTextField.setColumns(10);
        payExcessBaggageButton = new JButton("Pay Excess Baggage Fee");
        payExcessBaggageButton.addActionListener(this);
        excessBaggagePanel.add(excessBaggageFeeLabel);
        excessBaggagePanel.add(excessBaggageFeeTextField);
        excessBaggagePanel.add(payExcessBaggageButton);
        mainPanel.add(excessBaggagePanel, "excessBaggage");

        frame.setSize(480, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
                System.out.println(passengers);
                JOptionPane.showMessageDialog(frame, "Thank you for providing baggage information.");
                lastNameTextField.setText("");
                bookingRefTextField.setText("");
                baggageVolumeTextField.setText("");
                baggageWeightTextField.setText("");
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "personal");
            } else {
                // Show excess baggage fee panel
                excessBaggageFeeTextField.setText(""); // Clear any previous input
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
}

