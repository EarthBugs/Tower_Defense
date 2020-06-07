package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏地图
*/

import Enemies.Enemy;
import Towers.TeslaCoil.TeslaCoil;
import Towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameMap extends JPanel
{
	//地图大小，格数
	private int mapWidth;
	private int mapHeight;
	
	private GameWindow window;//窗口
	private GameJudger gameJudger;//裁判
	
	private Point wayPoint[];//敌人单位的移动路径点
	
	private int money = 1000;//玩家持有的金钱
	private JLabel moneyLabel;//玩家金钱的标签
	
	private Icon menu;//右侧的菜单栏
	private JLabel menuLabel;
	
	private HashMap<Point, Tower> towerMap;//存储塔的映射
	private TowerManager towerManager;//塔管理器
	
	private ArrayList<Enemy> enemyList;//存放敌人单位的列表
	private EnemyManager enemyManager;//敌人对象管理器
	
	GameMap(int mapWidth, int mapHeight, GameWindow window) throws IOException//构造函数，传参为地图大小
	{
		super();
		
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.gameJudger = window.getGameJudger();
		this.window = window;
		
		this.setBackground(new Color(205, 196, 201));
		
		this.menu = new ImageIcon("src\\Images\\Menu\\Menu.jpg");//载入图片
		
		this.setLayout(null);//禁用布局管理器
		
		//添加右侧的菜单栏
		this.menuLabel = new JLabel(menu);
		this.menuLabel.setSize(360, 720);
		this.menuLabel.setLocation(700, 0);
		this.add(menuLabel);//绘制menu
		
		//初始化路径点。60+120*n即第n行/列的行/列中心，地图共5列5行。当前地图形状：
		//┏━━┛
		//┗━━━━
		wayPoint = new Point[]{new Point(700, 72 + 144 * 3), new Point(72 + 144 * 1, 72 + 144 * 3), new Point(72 + 144 * 1, 72 + 144 * 1), new Point(72 + 144 * 3, 72 + 144 * 1), new Point(72 + 144 * 3, -256)};
		
		enemyList = new ArrayList<Enemy>();//初始化敌人列表
		
		this.enemyManager = new EnemyManager(this, enemyList);//创建敌人管理器
		
		towerMap = new HashMap<Point, Tower>();//初始化塔映射
		
		this.towerManager = new TowerManager(towerMap, enemyList);//创建塔管理器
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(Color.yellow);
		g.drawString(String.valueOf(money), 865, 24);//绘制金钱显示器
		
		g.setColor(Color.black);
		paintGrid(g);//绘制网格
		
		for(Point key : towerMap.keySet())//遍历towerMap中的每一个对象并执行绘制
		{
			Tower tower = towerMap.get(key);
			
			tower.paint(g);
		}
		
		for(int i = 0; i < enemyList.size(); i++)//遍历enemyList中的每一个对象并执行绘制
		{
			enemyList.get(i).paint(g);
		}
	}
	
	public void paintGrid(Graphics g)//一个用于绘制地面格子的函数
	{
		for(int i = 0; i < mapWidth; i++)//横向循环
		{
			for(int j = 0; j < mapHeight; j++)
			{
				g.drawRect(144 * i, 144 * j, 144, 144);
			}
		}
	}
	
	public Point[] getWayPoint()
	{
		return wayPoint;
	}
	
	public GameJudger getGameJudger()
	{
		return gameJudger;
	}
	
	public HashMap<Point, Tower> getTowerMap()
	{
		return towerMap;
	}
	
	public GameWindow getWindow()
	{
		return window;
	}
	
	public TowerManager getTowerManager()
	{
		return towerManager;
	}
	
	public EnemyManager getEnemyManager()
	{
		return enemyManager;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public int addMoney(int deltaMoney)
	{
		this.money += deltaMoney;
		System.out.println("玩家金钱已增加：" + deltaMoney + "，当前金钱：" + money);
		return money;
	}
	
	public int reduceMoney(int deltaMoney)
	{
		this.money -= deltaMoney;
		System.out.println("玩家金钱已减少：" + deltaMoney + "，当前金钱：" + money);
		return money;
	}
}