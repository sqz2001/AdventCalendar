import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class DaySeven extends JFrame {
    private Image backgroundImage;
    private Timer timer;
    private int basketX;
    private final int basketWidth = 50;
    private final int basketHeight = 20;
    private ArrayList<FallingObject> fallingObjects;
    private int score = 0;
    private int missed = 0;
    private Random random;

    public DaySeven() {
        backgroundImage = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\skypic.jpg").getImage();

        setTitle("Day 7 Surprise!");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        basketX = getWidth() / 2 - basketWidth / 2;
        fallingObjects = new ArrayList<>();
        random = new Random();

        // Create and display the intro panel with start button
        showIntroPanel();

        setVisible(true);
    }

    private void showIntroPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel introPanel = new JPanel(new GridBagLayout());
        introPanel.setOpaque(false);
        introPanel.setBackground(new Color(255, 255, 255, 150));

        JLabel textLabel = new JLabel(
                "<html><div style='text-align: center;'>"
                        + "Hello my love! Day 7 already?<br>"
                        + "It's raining cats and dogs in Seattle right now.<br>"
                        + "I decided to make a little game for you today.<br>"
                        + "The background photo is the Seattle sky today.<br><br>"
                        + "Click Start to play!"
                        + "</div></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.BLACK);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        introPanel.add(textLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 10, 10, 10);
        introPanel.add(startButton, gbc);

        backgroundPanel.add(introPanel, gbc);
        setContentPane(backgroundPanel);
        revalidate();
        repaint();
    }

    private void startGame() {
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        setContentPane(backgroundPanel);
        backgroundPanel.setFocusable(true);
        backgroundPanel.requestFocusInWindow();

        timer = new Timer(20, e -> updateGame());
        timer.start();

        backgroundPanel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && basketX > 0) {
                    basketX -= 15;
                } else if (key == KeyEvent.VK_RIGHT && basketX < getWidth() - basketWidth) {
                    basketX += 15;
                }
                backgroundPanel.repaint();
            }
        });

        revalidate();
        repaint();
    }

    private void updateGame() {
        if (random.nextInt(50) == 0) {
            int objectX = random.nextInt(getWidth() - 20);
            int diameter = 20;
            fallingObjects.add(new FallingObject(objectX, 0, diameter));
        }

        for (int i = 0; i < fallingObjects.size(); i++) {
            FallingObject obj = fallingObjects.get(i);
            obj.y += 2;  // Update position

            Rectangle basket = new Rectangle(basketX, getHeight() - basketHeight - 100, basketWidth, basketHeight);
            Rectangle circle = obj.getRectangle();

            if (circle.intersects(basket)) {
                score++; // Increment score
                fallingObjects.remove(i); // Remove the object after collision
                i--; // Adjust index after removal
            }
            else if (obj.y > getHeight()) {
                fallingObjects.remove(i); // Remove object if it goes below the screen
                missed++;
                i--; // Adjust index after removal
            }
        }

        // Game over condition
        if (missed >= 5) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Your Score: " + score);
            resetGame();
        }

        repaint(); // Repaint after the game state is updated
    }

    private void resetGame() {
        score = 0;
        missed = 0;
        fallingObjects.clear();
        timer.start();
    }

    private class FallingObject {
        int x, y, diameter;
        Color color;

        public FallingObject(int x, int y, int diameter) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, diameter, diameter); // Draw circle
        }

        public Rectangle getRectangle() {
            return new Rectangle(x, y, diameter, diameter);
        }
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            for (FallingObject obj : fallingObjects) {
                obj.draw(g);
            }

            g.setColor(Color.BLACK);
            g.fillRect(basketX, getHeight() - basketHeight - 80, basketWidth, basketHeight);

            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
            g.drawString("Missed: " + missed, 10, 40);
        }
    }
}
