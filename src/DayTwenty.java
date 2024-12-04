import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DayTwenty extends JFrame {

    private final String[] imagePaths = {
            "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\slot1.png",
            "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\slot2.png",
            "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\slot3.png",
            "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\slot4.png"
    };

    private final JPanel[] slots = new JPanel[4];
    private final Random random = new Random();
    private Timer animationTimer;
    private int randomImage;
    private int step = 0;

    public DayTwenty() {
        setTitle("Day 20 Surprise!");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(new Color(157, 191, 160));

        // Slot panel
        JPanel slotPanel = new JPanel();
        slotPanel.setBackground(new Color(86, 120, 95));
        slotPanel.setLayout(new GridLayout(1, 4, 10, 10));

        for (int i = 0; i < 4; i++) {
            JPanel slot = new JPanel();
            slot.setBackground(new Color(143, 118, 145));
            slots[i] = slot;
            slotPanel.add(slot);
        }
        main.add(slotPanel, BorderLayout.CENTER);

        JLabel intro = new JLabel("<html> Well look at that! It's day 20 already. Only a few more days until I'm " +
                "in New York and get to spend time with you! For today's surprise, you're gonna play a slot machine game " +
                "and win something! You'll get something sent to you in a day or two. May the odds be ever in your favor. " +
                " The game is based on having a different item correspond to a random int 1-4. Whatever int gets " +
                "generated is the item you'll get!");
        intro.setFont(new Font("Cooper Black", Font.BOLD, 18));
        intro.setForeground(new Color(227, 225, 227));
        intro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        main.add(intro, BorderLayout.NORTH);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Cooper Black", Font.BOLD, 24));
        playButton.setBackground(new Color(143, 118, 145));
        playButton.setForeground(new Color(227, 225, 227));
        main.add(playButton, BorderLayout.SOUTH);

        // Animation Timer
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JPanel slot : slots) {
                    slot.setBackground(
                            new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                }
                step++;
                if (step > 10) {
                    animationTimer.stop();
                    revealRandomImage();
                }
            }
        });

        playButton.addActionListener(e -> {
            resetSlots();
            step = 0;
            animationTimer.start();
        });

        add(main);
        setVisible(true);
    }

    private void resetSlots() {
        for (JPanel slot : slots) {
            slot.removeAll();
            slot.setBackground(new Color(143, 118, 145));
            slot.repaint();
        }
    }

    private void revealRandomImage() {
        int randomSlot = random.nextInt(4);
        randomImage = random.nextInt(4); // Assign the value here

        ImageIcon icon = new ImageIcon(imagePaths[randomImage]);
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));

        slots[randomSlot].setLayout(new BorderLayout());
        slots[randomSlot].add(imageLabel, BorderLayout.CENTER);
        slots[randomSlot].revalidate();
        slots[randomSlot].repaint();
        // Debugging output to ensure randomness
        System.out.println("Image path: " + imagePaths[randomImage]); // Debug
        System.out.println("Random slot index: " + randomSlot); // Debug
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayTwenty::new);
    }
}
