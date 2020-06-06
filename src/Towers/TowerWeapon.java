package Towers;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:18
*/

public class TowerWeapon
{
	private float fireRange;//攻击范围
	private float fireTimeInterval;//攻击时间间隔
	private int fireTimer;//攻击计时器
	
	protected TowerWeapon(float fireRange, float fireTimeInterval)//构造函数
	{
		this.fireRange = fireRange;
		this.fireTimeInterval = fireTimeInterval;
	}
	
	public float getFireRange()
	{
		return fireRange;
	}
	
	public void setFireRange(float fireRange)
	{
		this.fireRange = fireRange;
	}
	
	public float getFireTimeInterval()
	{
		return fireTimeInterval;
	}
	
	public void setFireTimeInterval(float fireTimeInterval)
	{
		this.fireTimeInterval = fireTimeInterval;
	}
	
	public int getFireTimer()
	{
		return fireTimer;
	}
	
	public void setFireTimer(int fireTimer)
	{
		this.fireTimer = fireTimer;
	}
}