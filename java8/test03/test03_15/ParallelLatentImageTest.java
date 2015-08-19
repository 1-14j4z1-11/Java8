package test03_15;

import javafx.scene.image.*;
import ex03_12.LatentImage;
import ex03_15.ParallelLatentImage;
import test03_12.LatentImageTest;

public class ParallelLatentImageTest extends LatentImageTest
{
	/*
	 * テスト内容自体はLatentImageTestで行う
	 * 生成するインスタンスをLatentImage->ParallelLatentImageに変更
	 */
	
	@Override
	protected LatentImage createInstance(Image in)
	{
		return ParallelLatentImage.from(in);
	}
}
