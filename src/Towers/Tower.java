package Towers;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    塔的父类
*/

import Enemies.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tower
{
	private static int index;//塔的序号
	private Point position;//位置
	private int angle;//炮塔角度
	private int price;//价格
	private int powerRequired;//塔需要的电力
	private boolean isActivated;//是否启用
	private Image image;//图像
	
	private TowerWeapon towerWeapon;//武器
	
	protected Tower(Point position, String imageURL, TowerWeapon towerWeapon) throws IOException//构造函数
	{
		index++;//序号自加
		this.position = position;
		image = ImageIO.read(new File(imageURL + "0.png"));//载入图片
		
		this.towerWeapon = towerWeapon;
	}
	
	public synchronized void attack(Enemy enemy)
	{
		if(towerWeapon.getFireTimer() == 0)//如果攻击Timer为零，执行攻击并重设Timer
		{
			System.out.println(this + "攻击了" + enemy);
			towerWeapon.playFireSound();//播放攻击音效
			towerWeapon.resetFireTimer();//重设Timer
			try
			{
				wait(towerWeapon.getFireDelay());
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			enemy.damaged(towerWeapon.getDamage());//调用敌人的damaged函数，并传入本塔对象的武器的伤害值
		}
		else//Timer不为零，Timer减少
		{
			towerWeapon.reduceFireTimer();
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, position.x - 64, position.y - 96, null);
	}
	
	@Override
	public String toString()
	{
		return "Tower" + index + "(x:" + position.x + ", y:" + position.y + ")";
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public TowerWeapon getTowerWeapon()
	{
		return towerWeapon;
	}
}