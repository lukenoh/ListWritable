import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

        public static String FILE_PATH = "C:\\workspace\\git\\edwards-class\\ListWritable\\";
//public static String FILE_PATH = "/";
        public static void main(String[] args) {
                ListWritable listWritable = new ListWritable();
                listWritable.add(1);
                listWritable.add(2);
                listWritable.add(3);
                listWritable.add(999);

                listWritable.writeToDisk(FILE_PATH + "serialized.ser");
                listWritable.readFromDisk(FILE_PATH + "serialized.ser");

        }
}
