import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListWritable implements Writable {

        private List<Integer> buff = null;

        public ListWritable() {
                buff = new ArrayList<Integer>();
        }

        public void add(int value) {
                buff.add(value);
        }

        @Override
        public void read(DataInput in) throws IOException {
                // TODO Auto-generated method stub
                int sizeOfData = 0;
                try {
                        sizeOfData = in.readInt();
                        if (sizeOfData > 0) {
                                buff = new ArrayList<>();
                        }
                        for (int i = 0; i < sizeOfData; i++) {
                                buff.add(in.readInt());
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                System.out.println("read complete: " + buff.toString());
        }

        @Override
        public void write(DataOutput out) throws IOException {
                // TODO Auto-generated method stub
                out.writeInt(buff.size());
                for (Integer num : buff) {
                        out.writeInt(num);
                }
        }

        public void readFromDisk(String filePath) {
                try {
                        FileInputStream fis = new FileInputStream(
                            filePath);
                        DataInputStream dis = new DataInputStream(fis);
                        read(dis);
                        dis.close();
                        fis.close();

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void writeToDisk(String filePath) {
                File file = new File(filePath);
                try {

                        if (!file.createNewFile()) {
                                System.out.println("file already exists");

                                if(file.delete()) {
                                        System.out.println("file deleted");
                                }
                                if(file.createNewFile()) {
                                        System.out.println("new file generated");
                                }
                        } else {
                                System.out.println("new file generated");
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                FileOutputStream fos = null;
                try {
                        fos = new FileOutputStream(filePath);
                        DataOutputStream dos = new DataOutputStream(fos);
                        write(dos);
                        dos.close();
                        fos.close();

                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

        private byte[] serialize(DataOutput out) {
                return null;
        }

        private Object deserialize(DataInput in) {

                return null;
        }

}
