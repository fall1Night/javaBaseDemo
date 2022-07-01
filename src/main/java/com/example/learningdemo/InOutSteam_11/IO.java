package com.example.learningdemo.InOutSteam_11;

import java.io.*;

/*
*
* 一、
*   Java 的 I/O 大概可以分成以下几类：
    磁盘操作：File
    字节操作：InputStream 和 OutputStream
    字符操作：Reader 和 Writer
    对象操作：Serializable
    网络操作：Socket
    新的输入/输出：NIO
    *
  二、装饰者模式
    Java I/O 使用了装饰者模式来实现。以 InputStream 为例，
    InputStream 是抽象组件；
    FileInputStream 是 InputStream 的子类，属于具体组件，提供了字节流的输入操作；
    FilterInputStream 属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如 BufferedInputStream 为 FileInputStream 提供缓存的功能。
    实例化一个具有缓存功能的字节流对象时，只需要在 FileInputStream 对象上再套一层 BufferedInputStream 对象即可。
    FileInputStream fileInputStream = new FileInputStream(filePath);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    DataInputStream 装饰者提供了对更多数据类型进行输入的操作，比如 int、double 等基本类型。
    *
  三、Reader 与 Writer
    不管是磁盘还是网络传输，最小的存储单元都是字节，而不是字符。但是在程序中操作的通常是字符形式的数据，因此需要提供对字符进行操作的方法。
    InputStreamReader 实现从字节流解码成字符流；
    OutputStreamWriter 实现字符流编码成为字节流。
  四、序列化
    序列化就是将一个对象转换成字节序列，方便存储和传输。
    序列化：ObjectOutputStream.writeObject()
    反序列化：ObjectInputStream.readObject()
    不会对静态变量进行序列化，因为序列化只是保存对象的状态，静态变量属于类的状态。
* */
public class IO {
    public static void main(String[] args) {
        // 扫描目录下所有的文件
        listAllFiles(new File("D:\\TestIO"));

        // 复制文件并修改他的文件名
        try {
            copyFile("D:\\TestIO\\新建 DOC 文档.doc","D:\\TestIO\\testIO\\新建 DOC 文档---复制.doc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("以下测试文本读取信息-----------------------------------------------------------------------------------------------------------------------");
        try {
            readFileContent("D:\\TestIO\\新建文本文档.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 递归地列出一个目录下所有文件
    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFiles(file);
        }
    }

    // 文件复制
    public static void copyFile(String src, String newSrc) throws IOException {
        // 输入流将磁盘文件由字节流转化为可操作的字符流
        FileInputStream in = new FileInputStream(src);
        // 输出流由字符流转化为字节流输出
        FileOutputStream out = new FileOutputStream(newSrc);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(out);
        byte[] buffer = new byte[20 * 1024];
        System.out.println(buffer.length+"字节");
        int cnt;

        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        // FileInputStream.read方法返回读取文件的大小(查看源码可知第一个参数为字节数组作为字节流载体,第二个参数为数据的起始位置,第三个参数为数据的种植位置)
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            // 输出流输出
            out.write(buffer, 0, cnt);
        }

        in.close();
        out.close();
    }


    // 读取文本内容
    public static void readFileContent(String filePath) throws IOException {
        //FileReader extends InputStreamReader extends Reader
        FileReader fileReader = new FileReader(filePath);
        //BufferedReader extends Reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        bufferedReader.close();
    }
}
