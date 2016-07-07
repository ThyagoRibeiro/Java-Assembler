package constantes;

public enum FlagTypeENUM {
	A(6), C(8), D(1), I(2), O(0), P(7), S(4), T(3), Z(5);

	public int column;

	FlagTypeENUM(int column) {
		this.column = column;
	}
}
