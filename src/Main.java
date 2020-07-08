import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Main {


  public static void main(String[] args) {
    ListWritable listWritable = new ListWritable();
    listWritable.add(1);
    listWritable.add(2);
    listWritable.add(3);
    listWritable.add(999);

//    byte[] bytes = serialize(listWritable);

//    ListWritable l = new ListWritable();
//    deserialize(bytes, l);
//
//    for (int i = 0; i < l.size(); i++) {
//      System.out.println(l.get(i));
//    }



    writeToDisk("serialize.se", listWritable);
    ListWritable w = (ListWritable)readFromDisk("serialize.se");

    for(int i = 0; i < w.size(); i++) {
      System.out.println(w.get(i));
    }

  }

  public static byte[] serialize(Writable w) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DataOutput output = new DataOutputStream(out);
    try {
      w.write(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out.toByteArray();

  }

  public static void deserialize(byte[] bytes, Writable obj) {
    DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
    try {
      obj.read(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeToDisk(String filePath, Writable w) {
    File file = new File(filePath);
    try {

      if (!file.createNewFile()) {
        System.out.println("file already exists");

        if (file.delete()) {
          System.out.println("file deleted");
        }
        if (file.createNewFile()) {
          System.out.println("new file generated");
        }
      } else {
        System.out.println("new file generated");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(serialize(w));
      fos.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Writable readFromDisk(String filePath) {
    try {
      File file = new File(filePath);
      if(!file.exists()) {
        System.out.println("file does not exist!");
        return null;
      }

      byte[] bytes = Files.readAllBytes(file.toPath());
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream dis = new DataInputStream(bais);
      ListWritable l = new ListWritable();
      deserialize(bytes, l);
      dis.close();

      return l;

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
