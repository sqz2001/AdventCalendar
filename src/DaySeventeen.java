import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class DaySeventeen extends JFrame {
    private static final int BOARD_SIZE = 800;
    private static final int TILE_SIZE = 20;

    private final LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private String direction = "RIGHT";
    private boolean running;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DaySeventeen() {
        setTitle("Day 17 Surprise!");
        setSize(BOARD_SIZE, BOARD_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add intro screen and game panel to CardLayout
        mainPanel.add(createIntroScreen(), "Intro");
        SnakePanel snakePanel = new SnakePanel();
        mainPanel.add(snakePanel, "Game");

        add(mainPanel);
        setVisible(true);

        // Show the intro screen first
        cardLayout.show(mainPanel, "Intro");

        // Start game when the "Game" panel is shown
        ((SnakePanel) mainPanel.getComponent(1)).startGame();
    }

    private JPanel createIntroScreen() {
        JPanel introPanel = new JPanel(new BorderLayout());
        introPanel.setBackground(new Color(157, 191, 160));

        // Intro text
        JTextArea textArea = new JTextArea(
                "Hi my love! You know how I enjoy playing the snake game? Well, turns out, " +
                        "while I am not quite ready to make Elden Ring, I do have enough computing power to make a basic " +
                        "version of this game, using the data structure using a linked list. Basically, when a snake eats " +
                        "something, an extra node is added to its tail, until the snake hits a boundary or itself. " +
                        "Use the arrow keys to control the snake!");
        textArea.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        textArea.setForeground(new Color(242, 241, 235));
        textArea.setBackground(new Color(157, 191, 160));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Cooper Black", Font.BOLD, 18));
        startButton.setBackground(new Color(81, 122, 101));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Game");
            ((SnakePanel) mainPanel.getComponent(1)).startGame(); // Start the game
        });

        // Add components to the intro panel
        introPanel.add(textScrollPane, BorderLayout.CENTER);
        introPanel.add(startButton, BorderLayout.SOUTH);

        return introPanel;
    }

    private class SnakePanel extends JPanel implements ActionListener, KeyListener {
        private final Timer timer;

        public SnakePanel() {
            setBackground(new Color(157, 191, 160));
            setFocusable(true);
            addKeyListener(this);

            timer = new Timer(100, this);
        }

        public void startGame() {
            System.out.println("Game started!");
            snake.clear();
            snake.add(new Point(5, 5)); // Starting point of the snake
            placeFood();
            direction = "RIGHT";
            running = true;
            timer.start();
            requestFocusInWindow(); // Ensure panel has focus for key events
            repaint();
        }

        private void placeFood() {
            Random random = new Random();
            int x, y;
            do {
                x = random.nextInt(BOARD_SIZE / TILE_SIZE);
                y = random.nextInt(BOARD_SIZE / TILE_SIZE);
            } while (snake.contains(new Point(x, y)));

            food = new Point(x, y);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw snake
            g.setColor(new Color(232, 192, 240));
            for (Point segment : snake) {
                g.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            // Draw food
            if (food != null) {
                g.setColor(new Color(81, 122, 101));
                g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            // Draw game-over message
            if (!running) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Cooper Black", Font.BOLD, 36));
                g.drawString("Game Over!", BOARD_SIZE / 2 - 100, BOARD_SIZE / 2);

                g.setFont(new Font("Cooper Black", Font.PLAIN, 18));
                g.drawString("Press R to Restart", BOARD_SIZE / 2 - 100, BOARD_SIZE / 2 + 30);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (running) {
                moveSnake();
                checkCollision();
                repaint();
            }
        }

        private void moveSnake() {
            Point head = snake.getFirst();
            Point newHead = switch (direction) {
                case "UP" -> new Point(head.x, head.y - 1);
                case "DOWN" -> new Point(head.x, head.y + 1);
                case "LEFT" -> new Point(head.x - 1, head.y);
                case "RIGHT" -> new Point(head.x + 1, head.y);
                default -> head;
            };

            snake.addFirst(newHead);

            if (newHead.equals(food)) {
                placeFood();
            } else {
                snake.removeLast();
            }
        }

        private void checkCollision() {
            Point head = snake.getFirst();

            // Check wall collisions
            if (head.x < 0 || head.x >= BOARD_SIZE / TILE_SIZE || head.y < 0 || head.y >= BOARD_SIZE / TILE_SIZE) {
                running = false;
                timer.stop();
            }

            // Check self-collision
            for (int i = 1; i < snake.size(); i++) {
                if (head.equals(snake.get(i))) {
                    running = false;
                    timer.stop();
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (running) {
                // Change direction
                if (key == KeyEvent.VK_UP && !direction.equals("DOWN")) {
                    direction = "UP";
                } else if (key == KeyEvent.VK_DOWN && !direction.equals("UP")) {
                    direction = "DOWN";
                } else if (key == KeyEvent.VK_LEFT && !direction.equals("RIGHT")) {
                    direction = "LEFT";
                } else if (key == KeyEvent.VK_RIGHT && !direction.equals("LEFT")) {
                    direction = "RIGHT";
                }
            }

            // Restart game
            if (!running && key == KeyEvent.VK_R) {
                startGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DaySeventeen::new);
    }
}
