package Images;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 20:51
*/

import javax.imageio.ImageIO;
import java.awt.*;
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
	
	public VideoPlayer(Point position, float roteAngle, Graphics g, String imageURL)//构造函数
	{
		this.position = position;
		this.roteAngle = roteAngle;
		this.imageURL = imageURL;
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
				this.sleep(16);//每16毫秒刷新一次，60FPS
				
				System.out.println(thisFrame);
				System.out.println(imageURL + "Lightning_" + thisFrame + ".png");
				
				image = ImageIO.read(new File(imageURL + "Lightning_" + thisFrame + ".png"));//读图片
				g.drawImage(image, 0, 0, 100, 100, null);
				thisFrame++;
				if(thisFrame == 60)
					break;
			}
			catch(InterruptedException | IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}