package dialogue;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class StoryDialogue extends JFrame implements Dialogue {
    private ArrayList<String> dialogues;
    private int currentIndex;
    private JTextArea textArea;
    private JButton nextButton;
    private Timer timer;
    private int charIndex;

    public StoryDialogue() {
        dialogues = new ArrayList<>();
        currentIndex = 0;

        // text dialogues details
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton, BorderLayout.SOUTH);

        loadDialogues();

        setVisible(true);
    }

    public boolean hasNext() {
        return currentIndex < dialogues.size();
    }


    public void loadDialogues() {
        // adding dialogues

        // narration
        dialogues.add("In a sunlit forest, the air is alive with the sounds of rustling leaves and chirping birds. Two brothers stand amidst towering trees, their playful rivalry evident in their excited voices. The challenge is simple yet spirited: whoever finds the largest branch wins.");
        dialogues.add("Andres darts between trees, his quick movements stirring the undergrowth. His eyes scan the forest floor eagerly, searching for the perfect branch to claim victory. Ethan, in contrast, moves with measured precision, his gaze focused and determined. He inspects the fallen limbs around him, calculating their size and weight with a practiced eye.");
        dialogues.add("The forest is their arena, each step crunching against the carpet of dry leaves and twigs. Jacob shouts in triumph as he hoists a sizable branch, his grin wide and confident. Moments later, Hakobe emerges, hauling an even larger, more impressive find over his shoulder.");
        dialogues.add("The brothers converge at the clearing, their laughter mingling with the sounds of the woods, as they compare their discoveries and revel in their shared moment of spirited competition.");

    }

    @Override
    public void display() {
        if (hasNext()) {
            startDisplayingDialogue();
        } else {
            textArea.setText("No dialogues available.");
            nextButton.setEnabled(false);
        }
    }



    private void startDisplayingDialogue() {
        textArea.setText("");
        charIndex = 0;
        String currentDialogue = getNextDialogue();

        if (currentDialogue != null) {
            timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayNextCharacter(currentDialogue);
                }
            });
            timer.start();
        }
    }

    private String getNextDialogue() {
        if (hasNext()) {
            return dialogues.get(currentIndex++);
        }
        return null;
    }

    private void displayNextCharacter(String currentDialogue) {
        if(charIndex < currentDialogue.length()) {
            textArea.append(String.valueOf(currentDialogue.charAt(charIndex++)));
        } else {
            timer.stop();
            nextButton.setEnabled(true);
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(false);

            if (hasNext()) {
                startDisplayingDialogue();
            } else {
                textArea.setText("..."); // could state its the end
                nextButton.setEnabled(false);
            }
        }
    }

}

