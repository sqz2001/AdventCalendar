import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// day 9 is a photo of me from 2016

public class DayNine extends JFrame {
    public DayNine() {
        setTitle("Day 9 Surprise!");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(191, 219, 196)); //set background color

        // Load GIF
        ImageIcon gifPic = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\catgif.gif");
        JLabel gifLabel = new JLabel(gifPic);
        gifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text below the GIF
        JLabel textLabel = new JLabel(
                "<html><center> Sorry you must have seen that one coming!<br>" +
                        "Give me credit though, I held out from doing that for one whole week.<br>" +
                        "Today's surprise is something I dug up from archives circa 2016.<br>" +
                        "Jumpscare warning. click the cat to see! <center> </html>", SwingConstants.CENTER);

        textLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        textLabel.setForeground(new Color(83, 105, 87));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add GIF and text to main panel
        mainPanel.add(gifLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between GIF and text
        mainPanel.add(textLabel);

        // Click listener to open new window with scaled image
        gifLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame imageFrame = new JFrame("Surprise Image");
                imageFrame.setSize(500, 500);
                imageFrame.setLocationRelativeTo(null);

                // Load fetus image and scale it
                ImageIcon originalImage = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\fetuspic.JPG");
                int originalWidth = originalImage.getIconWidth();
                int originalHeight = originalImage.getIconHeight();
                int maxWidth = 400;
                int maxHeight = 400;

                // Scaling to maintain aspect ratio
                double widthScale = (double) maxWidth / originalWidth;
                double heightScale = (double) maxHeight / originalHeight;
                double scale = Math.min(widthScale, heightScale);

                int newWidth = (int) (originalWidth * scale);
                int newHeight = (int) (originalHeight * scale);
                Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Set up panel with the image and caption
                JPanel contentPanel = new JPanel(new BorderLayout());
                contentPanel.setBackground(new Color(220, 247, 225));
                JLabel imageLabel = new JLabel(scaledIcon);
                JLabel captionLabel = new JLabel("suhhhh dude!", SwingConstants.CENTER);
                captionLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
                captionLabel.setForeground(new Color(83, 105, 87));

                contentPanel.add(imageLabel, BorderLayout.CENTER);
                contentPanel.add(captionLabel, BorderLayout.SOUTH);

                imageFrame.add(contentPanel);
                imageFrame.setVisible(true);
            }
        });

        // Add the main panel to the frame
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayNine::new);
    }
}
