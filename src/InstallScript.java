import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Callum on 30/11/2015.
 */
public class InstallScript
{
    public static void exec(String filename, InstallerInstall gui)
    {
        Path path = Paths.get(filename);

        try
        {
            BufferedReader reader = Files.newBufferedReader(path);

            String line = "";

            while((line = reader.readLine()) != null)
            {
                execLine(line, gui);
            }
        }catch(Exception e) {e.printStackTrace();}
    }

    private static void execLine(String line, InstallerInstall gui)
    {
        line = line.replaceAll("\\(", " ");
        line = line.replaceAll("\\)", "");
        line = line.trim();

        String[] split = line.split(" ");

        String command = split[0];

        if(command.equals("echo"))
        {
            gui.printLine(parseString(split[1]));
        }

        if(command.equals("setProgress"))
        {
            gui.updateProgressBar(parseInt(split[1]));
        }

        if(command.equals("updateProgress"))
        {
            gui.updateProgressBar(gui.progressBar1.getValue() + parseInt(split[1]));
        }
    }

    private static String parseString(String line)
    {
        return line.replaceAll("\"", "");
    }

    private static int parseInt(String line)
    {
        return Integer.parseInt(line);
    }
}
