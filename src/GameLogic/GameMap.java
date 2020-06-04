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
	
	private Point wayPoint[];//敌人单位的移动路径点
	
	private GameWindow window;//窗口
	private GameJudger gameJudger;//裁判
	
	private TeslaCoil teslaCoil;
	private RhinoHeavyTank rhinoHeavyTank[];
	
	GameMap(int mapWidth, int mapHeight, GameWindow window) throws IOException, InterruptedException//构造函数，传参为地图大小，格数
	{
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.gameJudger = window.getGameJudger();
		this.window = window;
		
		this.setBackground(new Color(205, 196, 201));
		
		//初始化路径点。32+64*n即第n行/列的行/列中心，地图共16列9行。当前地图形状：
		//┏━┛
		//┗━━━
		wayPoint = new Point[]{new Point(1024, 32 + 64 * 7), new Point(32 + 64 * 2, 32 + 64 * 7), new Point(32 + 64 * 2, 32 + 64 * 3), new Point(32 + 64 * 10, 32 + 64 * 3), new Point(32 + 64 * 10, -128)};
	}
	
	public void addElements() throws IOException, InterruptedException
	{
		teslaCoil = new TeslaCoil(new Point(100, 100));
		rhinoHeavyTank = new RhinoHeavyTank[]{new RhinoHeavyTank(new Point(0, 0), this), new RhinoHeavyTank(new Point(0, 0), this), new RhinoHeavyTank(new Point(0, 0), this), new RhinoHeavyTank(new Point(0, 0), this)};
		for(int i = 0; i < 3; i++)
		{
			rhinoHeavyTank[i].startController();
			synchronized(this)
			{
				wait(2000);
			}
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		teslaCoil.paint(g);
		for(int i = 0; i < 3; i++)
		{
			rhinoHeavyTank[i].paint(g);
		}
	}
	
	public Point[] getWayPoint()
	{
		return wayPoint;
	}
	
	public GameJudger getGameJudger()
	{
		return gameJudger;
	}
	
	public GameWindow getWindow()
	{
		return window;
	}
}