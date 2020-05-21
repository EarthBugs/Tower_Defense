package Images.VideoPlayer;/*
    @Auther TknHJQ
    @Create date 2020/5/18 - 20:51
    计划用于绘制攻击效果，因工期紧搁置
*/

import java.awt.*;

public class imagePainter
{
	private String effectName;//特效名称
	private int towerIndex;//特效对应的塔的序号
	private Image image;//图像
	
	public imagePainter(String effectName, int towerIndex, Image image)//构造函数
	{
		this.effectName = effectName;
		this.towerIndex = towerIndex;
		this.image = image;
	}
	
	public void start(Graphics g, Point position)
	{
		g.drawImage(image, position.x, position.y, null);
	}
}