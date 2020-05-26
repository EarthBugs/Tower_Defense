package GameLogic;/*
    @Auther TknHJQ
    @Create date 2020/5/21 - 20:48
    计划用于绘制攻击效果，因工期紧搁置
    BUG：设置透明背景不成功，若添加到JFrame中，则会遮挡GameMap层的内容
*/

import Images.VideoPlayer.imagePainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameEffects extends JPanel
{
	private ArrayList effectList;//负责储存塔的攻击效果，其中的每个元素都对应一个塔
	private Iterator iterator;//集合的迭代器
	
	public GameEffects()
	{
		//设置透明背景
		this.setBackground(null);
		this.setOpaque(false);
		
		//设置存储效果的集合
		effectList = new ArrayList();
		effectList.iterator();
	}
	
	public void getEffect(String effectName, int towerIndex, Image image)
	{
		effectList.add(new imagePainter(effectName, towerIndex, image));
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		for(int i = 0; i < effectList.size(); i++)
		{
			//使用迭代器来遍历每一个集合内的元素
		}
	}
}