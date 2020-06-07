package Audio.BGM;/*
    @Auther TknHJQ
    @Create date 2020/6/7 - 19:03
*/

import Audio.Sound;
import GameLogic.GameWindow;

public class BGM extends Thread
{
	GameWindow win;
	
	public BGM(GameWindow win)
	{
		this.win = win;
		start();
	}
	
	@Override
	public void run()
	{
		super.run();
		
		while(win.getRunning())
		{
			int max = 16, min = 1;
			long randomNum = System.currentTimeMillis();//随机数
			int ranNum = (int) (randomNum % (max - min) + min);
			
			new Sound("src\\Audio\\BGM\\" + ranNum + ".mp3", this);
		}
	}
}