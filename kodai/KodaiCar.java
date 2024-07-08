package jp.ac.kanazawait.ep.kodai;
import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class KodaiCar extends AbstCar {
	private boolean isLeftTracer = true;
 
	public static void main(String[] args) {
		TimeKeeper car = new KodaiCar();
		car.start();
	}
	
	public KodaiCar() {
		colorChecker = ColorCheckerThread.getInstance();
		driver = new KodaiNaiveDriver("B", "C");
		navigator = new KodaiNaiveLeftEdgeTracer(); // 初期トレーサーを設定
	}
	
	@Override
	public void run() {
		while (!Button.ESCAPE.isDown() && colorChecker.getColorId() !=Color.RED) {
			// Enter キーが押された場合にトレーサーを切り替える
			if (Button.ENTER.isDown()) {
				switchTracer();
				// ボタンが押されたと認識するための待ち時間
				while (Button.ENTER.isDown()) {
					// ボタンが放されるまで待機
				}
			 }
				navigator.decision(colorChecker, driver);
		}
	}
 
	private void switchTracer() {
		if (isLeftTracer) {
			navigator = new KodaiNaiveLeftEdgeTracer();
		} else {
			navigator = new KodaiNaiveRightEdgeTracer();
		}
		isLeftTracer = !isLeftTracer;
	}
}