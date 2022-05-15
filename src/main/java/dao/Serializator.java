package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Serializator.
 *
 * @param <T> the type parameter
 */
public class Serializator<T> {
    /**
     * Read list.
     *
     * @param path the path
     * @return the list
     */
    public List<T> read(String path) {
        List<T> objects = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            if (inputStream.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(inputStream);
                Object readObject = in.readObject();
                while (readObject != null) {
                    T object = (T) readObject;
                    objects.add(object);
                    if (inputStream.available() <= 0) {
                        break;
                    }
                    readObject = in.readObject();
                }
                in.close();
                return objects;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read one object t.
     *
     * @param path the path
     * @return the t
     */
    public T readOneObject(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            if (fileInputStream.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                T readObject = (T) in.readObject();
                in.close();
                return readObject;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Write.
     *
     * @param path   the path
     * @param object the object
     */
    public void write(String path, T object) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write.
     *
     * @param path    the path
     * @param objects the objects
     */
    public void write(String path, List<T> objects) {
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objects.forEach(object -> {
                try {
                    objectOutputStream.writeObject(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
