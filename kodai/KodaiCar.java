package jp.ac.kanazawait.ep.kodai;

import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class KodaiCar extends AbstCar {
    private boolean isLeftTracer = true;
    private int previousColorId = -1; // 前回のカラーIDを保持する変数

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

         // シアン以外の色からシアンに変化した場合にトレーサーを切り替える
            if (currentColorId != Color.CYAN && previousColorId == Color.CYAN) {
                switchTracer();
            }


            previousColorId = currentColorId; // 現在のカラーIDを保存
            navigator.decision(colorChecker, driver);
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