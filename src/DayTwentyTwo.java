import javax.swing.*;
import java.awt.*;

public class DayTwentyTwo extends JFrame {
    public DayTwentyTwo() {
        setTitle("Day 22 Surprise!");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the program exits when the window is closed
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(new Color(171, 199, 153));

        // Header Label
        JLabel header = new JLabel(
                "<html>Hi my love! It's day 22 today. I miss you like hell. It's the 5th of December " +
                        "at the time I'm making this. It's so cold in Seattle, I can only imagine what the weather " +
                        "is like in New York. I took a few minutes to doodle us as cartoons!</html>",
                SwingConstants.CENTER);
        header.setForeground(new Color(55, 55, 25));
        header.setFont(new Font("Cooper Black", Font.PLAIN, 24));
        main.add(header, BorderLayout.NORTH);

        // Load and scale image
        ImageIcon originalImage = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\drawing.jpg");
        int originalWidth = originalImage.getIconWidth();
        int originalHeight = originalImage.getIconHeight();
        int maxWidth = 400;
        int maxHeight = 400;
        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);
        Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Add scaled image to a label
        JLabel imageLabel = new JLabel(scaledIcon, SwingConstants.CENTER);
        imageLabel.setBackground(new Color(171, 199, 153));
        imageLabel.setOpaque(true);
        main.add(imageLabel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(main);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayTwentyTwo::new);
    }
}
