package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/6 - 18:00
*/

import Enemies.Enemy;
import Towers.TeslaCoil.TeslaCoil;
import Towers.Tower;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TowerManager extends Thread
{
	private GameWindow win;
	private GameMap map;
	
	private HashMap<Point, Tower> towerMap;
	
	private ArrayList<Enemy> enemyList;
	
	public TowerManager(HashMap<Point, Tower> towerMap, ArrayList<Enemy> enemyList) throws IOException
	{
		this.towerMap = towerMap;
		this.enemyList = enemyList;
		
		System.out.println("已构造：塔管理器");
	}
	
	public void addTower(Tower tower)//该函数用于向towerMap中添加tower
	{
		towerMap.put(tower.getPosition(), tower);
	}
	
	@Override
	public void run()
	{
		super.run();
		
		while(win.getRunning())//循环这个线程
		{
			synchronized(this)
			{
				/*Iterator towerIterator = towerMap.entrySet().iterator();
				System.out.println(towerMap.size() + "    " + towerIterator.hasNext());
				while(towerIterator.hasNext())
				{
					tower.paint();
					
					for(int i = 0; i < enemyList.size(); i++)//遍历enemyList中的每个敌人对象
					{
						if(enemyList.get(i).getPosition().distance(tower.getPosition()) < tower.getTowerWeapon().getFireRange())//判断敌人对象与本对象之间的距离是否小于武器的攻击范围
						{
							System.out.println("塔" + tower.getPosition() + "攻击了敌人！");
						}
					}
				}*/
				
				for (Point key : towerMap.keySet())//遍历towerMap
				{
					Tower tower = towerMap.get(key);
					
					for(int i = 0; i < enemyList.size(); i++)//为该塔对象索敌，遍历enemyList中的每个敌人对象
					{
						Enemy enemy = enemyList.get(i);
						if(enemy.getPosition().distance(tower.getPosition()) < tower.getTowerWeapon().getFireRange())//判断敌人对象与本对象之间的距离是否小于武器的攻击范围
						{
							System.out.println("塔" + tower.getPosition() + "攻击了敌人" + enemy + "！");
							break;//找到第一个敌人对象之后，跳出内层的for循环
						}
					}
				}
				
				try
				{
					wait(16);//每16毫秒刷新，60FPS
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void start(GameWindow win) throws IOException
	{
		System.out.println("已启动：塔管理器");
		
		this.win = win;
		
		towerMap.put(new Point(144 + 72, 144 + 72), new TeslaCoil(new Point(144 + 72, 144 + 72)));
		
		super.start();
	}
}