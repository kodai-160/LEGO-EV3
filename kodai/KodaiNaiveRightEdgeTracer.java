package jp.ac.kanazawait.ep.kodai;

import jp.ac.kanazawait.ep.majorlabB.driver.AbstDriver;

/**
 * 堅実な走行を行うドライバークラス
 * @author mmotoki
 *
 */
public class KodaiNaiveDriver extends AbstDriver {

	final int speedNormal = 538;
	final int speedLow = 148;

	/**
	 * 左モーターを "B" に，右モーターを "C" に接続した状態のコンストラクタ
	 */
	public KodaiNaiveDriver() {
		this("B", "C");
	}

	/**
	 *
	 * @param portLeft	左モーターを接続したポート（"A"～"D"の４つのいずれか）
	 * @param portRight	右モーターを接続したポート（"A"～"D"の４つのいずれか）
	 */
	public KodaiNaiveDriver(String portLeft, String portRight) {
		setMotor(portLeft, portRight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void goStraight() {
		setSpeed(speedNormal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeft() {
		setSpeed(speedLow, speedNormal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRight() {
		setSpeed(speedNormal, speedLow);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeftSharply() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRightSharply() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeftGently() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRightGently() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void increaseSpeed() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decreaseSpeed() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

}
