import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Home extends JFrame {
    private RegistrationForm register;

    public Home() {
        register = new RegistrationForm();
        setTitle("Welcome to waste management");
        setSize(1500, 1500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 128, 0));

        JPanel topPanel = new JPanel();
topPanel.setLayout(new FlowLayout());
topPanel.setBackground(new Color(0, 128, 255)); // Set the background color of the top panel to blue
Dimension buttonSize = new Dimension(160, 50); // Adjust the size as needed

// Increase the font size and make the text bold
Font buttonFont = new Font("Arial", Font.BOLD, 16); // Adjust the font as needed

JButton homeButton = new JButton("Home");
homeButton.setPreferredSize(buttonSize);
homeButton.setBackground(new Color(4, 128, 5)); // Set the background color of the buttons to blue
homeButton.setForeground(Color.WHITE); // Set the text color of the buttons to white
homeButton.setFont(buttonFont);

JButton wasteDisposalButton = new JButton("Waste Disposal");
wasteDisposalButton.setPreferredSize(buttonSize);
wasteDisposalButton.setBackground(new Color(4, 128, 5));
wasteDisposalButton.setForeground(Color.WHITE);
wasteDisposalButton.setFont(buttonFont);

JButton guidelinesButton = new JButton("Guidelines");
guidelinesButton.setPreferredSize(buttonSize);
guidelinesButton.setBackground(new Color(4, 128, 5));
guidelinesButton.setForeground(Color.WHITE);
guidelinesButton.setFont(buttonFont);

JButton reportButton = new JButton("Report");
reportButton.setPreferredSize(buttonSize);
reportButton.setBackground(new Color(4, 128, 5));
reportButton.setForeground(Color.WHITE);
reportButton.setFont(buttonFont);

        int buttonWidth = 80;
        int buttonHeight = 70;
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\Tsyon\\AP_Waste_Management_System\\Project\\src\\images\\tsiyon.jpg");
        ImageIcon resizedIcon = new ImageIcon(
                icon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        JButton adminButton = new JButton(resizedIcon);

        // Add the buttons to the top panel
        topPanel.add(homeButton);
        topPanel.add(wasteDisposalButton);
        topPanel.add(guidelinesButton);
        topPanel.add(reportButton);
        topPanel.add(adminButton);

        // Create the panel for the waste disposal content
        JPanel wasteDisposalPanel = new JPanel();
        wasteDisposalPanel.setLayout(new BorderLayout());
        wasteDisposalPanel.setBackground(new Color(4, 128, 5)); // Set the background color of the waste disposal panel

        JTextArea wasteDisposalText = new JTextArea();
        wasteDisposalText.setText("Welcome to AASTU's Waste Management System\n" +
                                 "***Reducing our Waste, Protecting our Future***\n\n" +
                                 "As members of the AASTU community, we have a responsibility to thoughtfully manage the waste we generate. Proper waste disposal and recycling are crucial steps in preserving our environment and creating a more sustainable campus.\n\n" +
                                 "This waste management system aims to educate and empower you to make informed decisions about your waste. Here you will find guidance on:\n\n" +
                                 "- **Waste Separation:** Learn how to properly sort recyclables, compostable items, and general waste.\n" +
                                 "- **Disposal Methods:** Discover the right ways to dispose of hazardous, electronic, and other specialized waste.\n" +
                                 "- **Recycling Programs:** Stay up-to-date on the latest campus recycling initiatives and collection points.\n" +
                                 "- **Waste Reduction Tips:** Explore practical strategies to minimize your waste footprint.\n\n" +
                                 "Together, we can cultivate a culture of environmental stewardship and leave a lasting, positive impact on our AASTU community. Let's embark on this journey towards a greener, cleaner campus!");
        
        wasteDisposalText.setEditable(false);
        wasteDisposalText.setLineWrap(true);
        wasteDisposalText.setWrapStyleWord(true);
        wasteDisposalText.setMargin(new Insets(20, 20, 20, 20)); // Add margin gaps to the text
        JEditorPane editorPane = new JEditorPane("text/html", "");
        editorPane.setEditable(false);
        editorPane.setText("<html><h1>Welcome</h1><p>This is the waste disposal panel.<br>Use this panel to manage your waste disposal operations.</p></html>");
        editorPane.setBackground(wasteDisposalPanel.getBackground());
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

        // int buttonWidth = 80;
        // int buttonHeight = 70;
        ImageIcon icon2 = new ImageIcon(
                "C:\\Users\\Tsyon\\AP_Waste_Management_System\\Project\\src\\images\\register.jpg");
        ImageIcon resizedIcon2 = new ImageIcon(
                icon2.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));

        JButton registerButton = new JButton(resizedIcon2);
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
        bottomPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 50, 0));
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
        new Home();
    }
}