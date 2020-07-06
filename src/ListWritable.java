import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
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

                byte[] bytes = new byte[1024];

                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                try {
                        buff = (ArrayList) ois.readObject();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }

                System.out.println("read complete: " + buff.toString());
        }

        @Override
        public void write(DataOutput out) throws IOException {
                // TODO Auto-generated method stub
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);

                oos.writeObject(buff);

                out.write(baos.toByteArray());
        }

}
