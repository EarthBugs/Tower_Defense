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
import java.util.Iterator;

public class TowerManager extends Thread
{
	private GameWindow win;
	
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
		towerMap.put(tower.getLocation(), tower);
	}
	
	@Override
	public void run()//索敌
	{
		super.run();
		
		while(win.getRunning())//循环这个线程
		{
			synchronized(this)
			{
				for(Point key : towerMap.keySet())//遍历towerMap中的每一个对象并索敌
				{
					Tower tower = towerMap.get(key);
					
					for(int i = 0; i < enemyList.size(); i++)//为该塔索敌，遍历enemyList中的每个敌人对象
					{
						Enemy enemy = enemyList.get(i);
						if(enemy.getLocation().distance(tower.getLocation()) < tower.getTowerWeapon().getFireRange())//判断敌人对象与本对象之间的距离是否小于武器的攻击范围
						{
							tower.setTarget(enemy);//为塔设置攻击目标
							break;//找到第一个敌人对象之后，跳出内层的for循环，开始为下一个塔索敌
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
		
		super.start();
	}
}