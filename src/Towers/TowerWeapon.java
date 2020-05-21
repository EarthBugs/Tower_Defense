package Towers;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:18
*/

public class TowerWeapon
{
	private float fireRange;//攻击范围
	private float fireTimeInterval;//攻击时间间隔
	
	protected TowerWeapon(float fireRange, float fireTimeInterval)//构造函数
	{
		this.fireRange = fireRange;
		this.fireTimeInterval = fireTimeInterval;
	}
}