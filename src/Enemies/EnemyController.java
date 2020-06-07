package Enemies;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 22:52
    敌人控制器
    这部分涉及到的算法：
    	设P1(x1, y1), P2(x2, y2), P3(x3, y3)
		P1P2差值得到一个方向向量V=(Δx, Δy)
		设参量t
		此时单位从P1出发走向P2，则坐标为(x1 + tΔx, y1 + tΔy)
*/

import GameLogic.GameJudger;
import GameLogic.GameMap;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyController extends Thread
{
	private GameMap map;//地图
	private GameJudger gameJudger;//裁判
	private Point[] wayPoint;//敌人单位的移动路径点
	private Point deltaLocation[];//该数组用于存储按顺序数下来每两个路径点之间的差值
	private float t = 0;//参数方程的参量，也就是距离系数，默认0
	private Enemy enemy;//控制器控制的敌人对象
	private double velocity;
	private int toward = 0;//敌人朝向，初始为0°，指向正东
	
	private int activateDelay;//启动延迟
	
	public EnemyController(@NotNull GameMap map, Enemy enemy)//在构造函数中将传入的wayPoint化为参数方程形式的分段函数
	{
		this.map = map;
		this.wayPoint = map.getWayPoint();
		this.enemy = enemy;
		this.velocity = enemy.getVelocity();
		
		//初始化两个数组
		deltaLocation = new Point[wayPoint.length - 1];
		
		//遍历
		for(int i = 0; i < wayPoint.length - 1; i++)
		{
			//i与i+1两点位置差值
			deltaLocation[i] = new Point(wayPoint[i + 1].x - wayPoint[i].x, wayPoint[i + 1].y - wayPoint[i].y);
		}
	}
	
	public void startController(int delay)
	{
		synchronized(this)
		{
			System.out.println("延迟：" + delay);
			this.activateDelay = delay;
			this.start();
			System.out.println("已启动：Controller");
		}
	}
	
	@Override
	public void run()
	{
		super.run();
		
		try
		{
			synchronized(this)
			{
				wait(activateDelay);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		while(map.getWindow().getRunning())
		{
			try
			{
				synchronized(this)//使用线程锁确保执行不中断
				{
					this.wait(16);//每16毫秒刷新，60FPS
					this.move();
					
					if(enemy.getHp() <= 0)//如果敌人对象的生命值归零
					{
						break;//跳出循环，结束该控制器
					}
				}
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void move()
	{
		if(t + velocity < wayPoint.length - 1)//如果t小于路径点长度，即此时敌人单位未到达终点，则t自加并调用setLocation函数来更改该敌人单位的坐标
		{
			t += velocity;//t=t+velocity
			setLocation();
		}
		else//敌人到达终点，任务失败
		{
			System.out.println(enemy + "stopped the game");
			map.getGameJudger().judge(2);//任务失败
		}
	}
	
	private void setLocation()//该函数用于更改enemy对象的坐标值
	{
		//对t取整，检测当前单位属于哪个路径段，实现分段函数
		int i = (int) t;
		
		//该敌人对象的坐标=该对象所处路径段（每两个点之间为一个段）的起始点坐标+路径始末点坐标差值*tModulus（也就是该段路径对应的系数）*(t-i)
		int x = (int) (wayPoint[i].x + deltaLocation[i].x * (t - i));
		int y = (int) (wayPoint[i].y + deltaLocation[i].y * (t - i));
		enemy.setLocation(x, y);
		
		//输出该对象的数据
		//System.out.println("控制器输出：  x:" + x + ", y:" + y + ", i:" + i + ", t:" + t + ", Δx:" + deltaLocation[i].x + ", Δy:" + deltaLocation[i].y);
		
		setToward(i);//检测下一个方向并转向
	}
	
	private void setToward(int i)
	{
		try
		{
			//这部分是检测并重设朝向的代码，若后续改为非水平/垂直路径，需要重写
			if(wayPoint[i + 1].x - wayPoint[i].x == 0)//若当前路径段始末点的x差值为零，即两者在同一列上，则对象方向向上
			{
				enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "90.png")));
			}
			else//否则单位向左或向右
			{
				if((wayPoint[i + 1].x - wayPoint[i].x) < 0)//向左
				{
					enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "180.png")));
				}
				else//向右
				{
					enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "0.png")));
				}
			}
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！" + i, "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}
	}
}