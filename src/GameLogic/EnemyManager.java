package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/5 - 12:24
*/

import Enemies.RhinoHeavyTank.RhinoHeavyTank;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyManager extends Thread
{
	private GameMap map;
	private ArrayList enemyList;
	
	public EnemyManager(GameMap map, ArrayList enemyList)
	{
		this.map = map;
		this.enemyList = enemyList;
		
		this.start();
	}
	
	@Override
	public void run()
	{
		super.run();
		
		try
		{
			//生成3个犀牛坦克对象
			for(int i = 0; i < 1; i++)
			{
				enemyList.add(new RhinoHeavyTank(map).startController(i * 1000 + 1));
			}
			
			/*
			synchronized(this)
			{
				wait(10000);//两组单位的出生间隔
			}
			
			for(int i=0;i<6;i++)//生成6个犀牛坦克对象
			{
				enemyList.add(new RhinoHeavyTank(map).startController());
				synchronized(this)
				{
					wait(1000);//单位的出生间隔
				}
			}*/
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}