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

import GameLogic.GameWindow;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyController
{
	private Point[] wayPoint;//敌人单位的移动路径点
	private Point deltaPosition[];//该数组用于存储按顺序数下来每两个路径点之间的差值
	private float tModulus[];//存储每一段路径中t的系数，用于改善路径长度不同导致一单位t对应的实际距离不一致的情况
	private float t = 0;//参数方程的参量，也就是距离系数，默认0
	private Enemy enemy;//控制器控制的敌人对象
	private double velocity;
	private int toward = 0;//敌人朝向，初始为0°，指向正东
	
	public EnemyController(@NotNull Point wayPoint[], Enemy enemy)//在构造函数中将传入的wayPoint化为参数方程形式的分段函数
	{
		this.wayPoint = wayPoint;
		this.enemy = enemy;
		this.velocity = enemy.getVelocity();
		
		//初始化两个数组
		deltaPosition = new Point[wayPoint.length - 1];
		tModulus = new float[wayPoint.length - 1];
		
		//遍历
		for(int i = 0; i < wayPoint.length - 1; i++)
		{
			//i与i+1两点位置差值
			deltaPosition[i] = new Point(wayPoint[i + 1].x - wayPoint[i].x, wayPoint[i + 1].y - wayPoint[i].y);
			
			float distance = (float) wayPoint[i].distance(wayPoint[i + 1]);//调用i号点的distance方法测量与i+1号点之间的距离
			tModulus[i] = 1 / distance;//系数为距离倒数，即可保证每段路径乘该系数之后一单位t对应实际距离一致
		}
	}
	
	public void move()
	{
		t += velocity;//t=t+velocity
		setCoordinate();
	}
	
	private void setCoordinate()//该函数用于更改enemy对象的坐标值
	{
		//对t取整，检测当前单位属于哪个路径段，实现分段函数
		int i = (int) t;
		
		//重要】【重要】【重要】【重要】【重要】需要对每个路径段减去路径i，使得不同t对应不同路径段
		//重要】【重要】【重要】【重要】【重要】需要对每个路径段减去路径i，使得不同t对应不同路径段
		//重要】【重要】【重要】【重要】【重要】需要对每个路径段减去路径i，使得不同t对应不同路径段
		//重要】【重要】【重要】【重要】【重要】需要对每个路径段减去路径i，使得不同t对应不同路径段
		
		//该敌人对象的坐标=该对象所处路径段（每两个点之间为一个段）的起始点坐标+路径始末点坐标差值*tModulus（也就是该段路径对应的系数）*t
		int x = (int) (wayPoint[i].x + deltaPosition[i].x * tModulus[i] * t);
		int y = (int) (wayPoint[i].y + deltaPosition[i].y * tModulus[i] * t);
		enemy.setPosition(x, y);
		
		//若t的小数部分大于0.95，即接近了拐点，检测下一个方向并转向
		if((t - (int) t) > 0.95)
			setToward(i);
	}
	
	private void setToward(int i)
	{
		try
		{
			//这部分是检测并重设朝向的代码，若后续改为非水平/垂直路径，需要重写
			if(wayPoint[i + 1].x - wayPoint[i].x == 0)//若当前路径段始末点的x差值为零，即两者在同一列上，则对象方向向上
			{
				System.out.println(enemy.getImageURL() + "90.png");
				enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "90.png")));
			}
			else//否则单位向左或向右
			{
				if(wayPoint[i + 1].x - wayPoint[i].x < 0)//向左
				{
					enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "180.png")));
				}
				else//向右
				{
					enemy.setImage(ImageIO.read(new File(enemy.getImageURL() + "0.png")));
				}
			}
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！" + i, "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}
	}
}