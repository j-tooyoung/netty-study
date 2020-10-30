package main.java.com.too.example.fileReadWrite;

import java.io.*;

// bio
public class BIO {

    public static void main(String[] args) throws IOException {

        //
        User user = new User();
        user.setAge(18);
        user.setName("hello");
        System.out.println(user);

        // 写对象到文件
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tmp"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            oos.close();
        }

        // 从文件读对象
        File file = new File("tmp");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User user1 = (User) ois.readObject();
            System.out.println(user1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ois.close();
        }


    }
}
