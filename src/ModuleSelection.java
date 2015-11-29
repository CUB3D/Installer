import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.tree.Tree;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.LayoutQueue;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
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
    private JButton dissableButton;
    private JButton enableButton;

    public ModuleSelection(JFrame frame)
    {
        frame.setContentPane(content);
        frame.pack();

        enableButton.addActionListener((a) -> onEnable());
    }

    private void onEnable()
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();

        String name = node.getUserObject().toString();

        ((TreeRenderer)tree1.getCellRenderer()).disabledModules.add(name);

        tree1.invalidate();
    }

    private void createUIComponents()
    {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Modules");

        loadModules(top);

        tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
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

                catagory.setAllowsChildren(false);

                top.add(catagory);
            }

            tree1 = new JTree(top);

            TreeRenderer renderer = new TreeRenderer();
            tree1.setCellRenderer(renderer);
        }catch(Exception e) {e.printStackTrace();}
    }
}
