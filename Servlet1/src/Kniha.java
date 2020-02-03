import java.math.BigDecimal;

public class Kniha {
	
	String nazev;
	String autor;
	BigDecimal cena;
	int mnozstvi;
	
	public Kniha(String nazev, String autor, BigDecimal cena, int mnozstvi) {
		this.nazev = nazev;
		this.autor = autor;
		this.cena = cena;
		this.mnozstvi = mnozstvi;
	}

	public String getNazev() {
		return nazev;
	}

	public String getAutor() {
		return autor;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public int getMnozstvi() {
		return mnozstvi;
	}
	
	
}
