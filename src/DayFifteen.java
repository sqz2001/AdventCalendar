import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class DayFifteen extends JFrame {
    private JTextField inputField;
    private JButton startButton;
    private JPanel numberPanel;

    public DayFifteen() {
        setTitle("Day 15 Surprise!");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(171, 199, 153));
        inputPanel.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Enter n:");
        inputLabel.setFont(new Font("Cooper Black", Font.BOLD, 25));
        inputLabel.setBackground(new Color(79, 92, 81));

        inputField = new JTextField(10);
        inputField.setBackground(new Color(171, 199, 153));

        startButton = new JButton("Compute Primes");
        startButton.setBackground(new Color(232, 206, 235));

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(startButton);

        add(inputPanel, BorderLayout.SOUTH);

        // intro panel
        JPanel introPanel = new JPanel();
        introPanel.setBackground(new Color(171, 199, 153));
        introPanel.setLayout(new BorderLayout());
        JLabel introLabel = new JLabel("<html> Hi my love! I needed to practice some " +
                "object-oriented programming so I did a mini-project using data structures " +
                "and made a visual to go with it! This is a program that illustrates The Sieve of " +
                "Eratosthenes. It's an ancient algorithm that finds all prime numbers up to a certain number n. " +
                "I love how supportive you are of me learning computer science. " +
                "It's nice to have people cheering me on. I wish I would have had the support I have now when I " +
                "was younger. So thankful to have you in my life now! Love you! <center>");
        introLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        introLabel.setForeground(new Color(242, 241, 235));


        introPanel.add(introLabel);
        add(introPanel, BorderLayout.NORTH);


        // Number panel
        numberPanel = new JPanel();
        numberPanel.setBackground(new Color(237, 236, 230));
        numberPanel.setLayout(new GridLayout(0, 10, 5, 5)); // 10 numbers per row
        add(new JScrollPane(numberPanel), BorderLayout.CENTER);

        // Button action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computePrimes();
                introPanel.removeAll();
            }
        });
    }

    private void computePrimes() {
        numberPanel.removeAll(); // Clear previous results
        int n;
        try {
            n = Integer.parseInt(inputField.getText());
            if (n < 2) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer ≥ 2.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Initialize queues
        Queue<Integer> numbers = new LinkedList<>();
        Queue<Integer> primes = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            numbers.add(i);
        }

        // Sieve algorithm with visualization
        while (!numbers.isEmpty() && numbers.peek() <= Math.sqrt(n)) {
            int p = numbers.poll();
            primes.add(p);

            // Update visualization for p (mark as prime)
            updateNumberPanel(p, new Color(203, 190, 209));

            // Filter numbers divisible by p
            int size = numbers.size();
            for (int i = 0; i < size; i++) {
                int num = numbers.poll();
                if (num % p != 0) {
                    numbers.add(num);
                } else {
                    // Update visualization for eliminated numbers
                    updateNumberPanel(num, Color.BLACK);
                }
            }
        }

        // Add remaining primes
        primes.addAll(numbers);

        // Final visualization for remaining primes
        for (int prime : primes) {
            updateNumberPanel(prime, new Color(203, 190, 209));
        }

        // Refresh the panel
        numberPanel.revalidate();
        numberPanel.repaint();
    }

    private void updateNumberPanel(int num, Color color) {
        JLabel numberLabel = new JLabel(String.valueOf(num));
        numberLabel.setOpaque(true);
        numberLabel.setBackground(color);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberPanel.add(numberLabel);

        // Optional: Add a delay for better visualization
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DayFifteen frame = new DayFifteen();
            frame.setVisible(true);
        });
    }
}
