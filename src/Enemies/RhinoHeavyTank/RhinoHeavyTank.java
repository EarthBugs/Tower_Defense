package Enemies.RhinoHeavyTank;/*
    @Auther TknHJQ
    @Create date 2020/5/28 - 13:44
*/

import Enemies.Enemy;

import java.awt.*;
import java.io.IOException;

public class RhinoHeavyTank extends Enemy
{
	public RhinoHeavyTank(Point position) throws IOException
	{
		super(position, 4, 250, 20, 'G', "HTank", "C:\\Users\\HJQ\\Documents\\JAVA测试工程\\Tower Defense\\src\\Images\\RhinoHeavyTank\\RhinoHeavyTank_");
	}
}