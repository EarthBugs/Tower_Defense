package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/7 - 15:44
    //该类用于向窗口中添加按钮，每个格子添加一个，以检测玩家点击格子
*/

import Towers.TeslaCoil.TeslaCoil;
import Towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TowerButton
{
	private GameWindow win;
	private GameMap map;
	
	Tower towerBrush;//塔笔刷，用于选择新增的塔的类型
	
	public class GridButton extends JButton//继承JButton的内部类
	{
		Point location;//按钮位置
		
		GridButton(Point location)
		{
			super();
			
			this.setLocation(location.x - 72, location.y - 72);
			this.setSize(144, 144);
			this.setBorderPainted(false);//不打印边框
			this.setFocusPainted(false);//除去焦点的框
			this.setContentAreaFilled(false);//除去默认的背景填充
			this.addActionListener//添加按钮监听器
					(new ActionListener()
					{
						@Override
						public synchronized void actionPerformed(ActionEvent e)
						{
							System.out.println("格：(x:" + ((location.x - 72) / 144) + ", y:" + ((location.y - 72) / 144) + ")被点击");
							
							if(map.getMoney() >= towerBrush.getPrice())//判断玩家金钱是否足够
							{
								if(!map.getTowerMap().containsKey(location))//判断towerMap中是否存在location对应的点，不存在则添加
								{
									map.reduceMoney(towerBrush.getPrice());
									Tower tower = new Tower(towerBrush, map);//此时的towerBrush存储的是无参构造的塔，需要设置塔的位置
									tower.setLocation(location);//设置塔的位置
									map.getTowerManager().addTower(tower);
								}
							}
						}
					});
			
			this.location = location;
		}
	}
	
	public TowerButton(GameWindow win) throws IOException
	{
		this.win = win;
		this.map = win.getGameMap();
		this.towerBrush = new TeslaCoil(map);
	}
	
	public void addButton()
	{
		//增加菜单栏的按钮
		JButton teslaCoilButton = new JButton();
		teslaCoilButton.setBorderPainted(false);//不打印边框
		teslaCoilButton.setFocusPainted(false);//除去焦点的框
		teslaCoilButton.setContentAreaFilled(false);//除去默认的背景填充
		teslaCoilButton.setLocation(755,400);//设置位置
		teslaCoilButton.setSize(120,85);//设置大小
		teslaCoilButton.addActionListener//添加按钮监听器
				(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							System.out.println("TeslaCoil菜单按钮被点击");
							towerBrush = new TeslaCoil(map);
						}catch(IOException e1)
						{
							JOptionPane.showMessageDialog(null, "读取图片文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
		win.add(teslaCoilButton);//添加按钮
		
		//暂时还没想好什么方法可以根据路径点来避开敌人路径放置按钮，故手动设置按钮位置
		for(int i = 0; i < 5; i++)//第一行
		{
			if(i < 3 || i > 3)
			{
				win.add(new GridButton(new Point(72 + 144 * i, 72)));
			}
		}
		for(int i = 0; i < 5; i++)//第二行
		{
			if(i < 1 || i > 3)
			{
				win.add(new GridButton(new Point(72 + 144 * i, 72 + 144 * 1)));
			}
		}
		for(int i = 0; i < 5; i++)//第三行
		{
			if(i < 1 || i > 1)
			{
				win.add(new GridButton(new Point(72 + 144 * i, 72 + 144 * 2)));
			}
		}
		for(int i = 0; i < 5; i++)//第四行
		{
			if(i < 1)
			{
				win.add(new GridButton(new Point(72 + 144 * i, 72 + 144 * 3)));
			}
		}
		for(int i = 0; i < 5; i++)//第五行
		{
			win.add(new GridButton(new Point(72 + 144 * i, 72 + 144 * 4)));
		}
	}
}