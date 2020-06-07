package Towers;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    塔的父类
*/

import Audio.Sound;
import Enemies.Enemy;
import GameLogic.GameMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tower extends Thread
{
	private static int index;//塔的序号
	private Point location;//位置
	private int angle;//炮塔角度
	private int price;//价格
	private int powerRequired;//塔需要的电力
	private boolean isActivated = false;//是否启用
	private String imageURL;//图像路径
	private Image image;//图像
	
	private TowerWeapon towerWeapon;//武器
	
	private Enemy target;//攻击目标
	private boolean isAttacking;//是否正在攻击目标
	
	private GameMap map;
	
	protected Tower(Point location, String imageURL, TowerWeapon towerWeapon) throws IOException//构造函数
	{
		index++;//序号自加
		this.location = location;
		this.imageURL = imageURL;
		image = ImageIO.read(new File(imageURL + "0.png"));//载入图片
		
		this.towerWeapon = towerWeapon;
		
		start();
		
		System.out.println("已构造塔：" + index);
		new Sound("src\\Audio\\SoundEffects\\建造完成.mp3").start();
	}
	
	protected Tower(String imageURL, TowerWeapon towerWeapon, GameMap map) throws IOException//无参构造函数
	{
		index++;//序号自加
		this.location = new Point(-2048, -2048);
		image = ImageIO.read(new File(imageURL + "0.png"));//载入图片
		
		this.towerWeapon = towerWeapon;
		
		this.map = map;
		
		this.start();
		
		System.out.println("已无参构造塔：" + index);
		new Sound("src\\Audio\\SoundEffects\\建造完成.mp3").start();
	}
	
	public Tower(Tower tower, GameMap map)//复制构造函数
	{
		index++;//序号自加
		
		this.location = (Point) tower.getLocation().clone();
		image = tower.getImage();
		
		this.towerWeapon = tower.getTowerWeapon();
		
		this.map = map;
		
		this.start();
		
		System.out.println("已复制构造塔：" + index);
		new Sound("src\\Audio\\SoundEffects\\建造完成.mp3").start();
	}
	
	@Override
	public synchronized void run()
	{
		super.run();
		
		while(map.getWindow().getRunning())
		{
			if(isActivated)//待setTarget执行后，target不为空，此时才能执行attack
			{
				this.attack();
			}
			
			try
			{
				wait(16);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void attack()
	{
		if(!isAttacking)
		{
			if(target != null)
			{
				isAttacking = true;
				if(towerWeapon.getFireTimer() == 0)//如果攻击Timer为零，执行攻击并重设Timer
				{
					System.out.println("Tower" + index + "攻击了" + target);
					towerWeapon.playFireSound();//播放攻击音效
					towerWeapon.resetFireTimer();//重设Timer
					try
					{
						wait(towerWeapon.getFireDelay());
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					target.damaged(towerWeapon.getDamage());//调用敌人的damaged函数，并传入本塔对象的武器的伤害值
				}
				else//Timer不为零，Timer减少
				{
					towerWeapon.reduceFireTimer();
				}
				isAttacking = false;
				
				if(target.getLocation().distance(this.getLocation()) > this.getTowerWeapon().getFireRange())//判断敌人对象与本对象之间的距离是否超出武器的攻击范围
				{
					this.setTarget(null);//清空目标
				}
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, location.x - 64, location.y - 96, null);
	}
	
	@Override
	public String toString()
	{
		return "Tower" + index + "(x:" + location.x + ", y:" + location.y + ")";
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public TowerWeapon getTowerWeapon()
	{
		return towerWeapon;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public void setTarget(Enemy target)
	{
		this.target = target;
		this.isActivated = true;
	}
}