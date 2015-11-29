import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum on 29/11/2015.
 */
public class TreeRenderer extends DefaultTreeCellRenderer
{
    ImageIcon check = new ImageIcon("Check.png");
    ImageIcon cross = new ImageIcon("Icon.png");

    public List<String> disabledModules = new ArrayList<>();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        if(disabledModules.contains(value.toString()))
        {
            setLeafIcon(cross);
        }
        else
        {
            setLeafIcon(check);
        }

        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    }
}
