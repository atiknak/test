package test;

import javafx.scene.paint.Color;

import java.util.List;

import fedorabots.client.Robot;
import fedorabots.client.sensor.DetectedEntity;

public class SampleBot extends Robot{

	//Using constants is good style, no magic numbers
		public static final Color BOT_COLOR = Color.LIGHTSALMON;
		public static final int DIFFICULTY = 1;
		
		
		public static void main(String[] args){
			new SampleBot().run();
		}
		
		public void run(){
			//Set a unique color so we can tell you apart
			setColor(BOT_COLOR);
			//Swap to a networked game when you want to compete with others
			joinLocalGame(DIFFICULTY);
			while(!isDead()){
				//Do something, how will your robot work?
				//int a = 0;
				//int r = 0;
				getHealth();
				setAcceleration(5, 0);
				shoot();
				List<DetectedEntity> nearby = nearbyEntities();
				if (nearby.size() > 0)
				{
					for(DetectedEntity e: nearby){
						double x = e.getX();
						double y = e.getY();
						setBlasterRotation(90 - 180 * Math.atan2(-y + getY(), x - getX()) / Math.PI);
					}
					//setAcceleration(a, 0);
					if(canShoot())
						shoot();
					//a++;
					//r = r + 90;
					
				}
			}
		}
}