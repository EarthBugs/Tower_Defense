package Audio;/*
    @Auther TknHJQ
    @Create date 2020/6/7 - 10:22
*/

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sound extends Thread//播放音效的类
{
	private String soundURL;//音效路径
	
	private Sound nextSound;//下一个播放的音效
	
	public Sound(String soundURL)//普通的构造函数
	{
		this.soundURL = soundURL;
		this.nextSound = null;
	}
	
	public Sound(String soundURL, Sound nextSound)//重载构造函数，参数中增加了下一个播放的音效
	{
		this.soundURL = soundURL;
		this.nextSound = nextSound;
	}
	
	@Override
	public void run()
	{
		Player player = null;
		try
		{
			//声明一个File对象
			File mp3 = new File(soundURL);
			
			//创建一个输入流
			FileInputStream fileInputStream = new FileInputStream(mp3);
			
			//创建播放器对象，把文件的输入流传进去
			player = new Player(fileInputStream);
			
			//调用播放方法进行播放
			player.play();
		}catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "读取音效文件失败！", "奇怪的错误出现了！", JOptionPane.ERROR_MESSAGE);
		}catch(JavaLayerException e)
		{
			e.printStackTrace();
		}
		
		if(nextSound != null)//如果下一个播放的音效不为空（即调用了带有下一个播放音效的构造函数），则播放下一个音效
		{
			nextSound.start();
		}
	}
}
