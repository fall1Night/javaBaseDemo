package com.example.learningdemo.InOutSteam_11;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/*
一、
    BIO:Java BIO即Block I/O ， 同步并阻塞的IO。BIO就是传统的java.io包下面的代码实现。

    NIO:NIO 与原来的 I/O 有同样的作用和目的, 他们之间最重要的区别是数据打包和传输的方式。原来的 I/O 以流的方式处理数据，而 NIO 以块的方式处理数据。
    面向流 的 I/O 系统一次一个字节地处理数据。一个输入流产生一个字节的数据，一个输出流消费一个字节的数据。为流式数据创建过滤器非常容易。链接几个过滤器，
    以便每个过滤器只负责单个复杂处理机制的一部分，这样也是相对简单的。不利的一面是，面向流的 I/O 通常相当慢。一个 面向块 的 I/O 系统以块的形式处理数据。
    每一个操作都在一步中产生或者消费一个数据块。按块处理数据比按(流式的)字节处理数据要快得多。但是面向块的 I/O 缺少一些面向流的 I/O 所具有的优雅性和简单性。

    AIO:Java AIO即Async非阻塞，是异步非阻塞的IO。
 */
public class BioTest {

    public static void main(String[] args) {

        //使用BIO实现文件的读取和写入。
        User1 user = new User1();
        user.setName("hollis");
        user.setAge(23);
        System.out.println(user);
        System.out.println("BIO写入-----------------------------------------------");
        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("D:\\TestIO\\新建文本文档333.txt"));
            oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //IOUtils.closeQuietly(oos);
        }
        System.out.println("BIO读取-----------------------------------------------");
        //Read Obj from File
        File file = new File("D:\\TestIO\\新建文本文档333.txt");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User1 newUser = (User1) ois.readObject();
            ois.close();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
           // IOUtils.closeQuietly(ois);
/*
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        }

    }




}
// 调用序列化对象一定还要实现Serializable接口
class User1 implements Serializable{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User1() {
    }

    public User1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}