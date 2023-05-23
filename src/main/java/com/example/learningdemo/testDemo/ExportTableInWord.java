package com.example.learningdemo.testDemo;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;

public class ExportTableInWord {


/*    public static void main(String[] args) throws Exception {
        XWPFDocument document = new XWPFDocument();



        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        // 设置页面为横向
        CTPageSz pageSize = sectPr.isSetPgSz()?sectPr.getPgSz(): sectPr.addNewPgSz();


        // 设置页面大小
        pageSize.setW(BigInteger.valueOf( 11906)); //宽度
        pageSize.setH(BigInteger.valueOf(16838)); //高度
        // 设置页面方向为横向
        pageSize.setOrient(STPageOrientation.LANDSCAPE);

//        CTPageMar pgMar = sectPr.isSetPgMar()?sectPr.getPgMar():sectPr.addNewPgMar();
//        pgMar.setTop(BigInteger.valueOf(2470)); //上边距
//        pgMar.setBottom(BigInteger.valueOf(2750)); //下边距
//        pgMar.setLeft(BigInteger.valueOf(1500)); //左边距
//        pgMar.setRight(BigInteger.valueOf(1500)); //右边距



        //设置纸张类型
//        CTPaperSize paperSize = pageSize.addNewPaperSize();
//        pageSize.setVal(STPaperSize.A4);
        // 创建一个段落并设置样式
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run = paragraph.createRun();
        run.setText("标题文本");
        run.setFontSize(22);
        run.setBold(true);
        run.setFontFamily("黑体");
        paragraph.setAlignment(ParagraphAlignment.CENTER);


        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("(主任调度电话:123456)");
        run1.setFontSize(16);
        run1.setFontFamily("宋体");
        paragraph1.setAlignment(ParagraphAlignment.RIGHT);


        XWPFTable table = document.createTable();
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("姓名");

        headerRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
// 添加表头
        headerRow.createCell().setText("年龄");
        headerRow.createCell().setText("课程");
        headerRow.createCell().setText("课程");
        mergeCellsHorizontal(table, 0, 2, 3);

// 合并表头的姓名和年龄单元格

// 添加学生信息和课程
        XWPFTableRow row1 = table.createRow();

        row1.getCell(0).setText("张三");
        for (XWPFParagraph xwpfParagraph : row1.getCell(0).getParagraphs()) {
            List<XWPFRun> runs = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : runs) {
                xwpfRun.setColor("FF0000");
            }
        }
        row1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
        row1.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFParagraph p = row1.getCell(0).getParagraphArray(0);
        p.setAlignment(ParagraphAlignment.CENTER);
        //设置

        row1.getCell(1).setText("18");
//        row1.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        row1.getCell(2).setText("数学");
        row1.getCell(3).setText("60");
        XWPFTableRow row2 = table.createRow();

        row2.getCell(2).setText("语文");
        row2.getCell(3).setText("90");


        mergeCellsVertically(table, 0, 1, 2);
        mergeCellsVertically(table, 1, 1, 2);


        //表格下方内容
        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("             主要事项:1.啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        run2.addBreak();
        XWPFRun run3 = paragraph2.createRun();
        run3.setText("                     2.水水水水水水水水水水水水水水水水水水水");
        run3.addBreak();
        XWPFRun run4 = paragraph2.createRun();
        run4.setText("                     3.水水水水水水水水水水水水水水水水水水水");
//        paragraph2.setAlignment(ParagraphAlignment.RIGHT);


        FileOutputStream out = new FileOutputStream("C:\\Users\\zhuangy\\Desktop\\output1.docx");
        document.write(out);
        out.close();
        document.close();
    }*/

