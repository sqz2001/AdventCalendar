import javax.swing.*;
import java.awt.*;

public class DaySixteen extends JFrame {
    public DaySixteen() {
        setTitle("Day 16 Surprise!");
        setBackground(new Color(157, 191, 160));
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(157, 191, 160));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(157, 191, 160)));
        mainPanel.setLayout(new BorderLayout());

        // Text panel (NORTH) with wrapping text
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(new Color(157, 191, 160));
        JTextArea textArea = new JTextArea("Hi my love! You know how you helped me proofread my" +
                " resume and in the project section, it had the Advent Calendar Project (this project) that I only gave " +
                "you a sneak peek of prior? Well in doing so I did accidentally let you know that there would be games " +
                "in here and you jokingly said 'ARE YOU PUTTING ELDEN RING IN THE CALENDAR?? If you love me you'd put " +
                "Elden Ring in the calendar'...knowing damn well I can't code like that. Well it just happens that " +
                "I love you, so I AM including (a photo) of Elden Ring in here. Love you!");
        textArea.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        textArea.setForeground(new Color(242, 241, 235));
        textArea.setBackground(new Color(157, 191, 160));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(BorderFactory.createEmptyBorder());
        textPanel.add(textScrollPane, BorderLayout.CENTER);

        // Load and scale the image (CENTER)
        ImageIcon originalImage = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\gregory.jpg");
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

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Caption (SOUTH)
        JLabel caption = new JLabel("Do you remember our son, Gregory?");
        caption.setFont(new Font("Cooper Black", Font.BOLD, 18));
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        caption.setForeground(new Color(242, 241, 235));

        // Add components to main panel
        mainPanel.add(textPanel, BorderLayout.NORTH); // Add text panel to NORTH
        mainPanel.add(imageLabel, BorderLayout.CENTER); // Add image label to CENTER
        mainPanel.add(caption, BorderLayout.SOUTH); // Add caption to SOUTH

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DaySixteen();
        });
    }
}
