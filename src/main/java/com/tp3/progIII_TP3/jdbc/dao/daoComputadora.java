
package com.tp3.progIII_TP3.jdbc.dao;

import com.tp3.progIII_TP3.jdbc.modelo.Componente;
import com.tp3.progIII_TP3.jdbc.modelo.Computadora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class daoComputadora extends Conexion{
    private Connection conexion;
    
    public boolean guardar(Computadora computadora){
        PreparedStatement ps;
        ResultSet rs;
        daoComponente daoCom;
        try {
            if(conexion == null)
                conexion = getConnection();
            daoCom = new daoComponente(conexion);
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement("INSERT INTO Computadoras(codigo, marca, modelo) values(?,?,?)");        
            ps.setString(1, computadora.getCodigo());            
            ps.setString(2, computadora.getMarca());
            ps.setString(3, computadora.getModelo());
            ps.executeUpdate();
            rs = conexion.prepareStatement("select last_insert_id() as id").executeQuery();
            rs.next();
            computadora.setId(rs.getLong("id"));
            for(Componente componente: computadora.getComponentes()){
                if(!daoCom.guardar(componente))
                    throw new Exception("no se agrego componente");
            }
            
            conexion.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(daoComputadora.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public List<Computadora> listarComputadoras(){
        
        PreparedStatement ps;
        ResultSet rs;
        List<Computadora> computadoras = new ArrayList<Computadora>();
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT * FROM Computadoras");
            rs = ps.executeQuery();
            while(rs.next()){
                Computadora computadora = new Computadora(
                        rs.getLong("id"), 
                        rs.getString("codigo"), 
                        rs.getString("marca"), 
                        rs.getString("modelo"));
                computadora.setComponentes(listarComponentes(computadora));
                computadoras.add(computadora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return computadoras;
    }

    private List<Componente> listarComponentes(Computadora computadora) {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Componente> componentes = new ArrayList<>();
        try {
            if(conexion == null)
                conexion = getConnection();
            ps = conexion.prepareStatement("SELECT * FROM Componentes WHERE computadoraId = "+ computadora.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                Componente componente = new Componente(
                        rs.getLong("id"), 
                        rs.getString("nombre"), 
                        rs.getString("nroSerie"), 
                        computadora);
                componentes.add(componente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return componentes;
    }
    
    public void finalizarConexion(){
        try{
            conexion.close();
            conexion = null;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
