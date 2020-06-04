package GameLogic;

import javax.swing.*;
import java.io.IOException;

public class Main
{
	public static GameWindow window;
	public static GameJudger gameJudger;
	
	public static void main(String[] args)
	{
		try
		{
			window = new GameWindow();
			Thread windowThread = new Thread(window);
			gameJudger = new GameJudger(window);//裁判，用于判断游戏输赢
			window.initializeGameMap(gameJudger);
			windowThread.start();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}
	}
}