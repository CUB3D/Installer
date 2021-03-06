import com.sun.corba.se.impl.protocol.giopmsgheaders.FragmentMessage;
import javafx.scene.shape.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Callum on 29/11/2015.
 */
public class InstallerMain
{
    private JButton cancelButton;
    private JButton nextButton;
    private JTextArea textArea1;
    private JPanel content;
    private JLabel mainIcon;

    public JFrame frame;

    public InstallerMain() throws IOException
    {
        frame = new JFrame("Installer");

        frame.setContentPane(content);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setVisible(true);

        Properties p = new Properties();
        p.load(Files.newInputStream(Paths.get("Init.cfg")));

        String iconPath = p.getProperty("Installer.Icon");
        mainIcon.setIcon(new ImageIcon(iconPath));

        String text = "";

        for(String s : Files.readAllLines(Paths.get("InitText.txt")))
        {
            text += s + "\n";
        }

        textArea1.setText(text);

        nextButton.addActionListener((a) -> advance());

        cancelButton.addActionListener(a -> frame.dispose());
    }

    private void advance()
    {
        new ModuleSelection(content, frame);
    }

    public static void main(String[] args) throws IOException
    {
        InstallerMain main = new InstallerMain();
    }
}
