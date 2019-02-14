package baignoire;

public class Baignoire {

	private double fill;
	private Integer capacite;
	
	public Baignoire(Integer capacite) {
		super();
		this.fill = 0;
		this.capacite = capacite; //capacité en cl
	}

	public double getFill() {
		return fill;
	}

	public void setFill(double fill) {
		this.fill = fill;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}
	
	public void remplirBaignoire(double sup) {
		if (this.fill + sup > this.capacite)
			this.fill = this.capacite;
		else
			this.fill = this.fill + sup;
	}
	
	public void viderBaignoire(double ded) {
		if (this.fill - ded < 0)
			this.fill = 0;
		else
			this.fill = this.fill - ded;
	}
	
	public boolean isFull() {
		if (fill < capacite) 
			return false;
		else
			return true;
	}
	
	public String temps(double sup, double ded) {
		double libre = this.capacite - this.fill;
		double vitesse = sup - ded;
		Integer temps = (int) Math.round((libre/vitesse)/60);
		return Double.toString(temps);
	}
	
}
