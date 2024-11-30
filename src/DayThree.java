import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DayThree extends JFrame {

    public DayThree() {
        setTitle("Day 3 Surprise!");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // panel for text and the clickable image
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use vertical BoxLayout
        panel.setBackground(new Color(180, 209, 161));

        // Add text to panel
        JLabel textLabel = new JLabel("Hello my love! Click the icon below for today's surprise <3");
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        panel.add(textLabel);

        // Add space between text and image
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between text and image

        // Make clickable icon
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\mysteryboxpic.png");
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
                openWebpage("https://www.youtube.com/watch?v=bEuClmWCWkI"); // URL to open
            }
        });

        // Add components to panel
        panel.add(textLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between text and image
        panel.add(imageLabel);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel

        // Add the panel to the center of the frame
        add(panel, BorderLayout.CENTER); // Center the panel in the frame
        setVisible(true);
    }

    private void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DayThree());
    }
}
