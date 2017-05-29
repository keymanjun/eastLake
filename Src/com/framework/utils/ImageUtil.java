package com.framework.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 给图片加上图片水印或文字水印工具类
 * @author dafei
 *
 */
public final class ImageUtil {

	private ImageUtil(){}
	public static void copyfile(String pathSrc,String pathDest) throws IOException
	{
		FileInputStream fi=null;
		FileOutputStream fo=null;
		try{
			fi=new FileInputStream(pathSrc);		
			fo=new FileOutputStream(pathDest);		
			byte data[]=new byte[fi.available()];		
			fi.read(data);		
			fo.write(data);
		}
		catch(Exception e){
			
		}
		finally{
			if(fi!=null){
				fi.close();	
			}
			if(fo!=null){
				fo.close();	
			}
		}
	}

	
	/** 
	* 把图片印刷到图片上 
	* @param pressImg -- 水印文件 
	* @param targetImg -- 目标文件 
	* @param x 
	* @param y 
	*/ 
	public final static void pressImage(String pressImg, String targetImg, int x, int y) throws Exception
	{ 
		try 
		{
		    File _file = new File(targetImg);
		    Image src = ImageIO.read(_file);		   
		    int wideth = src.getWidth(null);
		    int height = src.getHeight(null);		   
		    BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
		   
		    Graphics g = image.createGraphics();
		    g.drawImage(src, 0, 0, wideth, height, null);

		    // 水印文件
		    File _filebiao = new File(pressImg);
		    Image src_biao = ImageIO.read(_filebiao);
		   
		    int wideth_biao = src_biao.getWidth(null);
		    int height_biao = src_biao.getHeight(null);		   
		    g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
		    g.dispose();
		   
		    FileOutputStream out = new FileOutputStream(targetImg);
		    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		    encoder.encode(image);
		   
		    out.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
	} 
	
	/**
	 * 生成缩略图并加上图片水印
	 * @param pathS  源文件
	 * @param pathD  目标文件
	 * @param miniwidth 缩略图宽度
	 * @param miniheight 缩略图高度
	 * @param waterImg   水印图片名称
	 * @throws Exception
	 */
	public static void pressMiniImage(String pathS,String pathD,int miniwidth,int miniheight,String waterImg) throws Exception
	{
		copyfile(pathS,pathD);

		//生成缩小mimi图
		File stadimgfile2 = new File(pathD);
		//图片缓存
		BufferedImage img2 = ImageIO.read(stadimgfile2);

		//得到图片的宽和高
		double width = img2.getWidth();
		double height = img2.getHeight();		
		double ratew = miniwidth / width;
		double rateh = miniheight / height;

		//获得适合的缩放比率，即以在规定缩略尺寸中完整显示图片内容的同时又保证最大的缩放比率
		double rate = Math.min(ratew, rateh);
		rate = (Math.rint((rate * 100) + 0.5)) / 100;
		BufferedImage imgmini = new java.awt.image.BufferedImage(miniwidth, miniheight,BufferedImage.TYPE_USHORT_565_RGB);

		Graphics2D gmini = imgmini.createGraphics();
		gmini.setBackground(Color.WHITE);
		gmini.clearRect(0, 0, miniwidth, miniheight);
		AffineTransform trans = new AffineTransform();
		trans.scale(rate, rate);

		AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
		gmini.drawImage(img2, op, (int) (miniwidth - (width * rate)) / 2, (int) (miniheight - (height * rate)) / 2);
		ImageIO.write(imgmini, "jpg", stadimgfile2);

		//是否加水印，如果为空则不加
        if(waterImg==null || "".equals(waterImg)) return;
        
		//mimi图加水印
		BufferedImage img3 = ImageIO.read(stadimgfile2);
		int mimi_width2 = img3.getWidth();
		int mimi_height2 = img3.getHeight();
		BufferedImage imgmimi2 = new java.awt.image.BufferedImage(mimi_width2, mimi_height2,
		BufferedImage.TYPE_USHORT_565_RGB);

		//logo文件的位置，必须是真是的
		BufferedImage watermark3 = ImageIO.read(new File(waterImg));
		Graphics2D gmimi2 = imgmimi2.createGraphics();
		gmimi2.drawImage(img3, null, 0, 0);
		gmimi2.drawImage(watermark3, null, mimi_width2 - watermark3.getWidth(), mimi_height2 - watermark3.getHeight());
		ImageIO.write(imgmimi2, "jpg", stadimgfile2);
	}

	/** 普通文字水印
	* 打印文字水印图片 
	* @param pressText --文字 
	* @param targetImg -- 目标图片 
	* @param fontName -- 字体名 
	* @param fontStyle -- 字体样式 
	* @param color -- 字体颜色 
	* @param fontSize -- 字体大小 
	* @param x -- 偏移量 
	* @param y 
	*/
	public static void pressText(String pressText, String targetImg, String fontName,
			int fontStyle, int color, int fontSize, int x, int y) throws Exception
	{ 
		try 
		{ 
			File _file = new File(targetImg); 
			Image src = ImageIO.read(_file); 
			int wideth = src.getWidth(null); 
			int height = src.getHeight(null); 
			BufferedImage image = new BufferedImage(wideth, height, 
			BufferedImage.TYPE_INT_RGB); 
			Graphics g = image.createGraphics(); 
			g.drawImage(src, 0, 0, wideth, height, null); 
			// String s="www.qhd.com.cn"; 
			g.setColor(Color.RED); 
			g.setFont(new Font(fontName, fontStyle, fontSize)); 
		
		
			g.drawString(pressText, wideth - fontSize - x, height - fontSize/2 - y); 
			g.dispose(); 
			FileOutputStream out = new FileOutputStream(targetImg); 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
			encoder.encode(image); 
			out.close(); 
		}
		catch (Exception e) 
		{ 
			throw e;
		} 
	} 

	/**
	 * 透明文字水印
	 * @param pressText
	 * @param targetImg
	 * @param fontName
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 * @param x
	 * @param y
	 * @param alpha
	 */
    public static void pressTextTM(String pressText, String targetImg,
            String fontName, int fontStyle, int color, int fontSize, int x,int y,float alpha) 
    {
        try 
        {
        	float Alpha=alpha;
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();            
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(new Color(255,255,255));
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,Alpha)); 
            g.drawString(pressText, x+10,height-fontSize);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	public static void main(String[] args) throws Exception 
	{ 
		//--图片加图片水印方法----测试通过
		//pressImage("c:/image/ip.jpg", "c:/image/abc.jpg", 20 ,20);
		
		//--普通文件水印---测试通过
		//pressText("dafei", "d:/abc.jpg","宋体",1,1,25,100, 100);
		
		//透明文字水印----测试通过
		//pressTextTM("www.sina.com", "d:/abc.jpg","宋体", 1, 1, 25, 150,150,0.8f);
		
		//生成图片的缩略图
		pressMiniImage("c:/image/abc.jpg", "c:/image/ip2.jpg",120,90,null);
	} 
}
