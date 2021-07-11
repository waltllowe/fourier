import java.util.Arrays;

public class input {
	public static double[][] CFourierSeries(double[][] coords) {
		int N = coords.length;
		int Nhalf;
		if (N % 2 == 0) {
			Nhalf = N / 2;
		} else {
			Nhalf = (N - 1) / 2;
		}

		double[][] output = new double[N][3];
		for (int k = 0; k < N; k++) {
			for (int n = 0; n < N; n++) {
				double angle = (2 * Math.PI / N) * (k - Nhalf) * (n - Nhalf);
				output[k][0] += coords[n][0] * Math.cos(angle) + coords[n][1] * Math.sin(angle);
				output[k][1] += -coords[n][0] * Math.sin(angle) + coords[n][1] * Math.cos(angle);
			}
			output[k][0] /= N;
			output[k][1] /= N;
			output[k][2] = k - Nhalf;
		}
		System.out.println(Arrays.deepToString(output));
		output = ChangeToPolar(output);
		Arrays.sort(output, (a, b) -> Double.compare(b[0], a[0]));
		return output;
	}

	public static double[][] ChangeToPolar(double[][] FSeries) {
		double[] r = new double[FSeries.length];
		double[] theta = new double[FSeries.length];
		double[][] polar = new double[FSeries.length][3];
		for (int i = 0; i < FSeries.length; i++) {
			r[i] = Math.hypot(FSeries[i][0], FSeries[i][1]);
			if (r[i] == 0) {
				theta[i] = 0;
			} else if (FSeries[i][0] < 0) {
				theta[i] = Math.PI - Math.asin(FSeries[i][1] / r[i]);
			} else {
				theta[i] = Math.asin(FSeries[i][1] / r[i]);
			}
			polar[i][0] = r[i];
			polar[i][1] = theta[i];
			polar[i][2] = FSeries[i][2];
		}

		return polar;

	}

	public input() {

	}

}
