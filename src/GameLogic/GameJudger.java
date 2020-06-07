package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/6/3 - 23:43
*/

import Audio.Sound;

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
		{
			//输出语音
			Sound missionAccomplished = new Sound("src\\Audio\\SoundEffects\\任务成功.mp3");
			new Sound("src\\Audio\\SoundEffects\\你赢了.mp3", missionAccomplished).start();
			
			JOptionPane.showMessageDialog(null, "YOU WIN！", "YOU WIN！", JOptionPane.PLAIN_MESSAGE);//弹出提示框
		}
		if(gameState == 2)
		{
			//输出语音
			Sound missionFailed = new Sound("src\\Audio\\SoundEffects\\任务失败.mp3");
			new Sound("src\\Audio\\SoundEffects\\你输了.mp3", missionFailed).start();
			
			JOptionPane.showMessageDialog(null, "YOU HAVE BEEN DEFEATED！", "YOU HAVE BEEN DEFEATED！", JOptionPane.PLAIN_MESSAGE);//弹出提示框
		}
	}
}