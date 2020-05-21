package Images;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 20:51
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoPlayer extends Thread
{
	private Point position;//图像位置
	private float roteAngle;//图像角度
	private int thisFrame;//当前播放到的帧数，从0开始
	private Image image;//图像
	private Graphics g;//画笔
	private String imageURL;//序列帧路径
	
	/*public VideoPlayer(Point position, float roteAngle, Graphics g, String imageURL)//构造函数
	{
		this.position = position;
		this.roteAngle = roteAngle;
		this.imageURL = imageURL;
		this.g = g;
	}*/
	
	public VideoPlayer(Point position,Graphics g)//构造函数
	{
		this.position = position;
		this.g = g;
	}
	
	@Override
	public void run()
	{
		super.run();
		
		while(true)
		{
			try
			{
				System.out.println(thisFrame);
				System.out.println("src\\Images\\Effects\\Lightning\\Lightning_" + thisFrame + ".png");
				
				BufferedImage image = ImageIO.read(new File("src\\Images\\Effects\\Lightning\\Lightning_" + thisFrame + ".png"));//读图片
				g.drawImage(image, 0, 0, null);
				thisFrame+=2;
				
				if(thisFrame == 60)
					break;
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}