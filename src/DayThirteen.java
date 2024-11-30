import javax.swing.*;
import java.awt.*;

// inform him that there should be a package he is receiving tonight (red string)
// so we can make something together when I get to NY
// and show tutorial on how to make the item
// https://www.youtube.com/watch?v=tcC-uhKNvPU video for how to tie

public class DayThirteen extends JFrame {
    DayThirteen() {
        setTitle("Day 13 Surprise!");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));;
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding on each side
        mainPanel.setBackground(new Color(171, 199, 153));

        // Add text to panel
        JLabel textLabel = new JLabel(
                "<html> <center> Hello my love! Expect a package in the mail today! Don't open it yet though! " +
                        "It's for a very fast and simple arts and crafts time we can probably knock out in bed. " +
                        "We'll also need to have a lighter, scissors, and measuring tape. Can you guess what we're making? ");
        textLabel.setForeground(new Color(235, 233, 228));
        textLabel.setFont(new Font("Cooper Black", Font.BOLD, 25));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(textLabel);

        // Add space between text and image
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // load image and resize
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\amazonbox.png");
        Image img = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        mainPanel.add(imageLabel);

        add(mainPanel);
        setVisible(true);
    }
}
