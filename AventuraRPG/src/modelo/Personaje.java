package modelo;

public class Personaje {
	
	private String nombre;
	private String liga; //Justicia, Terror, Neutral, Picaro, Juvenil
	private int vida; 
	private int ataque;
	private int ataqueEspecial;
	private int defensa;
	private int velocidad;
	private static int contadorPersonajes; //Para controlar que no se creen más de dos personajes en la misma partida
	
	public Personaje() {
		//Constructor vacio por defecto
	}
	
	public Personaje(String nombre, String liga) {
		this.nombre = nombre;
		this.liga = liga;
		//En este constructor generamos los valores numericos de manera aletoria entre 1 y 50
		this.vida = (int) Math.floor(Math.random()*500+1);
		this.ataque = (int) Math.floor(Math.random()*100+1);
		this.ataqueEspecial = (int) Math.floor(Math.random()*50+1);
		this.defensa = (int) Math.floor(Math.random()*70+1);
		this.velocidad = (int) Math.floor(Math.random()*50+1);
		Personaje.contadorPersonajes++;
	}

	public Personaje(String nombre, String liga, int vida, int ataque, int ataqueEspecial, int defensa, int velocidad) {
		this.nombre = nombre;
		this.liga = liga;
		this.vida = vida;
		this.ataque = ataque;
		this.ataqueEspecial = ataqueEspecial;
		this.defensa = defensa;
		this.velocidad = velocidad;
		Personaje.contadorPersonajes++;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public void atacar(Personaje p) {
		this.ataque=(int) Math.floor(Math.random()*this.ataque+1);
		p.vida=(p.vida+p.defensa)-(this.ataque);
	}
	
	public void atacarEspecial(Personaje p) {
		this.ataque=(int) Math.floor(Math.random()*this.ataque+1);
		p.vida=(p.defensa+p.vida)-(this.ataque+this.ataqueEspecial);
	}
	
	public int getContadorPersonajes() {
		return contadorPersonajes;
	}

	public void setContadorPersonajes(int contadorPersonajes) {
		Personaje.contadorPersonajes = contadorPersonajes;
	}

	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + ", liga=" + liga + ", vida=" + vida + ", ataque=" + ataque
				+ ", ataqueEspecial=" + ataqueEspecial + ", defensa=" + defensa + ", velocidad=" + velocidad + "]";
	}
	
	

}
