package com.example.learningdemo.pojo;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;

import java.io.*;
import java.util.List;

public class XWPFDocTest {
    private static String filePath = "C:\\Users\\zhuangy\\Desktop\\测试水印.docx";

    private static String waterMark = "<w:r><w:rPr><w:noProof/></w:rPr><w:pict w14:anchorId=\"58771E30\"><v:shapetype id=\"_x0000_t136\" coordsize=\"21600,21600\" o:spt=\"136\" adj=\"10800\" path=\"m@7,l@8,m@5,21600l@6,21600e\"><v:formulas><v:f eqn=\"sum #0 0 10800\"/><v:f eqn=\"prod #0 2 1\"/><v:f eqn=\"sum 21600 0 @1\"/><v:f eqn=\"sum 0 0 @2\"/><v:f eqn=\"sum 21600 0 @3\"/><v:f eqn=\"if @0 @3 0\"/><v:f eqn=\"if @0 21600 @1\"/><v:f eqn=\"if @0 0 @2\"/><v:f eqn=\"if @0 @4 21600\"/><v:f eqn=\"mid @5 @6\"/><v:f eqn=\"mid @8 @5\"/><v:f eqn=\"mid @7 @8\"/><v:f eqn=\"mid @6 @7\"/><v:f eqn=\"sum @6 0 @5\"/></v:formulas><v:path textpathok=\"t\" o:connecttype=\"custom\" o:connectlocs=\"@9,0;@10,10800;@11,21600;@12,10800\" o:connectangles=\"270,180,90,0\"/><v:textpath on=\"t\" fitshape=\"t\"/><v:handles><v:h position=\"#0,bottomRight\" xrange=\"6629,14971\"/></v:handles><o:lock v:ext=\"edit\" text=\"t\" shapetype=\"t\"/></v:shapetype><v:shape id=\"PowerPlusWaterMarkObject1584793859\" o:spid=\"_x0000_s2049\" type=\"#_x0000_t136\" style=\"position:absolute;left:0;text-align:left;margin-left:0;margin-top:0;width:470.5pt;height:115pt;rotation:315;z-index:-251657216;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin\" o:allowincell=\"f\" fillcolor=\"silver\" stroked=\"f\"><v:fill opacity=\".5\"/><v:textpath style=\"font-family:&quot;宋体&quot;;font-size:1pt\" string=\"水印\"/></v:shape></w:pict></w:r></xml-fragment>";

    public static void main(String[] args) throws Exception {
        String waterMarkValue = "传入水印";
        addWaterMark(filePath, waterMarkValue);
    }

    /**
     * 添加水印
     * 根据传入的word文档地址给word添加水印，添加完成后在相同路径下生成后缀为_after.docx的文件
     * 保留了源文件。如源文件为C:\tmp\个人合同.docx；添加水印后有水印的文件为C:\tmp\个人合同_after.docx；
     *
     * @param filePath       要添加的水印word
     * @param waterMarkValue 水印字符串
     * @throws IOException
     * @throws XmlException
     */
    public static void addWaterMark(String filePath, String waterMarkValue) throws IOException, XmlException {
        InputStream in = new FileInputStream(new File(filePath));
        XWPFDocument document = new XWPFDocument(in);
        XWPFHeaderFooterPolicy xFooter = new XWPFHeaderFooterPolicy(document);
        XWPFHeader header = xFooter.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
        List<XWPFParagraph> paragraphs = header.getParagraphs();
        for (XWPFParagraph graph : paragraphs) {
            String paraText = graph.getCTP().xmlText();
            //如果已经有水印了，那么就进行替换
            if (paraText.contains("id=\"PowerPlusWaterMarkObject")) {
                replaceWaterMark(graph, waterMarkValue);
            } else {//如果没有水印就添加
                String newParaText = waterMark.replace("水印", waterMarkValue);
                String newText = paraText.replace("</xml-fragment>", newParaText);
                XmlToken token = XmlToken.Factory.parse(newText);
                graph.getCTP().set(token);
            }
        }
        String afterPath = filePath.substring(0, filePath.lastIndexOf(".")) + "_after.docx";
        OutputStream out = new FileOutputStream(new File(afterPath));
        document.write(out);
        out.flush();
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将水印中的文字替换成传进来的字符串
     *
     * @param graph          要替换的段落
     * @param waterMarkValue 水印文字
     * @throws IOException
     * @throws XmlException
     */
    public static void replaceWaterMark(XWPFParagraph graph, String waterMarkValue) throws IOException, XmlException {
        String paraText = graph.getCTP().xmlText();
        if (paraText.contains("id=\"PowerPlusWaterMarkObject")) {//<v:shape id=\"PowerPlusWaterMarkObject
            String beginStr = "string=\"";
            int begin = paraText.indexOf(beginStr) + beginStr.length();
            int end = paraText.indexOf("\"", begin);
            String oldWaterMarkText = paraText.substring(begin, end);
            String newText = paraText.replace("string=\"" + oldWaterMarkText + "\"",
                    "string=\"" + waterMarkValue + "\"");
            XmlToken token = XmlToken.Factory.parse(newText);
            graph.getCTP().set(token);
        }
    }
}

