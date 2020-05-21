package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏地图
*/

import Images.VideoPlayer;
import Towers.TeslaCoil.TeslaCoil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameMap extends JPanel
{
	int thisFrame;
	TeslaCoil teslaCoil;
	
	GameMap() throws IOException//构造函数
	{
		this.setBackground(new Color(205, 196, 201));
		teslaCoil = new TeslaCoil();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		try
		{
			teslaCoil.paint(g);
			
			//Thread.sleep(16);//每16毫秒刷新一次，60FPS
			
			
			//这段代码应该写在VedioPlayer里
			System.out.println(thisFrame);
			System.out.println("src\\Images\\Lightning\\Lightning_" + thisFrame + ".png");
			
			BufferedImage image = ImageIO.read(new File("src\\Images\\Lightning\\Lightning_" + thisFrame + ".png"));//读图片
			g.drawImage(image, 0, 0, null);
			thisFrame+=2;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}