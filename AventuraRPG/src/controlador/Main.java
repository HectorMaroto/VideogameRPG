package controlador;

import java.util.Scanner;

import modelo.Personaje;

public class Main {
	public static Scanner sc=new Scanner(System.in);
	
	public static int menuPrincipal() {
		int opcion=0;
		System.out.println("Escoje una opción:");
		System.out.println("-----Menú jugador-----");
		System.out.println("---Crear Jugador---: 1");
		System.out.println("------Combate------: 2");
		System.out.println("-------Salir-------: 3");
		System.out.println("----------------------");
		opcion=sc.nextInt();
		return opcion;
	}
	
	public static Personaje crearPersonaje(Personaje p) {
		System.out.println("Vamos a crear los personajes para jugar");
		System.out.println("¿Cómo desea crear a su personaje: Dar stats (1) o stats aleatorias (2)?");
		int opcion=sc.nextInt();
		sc.nextLine();//Limpiamos buffer
		if(opcion==1) {
			System.out.println("Dime el nombre de tu personaje");
			String nombre=sc.nextLine();
			System.out.println("Dime su liga: (JUSTICIA, TERROR, NEUTRAL, JUVENIL o PICARO)");
			String liga=sc.nextLine().toUpperCase(); //Hacemos que la liga siempre se lea en mayusculas.
			System.out.println("Puntos de vida:");
			int vida=sc.nextInt();
			System.out.println("Puntos de ataque:");
			int ataque=sc.nextInt();
			System.out.println("Puntos de ataque especial:");
			int ataqueEspecial=sc.nextInt();
			System.out.println("Puntos de defensa:");
			int defensa=sc.nextInt();
			System.out.println("Puntos de velocidad:");
			int velocidad=sc.nextInt();
			p=new Personaje(nombre,liga,vida,ataque,ataqueEspecial,defensa,velocidad);
			System.out.println(p+" creado con éxito!");
			
		}else if (opcion==2) { 
			//En este modo de crear personajes los valores numericos seran aleatorios
			System.out.println("Dime el nombre de tu personaje");
			String nombre=sc.nextLine();
			System.out.println("Dime su liga: (Justicia, terror, neutral, juvenil o picaro)");
			String liga=sc.nextLine().toUpperCase();
			p=new Personaje(nombre,liga);
			System.out.println(p+" creado con éxito!");
			
		}
		return p;
	}
	
	public static void personajeMuerto(Personaje p) {
		System.out.println(p+" ha caído en combate");
	}
	
	public static void cambiosStats(Personaje p, Personaje p1) {
		//Aqui se determina, dependiendo de que liga sea cada uno de los jugadores, como varían sus stats a la hora de combatir
		
		if(p.getLiga().equals("JUSTICIA") && p1.getLiga().equals("JUVENIL")) {
			p.setAtaque(p.getAtaque()+5);
			p1.setDefensa(p1.getDefensa()-3);
			p1.setAtaque(p1.getAtaque()+2);
		}else if(p.getLiga().equals("TERROR") && p1.getLiga().equals("JUSTICIA")) {
			p.setAtaqueEspecial(p.getAtaqueEspecial()+3);
			p.setAtaque(p.getAtaque()+2);
			p1.setDefensa(p1.getDefensa()+5);
		}else if(p.getLiga().equals("JUSTICIA") && p1.getLiga().equals("PICARO")) {
			p.setDefensa(p.getDefensa()-5);
			p.setAtaque(p.getAtaque()+7);
			p1.setDefensa(p1.getDefensa()+5);
		}else if(p.getLiga().equals("TERROR") && p1.getLiga().equals("NEUTRAL")) {
			p.setAtaqueEspecial(p.getAtaqueEspecial()+5);
			p1.setDefensa(p1.getDefensa()-6);
		}else if(p.getLiga().equals("JUVENIL") && p1.getLiga().equals("NEUTRAL")) {
			p1.setDefensa(p1.getDefensa()+3);
			p1.setAtaque(p1.getAtaque()+2);
			p.setDefensa(p.getDefensa()+5);
		}else if(p.getLiga().equals("PICARO") && p1.getLiga().equals("TERROR")) {
			p.setAtaqueEspecial(p.getAtaqueEspecial()+3);
			p1.setAtaque(p.getAtaque()+4);
		}
	}

