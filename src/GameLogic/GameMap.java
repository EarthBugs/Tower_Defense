package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏地图
*/

import Enemies.RhinoHeavyTank.RhinoHeavyTank;
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
	private RhinoHeavyTank rhinoHeavyTank;
	
	GameMap(int mapWidth, int mapHeight) throws IOException//构造函数，传参为地图大小，格数
	{
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		this.setLocation(-100, -100);
		this.setBackground(new Color(205, 196, 201));
		teslaCoil = new TeslaCoil(new Point(100, 100));
		rhinoHeavyTank = new RhinoHeavyTank(new Point(0, 0));
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		teslaCoil.paint(g);
		rhinoHeavyTank.paint(g);
		
		g.setColor(Color.red);
		g.fillRect(0, 0, 1000, 1000);
		
		try
		{
			synchronized(this)
			{
				wait(16);//每16毫秒刷新，60FPS
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}