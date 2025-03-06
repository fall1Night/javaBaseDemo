package com.example.learningdemo.pojo.newTest;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, XmlException {

        //设置默认值
        String watermark = "hello\n 啊啊啊啊";
        String color = "#d8d8d8";
        String fontSize = "0.5pt";
        //角度
        String rotation = "-30";
        XWPFDocument doc = null;
        InputStream in = new FileInputStream(new File("C:\\Users\\zhuangy\\Desktop\\测试水印.docx"));
        OutputStream out = new FileOutputStream("C:\\Users\\zhuangy\\Desktop\\测试水印-添加水印测试后.docx");

        doc = new XWPFDocument(in);
        addWaterMark(doc, watermark, color, fontSize, rotation);
        doc.write(out);

        in.close();
        out.close();
    }


    /**
     * 添加水印信息
     *
     * @param obj
     * @param watermark
     * @param color
     * @param fontSize
     * @param rotation
     */
    private static void addWaterMark(Object obj, String watermark, String color, String fontSize, String rotation) {
        if (obj instanceof XWPFDocument) {
            XWPFDocument doc = (XWPFDocument) obj;
            DocxUtil.makeFullWaterMarkByWordArt(doc, watermark, color, fontSize, rotation);
        }
    }

}
