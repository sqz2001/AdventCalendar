import javax.swing.*;
import java.awt.*;

public class AdventCalendar extends JFrame {

    public AdventCalendar() {
        super("Hello Princess <3");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use card layout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Intro screen setup
        JPanel loadingPanel = new JPanel();
        loadingPanel.setLayout(new BorderLayout(20, 20));
        loadingPanel.setBackground(new Color(124, 145, 110));

        // Panel with gif and added space on the top
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout()); // Using BorderLayout for centering
        content.setBackground(new Color(124, 145, 110));

        // Add space before the GIF
        content.add(Box.createRigidArea(new Dimension(0, 30)), BorderLayout.NORTH);

        // Load GIF
        ImageIcon gifPic = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\waving.gif");
        JLabel gifLabel = new JLabel(gifPic);

        // Center the GIF within its label
        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the GIF to the center of the content panel
        content.add(gifLabel, BorderLayout.CENTER);

        // Intro message
        JLabel messageLabel = new JLabel(
                "<html> <center> Hello my love!<br>" +
                        "I made a program that functions like a digital advent calendar " +
                        "with different surprises for you each day leading up to Christmas. <br>" +
                        "Click the day of the month for a treat :) <center>"
        );
        // Set font and color of message and center it
        messageLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
        messageLabel.setForeground(new Color(255, 255, 255));

        // Add the message label to the bottom of the content panel
        content.add(messageLabel, BorderLayout.SOUTH);

        // Button to move to the calendar screen
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "CalendarPanel"));

        // Arrange components on loading panel
        loadingPanel.add(content, BorderLayout.CENTER);
        loadingPanel.add(nextButton, BorderLayout.SOUTH);

        // Calendar screen setup
        JPanel calendarPanel = createCalendarPanel();
        mainPanel.add(loadingPanel, "LoadingPanel");
        mainPanel.add(calendarPanel, "CalendarPanel");

        add(mainPanel);
        setVisible(true);
    }

    // Method to create the calendar screen
    private JPanel createCalendarPanel() {
        JPanel calendarPanel = new JPanel(new GridLayout(5, 7, 10, 10));
        calendarPanel.setBackground(new Color(189, 212, 174));
        Color calendarDayButton = new Color(124, 145, 110);

        for (int i = 1; i <= 25; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setFont(new Font("Cooper Black", Font.BOLD, 30));
            int day = i;
            dayButton.setBackground(calendarDayButton);
            dayButton.setOpaque(true);
            dayButton.addActionListener(e -> showDayContent(day));
            calendarPanel.add(dayButton);
        }


        return calendarPanel;
    }

    // Method to show content for each day
    private void showDayContent(int day) {
        switch (day) {
            case 1 -> new DayOne();
            case 2 -> new DayTwo();
            case 3 -> new DayThree();
            case 4 -> new DayFour();
            case 5 -> new DayFive();
            case 6 -> new DaySix();
            case 7 -> new DaySeven();
            case 8 -> new DayEight();
            case 9 -> new DayNine();
            case 10 -> new DayTen();
            case 11 -> new DayEleven();
            case 12 -> new DayTwelve();
            case 13 -> new DayThirteen();
            case 14 -> new DayFourteen();
            case 15 -> new DayFifteen();
            case 16 -> new DaySixteen();
            case 17 -> new DaySeventeen();
            case 18 -> new DayEighteen();
            case 19 -> new DayNineteen();
            case 20 -> new DayTwenty();
            case 21 -> new DayTwentyOne();
            case 22 -> new DayTwentyTwo();
            case 23 -> new DayTwentyThree();
            case 24 -> new DayTwentyFour();

            default -> JOptionPane.showMessageDialog(this, "Stay tuned for more surprises!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdventCalendar::new);
    }
}
