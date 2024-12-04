import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DayNineteen extends JFrame {
    public DayNineteen() {
        setTitle("Day 19 Surprise!");
        setSize(550, 650);
        setLocationRelativeTo(null);
        setBackground(new Color(157, 191, 160));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel setup
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(157, 191, 160));

        // Caption setup
        JLabel caption = new JLabel("<html> Hi my love! For today's surprise I wanted to share " +
                "with you one of the most impactful articles I've read in regards to race and cultural image. " +
                "Click the icon to be linked to it. " +
                "I feel that it is important to us because we come from two different cultural backgrounds. " +
                "Understanding our unique experiences growing up in our differing cultures will help us understand " +
                "each other better and know why we may have different opinions on topics down the road. " +
                "One passage from this article that's stuck with me ever since I read it has been: <br><br>" +
                "<i>'My husband and I, in the darkest moment of our private reckoning, in the face of a threat to " +
                "our very future, should end up reproducing the flat outlines of these mirror images has everything " +
                "to do with the racial and gender formulations that inevitably shape and fall terribly short of who " +
                "we are. It tells us how powerful and yet also how impoverished cultural images are.'</i></html>");
        caption.setFont(new Font("Cooper Black", Font.BOLD, 18));
        caption.setForeground(new Color(83, 105, 87));
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        caption.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        main.add(caption, BorderLayout.NORTH);

        // Load and resize the image
        String imagePath = "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\readingicon.png";
        ImageIcon bookIcon = new ImageIcon(imagePath);
        Image img = bookIcon.getImage();
        Image resizedImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        // Create clickable image label
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebpage("https://www.thenation.com/article/culture/essay-interracial-love/"); // Replace with your actual URL
            }
        });

        // Add image to center of main panel
        main.add(imageLabel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(main);

        // Set frame visible
        setVisible(true);
    }

    private static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(null, "Unable to open the link. Check your internet connection.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayNineteen::new);
    }
}
