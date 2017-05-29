package com.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sun.misc.BASE64Decoder;

public class ImageExtractor 
{

   CharArrayWriter text = new CharArrayWriter();
   Map dataMap = new HashMap();
   int foundImages;

   public ImageExtractor() {}
   
   /**
    * InputStream is closed internally.
    * @param is
    * @throws IOException
    */
   public ImageExtractor(InputStream is) throws IOException 
   {
      parseXmlFile(is, new ImageParseHandler() , false);
      is.close();
   }
   
   /**
    * Refuse to Validate against dtd.
    * @param is
    * @param handler
    * @param validating
    */
   private void parseXmlFile(InputStream is, DefaultHandler handler,boolean validating)
   {
      try
      {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(validating);
        factory.newSAXParser().parse(is , handler);
      } 
      catch (SAXException e)
      {
        // A parsing error occurred; the xml input is not valid
      } catch (ParserConfigurationException e)
      {
        //
      } catch (IOException e)
      {
        //
      }
   }

   private class ImageParseHandler extends DefaultHandler 
   {

      private boolean inImage = false;
      private StringBuffer encodedDataSb = null;
      private String imageName;
      Locator locator;

      public void setDocumentLocator(Locator locator)
      {
        this.locator = locator;
      }

      public void characters(char[] chars, int start, int len)
           throws SAXException
      {
        if (inImage)
        {
           encodedDataSb.append(new String(chars, start, len));
        }

      }

      public void startElement(String uri, String localName, String qName,
           Attributes attributes) throws SAXException
      {
        text.reset();
        if (qName.equals("w:binData"))
        {
           imageName = attributes.getValue("w:name");
           if (imageName.endsWith(".png") || imageName.endsWith(".jpg"))
           {
              encodedDataSb = new StringBuffer();
              inImage = true;
           } else
           {
              inImage = false;
           }
           foundImages++;
        }
      }

      public void endElement(String uri, String localName, String qName) throws SAXException
      {
        if (qName.equals("w:binData") && inImage)
        {
           ByteArrayInputStream is = new ByteArrayInputStream(encodedDataSb.toString().getBytes());
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           //ImageDecoder id = new ImageDecoder();
           decodeImage(is, baos);
           System.out.println(baos.toString());
           dataMap.put(imageName, baos.toByteArray());
           try
           {
              is.close();
              baos.close();
           } catch (IOException e)
           {
              // TODO Auto-generated catch block
              e.printStackTrace();
           }
           inImage = false;
        }
      }

   }
	   
   public void decodeImage(InputStream is, OutputStream os)
   {
      BASE64Decoder decoder = new BASE64Decoder();
      try
      {
        decoder.decodeBuffer(is, os);
      } catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
   }

   public int getFoundImages()
   {
      return foundImages;
   }
   public Map getDataMap()
   {
      return dataMap;
   }
   
   public static void main(String[] args) throws Exception
   {
	   String img="D:/kyhuaqing/yqsys/Src/file/快报样式.xml";
	   InputStream is=new FileInputStream(new File(img));
	   ImageExtractor aa=new ImageExtractor(is);
	   System.out.println();
   }
} 

