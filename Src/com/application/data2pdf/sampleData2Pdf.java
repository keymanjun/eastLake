package com.application.data2pdf;

import java.io.File;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class sampleData2Pdf {

		
		public static final String DEST = "results/chapter01/hello_world.pdf";
		 
	    public static void main(String args[]) throws IOException, java.io.IOException {
	        File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new sampleData2Pdf().createPdf(DEST);
	    }
	 
	    public void createPdf(String dest) throws IOException, java.io.IOException {
	        //Initialize PDF writer
	        PdfWriter writer = new PdfWriter(dest);
	 
	        //Initialize PDF document
	        PdfDocument pdf = new PdfDocument(writer);
	 
	        // Initialize document
	        Document document = new Document(pdf);
	 
	        //Add paragraph to the document
	        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
	        document.add(new Paragraph("中文!").setFont(font));
	        
	        Table table = new Table(4);
	        Cell cell = new Cell().add(" 1,1 ");
	        table.addCell(cell);
	        cell = new Cell().add(" 1,2 ");
	        table.addCell(cell);
	        Cell cell23 = new Cell(2, 2).add("multi 1,3 and 1,4");
	        table.addCell(cell23);
	        cell = new Cell().add(" 2,1 ");
	        table.addCell(cell);
	        cell = new Cell().add(" 2,2 ");
	        table.addCell(cell);
	        document.add(table);
	        //Close document
	        document.close();
	}

}
