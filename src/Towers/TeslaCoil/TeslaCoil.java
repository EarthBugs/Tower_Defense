package Towers.TeslaCoil;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 18:02
*/

import GameLogic.GameMap;
import Towers.Tower;

import java.awt.*;
import java.io.IOException;

public class TeslaCoil extends Tower
{
	public TeslaCoil(Point location) throws IOException
	{
		super(location, "src\\Images\\TeslaCoil\\TeslaCoil_", new TeslaCoil_Weapon(100, 256, 100));//初始化towerWeapon);
		this.setPrice(500);
	}
	
	public TeslaCoil(GameMap map) throws IOException
	{
		super("src\\Images\\TeslaCoil\\TeslaCoil_", new TeslaCoil_Weapon(100, 256, 100), map);//初始化towerWeapon);
		this.setPrice(500);
	}
}