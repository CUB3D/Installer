import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by Callum on 29/11/2015.
 */
public class ModuleSelection
{
    private JButton cancelButton;
    private JButton nextButton;
    private JButton backButton;
    private JPanel content;
    private JTable table1;

    private JFrame frame;

    public ModuleSelection(JFrame frame)
    {
        frame.setContentPane(content);
        frame.pack();
        nextButton.addActionListener((a) -> onNext());

        this.frame = frame;
    }

    private void onNext()
    {
        new InstallerInstall(frame);
    }

    private void createUIComponents()
    {
        loadModules();
    }

    private void loadModules()
    {
        String[] columnNames = {"Selected", "Name"};
        Object[][] data = new Object[0][2];

        TableModel model = new DefaultTableModel(data, columnNames)
        {
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                if (getColumnName(columnIndex).equals("Selected"))
                {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 0 && !getValueAt(row, 1).toString().endsWith("Required");
            }
        };

        table1 = new JTable(model);

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

                Object[] moduleData = {isRequired, name + (isRequired ? " - Required" : "")};

                ((DefaultTableModel)table1.getModel()).addRow(moduleData);
            }
        }catch(Exception e) {e.printStackTrace();}
    }
}
