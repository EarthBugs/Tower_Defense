package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏窗口
*/

import Audio.BGM.BGM;
import Audio.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame implements Runnable
{
	private GameMap gameMap;
	private GameJudger gameJudger;//裁判
	
	private boolean isRunning = true;//游戏是否正在运行
	
	GameWindow() throws IOException
	{
		this.setTitle("红警塔防-Author@地球虫子");//窗口标题
		this.setResizable(false);//窗口不支持缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方式
		this.setSize(1050, 755);//设置窗口尺寸（像素）
		this.setLocationRelativeTo(null);//设置窗口启动时位于屏幕中央
		this.setLayout(null);//禁用布局管理器
		this.setVisible(true);//显示窗口
		
		//播放开场语音
		Sound battleControlOnline = new Sound("src\\Audio\\SoundEffects\\战场控制上线.mp3");
		new Sound("src\\Audio\\SoundEffects\\正在稳定战场控制.mp3", battleControlOnline).start();
	}
	
	public void initializeGameMap(GameJudger gameJudger) throws IOException
	{
		this.gameJudger = gameJudger;
		
		/*//添加提示文字
		JLabel notice0 = new JLabel("正在稳定战场控制系统……");
		notice0.setFont(new Font(Font.DIALOG, 1, 32));
		notice0.setBounds(100, 100, 1024, 32);
		this.add(notice0);
		
		try
		{
			synchronized(this)
			{
				wait(5500);//延迟构造GameMap，等待开场语音播放完毕
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}*/
		
		new BGM(this);//创建BGM播放线程
		
		gameMap = new GameMap(5, 5, this);//创建游戏地图
		gameMap.setBounds(0, 0, 1050, 755);//设置面板的位置
		this.add(gameMap);//向窗口中添加地图面板
		
		//启动两个管理器
		gameMap.getTowerManager().start(this);
		gameMap.getEnemyManager().start(this);
		
		new TowerButton(this).addButton();//向窗口中添加按钮
	}
	
	@Override
	public void run()
	{
		while(isRunning)
		{
			try
			{
				synchronized(this)
				{
					gameMap.repaint();//调用JPanel的重绘
					wait(16);//每16毫秒刷新，60FPS
				}
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public GameJudger getGameJudger()
	{
		return gameJudger;
	}
	
	public boolean getRunning()
	{
		return isRunning;
	}
	
	public GameMap getGameMap()
	{
		return gameMap;
	}
	
	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}
}