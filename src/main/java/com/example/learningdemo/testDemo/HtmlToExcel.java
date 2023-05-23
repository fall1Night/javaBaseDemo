package com.example.learningdemo.testDemo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author sxc
 * @Description 读取excel中富文本内容为富文本标签内容
 * @Date 2023/04/18
 */
public class HtmlToExcel {
    public static void main(String[] args) throws IOException {
        // 读取HTML文件
//        File input = new File("table.html");
//        Document doc = Jsoup.parse(input, "UTF-8", "");

        Document doc = Jsoup.parse("<html> <head></head> <body> <table> <tbody> <tr> <th>Name</th> <th>Age</th> </tr> <tr> <td style=\"color: red;\">John</td> <td>25</td> </tr> <tr> <td style=\"background-color: blue; color: white;\">这是红色的文本。</span> <td style=\"color: red;\">30</td> </tr> </tbody> </table> </body> </html>");


        // 创建Excel工作簿
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();

        // 处理表格
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements columns = row.select("td");
            for (int j = 0; j < columns.size(); j++) {
                Element column = columns.get(j);
                // 获取单元格内容
                String cellValue = column.text().trim();
                // 获取单元格样式
                String cellStyle = column.attr("style");
                // 解析样式
                Font font = wb.createFont();
                CellStyle style = wb.createCellStyle();
                for (String s : cellStyle.split(";")) {
                    if (s.startsWith("font-weight")) {
                        font.setBold(true);
                    } else if (s.startsWith("font-style")) {
                        font.setItalic(true);
                    } else if (s.startsWith("font-size")) {
                        int fontSize = Integer.parseInt(s.split(":")[1].trim().replace("px", ""));
                        font.setFontHeightInPoints((short) (fontSize * 0.75)); // 将像素转换为Excel字体大小
                    } else if (s.startsWith("color")) {
                        String color = s.split(":")[1].trim();
                        font.setColor(IndexedColors.valueOf(color.toUpperCase()).getIndex());
                    } else if (s.startsWith("background-color")) {
                        String color = s.split(":")[1].trim();
                        style.setFillForegroundColor(IndexedColors.valueOf(color.toUpperCase()).getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    } else if (s.startsWith("border")) {
                        String[] borderStyle = s.split(":")[1].trim().split(" ");
                        for (String bs : borderStyle) {
                            String[] bsArr = bs.split("-");
                            String borderSide = bsArr[1];
                            if (bsArr.length > 2) {
                                borderSide += "-" + bsArr[2];
                            }
                            String borderStyleStr = bsArr[0];
                            if (borderStyleStr.equals("none")) {
                                continue;
                            }
                            BorderStyle borderStyleEnum = BorderStyle.valueOf(borderStyleStr.toUpperCase());
                            if (borderSide.equals("left")) {
                                style.setBorderLeft(borderStyleEnum);
                            } else if (borderSide.equals("right")) {
                                style.setBorderRight(borderStyleEnum);
                            } else if (borderSide.equals("top")) {
                                style.setBorderTop(borderStyleEnum);
                            } else if (borderSide.equals("bottom")) {
                                style.setBorderBottom(borderStyleEnum);
                            }
                        }
                    } else if (s.startsWith("text-align")) {
                        String align = s.split(":")[1].trim();
                        if (align.equals("left")) {
                            style.setAlignment(HorizontalAlignment.LEFT);
                        } else if (align.equals("center")) {
                            style.setAlignment(HorizontalAlignment.CENTER);
                        } else if (align.equals("right")) {
                            style.setAlignment(HorizontalAlignment.RIGHT);
                        }
                    }
                }
                // 在Excel中写入单元格
                Row excelRow = sheet.getRow(i);
                if (excelRow == null) {
                    excelRow = sheet.createRow(i);
                }
                Cell excelCell = excelRow.createCell(j);
                excelCell.setCellValue(cellValue);
                excelCell.setCellStyle(style);
                excelCell.getCellStyle().setFont(font);
            }
        }

        // 保存Excel文件
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\zhuangy\\Desktoptable.xlsx");
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }
}