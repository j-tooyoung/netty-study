package main.java.com.too.example.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadWriteDemo {

    public static void main(String[] args) throws IOException {

        String filepath = "D:\\log.txt";
        String content = "too哈哈";
        // 写文件
//        InputStream fileInputStream = new FileInputStream("D:\\log.txt");
        FileWriter fileWriter = new FileWriter(filepath, true);
        fileWriter.write(content);
        fileWriter.close();

        // 读文件
        FileReader fileReader = new FileReader(filepath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer buffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str + "\n");
        }
        fileReader.close();
        bufferedReader.close();
        System.out.println(buffer.toString());

        // java1.7 nio
//        Files.write(Paths.get(filepath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        // 读文件
        byte[] data = Files.readAllBytes(Paths.get(filepath));
        System.out.println(new String(data, StandardCharsets.UTF_8));

    }
}
