import javax.swing.*;
import java.awt.*;

public class DayOne extends JFrame {
    public DayOne() {
        setTitle("Day 1 Surprise!");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load image
        ImageIcon originalImage = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\dayone.jpg");

        // Get original dimensions of image
        int originalWidth = originalImage.getIconWidth();
        int originalHeight = originalImage.getIconHeight();

        // Set maximum dimensions
        int maxWidth = 400;
        int maxHeight = 400;

        // Create scaling factor to size down image while maintaining aspect ratio
        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);

        // Applying the new scale factor to resize image
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        // Scaled image to fit screen while maintaining aspect ratio
        Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set up panel w image and text
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout()); // borderlayout defaults to having 5 regions
        JLabel imageLabel = new JLabel(scaledIcon); // Use the scaled image
        imageLabel.setBackground(new Color(171, 199, 153));

        JLabel textLabel = new JLabel("This is a photo I took on the first night we met",
                SwingConstants.CENTER);
        textLabel.setFont(new Font("Cooper Black", Font.BOLD, 15));
        textLabel.setBackground(new Color(171, 199, 153));
        contentPanel.add(imageLabel, BorderLayout.CENTER); //image in center
        contentPanel.add(textLabel, BorderLayout.SOUTH); //text at bottom
        contentPanel.setBackground(new Color(171, 199, 153));

        // create panel at bottom with empty lines for aesthetics
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout()); // using borderlayout
        outerPanel.add(contentPanel, BorderLayout.CENTER); // place contentpanel in center of outerpanel
        outerPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH); //space at bottom
        outerPanel.setBackground(new Color(171, 199, 153));

        add(outerPanel); // Add the outer panel to the frame
        setVisible(true);
    }
}
