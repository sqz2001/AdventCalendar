import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DayTwentyFour extends JFrame {
    public DayTwentyFour() {
        super("Day 24 Surprise!");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel for content
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(new Color(171, 199, 153));

        // Text label with HTML content
        JLabel text = new JLabel("<HTML> Hi my love! Christmas eve today <3 Just a few days until I'm in New York. " +
                "I'm soooo excited. I wanted to share this insane Christmas song I found. It's AI generated. This " +
                "person named Evan Tunes uses AI to replicate Justin Bieber's voice and then made a name for himself " +
                "that way. Isn't the world of tech just getting a little bit crazy? The whole music video is also AI. " +
                "Click the icon to watch</HTML>");
        text.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        text.setForeground(new Color(255, 255, 255));
        main.add(text, BorderLayout.NORTH);

        // Space between text and image
        main.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.CENTER);

        // Image label
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\gifts.png");
        JLabel imageLabel = new JLabel(imageIcon);

        // Resize the image to a smaller size
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(newImg));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(imageLabel, BorderLayout.CENTER);

        // Detect if image is clicked
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebpage("https://www.youtube.com/watch?v=6qYksTDtNVY");
            }
        });

        // Add main panel to the JFrame
        add(main);

        // Make the JFrame visible
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
        SwingUtilities.invokeLater(() -> new DayTwentyFour().setVisible(true));
    }
}