	public static void main(String[] args) {
		int opcion=0;
		Personaje j1=new Personaje();
		Personaje j2=new Personaje();
		boolean jugadores=false;
		int opcionAtaqueJ1;
		int opcionAtaqueJ2;
		do {
			opcion=menuPrincipal();
			
			switch(opcion) {
			case 1:{
				//Creamos dos personajes para poder realizar el combate
				if(j1.getContadorPersonajes()<2) {
					j1=crearPersonaje(j1);
					j2=crearPersonaje(j2);
					jugadores=true;
				}else {
					System.out.println("No se pueden crear más personajes en esta partida");
					System.out.println("Pulsa intro para continuar...");
					sc.nextLine();
					sc.nextLine();
				}
					
				break;
			}
			case 2:{
				//Aqui se lleva a cabo el combate entre ambos personajes
				if(!jugadores) {
					System.out.println("Usuario antes de atacar debes crear los dos personajes para jugar");
					System.out.println("Pulsa intro para continuar...");
					sc.nextLine();
					sc.nextLine();
				}else {
					cambiosStats(j1,j2);//En la seccion de combate las stats de los jugadores variaran segun su liga y la de su oponente.
					System.out.println(j1+" se enfrentará a "+j2);
					System.out.println("Pulsa intro para continuar...");
					sc.nextLine();
					sc.nextLine();
					
					System.out.println("Es hora de luchar, el que tenga mayor velocidad empezará atacando");
					if(j1.getVelocidad()>j2.getVelocidad()) {
						System.out.println("Empezará atacando "+j1);
						System.out.println("Pulsa intro para empezar...");
						sc.nextLine();
						sc.nextLine();
						do {
							//Decidiremos que tipo de ataque hace cada jugador mediante un numero aleatorio
							j1.getVida();//Antes de cada turno comprobamos la vida del jugador para saber si puede atacar o no
							if(j1.getVida()>0) {
								System.out.println("¿Qué ataque va a realizar, jugador 1? Ataque(1) Ataque Especial(2)");
								opcionAtaqueJ1=sc.nextInt();
								if(opcionAtaqueJ1==1) {
									j1.atacar(j2);
									System.out.println("Vida - jugador 2: "+j2.getVida());
								}else if(opcionAtaqueJ1==2 && j1.getVida()>0){
									j1.atacarEspecial(j2);
									System.out.println("Vida - jugador 2: "+j2.getVida());
								}
							}
							
							j2.getVida();
							if(j2.getVida()>0) {
								System.out.println("¿Qué ataque va a realizar, jugador 2? Ataque(1) Ataque Especial(2)");
								opcionAtaqueJ2=sc.nextInt();
								if(opcionAtaqueJ2==1) {
									j2.atacar(j1);
									System.out.println("Vida - jugador 1: "+j1.getVida());
								}else if(opcionAtaqueJ2==2 && j2.getVida()>0){
									j2.atacarEspecial(j1);
									System.out.println("Vida - jugador 1: "+j1.getVida());
								}
							}
						
						}while(j1.getVida()>0 && j2.getVida()>0);
						
					}else if(j1.getVelocidad()<j2.getVelocidad()) {
						System.out.println("Empezará atacando "+j2);
						System.out.println("Pulsa intro para empezar...");
						sc.nextLine();
						sc.nextLine();
						do {
							
							j2.getVida();
							if(j2.getVida()>0) {
								System.out.println("¿Qué ataque va a realizar, jugador 2? Ataque(1) Ataque Especial(2)");
								opcionAtaqueJ2=sc.nextInt();
								if(opcionAtaqueJ2==1) {
									j2.atacar(j1);
									System.out.println("Vida - jugador 1: "+j1.getVida());
								}else if(opcionAtaqueJ2==2 && j2.getVida()>0) {
									j2.atacarEspecial(j1);
									System.out.println("Vida - jugador 1: "+j1.getVida());
								}
							}
							
							j1.getVida();
							if(j1.getVida()>0) {
								System.out.println("¿Qué ataque va a realizar, jugador 1? Ataque(1) Ataque Especial(2)");
								opcionAtaqueJ1=sc.nextInt();
								if(opcionAtaqueJ1==1) {
									j1.atacar(j2);
									System.out.println("Vida - jugador 2: "+j2.getVida());
								}else if(opcionAtaqueJ1==2 && j1.getVida()>0){
									j1.atacarEspecial(j2);
									System.out.println("Vida - jugador 2: "+j2.getVida());
								}
							}
							
						}while(j1.getVida()>0 && j2.getVida()>0);
					}
				}
				
				break;
			}
			case 3:{
				//No hace nada, es el caso de salida
				break;
			}
			}
		}while(opcion!=3 && j1.getVida()>0 && j2.getVida()>0 || j1.getNombre()==null); //Esta ultima condicion es para que no salga del programa si no metemos jugadores
		//El juego acaba si se da a la opcion de salir o uno de los jugadores muere
		
		if(opcion!=3) {
			if(j1.getVida()<=0){
				personajeMuerto(j1);
			}else if(j2.getVida()<=0) {
				personajeMuerto(j2);
			}
		}
		
		System.out.println("Fin del juego, vuelva a ejecutar el programa para una nueva partida");
		
		
		
		
		
		
		
		

	}

}
