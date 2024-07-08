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
        navigator = new KodaiNaiveLeftEdgeTracer(); // 初期状態は左エッジトレーサー
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        
        KodaiNaiveLeftEdgeTracer navL = new KodaiNaiveLeftEdgeTracer();
        KodaiNaiveRightEdgeTracer navR = new KodaiNaiveRightEdgeTracer();

        while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
            if (Button.ENTER.isDown()) {
                switchTracer();
                while (Button.ENTER.isDown()) {
                }
            }
            
            // 色によってトレーサーを切り替え（黒→右、白→左）
            int colorId = colorChecker.getColorId();
            if (colorId == Color.BLACK) {
                navigator = navR; // 黒を検出したら右エッジトレーサー
            } else if (colorId == Color.WHITE) {
                navigator = navL; // 白を検出したら左エッジトレーサー
            }

            navigator.decision(colorChecker, driver);
        }
    }

    private void switchTracer() {
        // ENTERボタンによる切り替えはそのまま
        if (isLeftTracer) {
            navigator = new KodaiNaiveLeftEdgeTracer();
        } else {
            navigator = new KodaiNaiveRightEdgeTracer();
        }
        isLeftTracer = !isLeftTracer;
    }
}
