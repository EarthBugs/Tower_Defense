package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏地图
*/

import Towers.TeslaCoil.TeslaCoil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameMap extends JPanel
{
	//地图大小，格数
	private int mapWidth;
	private int mapHeight;
	
	private TeslaCoil teslaCoil;
	
	GameMap(int mapWidth, int mapHeight) throws IOException//构造函数，传参为地图大小，格数
	{
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		this.setBackground(new Color(205, 196, 201));
		teslaCoil = new TeslaCoil(new Point(100, 100));
	}
	
	public static void coordinateConverter(Point position)//坐标转换器，将格数坐标转换为绝对坐标
	{
	
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		try
		{
			teslaCoil.paint(g);
			
			Thread.sleep(16);//每16毫秒刷新一次，60FPS
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}