package Enemies;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 13:45
*/

import GameLogic.GameMap;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;

public class Enemy
{
	private static int index;//序号
	private Point position;//位置
	private float velocity;//移动速度
	private int hp;//生命值
	private int bounty;//单位被摧毁时玩家得到的的赏金
	private final int firePower;//火力
	private final char type;//敌人类型，G陆军，A空军
	private final char armor;//装甲类型，I(Infantry)步兵，L(LightTank)轻型坦克，H(HeavyTank)重型坦克，F(Fighter)战斗机，B(Bomber)轰炸机
	private Image image;//图像
	private final String imageURL;//图像路径
	public EnemyController enemyController;//该敌人对象的控制器，控制敌人移动
	
	public Enemy(GameMap map, float velocity, int hp, int firePower, char type, char armor, String imageURL) throws IOException
	{
		index++;//序号自加
		this.position = map.getWayPoint()[0];
		this.velocity = velocity;
		this.hp = hp;
		this.firePower = firePower;
		this.type = type;
		this.armor = armor;
		
		this.imageURL = imageURL;
		this.image = ImageIO.read(new File(imageURL + "180.png"));//载入图片
		
		enemyController = new EnemyController(map, this);
		
		System.out.println("已构造 Enemy" + index);
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, position.x, position.y, 128, 128, null);//绘制本体
		g.setColor(Color.green);
		g.fillRect(position.x + 36, position.y + 48, hp / 5, 4);//绘制血量条
	}
	
	public Enemy startController(int delay) throws InterruptedException
	{
		enemyController.startController(delay);
		return this;
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
	
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public float getVelocity()
	{
		return velocity;
	}
	
	public String getImageURL()
	{
		return imageURL;
	}
	
	public EnemyController getEnemyController()
	{
		return enemyController;
	}
	
	public int getHp()
	{
		return hp;
	}
}