package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/3 - 23:43
*/

import javax.swing.*;

public class GameJudger
{
	GameWindow window;
	
	public GameJudger(GameWindow window)
	{
		this.window = window;
	}
	
	public void judge(int gameState)
	{
		window.setRunning(false);//停止所有线程中的循环
		System.out.println("##########\nYou have been defeated, GameWindow stopped!\n##########");
		
		if(gameState == 1)
			JOptionPane.showMessageDialog(null, "YOU WIN！", "YOU WIN！", JOptionPane.PLAIN_MESSAGE);
		if(gameState == 2)
			JOptionPane.showMessageDialog(null, "YOU HAVE BEEN DEFEATED！", "YOU HAVE BEEN DEFEATED！", JOptionPane.PLAIN_MESSAGE);
	}
}