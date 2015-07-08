package alexiil.utils.math;

public class MathHelper
	{
		private static double[]	sin	= new double[360];

		static
			{
				for (int i = 0; i < 360; i++)
					sin[i] = Math.sin((i / 360D) * Math.PI * 2);
				sin[0] = 0;
				sin[180] = 0;

				double diff = 1 / 3D;
				for (double i = 0; i < 93; i += diff)
					{
						double mathSin = Math.sin(i / 360D * Math.PI * 2);
						double mySin = sin((int) i);
						double mySinInterp = sin(i);
						double diffMathSinMySin = mathSin - mySin;
						System.out.println("sin(" + i + ") =" + mySin + ", interp=" + mySinInterp + ", math=" + mathSin + ", diff=" + diffMathSinMySin);
					}
			}

		/**
		 * @param number
		 *            The number to round down
		 * @return The closest integer that is less than or equal to the number
		 */
		public static int roundDown(double number)
			{
				int num = (int) Math.round(number);
				if (num > number)
					return num - 1;
				return num;
			}

		/**
		 * @param number
		 *            The number to round up
		 * @return The closest integer that is greater than or equal to the number
		 */
		public static int roundUp(double number)
			{
				int num = (int) Math.round(number);
				if (num < number)
					return num + 1;
				return num;
			}

		/**
		 * @param degrees
		 *            The angle in degrees
		 * @return Mathematical sine of the angle
		 */
		public static double sin(int degrees)
			{
				while (degrees < 0)
					degrees += 360;
				while (degrees >= 360)
					degrees -= 360;
				return sin[degrees];
			}

		/**
		 * @param degrees
		 *            The angle in degrees
		 * @return Mathematical sine of the angle, interpolated
		 */
		public static double sin(double degrees)
			{
				while (degrees < 0)
					degrees += 360;
				while (degrees > 360)
					degrees -= 360;
				int angBelow = roundDown(degrees);
				double above = sin(roundUp(degrees));
				double below = sin(angBelow);
				double diff = above - below;
				return (below + diff * angBelow);
			}

		/**
		 * @param radians
		 *            The angle in radians
		 * @return Mathematical sine of the angle, interpolated
		 */
		public static double sinr(double radians)
			{
				return sin(radians * 360D / Math.PI / 2D);
			}

		/**
		 * @param degrees
		 *            The angle in degrees
		 * @return Mathematical cosine of the angle
		 */
		public static double cos(int degrees)
			{
				return sin(degrees + 90);
			}

		/**
		 * @param degrees
		 *            The angle in degrees
		 * @return Mathematical cosine of the angle, interpolated
		 */
		public static double cos(double degrees)
			{
				return sin(degrees);
			}

		/**
		 * @param radiansn
		 *            The angle in radians
		 * @return Mathematical cosine of the angle, interpolated
		 */
		public static double cosr(double radians)
			{
				return sin((radians * 360D / Math.PI / 2D) + 90D);
			}
	}
