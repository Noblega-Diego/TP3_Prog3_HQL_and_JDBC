package com.tp3.progIII_TP3.jdbc.dao;

import com.tp3.progIII_TP3.jdbc.modelo.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class daoComponente extends Conexion{
    private Connection conexion;

    daoComponente(Connection conexion) {
        this.conexion = conexion;
    }
    daoComponente(){}
    
    public boolean guardar(Componente componente) {
        try {
            if(conexion == null)
                conexion = getConnection();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO componentes(nombre, nroSerie, computadoraid) values(?,?,?)");
            ps.setString(1, componente.getNombre());
            ps.setString(2, componente.getNroSerie());
            ps.setLong(3, componente.getComputadora().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void finalizarConexion(){
        try{
            conexion.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
