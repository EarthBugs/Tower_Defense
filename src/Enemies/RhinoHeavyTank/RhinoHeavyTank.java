package Enemies.RhinoHeavyTank;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 13:44
*/

import Enemies.Enemy;
import GameLogic.GameMap;

import java.io.IOException;

public class RhinoHeavyTank extends Enemy
{
	public RhinoHeavyTank(GameMap map) throws IOException
	{
		super(map, 0.005f, 250, 500, 20, 'G', 'H', "C:\\Users\\HJQ\\Documents\\JAVA测试工程\\Tower Defense\\src\\Images\\RhinoHeavyTank\\RhinoHeavyTank_");
	}
}