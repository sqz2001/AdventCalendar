import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class DayTwentyThree extends JFrame {
    private JPanel puzzlePanel;
    private ArrayList<JButton> tiles = new ArrayList<>();
    private int gridSize = 3;
    private JButton blankTile;

    public DayTwentyThree() {
        setTitle("Day 23 Surprise!");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel for intro text
        JPanel main = new JPanel();
        main.setBackground(new Color(171, 199, 153));
        main.setLayout(new BorderLayout());

        JLabel intro = new JLabel(
                "<html>Hi my love! For day 23 we have a puzzle game! Can you solve it as fast as you can?</html>",
                SwingConstants.CENTER
        );
        intro.setFont(new Font("Cooper Black", Font.PLAIN, 24));
        main.add(intro, BorderLayout.NORTH);
        add(main, BorderLayout.NORTH);

        // Create the puzzle panel
        puzzlePanel = new JPanel(new GridLayout(gridSize, gridSize));
        add(puzzlePanel, BorderLayout.CENTER);

        // Load and split the image
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\kissingcats.jpg");
        Image image = imageIcon.getImage();

        // Create a BufferedImage from the Image
        BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Draw the Image onto the BufferedImage
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // Calculate tile dimensions
        int tileWidth = bufferedImage.getWidth() / gridSize;
        int tileHeight = bufferedImage.getHeight() / gridSize;

        // Create and populate tiles
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (row == gridSize - 1 && col == gridSize - 1) {
                    blankTile = new JButton();
                    tiles.add(blankTile);
                } else {
                    BufferedImage tileImage = bufferedImage.getSubimage(
                            col * tileWidth,
                            row * tileHeight,
                            tileWidth,
                            tileHeight
                    );
                    JButton tile = new JButton(new ImageIcon(tileImage));
                    tile.setText(String.valueOf(row * gridSize + col + 1));
                    tile.setFont(new Font("Cooper Black", Font.PLAIN, 0));
                    tiles.add(tile);
                }
            }
        }

        // Shuffle and add tiles to the panel
        Collections.shuffle(tiles);
        for (JButton tile : tiles) {
            puzzlePanel.add(tile);
            tile.addActionListener(new TileClickListener());
        }

        setVisible(true);
    }

    private class TileClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedTile = (JButton) e.getSource();
            int clickedIndex = tiles.indexOf(clickedTile);
            int blankIndex = tiles.indexOf(blankTile);

            // Swap the clicked tile with the blank tile
            Collections.swap(tiles, clickedIndex, blankIndex);

            // Update the UI
            puzzlePanel.removeAll();
            for (JButton tile : tiles) {
                puzzlePanel.add(tile);
            }
            puzzlePanel.revalidate();
            puzzlePanel.repaint();

            // Check if the puzzle is solved
            if (isSolved()) {
                JOptionPane.showMessageDialog(
                        DayTwentyThree.this,
                        "Congratulations! You've solved the puzzle!",
                        "Puzzle Solved",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < tiles.size() - 1; i++) {
            JButton tile = tiles.get(i);
            String expectedText = String.valueOf(i + 1);
            if (!expectedText.equals(tile.getText())) {
                return false;
            }
        }
        return tiles.indexOf(blankTile) == tiles.size() - 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayTwentyThree::new);
    }
}
