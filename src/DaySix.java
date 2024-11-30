import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DaySix extends JFrame {

    private HashMap<Integer, String> inputs = new HashMap<>();
    private int currentIndex = 1;

    private String[] prompts = {
            "Enter a noun: ",
            "Enter a verb: ",
            "Enter a noun: ",
            "Enter a noun: ",
    };

    private JLabel introLabel = new JLabel("Hello my love! Here's a MadLibs game for today's surprise :D");
    private JTextField inputField = new JTextField(15); // make the input box 15 chars wide
    private JLabel promptLabel = new JLabel(prompts[0]);

    public DaySix() {
        setTitle("Day 6 Surprise!");
        setSize(500, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        introLabel.setFont(new Font("Serif", Font.BOLD, 16));
        introLabel.setForeground(new Color(128, 128, 128));
        add(introLabel, BorderLayout.NORTH); // intro label at top
        add(promptLabel, BorderLayout.CENTER); // prompt in center
        add(inputField, BorderLayout.SOUTH);  // input box at bottom

        // create next button
        JButton nextButton = new JButton("Next");
        add(nextButton, BorderLayout.EAST);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeInput();
            }
        });
        setVisible(true);
    }

    private void storeInput() {

        String userInput = inputField.getText().trim(); //get rid of extra blank space at end

        inputs.put(currentIndex, userInput); // store input
        inputField.setText(""); //clear input field

        if (currentIndex < prompts.length) {
            currentIndex++;
            promptLabel.setText(prompts[currentIndex - 1]); // currentIndex - 1 = prompts[0]
        } else {
            showMadLib();
        }
    }

    private void showMadLib() {
        String madLib = String.format(
                "Robert and Dale sit at the kitchen table of their upper-middle-class home. " +
                        "They eat %s. \n" +
                        "Dale: “We’re men, dad. We %s. We talk about %s. " +
                        "We go on riverboat gambling trips. We make our own %s. " +
                        "And now that's all wrecked.\"" ,
                inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4)
        );
        JOptionPane.showMessageDialog(this, madLib, "Step Brothers Mad Lib", JOptionPane.INFORMATION_MESSAGE);
    }
}
