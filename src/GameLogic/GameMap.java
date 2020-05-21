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
	private TeslaCoil teslaCoil;
	
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
			
			Thread.sleep(16);//每16毫秒刷新一次，60FPS
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}