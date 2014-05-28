
/**
 * Self-describing Sequence #110607
 *
 * @author Matus Marko
 * @see http://oeis.org/A001462
 */
public class SelfdescribingSequence implements Runnable {

	public void run() {

		int n;

		String line;

		while (true) {

			line = Main.ReadLn(200000000);
			if (line == null) {
				return;
			}

			n = Integer.parseInt(line);

			if (n == 0) {
				continue;
			}

			System.out.println( this.formula( n ) );
		}
	}

	/**
	 * Formula
	 * a( n ) = phi^( 2 - phi ) * n^( phi - 1 ) + E( n )
	 *
	 * @param n
	 * @return long
	 */
	public double formula(int n) {
		return Math.round(
				Math.pow( this.phi(), ( 2 - this.phi() ) )
				* Math.pow( n, this.phi() - 1 )
		);
	}

	/**
	 * Golden number (Golden ratio)
	 * ( 1 + sqrt( 5 ) ) / 2
	 * 
	 * @return double
	 */
	public double phi() {
		return ( 1 + Math.sqrt( 5 ) ) / 2;
	}
}
