package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/3 - 23:43
*/

import javax.swing.*;

public class GameJudger
{
	Thread windowThread;
	
	public GameJudger(Thread windowThread)
	{
		this.windowThread = windowThread;
	}
	
	public void judge(int gameState)
	{
		windowThread.stop();
		
		if(gameState == 1)
			JOptionPane.showMessageDialog(null, "YOU WIN！", "YOU WIN！", JOptionPane.PLAIN_MESSAGE);
		if(gameState == 2)
			JOptionPane.showMessageDialog(null, "YOU HAVE BEEN DEFEATED！", "YOU HAVE BEEN DEFEATED！", JOptionPane.PLAIN_MESSAGE);
	}
}