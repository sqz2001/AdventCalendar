import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DayFourteen extends JFrame {
    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int CELL_SIZE = 40;

    public static final int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static boolean[][] visited;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final MazePanel mazePanel = new MazePanel();
    private final JButton dfsButton = new JButton("Solve with DFS");
    private final JButton bfsButton = new JButton("Solve with BFS");
    private final JButton resetButton = new JButton("Reset");

    public DayFourteen() {
        setTitle("Day 14 Surprise!");
        setSize(COLS * CELL_SIZE + 300, ROWS * CELL_SIZE + 20);
        setLocationRelativeTo(null);
        setBackground(new Color(195, 200, 232));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(mainPanel);
        mainPanel.add(createIntroScreen(), "Intro");
        mainPanel.add(createMazeScreen(), "Maze");

        cardLayout.show(mainPanel, "Intro"); // Show intro screen as default
        setVisible(true);
    }

    private JPanel createIntroScreen() {
        JPanel introPanel = new JPanel(new BorderLayout());
        introPanel.setBackground(new Color(195, 200, 232));

        JLabel introLabel = new JLabel("<html>Hi my love! Today I want to show you a maze solver! " +
                "I've wanted to make a maze solver for a while now. My interest was piqued after hearing about " +
                "the maze-solving contest called Micromouse. It was inspired by one of the world's first examples of " +
                "machine learning, a robotic mouse named Theseus who solved mazes. " +
                "Maybe in the future we can attempt to build a micromouse of our own or attend the contest as viewers. " +
                "I think it would be super cool! Anyways, for how this works: Algorithms are utilized to solve mazes; " +
                "two of the most common methods are depth-first search and breadth first search. " +
                "BFS use the data structure of queues and DFS uses the data structure of stacks. " +
                "The best way to explain this is to examine the literal name of each. With a queue, or line, " +
                "the first person in line is the first attended to. With stacking objects, the last thing you stack is the " +
                "easiest to access. For BFS we find the shortest path and for DFS we simply try to find a path. " +
                "My program today is a visual representation of each of the methods! <center> </html>");
        introLabel.setFont(new Font("Cooper Black", Font.PLAIN, 18));

        JButton startButton = new JButton("Start the Maze!");
        startButton.setFont(new Font("Cooper Black", Font.BOLD, 20));
        startButton.setBackground(new Color(157, 191, 160));
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "Maze"));

        introPanel.add(introLabel, BorderLayout.CENTER);
        introPanel.add(startButton, BorderLayout.SOUTH);
        return introPanel;
    }

    private JPanel createMazeScreen() {
        JPanel mazeScreen = new JPanel(null);
        mazeScreen.setBackground(new Color(157, 191, 160));

        dfsButton.setBounds(COLS * CELL_SIZE + 10, 10, 220, 30);
        bfsButton.setBounds(COLS * CELL_SIZE + 10, 50, 220, 30);
        resetButton.setBounds(COLS * CELL_SIZE + 10, 90, 220, 30);

        mazePanel.setBounds(0, 0, COLS * CELL_SIZE, ROWS * CELL_SIZE);

        mazeScreen.add(mazePanel);
        mazeScreen.add(dfsButton);
        mazeScreen.add(bfsButton);
        mazeScreen.add(resetButton);

        dfsButton.addActionListener(e -> visualizeDFS(1, 1, 8, 8));
        bfsButton.addActionListener(e -> visualizeBFS(1, 1, 8, 8));
        resetButton.addActionListener(e -> resetMaze());

        initializeVisitedArray();
        return mazeScreen;
    }

    private void initializeVisitedArray() {
        visited = new boolean[ROWS][COLS];
    }

    private void visualizeDFS(int startRow, int startCol, int endRow, int endCol) {
        new Thread(() -> {
            Stack<Point> stack = new Stack<>();
            stack.push(new Point(startRow, startCol));

            while (!stack.isEmpty()) {
                Point current = stack.pop();
                int row = current.x, col = current.y;

                if (row < 0 || col < 0 || row >= ROWS || col >= COLS || maze[row][col] == 1 || visited[row][col]) {
                    continue;
                }

                SwingUtilities.invokeLater(() -> {
                    visited[row][col] = true;
                    mazePanel.repaint();
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (row == endRow && col == endCol) {
                    return;
                }

                stack.push(new Point(row - 1, col)); // Up
                stack.push(new Point(row + 1, col)); // Down
                stack.push(new Point(row, col - 1)); // Left
                stack.push(new Point(row, col + 1)); // Right
            }
        }).start();
    }

    private void visualizeBFS(int startRow, int startCol, int endRow, int endCol) {
        new Thread(() -> {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(startRow, startCol));

            while (!queue.isEmpty()) {
                Point current = queue.poll();
                int row = current.x, col = current.y;

                if (row < 0 || col < 0 || row >= ROWS || col >= COLS || maze[row][col] == 1 || visited[row][col]) {
                    continue;
                }

                SwingUtilities.invokeLater(() -> {
                    visited[row][col] = true;
                    mazePanel.repaint();
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (row == endRow && col == endCol) {
                    return;
                }

                queue.add(new Point(row - 1, col)); // Up
                queue.add(new Point(row + 1, col)); // Down
                queue.add(new Point(row, col - 1)); // Left
                queue.add(new Point(row, col + 1)); // Right
            }
        }).start();
    }

    private void resetMaze() {
        initializeVisitedArray();
        mazePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayFourteen::new);
    }
}

class MazePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < DayFourteen.ROWS; row++) {
            for (int col = 0; col < DayFourteen.COLS; col++) {
                if (DayFourteen.maze[row][col] == 1) {
                    g.setColor(new Color(157, 191, 160));
                } else if (DayFourteen.visited[row][col]) {
                    g.setColor(new Color(205, 195, 212));
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * DayFourteen.CELL_SIZE, row * DayFourteen.CELL_SIZE, DayFourteen.CELL_SIZE, DayFourteen.CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(col * DayFourteen.CELL_SIZE, row * DayFourteen.CELL_SIZE, DayFourteen.CELL_SIZE, DayFourteen.CELL_SIZE);
            }
        }
    }
}
