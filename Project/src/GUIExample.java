import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIExample extends JFrame {
    private RegistrationForm register;

    public GUIExample() {
        register = new RegistrationForm();
        setTitle("GUI Example");
        setSize(1000, 1000);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 128, 0));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(new Color(0, 128, 255)); // Set the background color of the top panel to blue
        JButton homeButton = new JButton("Home");
        homeButton.setBackground(new Color(0, 128, 255)); // Set the background color of the buttons to blue
        homeButton.setForeground(Color.WHITE); // Set the text color of the buttons to white
        JButton wasteDisposalButton = new JButton("Waste Disposal");
        wasteDisposalButton.setBackground(new Color(0, 128, 255));
        wasteDisposalButton.setForeground(Color.WHITE);
        JButton guidelinesButton = new JButton("Guidelines");
        guidelinesButton.setBackground(new Color(0, 128, 255));
        guidelinesButton.setForeground(Color.WHITE);
        JButton quitButton = new JButton("Quit");
        quitButton.setBackground(new Color(0, 128, 255));
        quitButton.setForeground(Color.WHITE);

        // Add the buttons to the top panel
        topPanel.add(homeButton);
        topPanel.add(wasteDisposalButton);
        topPanel.add(guidelinesButton);
        topPanel.add(quitButton);

        // Create the panel for the waste disposal content
        JPanel wasteDisposalPanel = new JPanel();
        wasteDisposalPanel.setLayout(new BorderLayout());
        wasteDisposalPanel.setBackground(new Color(0, 128, 0)); // Set the background color of the waste disposal panel

        JTextArea wasteDisposalText = new JTextArea();
        wasteDisposalText.setText("This is where you would provide information about waste disposal. " +
                "This could include details on how to properly dispose of different types of waste, " +
                "the importance of recycling, and any local waste management programs.");
        wasteDisposalText.setEditable(false);
        wasteDisposalText.setLineWrap(true);
        wasteDisposalText.setWrapStyleWord(true);
        wasteDisposalText.setMargin(new Insets(20, 20, 20, 20)); // Add margin gaps to the text
        wasteDisposalPanel.add(wasteDisposalText, BorderLayout.CENTER);

         JPanel contactUsPanel = new JPanel();
        contactUsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        contactUsPanel.setBackground(new Color(4, 128, 5));

        // Add labels and text fields for name, email, and comments
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(250, 30));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(250, 30));

        JLabel commentsLabel = new JLabel("Comments:");
        commentsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        commentsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextArea commentsArea = new JTextArea(5, 20);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        commentsArea.setPreferredSize(new Dimension(250, 100));
        JScrollPane commentsScrollPane = new JScrollPane(commentsArea);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.setBackground(new Color(0, 128, 255));
        sendButton.setForeground(Color.WHITE);

        // Add the components to the contact us panel
        contactUsPanel.add(nameLabel);
        contactUsPanel.add(nameField);
        contactUsPanel.add(emailLabel);
        contactUsPanel.add(emailField);
        contactUsPanel.add(commentsLabel);
        contactUsPanel.add(commentsScrollPane);
        contactUsPanel.add(sendButton);

        // Create the panel for the bottom button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(new Color(0, 128, 255));

        int buttonWidth = 80;
        int buttonHeight = 70;
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\Tsyon\\AP_Waste_Management_System\\Project\\src\\images\\register.jpg");
        ImageIcon resizedIcon = new ImageIcon(
                icon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));

        JButton registerButton = new JButton(resizedIcon);
        registerButton.setBounds(50, 50, 40, 40);
        registerButton.setBackground(new Color(0, 128, 255));
        registerButton.setForeground(Color.WHITE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register.showRegisterFrame();
            }
        });
        // Add the register button to the bottom panel
        bottomPanel.add(registerButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        centerPanel.add(wasteDisposalPanel);
        centerPanel.add(contactUsPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        getContentPane().setBackground(Color.GREEN);
        this.getContentPane().setBackground(new Color(0, 128, 0));
        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIExample();
    }
}