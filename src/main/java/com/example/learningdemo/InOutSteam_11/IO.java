package com.example.learningdemo.InOutSteam_11;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
    * Serializable
    序列化的类需要实现 Serializable 接口，它只是一个标准，没有任何方法需要实现，但是如果不去实现它的话而进行序列化，会抛出异常。
    * transient
    transient 关键字可以使一些属性不会被序列化。
    ArrayList 中存储数据的数组 elementData 是用 transient 修饰的，因为这个数组是动态扩展的，并不是所有的空间都被使用，因此就不需要所有的内容都被序列化。
    通过重写序列化和反序列化方法，使得可以只序列化数组中有内容的那部分数据。
  五、网络操作
    Java 中的网络支持：
    InetAddress：用于表示网络上的硬件资源，即 IP 地址；
    URL：统一资源定位符；
    Sockets：使用 TCP 协议实现网络通信；
    Datagram：使用 UDP 协议实现网络通信。
  六、Linux 5种IO模型
    阻塞式IO模型:最传统的一种IO模型，即在读写数据过程中会发生阻塞现象。当用户线程发出IO请求之后，内核会去查看数据是否就绪，如果没有就绪就会等待数据就绪，
    而用户线程就会处于阻塞状态，用户线程交出CPU。当数据就绪之后，内核会将数据拷贝到用户线程，并返回结果给用户线程，用户线程才解除block状态。

    非阻塞IO模型:当用户线程发起一个read操作后，并不需要等待，而是马上就得到了一个结果。如果结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送read操作。
    一旦内核中的数据准备好了，并且又再次收到了用户线程的请求，那么它马上就将数据拷贝到了用户线程，然后返回。
    所以事实上，在非阻塞IO模型中，用户线程需要不断地询问内核数据是否就绪，也就说非阻塞IO不会交出CPU，而会一直占用CPU。---存在问题,因需不断询问数据是否准备就绪CPU占用率很高

    IO复用模型:NIO用的就是此模型,会有一个线程不断去轮询多个socket的状态，只有当socket真正有读写事件时，才真正调用实际的IO读写操作。
    因为在多路复用IO模型中，只需要使用一个线程就可以管理多个socket，系统不需要建立新的进程或者线程，也不必维护这些线程和进程，
    并且只有在真正有socket读写事件进行时，才会使用IO资源，所以它大大减少了资源占用。

    信号驱动IO模型:在信号驱动IO模型中，当用户线程发起一个IO请求操作，会给对应的socket注册一个信号函数，然后用户线程会继续执行，当内核数据就绪时会发送一个信号给用户线程，
    用户线程接收到信号之后，便在信号函数中调用IO读写操作来进行实际的IO请求操作。
-----------------------------------------以上4个为同步I/O模型-----------------------------------------------------------------------------------------------------------------
    异步IO模型:异步IO模型是比较理想的IO模型，在异步IO模型中，当用户线程发起read操作之后，立刻就可以开始去做其它的事。
    而另一方面，从内核的角度，当它受到一个asynchronous read之后，它会立刻返回，说明read请求已经成功发起了，因此不会对用户线程产生任何block。
    然后，内核会等待数据准备完成，然后将数据拷贝到用户线程，当这一切都完成之后，内核会给用户线程发送一个信号，告诉它read操作完成了。
    也就说用户线程完全不需要知道实际的整个IO操作是如何进行的，只需要先发起一个请求，当接收内核返回的成功信号时表示IO操作已经完成，可以直接去使用数据了。
* */
public class IO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 扫描目录下所有的文件
        List<File> fileList = new ArrayList<>();
        listAllFiles(new File("D:\\TestIO"), fileList);
        for (File file : fileList) {
            Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class).creationTime();
            System.out.println("111");
        }


        System.out.println("1111");
        // 复制文件并修改他的文件名
