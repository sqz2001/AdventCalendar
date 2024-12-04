import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DayEleven extends JFrame {
    public DayEleven() {
        setTitle("Day 11 Surprise!");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(191, 219, 196));

        // Text area
        JTextArea text = new JTextArea("Hi my love! Today's surprise is a video of me being emo during COVID. " +
                "Totally forgot this existed but found it digging through Google Drive. Click the pic to see!");
        text.setFont(new Font("Cooper Black", Font.BOLD, 20));
        text.setForeground(new Color(83, 105, 87));
        text.setBackground(new Color(191, 219, 196));
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        main.add(text, BorderLayout.NORTH);

        // center panel holding image
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(191, 219, 196));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Clickable icon
        String imagePath = "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\emo.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel();

        // Resize the icon
        Image img = imageIcon.getImage();
        Image resizedImage = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(resizedImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add click listener
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebpage("https://youtube.com/shorts/RuJQzIQ3avw");
            }
        });

        // Add image to center panel
        centerPanel.add(imageLabel);
        main.add(centerPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(main);

        // Display the frame
        setVisible(true);
    }

    private void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, "Unable to open the link. Check your internet connection or try again later.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayEleven::new);
    }
}
