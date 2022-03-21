package com.nurullahkarakoca.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SurvivorBird extends ApplicationAdapter {
	Texture background;
	SpriteBatch batch;
	Texture bird;
	Texture enemy1;
	Texture enemy2;
	Texture enemy3;
	float birdX;
	float birdY;
	int gameState=0;
	float velocity =0;
	float gravity=0.8f;
	float enemyGravity=0.001f;
	float enemyVelocity=9;


	int numberOfEnemies=4;
	float[] enemyX =new float[numberOfEnemies];
	float distance=0;




	
	@Override
	public void create () {
		batch=new SpriteBatch();
		background=new Texture("Background.png");
		bird =new Texture("bird.png");
		enemy1=new Texture("enemy.png");
		enemy2=new Texture("enemy.png");
		enemy3=new Texture("enemy.png");
		birdX=Gdx.graphics.getWidth()/4;
		birdY=Gdx.graphics.getHeight()/2;

		distance=Gdx.graphics.getWidth()/2;


		for (int i=0; i<numberOfEnemies; i++){
			enemyX[i]=Gdx.graphics.getWidth() - enemy1.getWidth() / 2 + i * distance;

		}



	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState==1){
			if (Gdx.input.justTouched()){
				velocity=-15;
			}


			for (int i=0; i<numberOfEnemies; i++){

				if (enemyX[i]<0){
					enemyX[i]=enemyX[i]+numberOfEnemies*distance;

				}else{
					enemyVelocity=enemyVelocity+enemyGravity;
					enemyX[i]=enemyX[i]-enemyVelocity;
				}



				batch.draw(enemy1,enemyX[i],50,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(enemy2,enemyX[i],150,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(enemy3,enemyX[i],350,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);

			}






			if (birdY>0||velocity<0){
				velocity=velocity+gravity;
				birdY=birdY-velocity;
			}
			if (birdY>Gdx.graphics.getHeight()-105){
				birdY=Gdx.graphics.getHeight()-105;
			}



		}else{
			if (Gdx.input.justTouched()){
				gameState=1;
			}
		}


		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
