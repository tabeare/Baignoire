package baignoire;

public class Baignoire {

	private double fill;
	private Integer capacite;
	
	public Baignoire(Integer capacite) {
		this.fill = 0; // initial fill = 0; fill en cl
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
		return true;
	}
	
	public double temps(double sup, double ded) {
		double libre = this.capacite - this.fill; // volume d'espace qui est encore libre dans la baignoire en cl
		double vitesse = sup - ded; // vitesse de l'eau entrant en cl/s
		if (vitesse > 0) {
			double temps = Math.round(libre/vitesse*10.00)/10.00; // temps qu'il faut encore pour remplir la baignoire
			return temps;
		}
		return Double.POSITIVE_INFINITY;
	}
	
}
