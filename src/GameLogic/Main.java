package GameLogic;

import javax.swing.*;
import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			GameWindow window = new GameWindow();
			window.run();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "读取图片文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}
	}
}