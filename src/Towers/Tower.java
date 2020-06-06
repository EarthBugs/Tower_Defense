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
	
	protected Tower(Point position, String imageURL) throws IOException//构造函数
	{
		index++;//序号自加
		this.position = position;
		image = ImageIO.read(new File(imageURL + "0.png"));//载入图片
		
		towerWeapon = new TowerWeapon(256, 1000);//初始化towerWeapon
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, position.x - 64, position.y - 96, null);
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