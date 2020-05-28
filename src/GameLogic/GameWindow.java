package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 16:07
    游戏窗口
*/

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame
{
	GameWindow() throws InterruptedException, IOException
	{
		this.setTitle("红警塔防-Author@地球虫子");//窗口标题
		//this.setResizable(false);//窗口不支持缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方式
		this.setSize(1024, 576);//设置窗口尺寸（像素）
		this.setLocationRelativeTo(null);//设置窗口启动时位于屏幕中央
		
		//this.setLayout(null);//禁用布局管理器
		GameMap gameMap = new GameMap(10, 10);//创建游戏地图
		this.add(gameMap);//向窗口中添加地图面板
		
		this.setVisible(true);//在上述流程完成后显示窗口，确保此时内容已经初始化完毕
		
		while(true)
		{
			gameMap.repaint();//调用JPanel的重绘
		}
	}
}