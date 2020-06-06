package Towers.TeslaCoil;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 18:02
*/

import Towers.Tower;

import java.awt.*;
import java.io.IOException;

public class TeslaCoil extends Tower
{
	public TeslaCoil(Point position) throws IOException
	{
		super(position, "src\\Images\\TeslaCoil\\TeslaCoil_");
		
		System.out.println("已构造：TeslaCoil");
	}
}