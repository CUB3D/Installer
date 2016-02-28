import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by cub3d on 28/02/16.
 */
public class InstallUtils
{
    public static void extract(String zip, String outFolder) throws IOException
    {
        ZipFile zipFile = new ZipFile(new File(zip));
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();

        Path outFolderPath = Paths.get(outFolder);

        Files.createDirectories(outFolderPath);

        while(entries.hasMoreElements())
        {
            ZipEntry zipEntry = entries.nextElement();

            if(zipEntry.isDirectory())
            {
                Path directoryPath = outFolderPath.resolve(zipEntry.getName());

                Files.createDirectories(directoryPath);
            }
            else
            {
                InputStream zipEntryStream = zipFile.getInputStream(zipEntry);

                Path outPath = outFolderPath.resolve(zipEntry.getName());

                Files.createFile(outPath);

                OutputStream outFileOutputStream = Files.newOutputStream(outPath);

                while(zipEntryStream.available() > 0)
                {
                    outFileOutputStream.write(zipEntryStream.read());
                }


                zipEntryStream.close();
            }
        }

        zipFile.close();
    }
}
