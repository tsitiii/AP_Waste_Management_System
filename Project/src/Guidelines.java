import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Guidelines {
    public void displayGuidelines() {
        // Create a dialog box to display the guidelines information
        JFrame guidelinesFrame = new JFrame();
        guidelinesFrame.setTitle("Waste Disposal Guidelines");
        guidelinesFrame.setFont(new Font("Arial", Font.BOLD, 22));
        guidelinesFrame.setForeground(new Color(0, 128, 0));
        guidelinesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel guidelinesPanel = new JPanel();
        guidelinesPanel.setLayout(new BoxLayout(guidelinesPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Waste Disposal Guidelines");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.MAGENTA);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JTextArea guidelinesText = new JTextArea();
        guidelinesText.setText(
                "1. Separate your waste into recyclables and non-recyclables. " +
                        "Recyclables include paper, cardboard, plastic bottles, glass, " +
                        "and aluminum cans. Non-recyclables include food waste, " +
                        "plastic bags, and Styrofoam.\n\n" +
                        "2. Use designated bins for different types of waste. " +
                        "Make sure to dispose of recyclables in the recycling bins " +
                        "and non-recyclables in the appropriate trash bins. " +
                        "Follow color-coded labels or signage to identify the bins.\n\n" +
                        "3. Avoid littering or dumping waste in non-designated areas. " +
                        "Dispose of your waste responsibly in designated bins. " +
                        "Littering not only harms the environment but also poses " +
                        "health risks to humans and animals.\n\n" +
                        "4. Follow proper disposal procedures for hazardous waste. " +
                        "Examples of hazardous waste include batteries, chemicals, " +
                        "and electronic devices. Do not dispose of hazardous waste " +
                        "in regular bins. Contact the appropriate authorities " +
                        "for safe disposal methods.\n\n" +
                        "5. Educate others about responsible waste management practices. " +
                        "Promote awareness and encourage your peers to follow " +
                        "environmentally friendly waste disposal habits. " +
                        "Together, we can make a positive impact on the environment.");
        guidelinesText.setFont(new Font("Arial", Font.BOLD, 14));
        guidelinesText.setEditable(false);
        guidelinesText.setLineWrap(true);
        guidelinesText.setWrapStyleWord(true);

        JScrollPane guidelinesScrollPane = new JScrollPane(guidelinesText);
        guidelinesScrollPane.setPreferredSize(new Dimension(800, 300));

        JLabel recyclingTitle = new JLabel("Guidelines for Managing Recyclable Materials");
        recyclingTitle.setFont(new Font("Arial", Font.BOLD, 18));
        recyclingTitle.setForeground(new Color(0, 128, 0));
        recyclingTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JTextArea recyclingText = new JTextArea();
        recyclingText.setText(
                "1. Rinse and clean recyclable items before disposing. " +
                        "This helps ensure they can be properly processed and reused.\n\n" +
                        "2. Flatten or crush recyclable containers to save space " +
                        "in the recycling bins.\n\n" +
                        "3. Remove any lids or caps from bottles and jars before " +
                        "recycling. The lids and caps are often made of different " +
                        "materials that require separate processing.\n\n" +
                        "4. Keep recyclables separate from non-recyclable waste. " +
                        "Mixing them can contaminate the recycling stream.");
        recyclingText.setFont(new Font("Arial", Font.BOLD, 14));
        recyclingText.setEditable(false);
        recyclingText.setLineWrap(true);
        recyclingText.setWrapStyleWord(true);

        JScrollPane recyclingScrollPane = new JScrollPane(recyclingText);
        recyclingScrollPane.setPreferredSize(new Dimension(800, 200));

        JLabel nonRecyclingTitle = new JLabel("Guidelines for Managing Non-Recyclable Waste");
        nonRecyclingTitle.setFont(new Font("Arial", Font.BOLD, 18));
        nonRecyclingTitle.setForeground(Color.MAGENTA);
        nonRecyclingTitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 30));

        JTextArea nonRecyclingText = new JTextArea();
        nonRecyclingText.setText(
                "1. Dispose of non-recyclable waste in the designated trash bins. " +
                        "This includes items like food waste, plastic bags, and Styrofoam.\n\n" +
                        "2. Consider reducing the use of non-recyclable materials " +
                        "by choosing reusable or compostable alternatives whenever possible.\n\n" +
                        "3. For large or bulky non-recyclable items, contact your local " +
                        "waste management authority to inquire about special " +
                        "disposal or collection services.");
        nonRecyclingText.setFont(new Font("Arial", Font.PLAIN, 14));
        nonRecyclingText.setEditable(false);
        nonRecyclingText.setLineWrap(true);
        nonRecyclingText.setWrapStyleWord(true);

        JScrollPane nonRecyclingScrollPane = new JScrollPane(nonRecyclingText);
        nonRecyclingScrollPane.setPreferredSize(new Dimension(800, 200));
        // nonRecyclingScrollPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 70,
        // 30));

        guidelinesPanel.add(titleLabel);
        guidelinesPanel.add(guidelinesScrollPane);
        guidelinesPanel.add(recyclingTitle);
        guidelinesPanel.add(recyclingScrollPane);
        guidelinesPanel.add(nonRecyclingTitle);
        guidelinesPanel.add(nonRecyclingScrollPane);
        guidelinesPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 70, 30));

        guidelinesFrame.setSize(1500, 1500);
        guidelinesFrame.add(guidelinesPanel);
        guidelinesFrame.setVisible(true);
    }

    // public static void main(String[] args) {
    // Guidelines guidelines = new Guidelines();
    // guidelines.displayGuidelines();
    // }
}