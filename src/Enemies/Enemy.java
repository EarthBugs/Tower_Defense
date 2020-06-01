package Enemies;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 13:45
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Enemy
{
	private static int index;//序号
	private Point position;//位置
	private float velocity;//移动速度
	private int hp;//生命值
	private int firePower;//火力
	private char type;//敌人类型，G陆军，A空军
	private char armor;//装甲类型，I(Infantry)步兵，L(LightTank)轻型坦克，H(HeavyTank)重型坦克，F(Fighter)战斗机，B(Bomber)轰炸机
	private Image image;//图像
	private EnemyController enemyController;//该敌人对象的控制器，控制敌人移动
	
	public Enemy(Point position, float velocity, int hp, int firePower, char type, char armor, String imageURL) throws IOException
	{
		index++;//序号自加
		this.position = position;
		this.velocity = velocity;
		this.hp = hp;
		this.firePower = firePower;
		this.type = type;
		this.armor = armor;
		image = ImageIO.read(new File(imageURL + "0.png"));//载入图片
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, position.x, position.y, 128, 128, null);
	}
	
	public void setX(int x)
	{
		this.position.x = x;
	}
	
	public void setY(int y)
	{
		this.position.y = y;
	}
	
	public void setPosition(Point position)
	{
		this.position = position;
	}
	
	public void setPosition(int x, int y)
	{
		this.position.x = x;
		this.position.y = y;
	}
}