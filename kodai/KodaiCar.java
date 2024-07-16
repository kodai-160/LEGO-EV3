package jp.ac.kanazawait.ep.kodai;

import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class KodaiCar extends AbstCar {
    private boolean isLeftTracer = true;
    private int previousColorId = -1; // 前回のカラーIDを保持する変数
    private long cyanDetectedTime = -1; // シアンを検出した時間を保持する変数

    public static void main(String[] args) {
        TimeKeeper car = new KodaiCar();
        car.start();
    }

    public KodaiCar() {
        colorChecker = ColorCheckerThread.getInstance();
        driver = new KodaiNaiveDriver("B", "C");
        navigator = new KodaiNaiveLeftEdgeTracer(); 
    }

    @Override
    public void run() {
        while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
            int currentColorId = colorChecker.getColorId();

            // Enter キーが押された場合にトレーサーを切り替える
            if (Button.ENTER.isDown()) {
                switchTracer();
                while (Button.ENTER.isDown()) { /* wait */ }
            }

            // シアン以外の色からシアンに変化したときにトレーサーを切り替える
            if (currentColorId == Color.CYAN) {
                if (cyanDetectedTime == -1) {
                    cyanDetectedTime = System.currentTimeMillis(); // シアンを検出した時間を記録
                } else if (System.currentTimeMillis() - cyanDetectedTime >= 300) {
                    switchTracer();
                    cyanDetectedTime = -1; // 時間をリセット
                }
            } else {
                cyanDetectedTime = -1; // シアン以外の色の場合はリセット
            }

            navigator.decision(colorChecker, driver);
            previousColorId = currentColorId; // 現在のカラーIDを保存
        }
    }

    private void switchTracer() {
        if (isLeftTracer) {
            navigator = new KodaiNaiveRightEdgeTracer();
        } else {
            navigator = new KodaiNaiveLeftEdgeTracer();
        }
        isLeftTracer = !isLeftTracer;
    }
}
