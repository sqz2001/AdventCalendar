import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

// Day four is a memory jar.
public class DayFour extends JFrame {
    public DayFour() {
        setTitle("Day 4 Surprise!");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the image and clickable image
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        // Create a panel for the text with right alignment
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Right-aligned layout

        // Add text to the text panel
        JLabel textLabel = new JLabel(
                "<html>Hello my love! You're probably wondering what this image is! Let me explain :) <br>" +
                        "Today's surprise is a memory jar, hence the photo of Ashton Kutcher. <br>" +
                        "Specifically, it's him in 'The Butterfly Effect' which we watched together. <br>" +
                        "Felt like it was fitting since the film deals with memories from the past. <br>" +
                        "If you click the image, you'll be prompted to download a .jar file. <br>" +
                        "You need to run the file to see what's in the memory jar. </html>");
        // Set maximum size for textPanel to prevent it from growing too much
        textPanel.setMaximumSize(new Dimension(500, 120)); // Limit height to reduce space
        textPanel.add(textLabel); // Add text label to text panel
        panel.add(textPanel); // Add text panel to main panel

        // Load the jar image
        ImageIcon originalJarImage = new ImageIcon("C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\project photos\\Memory jar.jpg"); // Change this to your jar image path

        // Original dimensions
        int originalWidth = originalJarImage.getIconWidth();
        int originalHeight = originalJarImage.getIconHeight();

        // Calculate the new dimensions while maintaining the aspect ratio
        int newWidth = 300; // Desired width
        int newHeight = (originalHeight * newWidth) / originalWidth; // Calculate height to maintain aspect ratio

        // Resize the image
        Image resizedImage = originalJarImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon jarImage = new ImageIcon(resizedImage);

        // New label for image
        JLabel imageLabel = new JLabel(jarImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Detect if jar is clicked
        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadJarFile();
            }
        });

        // Add the image label to the main panel
        panel.add(imageLabel); // Add image label to main panel

        // Add the panel to the frame
        add(panel);
        setVisible(true);
    }

    private void downloadJarFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where to save the file");
        fileChooser.setSelectedFile(new File("MemoryJar.jar")); // Default file name

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String jarFileUrl = "file:///C:/Users/sarah/IdeaProjects/Advent%20Calendar/src/resources/Memory%20Jar.jar"; // JAR file URL

            try {
                // Downloading the JAR file
                URL url = new URL(jarFileUrl);
                try (var in = url.openStream(); var out = new java.io.FileOutputStream(fileToSave)) {
                    byte[] buffer = new byte[2048];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                JOptionPane.showMessageDialog(this,
                        "Download completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error downloading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DayFour::new);
    }
}
