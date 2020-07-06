import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static String FILE_PATH = "C:\\workspace\\git\\edward-class\\";

  public static void main(String[] args) {
    File file = new File("\\text.txt");
    try {
      if(!file.exists()) {
        file.mkdir();
        file.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    ListWritable listWritable = new ListWritable();
    listWritable.add(1);
    listWritable.add(2);
    listWritable.add(3);
    listWritable.add(999);
    try {

      FileOutputStream fos = new FileOutputStream(FILE_PATH + "test.txt");
      DataOutputStream dos = new DataOutputStream(fos);
      listWritable.write(dos);
      dos.close();
      fos.close();

      FileInputStream fis = new FileInputStream(FILE_PATH + "test.txt");
      DataInputStream dis = new DataInputStream(fis);

      listWritable.read(dis);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
