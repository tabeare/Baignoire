package baignoire;

public class Baignoire {

	private Integer fill;
	private Integer capacite;
	
	public Baignoire(Integer capacite) {
		super();
		this.fill = 0;
		this.capacite = capacite; //capacité en cl
	}

	public Integer getFill() {
		return fill;
	}

	public void setFill(Integer fill) {
		this.fill = fill;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}
	
	public void remplirBaignoire(Integer sup) {
		if (this.fill + sup > this.capacite)
			this.fill = this.capacite;
		else
			this.fill = this.fill + sup;
	}
	
	public void viderBaignoire(Integer ded) {
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
	
//	public float temps() {
//		
//	}
	
	
}
