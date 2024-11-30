import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DayFive extends JFrame {
    public DayFive() {
        setTitle("Day 5 Surprise!");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding on each side
        mainPanel.setLayout(new BorderLayout());

        // Panel for text and the clickable image
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add text to panel
        JLabel textLabel = new JLabel(
                "<html> Hello my love! Day 4 took forever to make.<br>" +
                        "Today, we have something a little more chill. <br>" +
                        "I hope you enjoy :) love you");
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        panel.add(textLabel);

        // Add space between text and image
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Make clickable icon
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\click here pic.png");
        JLabel imageLabel = new JLabel(imageIcon);

        // Resize the icon
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(newImg));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Detect if clicked
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebpage("https://youtu.be/tXsPyeInMK4?feature=shared"); // URL to open
            }
        });

        // Add components to panel
        panel.add(textLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(imageLabel);

        // Add panel to mainPanel and mainPanel to the frame
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
    }

    private void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
