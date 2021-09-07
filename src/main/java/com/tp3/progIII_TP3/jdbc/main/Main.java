package com.tp3.progIII_TP3.jdbc.main;

import com.tp3.progIII_TP3.jdbc.dao.daoComputadora;
import com.tp3.progIII_TP3.jdbc.modelo.Componente;
import com.tp3.progIII_TP3.jdbc.modelo.Computadora;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Main {
    public static void main(String[] args){
        
        daoComputadora persisComputadora = new daoComputadora();
        Computadora computadora = pedirComputadora();
        do{
            System.out.println("   ingrese el componenete   ");
            pedirComponente(computadora);
        }while(siCotinuar("Continuar Agregarndo Componentes (s): "));
        
        persisComputadora.guardar(computadora);
        mostrarComputadoras(persisComputadora.listarComputadoras());
        persisComputadora.finalizarConexion();
    }
    
    private static void mostrarComputadoras(List<Computadora> computadoras) {
        for(Computadora c : computadoras){
            System.out.println("-------------------------------------");
            System.out.println("  cod:" + c.getCodigo());
            System.out.println("  marca:" + c.getMarca());
            System.out.println("  modelo:" + c.getModelo());
            System.out.println("  Componentes:");
            for (Componente componente : c.getComponentes()) {
                System.out.println("   |-- nombre:" + componente.getNombre());                System.out.println("   |-- " + componente.getNombre());
                System.out.println("   |   nro Serie:" + componente.getNroSerie());
            }
        }
    }

    private static Computadora pedirComputadora() {
        Computadora computadora;
        String marca, modelo, codigo;
        System.out.println("marca:");
        marca = new Scanner(System.in).nextLine();
        System.out.println("modelo:");
        modelo = new Scanner(System.in).nextLine();
        System.out.println("codigo:");
        codigo = new Scanner(System.in).nextLine();
        
        computadora = new Computadora(codigo, marca, modelo);
        return computadora;
    }

    private static void pedirComponente(Computadora computadora) {
        Componente componente;
        String nombre, nroSerie;
        System.out.println("nombre:");
        nombre = new Scanner(System.in).nextLine();
        System.out.println("nroSerie:");
        nroSerie = new Scanner(System.in).nextLine();
        componente = new Componente(nombre, nroSerie, computadora);
        computadora.addComponente(componente);
    }

    private static boolean siCotinuar(String message) {
        System.out.println(message);
        return (new Scanner(System.in).nextLine().equalsIgnoreCase("s"));
    }
}
