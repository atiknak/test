package test;

import javafx.scene.paint.Color;

import java.util.List;

import fedorabots.client.Robot;
import fedorabots.client.sensor.DetectedEntity;

public class SampleBot2 extends Robot {
	
	//Using constants is good style, no magic numbers
	public static final Color BOT_COLOR = Color.DEEPSKYBLUE;
	public static final int DIFFICULTY = 1;
	
	public static void main(String[] args){
		new SampleBot2().shootAtIt();
	}
	
	public void run(){
		//Set a unique color so we can tell you apart
		setColor(BOT_COLOR);
		//Swap to a networked game when you want to compete with others
		joinLocalGame(DIFFICULTY);
		while(!isDead()){
			//Do something, how will your robot work?
			System.out.println(getHealth());
			for(int i=1; i<=4; i++)
			{
				setBlasterRotation(90*i);
				if(canShoot()) 
					shoot();
			}
		}
	}
	
	public void shootAtIt() {
		setColor(BOT_COLOR);
		//Swap to a networked game when you want to compete with others
		joinLocalGame(DIFFICULTY);
		while(!isDead())
		{
			List <DetectedEntity> nearby = nearbyEntities();

			for(DetectedEntity e: nearby)
			  System.out.println(e.getX() + "," + e.getY());

			DetectedEntity entity;
			if(nearby.size() > 0)
			{
				entity = nearby.get(0);
				int x = entity.getX();
				int y = entity.getY();
				if(canShoot())
				{
					setBlasterRotation(90 - 180 * Math.atan2(-y + getY(), x - getX()) / Math.PI);
					shoot();
				}
				setAcceleration(5,3);
			}
		}
	}

}