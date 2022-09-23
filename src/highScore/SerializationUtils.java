package highScore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeMap;

public class SerializationUtils {
    /**
     * Serialize the given object to the file
     */
    public static void serialize(Object object, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static ArrayList<Player> deserialize(String fileName) throws IOException, ClassNotFoundException {
        Object object = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
            ois.close();
        } catch (EOFException e) {

        }
        return (ArrayList<Player>) object;
    }


}