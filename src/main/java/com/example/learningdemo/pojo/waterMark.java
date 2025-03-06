package com.example.learningdemo.pojo;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class waterMark {
    public static void main(String[] args) throws Exception {

        //输入的docx文档
        InputStream in = new FileInputStream(new File("C:\\Users\\zhuangy\\Desktop\\测试水印.docx"));
        XWPFDocument doc = new XWPFDocument(in);

        // the body content
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("The Body:");

        // create header-footer
        XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) headerFooterPolicy = doc.createHeaderFooterPolicy();

        // 水印内容
        headerFooterPolicy.createWatermark("申铁信息");

        // get the default header
        // Note: createWatermark also sets FIRST and EVEN headers
        // but this code does not updating those other headers
        XWPFHeader header = headerFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
        paragraph = header.getParagraphArray(0);

        // get com.microsoft.schemas.vml.CTShape where fill color and rotation is set
        org.apache.xmlbeans.XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(
                new javax.xml.namespace.QName("urn:schemas-microsoft-com:vml", "shape"));

        if (xmlobjects.length > 0) {
            com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape) xmlobjects[0];
            // set fill color
            ctshape.setFillcolor("#d8d8d8");
            // set rotation
            ctshape.setStyle(ctshape.getStyle() + ";rotation:315");
            //System.out.println(ctshape);
        }
        //文件输出地址
        FileOutputStream out = new FileOutputStream("C:\\Users\\zhuangy\\Desktop\\测试水印-添加水印测试后.docx");
        System.out.println("水印添加成功!");
        doc.write(out);
        out.close();
        doc.close();
    }


    /**
     * @Author sxc
     * * @param null
     * @Description
     * @Date 2025/01/13
     */
    private String test(String a, Integer b) {
        return "111";
    }
}
