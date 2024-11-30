import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// day 10 is a photo of me looking pretty as an apology for the previous day's jumpscare

public class DayTen extends JFrame {

    public DayTen() {
        setTitle("Day 10 Surprise!");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(171, 199, 153));

        // Add text to mainPanel
        JLabel textLabel = new JLabel(
                "<html><center>Hello my love! <br>" +
                        "Yesterday I showed you a very scary picture, so today we get a pretty picture. " +
                        "I know you think I am pretty all the time (you are the sweetest), " +
                        "but here is a pic of me feeling cutesy.<center>");
        textLabel.setFont(new Font("Cooper Black", Font.BOLD, 25));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
        textLabel.setForeground(new Color(96, 128, 114));
        mainPanel.add(textLabel, BorderLayout.NORTH);

        // Make clickable button
        RoundButton roundButton = new RoundButton("Click Here!");
        roundButton.setPreferredSize(new Dimension(100, 100));
        roundButton.setBackground(new Color(190, 201, 183));
        roundButton.setForeground(new Color(96, 128, 114));

        // Add mouse listener to detect click
        roundButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open new window with image
                openImageWindow();
            }
        });

        //wrap button in panel to add to main panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(mainPanel.getBackground());
        buttonPanel.add(roundButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private void openImageWindow() {
        JFrame imageWindow = new JFrame("YIKES");
        imageWindow.setSize(400, 500);
        imageWindow.setLocationRelativeTo(null);
        imageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon yikes = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\ew.jpg");

        JLabel imageLabel = new JLabel(yikes);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        imageWindow.add(imageLabel);
        imageWindow.setVisible(true);
    }

    // Custom RoundButton class
    static class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Enable anti-aliasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw circle background
            g2.setColor(getBackground());
            g2.fillOval(0, 0, getWidth(), getHeight());

            // Draw button text
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            // Check if the point (x, y) is within the circle
            int radius = Math.min(getWidth(), getHeight()) / 2;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayTen::new);  // Run the DayTen JFrame
    }
}
