import javax.swing.*;
import java.awt.*;

public class DayEleven extends JFrame {
    public DayEleven() {
        setTitle("Day 12 Surprise!");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        //make main panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(new Color(191, 219, 196));
        
        //make text area
        JTextArea text = new JTextArea("Hi my love! Sorry for playing too much, here's a photo of me scheming");
        text.setFont(new Font("Cooper Black", Font.BOLD, 20));
        text.setForeground(new Color(83, 105, 87));
        text.setBackground(new Color(191, 219, 196));
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.add(text, BorderLayout.NORTH);

        // load image
        ImageIcon originalImage = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\.jpg");

        // Get original dimensions of image
        int originalWidth = originalImage.getIconWidth();
        int originalHeight = originalImage.getIconHeight();

        // Set maximum dimensions
        int maxWidth = 350;
        int maxHeight = 300;

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

        //add image to main panel
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        main.add(imageLabel, BorderLayout.CENTER);

        //add pain panel to frame
        add(main);
        setVisible(true);
    }
}
