package Enemies;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 22:52
    敌人控制器
*/

import java.awt.*;

public class EnemyController
{
	private Point[] wayPoint;//敌人单位的移动路径点
	private int phase;//敌人走过的第几个路径阶段，第0个点和第1个点之间为第0阶段
	
	public static void moveToPoint(Point targetPoint)
	{
		//传入敌人单位，采用某种方式判断该单位所属的位置，参考敌人对象的velocity属性，使用setPosition改变其位置，并使用某种方式判断敌人是否应该转弯，若转弯则调用该对象的setTowards属性
		
	}
}