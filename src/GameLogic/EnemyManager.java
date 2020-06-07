package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/5 - 12:24
*/

import Audio.Sound;
import Enemies.Enemy;
import Enemies.RhinoHeavyTank.RhinoHeavyTank;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyManager extends Thread
{
	private GameWindow win;
	private GameMap map;
	private ArrayList<Enemy> enemyList;
	
	public EnemyManager(GameMap map, ArrayList<Enemy> enemyList)
	{
		this.map = map;
		this.enemyList = enemyList;
		
		System.out.println("已构造：敌人管理器");
	}
	
	@Override
	public void run()
	{
		super.run();
		
		try
		{
			//生成3个犀牛坦克对象
			for(int i = 0; i < 3; i++)
			{
				if(win.getRunning())//判断游戏是否结束
					enemyList.add(new RhinoHeavyTank(map).startController(1 + 2000 * i));
			}
			
			for(int i = 0; i < 6; i++)//生成6个犀牛坦克对象
			{
				if(win.getRunning())//判断游戏是否结束
					enemyList.add(new RhinoHeavyTank(map).startController(10000 + 2000 * i));
			}
			
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		while(win.getRunning())//检测敌人生命值并决定是否移除敌人
		{
			for(int i = 0; i < enemyList.size(); i++)//遍历enemyList中的每一个对象
			{
				Enemy enemy = enemyList.get(i);
				if(enemy.getHp() <= 0)//检测敌人生命值是否<=0
				{
					System.out.println("已删除：" + enemyList.get(i));
					enemyList.remove(enemy);//移除敌人对象
					
					map.addMoney(enemy.getBounty());//为玩家增加金钱
					
					new Sound("src\\Audio\\SoundEffects\\单位爆炸.mp3").start();//播放爆炸音效
				}
			}
		}
	}
	
	public synchronized void start(GameWindow win)
	{
		System.out.println("已启动：敌人管理器");
		
		this.win = win;
		
		super.start();
	}
}