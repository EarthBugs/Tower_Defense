package Towers.TeslaCoil;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 15:25
    TeslaCoil的武器
*/

import Audio.Sound;
import Towers.TowerWeapon;

public class TeslaCoil_Weapon extends TowerWeapon
{
	private String fireSoundURL = "src\\Audio\\";//开火音效路径
	
	protected TeslaCoil_Weapon(int damage, int fireRange, int fireTimeInterval)
	{
		super(damage, fireRange, fireTimeInterval, 1500);
	}
	
	@Override
	public synchronized void playFireSound()//播放开火的音效
	{
		System.out.println("已播放开火音效：TeslaCoil");
		
		long randomNum = System.currentTimeMillis();
		int intRan = (int) (randomNum % 2);
		
		Sound attackSound = new Sound(fireSoundURL + "磁暴线圈攻击" + intRan + ".mp3");
		
		new Sound(fireSoundURL + "磁暴线圈蓄能.mp3", attackSound).start();//构造蓄能音效
	}
}
