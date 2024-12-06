import javax.swing.*;
import java.awt.*;

public class DayTwentyFive extends JFrame {

    public DayTwentyFive() {
        // Set up the JFrame
        setTitle("Day Twenty Five - Merry Christmas");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel with a background color
        JPanel main = new JPanel();
        main.setBackground(new Color(171, 199, 153));
        main.setLayout(new BorderLayout());

        // Add the message as a JLabel
        JLabel text = new JLabel(
                "<html><div style='text-align: center;'>"
                        + "Merry Christmas!!!<br><br>"
                        + "I couldn't think of a way to cap off the advent calendar. "
                        + "Tried many different brainstorming sessions and couldn't quite cap it off with a bang. "
                        + "Instead, I'm using today to just reiterate what I tell you often. "
                        + "You are the best man I know and I am so fortunate to have you in my life. "
                        + "You are the kind of man that every girl wishes to God for, who makes her feel safe, "
                        + "loved, and supported no matter what.<br><br>"
                        + "While the distance has been tough, loving you has never been. I love you so much. "
                        + "I am excited to hop on that plane soon and be back with you in New York. "
                        + "I hope you enjoyed this advent calendar as much as I enjoyed making it."
                        + "</div></html>",
                SwingConstants.CENTER
        );

        text.setFont(new Font("Serif", Font.PLAIN, 16));
        text.setForeground(new Color(50, 50, 50)); // A subtle gray for contrast
        main.add(text, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(main);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayTwentyFive::new);
    }
}