//        try {
//            copyFile("D:\\TestIO\\新建 DOC 文档.doc", "D:\\TestIO\\testIO\\新建 DOC 文档---复制.doc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("以下测试文本读取信息-----------------------------------------------------------------------------------------------------------------------");
//        try {
//            readFileContent("D:\\TestIO\\新建文本文档.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("以下为测试可序列化类,实现了Serializable类-----------------------------------------------------------------------------------------------------------------------");
//        Integer[] data = new Integer[]{1, 2};
//        serializableClass a1 = new serializableClass(123, "abc", data);
//        String objectFile = "D:\\TestIO\\新建文本文档 (2).txt";
//        // 此处因为FileOutputStream只支持byte[]字符流格式而我们需要序列化对象所以使用ObjectOutputStream
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile));
//        // 序列化输出文件
//        objectOutputStream.writeObject(a1);
//        objectOutputStream.close();
//
//        // 输入流读取文件中序列化后的对象
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(objectFile));
//        serializableClass a2 = (serializableClass) objectInputStream.readObject();
//        objectInputStream.close();
//        System.out.println(a2);
//
//
//
//
//        System.out.println("以下为网络I/O-----------------------------------------------------------------------------------------------------------------------");
//        URL url = new URL("http://www.baidu.com");
//
//        /* 字节流 */
//        InputStream is = url.openStream();
//
//        /* 字符流 */
//        InputStreamReader isr = new InputStreamReader(is, "utf-8");
//
//        /* 提供缓存功能 */
//        BufferedReader br = new BufferedReader(isr);
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        br.close();
    }

    // 递归地列出一个目录下所有文件
    public static void listAllFiles(File dir, List<File> fileList) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            fileList.add(dir);
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFiles(file, fileList);
        }
    }

    // 文件复制
    public static void copyFile(String src, String newSrc) throws IOException {
        // 输入流将磁盘文件由字节流转化为可操作的字符流
        FileInputStream in = new FileInputStream(src);
        // 输出流由字符流转化为字节流输出
        FileOutputStream out = new FileOutputStream(newSrc);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
        byte[] buffer = new byte[20 * 1024];
        System.out.println(buffer.length + "字节");
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

    // 序列化的类需要实现 Serializable 接口,它只是一个标准，没有任何方法需要实现，但是如果不去实现它的话而进行序列化，会抛出异常。
    private static class serializableClass implements Serializable {

        private int x;
        private String y;
        // transient 关键字可以使数组不会被序列化。
        private transient Object[] elementData;

        serializableClass(int x, String y, Object[] elementData) {
            this.x = x;
            this.y = y;
            this.elementData = elementData;
        }

        @Override
        public String toString() {
            return "serializableClass{" +
                    "x=" + x +
                    ", y='" + y + '\'' +
                    ", elementData=" + Arrays.toString(elementData) +
                    '}';
        }



        /*        　*//**
         * 将 ArrayList 实例的状态保存到流（即序列化它）。*//*
        private void writeObject(java.io.ObjectOutputStream s)
                throws java.io.IOException{
            // 写出元素计数和任何隐藏的东西
            int expectedModCount = modCount;
            s.defaultWriteObject(); // 写出当前类的所有非静态字段(non-static)和非瞬态字段(non-transient)到ObjectOutputStream

            // 将size写出到ObjectOutputStream
            s.writeInt(size);

            // 因为ArrayList是可扩容的,在添加元素时,可能会扩容,这个时候会存在一些没有使用的空间,所以采用这种方式,来节约空间和减少序列化的时间
            for (int i=0; i<size; i++) {　　// size代表数组中储存的元素的个数
                s.writeObject(elementData[i]); // 有序的将elementData中已使用的元素读出到流中
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
　　*//**
         * 从流中重构 ArrayList 实例（即，对其进行反序列化）。
         *//*
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            elementData = EMPTY_ELEMENTDATA;

            // 读入size, 和所有隐藏的东西
            s.defaultReadObject();

            // 读入容量
            s.readInt(); // ignored

            if (size > 0) {
                // 就像clone（）一样，根据大小而不是容量来分配数组
                ensureCapacityInternal(size);

                Object[] a = elementData;
                // 按正确的顺序读入所有元素。
                for (int i=0; i<size; i++) {
                    a[i] = s.readObject();
                }
            }
        }*/
    }
}