    public static void main(String[] args) throws Exception {
        XWPFDocument document = new XWPFDocument();

// 设置页面大小为A4
        CTDocument1 doc = document.getDocument();
        CTBody body = doc.getBody();
        if (!body.isSetSectPr()) {
            body.addNewSectPr();
        }
        CTSectPr section = body.getSectPr();
        if (!section.isSetPgSz(

        )) {
            section.addNewPgSz();
        }
        CTPageSz pageSize = section.getPgSz();
        pageSize.setW(BigInteger.valueOf(16840)); // 11906 twips = 16840 twentieths of a point = A4 width in twentieths of a point
        pageSize.setH(BigInteger.valueOf(11906)); // 16840 twips = 11906 twentieths of a point = A4 height in twentieths of a point

// 设置页面方向为横向
        if (!section.isSetPgMar()) {
            section.addNewPgMar();
        }
        CTPageMar margin = section.getPgMar();
        margin.setLeft(BigInteger.valueOf(1008)); // 0.5 inch = 1008 twentieths of a point
        margin.setRight(BigInteger.valueOf(1008));
        margin.setTop(BigInteger.valueOf(1440)); // 1 inch = 1440 twentieths of a point
        margin.setBottom(BigInteger.valueOf(1440));
        margin.setHeader(BigInteger.valueOf(720)); // 0.5 inch = 720 twentieths of a point
        margin.setFooter(BigInteger.valueOf(720));

        section.getPgSz().setOrient(STPageOrientation.LANDSCAPE); // 横向页面方向

        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run = paragraph.createRun();
        run.setText("标题文本");
        run.setFontSize(22);
        run.setBold(true);
        run.setFontFamily("黑体");
        paragraph.setAlignment(ParagraphAlignment.CENTER);


        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("(主任调度电话:123456)");
        run1.setFontSize(16);
        run1.setFontFamily("宋体");
        paragraph1.setAlignment(ParagraphAlignment.RIGHT);


        XWPFTable table = document.createTable();
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("姓名");

        headerRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
// 添加表头
        headerRow.createCell().setText("年龄");
        headerRow.createCell().setText("课程");
        headerRow.createCell().setText("课程");
        mergeCellsHorizontal(table, 0, 2, 3);

// 合并表头的姓名和年龄单元格

// 添加学生信息和课程
        XWPFTableRow row1 = table.createRow();

        row1.getCell(0).setText("张三");
        for (XWPFParagraph xwpfParagraph : row1.getCell(0).getParagraphs()) {
            List<XWPFRun> runs = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : runs) {
                xwpfRun.setColor("FF0000");
            }
        }
        row1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
        row1.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFParagraph p = row1.getCell(0).getParagraphArray(0);
        p.setAlignment(ParagraphAlignment.CENTER);
        //设置

        row1.getCell(1).setText("18");
//        row1.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        row1.getCell(2).setText("数学");
        row1.getCell(3).setText("60");
        XWPFTableRow row2 = table.createRow();

        row2.getCell(2).setText("语文");
        row2.getCell(3).setText("90");


        mergeCellsVertically(table, 0, 1, 2);
        mergeCellsVertically(table, 1, 1, 2);


        //表格下方内容
        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setStyle("Heading1"); // 标题样式可以自定义或使用现有的样式
        XWPFRun run2 = paragraph2.createRun();
        run2.setText(" 主要事项:1.啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        run2.addBreak();
        XWPFRun run3 = paragraph2.createRun();
        run3.setText("                2.水水水水水水水水水水水水水水水水水水水");
        run3.addBreak();
        XWPFRun run4 = paragraph2.createRun();
        run4.setText("                     3.水水水水水水水水水水水水水水水水水水水");
//        paragraph2.setAlignment(ParagraphAlignment.RIGHT);


        FileOutputStream out = new FileOutputStream("C:\\Users\\zhuangy\\Desktop\\output1.docx");
        document.write(out);
        out.close();
        document.close();
    }

    /**
     * @Description: 跨列合并
     * table要合并单元格的表格
     * row要合并哪一行的单元格
     * fromCell开始合并的单元格
     * toCell合并到哪一个单元格
     */
    private static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }


    /**
     * @Description: 跨行合并
     * table要合并单元格的表格
     * col要合并哪一列的单元格
     * fromRow从哪一行开始合并单元格
     * toRow合并到哪一个行
     */
    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE~
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

}
