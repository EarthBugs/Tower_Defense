package Towers;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:18
*/

public class TowerWeapon
{
	private int damage;//伤害
	private int fireRange;//攻击范围
	private int fireTimeInterval;//攻击时间间隔
	private int fireTimer = 0;//攻击计时器，默认为零，攻击后重设为fireTimeInterval
	
	private int fireDelay;//开火延迟，单位毫秒。用于将攻击音效与敌人受伤害同步
	
	public TowerWeapon(int damage, int fireRange, int fireTimeInterval, int fireDelay)//构造函数
	{
		this.damage = damage;
		this.fireRange = fireRange;
		this.fireTimeInterval = fireTimeInterval;
		
		this.fireDelay = fireDelay;
	}
	
	public TowerWeapon(TowerWeapon towerWeapon)//复制构造函数
	{
		this.damage = towerWeapon.getDamage();
		this.fireRange = towerWeapon.getFireRange();
		this.fireTimeInterval = towerWeapon.getFireTimeInterval();
		
		this.fireDelay = towerWeapon.getFireDelay();
	}
	
	public synchronized void playFireSound()
	{
		//空函数
	}
	
	public int getFireRange()
	{
		return fireRange;
	}
	
	public void setFireRange(int fireRange)
	{
		this.fireRange = fireRange;
	}
	
	public int getFireTimeInterval()
	{
		return fireTimeInterval;
	}
	
	public void setFireTimeInterval(int fireTimeInterval)
	{
		this.fireTimeInterval = fireTimeInterval;
	}
	
	public int getFireTimer()
	{
		return fireTimer;
	}
	
	public void resetFireTimer()
	{
		this.fireTimer = fireTimeInterval;
	}
	
	public void reduceFireTimer()
	{
		this.fireTimer--;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public int getFireDelay()
	{
		return fireDelay;
	}
	
	public void setFireDelay(int fireDelay)
	{
		this.fireDelay = fireDelay;
	}
}