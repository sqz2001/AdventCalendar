import javax.swing.*;
import java.awt.*;

public class DayEighteen extends JFrame {
    public DayEighteen() {
        setTitle("Day 18 Surprise!");
        setSize(800, 800); // Larger frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(157, 191, 160));

        // Header text
        JLabel headerText = new JLabel("<html><center>Hi my love! Today I have curated an exhibit/gallery of my favorite " +
                "FB Marketplace finds. Click the 'Next' button to see today's collection.</center></html>");
        headerText.setFont(new Font("Cooper Black", Font.PLAIN, 26));
        headerText.setForeground(new Color(242, 241, 235));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerText, BorderLayout.NORTH);

        // Card panel with CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBackground(new Color(157, 191, 160));

        // Use larger max dimensions for images
        ImageIcon scaledIcon1 = scaleImage("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\fbmarketplace1.png", 700, 700);
        ImageIcon scaledIcon2 = scaleImage("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\fbmarketplace2.png", 700, 700);
        ImageIcon scaledIcon3 = scaleImage("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\fbmarketplace3.png", 700, 700);

        // Create cards
        JPanel card1 = new JPanel(new BorderLayout());
        card1.setBackground(new Color(157, 191, 160));
        card1.add(new JLabel(scaledIcon1), BorderLayout.CENTER);
        card1.add(new JLabel("Exhibit A: Crochet, 6ft"), BorderLayout.SOUTH);

        JPanel card2 = new JPanel(new BorderLayout());
        card2.setBackground(new Color(157, 191, 160));
        card2.add(new JLabel(scaledIcon2), BorderLayout.CENTER);
        card2.add(new JLabel("Exhibit B: Cursed Doll"), BorderLayout.SOUTH);

        JPanel card3 = new JPanel(new BorderLayout());
        card3.setBackground(new Color(157, 191, 160));
        card3.add(new JLabel(scaledIcon3), BorderLayout.CENTER);
        card3.add(new JLabel("Exhibit C: Mickey Phone"), BorderLayout.SOUTH);

        // Add cards to cardPanel
        cardPanel.add(card1, "Exhibit A");
        cardPanel.add(card2, "Exhibit B");
        cardPanel.add(card3, "Exhibit C");

        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Cooper Black", Font.BOLD, 18));
        nextButton.setBackground(new Color(81, 122, 101));
        nextButton.setForeground(Color.WHITE);

        // Store CardLayout for navigation
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        nextButton.addActionListener(e -> cardLayout.next(cardPanel));

        // Footer panel for navigation
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(157, 191, 160));
        footerPanel.add(nextButton);

        // Add components to main panel
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
    }

    // Helper method to scale images
    private ImageIcon scaleImage(String path, int maxWidth, int maxHeight) {
        ImageIcon originalImage = new ImageIcon(path);
        int originalWidth = originalImage.getIconWidth();
        int originalHeight = originalImage.getIconHeight();

        // If the image fits within the dimensions, return it as-is
        if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
            return originalImage;
        }

        // Calculate scaling factor
        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);

        // Scale image
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);
        Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayEighteen::new);
    }
}
