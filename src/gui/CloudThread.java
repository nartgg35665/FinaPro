package gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CloudThread {
	private int speed;
	private Image cloudImg;
	private ImageView cloudImgView;
	public CloudThread() {
		speed = 1;
		cloudImg = new Image("Cloud.png");
		cloudImgView = new ImageView(cloudImg);
		cloudImgView.relocate(0,0);
		cloudImgView.setFitHeight(150);
		cloudImgView.setFitWidth(350);
		Thread t = new Thread(()->{	
			updateCloudMovement(speed);	
		});
		t.start();
	}
	private void updateCloudMovement(int speed) {
		try {
			while(true) {
				Thread.sleep(50);
				if(cloudImgView.getLayoutX()+cloudImgView.getFitWidth()>1280 || cloudImgView.getLayoutX()<0) {
						speed = speed*-1;
				}
				final int speedIn = speed;
				Platform.runLater(new Runnable() {
					public void run() {
						cloudImgView.setLayoutX(cloudImgView.getLayoutX()+speedIn);
					}
				});
									
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
	}
	public ImageView getCloud() {
		return cloudImgView;
	}
}
