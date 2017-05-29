package com.application.data2pdf;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.application.entity.po.EnrollMajorEntity;
import com.application.entity.po.EnrollMajorItemEntity;
import com.application.entity.po.EnrollStudentEntity;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

public class EnrollExport2PDFUtil {
	public static final String contextPath = ServletActionContext.getServletContext().getRealPath("/");
	public static final String destPath =contextPath+"/enrollExport2PDF/";
	public static void main(String args[]) throws Exception {
//	        new EnrollExport2PDFUtil().createEnrollPDf(new EnrollStudentEntity());
	    }
	 
	 
	public void createEnrollPDf(PdfWriter writer,EnrollStudentEntity enroll,EnrollMajorEntity major,List<EnrollMajorItemEntity> majorItemList) throws Exception{
//		String filePath = destPath+enroll.getIdentify()+".pdf";
//		File file = new File(filePath);
//        file.getParentFile().mkdirs();
//		 PdfWriter writer = new PdfWriter(filePath);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);
        float cellHeight = 55;
        float noticeFontSize = 14;
        //Add paragraph to the document
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
        Table table = new Table(6);
        table.setWidthPercent(100);
        table.setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        Cell cell = new Cell(1,6).add(new Paragraph("武汉东湖学院"+Calendar.getInstance().get(Calendar.YEAR)+"年\"专升本\"考试").setFont(font).setFontSize(25));
        table.addHeaderCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("准     考     证").setFont(font).setFontSize(32));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("准考证号").setFont(font).setFontSize(noticeFontSize));
        cell.setHeight(cellHeight);
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(enroll.getExamCode()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("姓名").setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(enroll.getName()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("性别").setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(enroll.getSexName()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("报考专业").setFont(font).setFontSize(noticeFontSize));
        cell.setHeight(cellHeight);
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(enroll.getTargetMajor()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("考试日期").setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(major.getExamDate()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
      
        Image pic = new Image(ImageDataFactory.create(contextPath+enroll.getPicPath()));
        pic.setWidth(150);
        pic.setHeight(180);
        cell = new Cell(3,2).add(new Paragraph().add(pic));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("考试科目").setFont(font).setFontSize(noticeFontSize));
        cell.setHeight(cellHeight);
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(0).getName()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(1).getName()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(2).getName()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("考试时间").setFont(font).setFontSize(noticeFontSize));
        cell.setHeight(cellHeight);
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(0).getExamTime()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(1).getExamTime()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph(majorItemList.get(2).getExamTime()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,1).add(new Paragraph("考试地点").setFont(font).setFontSize(noticeFontSize));
        cell.setHeight(cellHeight);
        table.addCell(cell);
        
        cell = new Cell(1,5).add(new Paragraph(enroll.getExamRoom()).setFont(font).setFontSize(noticeFontSize));
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("考证注意事项").setFont(font).setFontSize(30));
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("一 、考生凭准考证、身份证入室对号入座，准考证放在课桌左上角，以便查对，考试响铃开始答题。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("二、考生除自带必要的文具用品（钢笔、签字笔、圆珠笔、橡皮擦）外，其他物品一律不得带入考场，艺术类考生自带绘画工具。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("三、各科考前15分钟打预备铃。开考15分钟后，不得进入考场；考试开始30分钟内，不得交卷出考场；交卷后不得在考场附近逗留、谈论。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("四、答题应写在指定答题区域内，不得写在密封线以外或草稿纸上，否则一律无效。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("五、考生除遇试卷分发错误或缺页、缺题和字迹模糊等问题提问外，其他不准提问。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("六、考生必须严格遵守考试规则，不准吸烟、左顾右盼，凡是在考试中夹带、传接答案、交换答案、找人代考、抄袭他人答案、带走他人答案、携带规定以外物品入考场等，考卷作零分记。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("七、考试结束响铃后，考生立即停止答卷。注意不得将草稿纸带走。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        cell = new Cell(1,6).add(new Paragraph("八、本证应妥善保存，如有遗失，不补发。").setFont(font).setFontSize(noticeFontSize));
        cell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell);
        
        document.add(table);
        //Close document
        document.close();
	 }
}
