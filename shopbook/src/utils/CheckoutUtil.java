package utils;

public class CheckoutUtil {
	public static float usd(int vnd) {
		float kq = (float) vnd / 22000;
		kq = (float) Math.round(kq * 100) / 100;
		return kq;
	}
}
