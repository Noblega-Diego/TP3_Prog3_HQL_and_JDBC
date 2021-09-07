 package com.tp3.progIII_TP3.hql.main;

import com.tp3.progIII_TP3.hql.modelo.Componente;
import com.tp3.progIII_TP3.hql.modelo.Computadora;
import com.tp3.progIII_TP3.hql.persistencia.ConfigHibernate;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;

/**
 *
 * @author diego
 */
public class Main {
    public static void main(String[] args){
        ConfigHibernate hibernate = new ConfigHibernate();
        EntityManager entityManager = hibernate.getSessionFactory().openSession();
        
        Computadora computadora = pedirComputadora();
        do{
            System.out.println("   ingrese el componenete   ");
            pedirComponente(computadora);
        }while(siCotinuar("Continuar Agregarndo Componentes (s): "));
        
        
        entityManager.getTransaction().begin();
        //operaciones en la base de datos
        entityManager.persist(computadora);
        
        entityManager.getTransaction().commit();
        
        // mostramos las computadoras
        List<Computadora> stock = ( List<Computadora> ) entityManager.createQuery("FROM Computadora").getResultList();
        mostrarComputadoras(stock);
        
        entityManager.close();
    }
    
    private static void mostrarComputadoras(List<Computadora> computadoras) {
        for(Computadora c : computadoras){
            System.out.println("-------------------------------------");
            System.out.println("  cod:" + c.getCodigo());
            System.out.println("  marca:" + c.getMarca());
            System.out.println("  modelo:" + c.getModelo());
            System.out.println("  Componentes:");
            for (int i = 0; i < c.getComponentes().size(); i++) {
                System.out.println("   |-- nombre:" + c.getComponentes().get(i).getNombre());
                System.out.println("   |   nro Serie:" + c.getComponentes().get(i).getNroSerie());
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
