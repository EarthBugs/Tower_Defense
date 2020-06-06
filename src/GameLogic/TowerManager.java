package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/6 - 18:00
*/

import Enemies.Enemy;
import Towers.Tower;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TowerManager extends Thread
{
	private HashMap<Point, Tower> towerMap;
	private Iterator towerIterator;//塔映射的迭代器
	
	private ArrayList<Enemy> enemyList;
	
	private Graphics g;//来自GameMap的画笔
	
	public TowerManager(HashMap<Point, Tower> towerMap, ArrayList<Enemy> enemyList, Graphics g)
	{
		//map构造完成前，无法获得g
		
		this.towerMap = towerMap;
		this.enemyList = enemyList;
		this.g = g;
		
		towerIterator = towerMap.entrySet().iterator();
	}
	
	@Override
	public void run()
	{
		super.run();
		
		while(true)
		{
			synchronized(this)
			{
				while(towerIterator.hasNext())
				{
					Map.Entry entry = (Map.Entry) towerIterator.next();//得到入口
					Tower tower = (Tower) entry.getValue();//得到tower
					
					for(int i = 0; i < enemyList.size(); i++)//遍历enemyList中的每个敌人对象
					{
						if(enemyList.get(i).getPosition().distance(tower.getPosition()) < tower.getTowerWeapon().getFireRange())//判断敌人对象与本对象之间的距离是否小于武器的攻击范围
						{
							System.out.println("塔" + tower.getPosition() + "攻击了敌人！");
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
}