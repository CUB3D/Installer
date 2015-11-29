import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.LayoutQueue;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Callum on 29/11/2015.
 */
public class ModuleSelection
{
    private JButton cancelButton;
    private JButton nextButton;
    private JButton backButton;
    private JPanel content;
    private JTree tree1;

    public ModuleSelection(JFrame frame)
    {
        frame.setContentPane(content);
        frame.pack();
    }

    private void createUIComponents()
    {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Modules");

        loadModules(top);
    }

    private void loadModules(DefaultMutableTreeNode top)
    {
        try
        {
            String text = "";

            for(String s : Files.readAllLines(Paths.get("Modules.json")))
            {
                text += s + "\n";
            }

            JSONObject object = new JSONObject(text);

            JSONObject modules = object.getJSONObject("Modules");

            for(int i = 0; i < modules.names().length(); i++)
            {
                String name = modules.names().getString(i);

                JSONObject object1 = modules.getJSONObject(name);

                boolean isRequired = object1.getBoolean("required");

                System.out.println("Found module: " + name + ", isREquired: " + isRequired);

                DefaultMutableTreeNode catagory = new DefaultMutableTreeNode(name);

                top.add(catagory);
            }

            tree1 = new JTree(top);

            ImageIcon leafIcon = new ImageIcon("Check.png");
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setIcon(leafIcon);
            renderer.setLeafIcon(leafIcon);
            tree1.setCellRenderer(renderer);
        }catch(Exception e) {e.printStackTrace();}
    }
}
