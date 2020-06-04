package Enemies.RhinoHeavyTank;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 13:44
*/

import Enemies.Enemy;
import GameLogic.GameMap;

import java.awt.*;
import java.io.IOException;

public class RhinoHeavyTank extends Enemy
{
	public RhinoHeavyTank(Point position, GameMap map) throws IOException
	{
		super(position, map, 0.05f, 250, 20, 'G', 'H', "C:\\Users\\HJQ\\Documents\\JAVA测试工程\\Tower Defense\\src\\Images\\RhinoHeavyTank\\RhinoHeavyTank_");
	}
}