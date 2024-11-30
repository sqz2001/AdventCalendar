import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// day two surprise is a screenplay I wrote a long time ago
// the format of this day is in screenplay formatting for style purposes

public class DayTwo extends JFrame {
    public DayTwo() {
        setTitle("Day 2 Surprise!");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTextPane for screenplay formatting
        JTextPane screenplayPane = new JTextPane();
        screenplayPane.setContentType("text/html");
        screenplayPane.setEditable(false);
        screenplayPane.setFont(new Font("Courier", Font.PLAIN, 12));

        // HTML for screenplay formatting
        String screenplayText = "<html>"
                + "<div style='font-size: 12px; font-weight: bold; text-align: left;" +
                "margin-left: 40px; margin-right: 40px;'>"
                + "INT. BEDROOM - DAY </div><br>"

                + "<div style='font-size: 12px; text-align: left;" +
                "margin-left: 40px; margin-right: 40px;'>"
                + "MARK is at his computer, clicking through an advent calendar his girlfriend made him.</div><br>"

                + "<div style='font-size: 12px; text-align: center;'>"
                + "SARAH</div><br>"

                + "<div style='font-size: 12px;  text-align: left;" +
                " margin-left: 150px; margin-right: 150px;'>"
                + "Hello my love! Yesterday you got a picture, today let's switch it up. A while ago, " +
                "you asked to see a specific screenplay I wrote. "
                + "This isn't 'Lego Flowers,' but I stumbled across this one in Google Drive. "
                + "It's something I wrote in uni before I got Final Draft and before I took the film class. "
                + "I re-read it and kind of really like it. I would love to write with you someday. "
                + "I feel like we could write a really good film together."
                + "</div>"
                + "</html>";

        screenplayPane.setText(screenplayText);

        // button to download/view the PDF
        JButton pdfButton = new JButton("View 'Leap of Faith'");

        // Action listener for the button
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // path to screenplay
                File pdfFile = new File(
            "C:\\Users\\sarah\\IdeaProjects\\Advent Calendar\\src\\resources\\Leap of Faith Screenplay.pdf");

                if (pdfFile.exists()) {
                    try {
                        // Open the PDF file using the default PDF viewer
                        Desktop.getDesktop().open(pdfFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Unable to open the PDF file.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The file does not exist.",
                            "File Not Found",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout for message and button
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JScrollPane(screenplayPane), BorderLayout.CENTER);
        contentPanel.add(pdfButton, BorderLayout.SOUTH);

        add(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DayTwo();
    }
}
