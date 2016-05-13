import javax.swing.*;
import java.util.List;

/**
 * Created by Callum on 29/11/2015.
 */
public class InstallerInstall
{
    private JTextArea textArea1;
    public JProgressBar progressBar1;
    private JButton finishButton;
    private JPanel content;

    public InstallerInstall(JFrame frame, List<String> acceptedModules)
    {
        frame.setContentPane(content);
        frame.pack();

        for (String s : acceptedModules)
        {
            InstallScript.exec(s + ".ins", this);
        }

        finishButton.addActionListener(a -> frame.dispose());
    }

    public void printLine(String s)
    {
        textArea1.setText(textArea1.getText() + s + "\n");
    }

    public void updateProgressBar(int newValue)
    {
        progressBar1.setValue(newValue);
    }
}
