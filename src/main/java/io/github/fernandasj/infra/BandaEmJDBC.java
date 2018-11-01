package io.github.fernandasj.infra;

import io.github.fernandasj.domain.Banda;
import io.github.fernandasj.domain.InterfaceDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class BandaEmJDBC implements InterfaceDAO<Banda>{
    private Connection connection;
    
    public BandaEmJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/albuns",
                    "fernanda", "123"
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void salvar(Banda banda) {
        try {
            PreparedStatement createStatement = this.connection.prepareStatement(
                    "INSERT INTO banda(nome, localDeOrigem, integrantes) VALUES (?,?,?);"
            );
            createStatement.setString(1, banda.getNome());
            createStatement.setDate(2, Date.valueOf(banda.getLocalDeOrigem()));
            createStatement.setString(3, banda.getIntegrantes().toString());
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void excluir(Banda banda) {
        try{
            if(buscar(banda.getIdBanda()) != null){
                PreparedStatement createStatement = this.connection.prepareStatement(
                    "DELETE FROM banda WHERE idBanda = ?"
                );
                createStatement.setInt(1, banda.getIdBanda());
                createStatement.executeUpdate();   
            }
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public Banda buscar(int id) {
        try{
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM banda WHERE idBanda = "+id);
            if(result.next()){
                return criarBanda(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; 
    }

    @Override
    public void atualizar(Banda banda) {
        try{
            if(buscar(banda.getIdBanda()) != null){
                PreparedStatement createStatement = this.connection.prepareStatement(
                        "UPDATE banda SET nome = ?, localDeOrigem = ?, integrantes = ?"
                                + "WHERE idBanda = ?"
                );
                createStatement.setString(1, banda.getNome());
                createStatement.setString(2, banda.getLocalDeOrigem());
                createStatement.setString(3, String.valueOf(banda.getIntegrantes()));
                createStatement.setInt(4, banda.getIdBanda());
                createStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Banda> todosOsObjetos() {
        List<Banda> lista = new ArrayList<>();
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM banda;");
            iterarComBandas(result, lista);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    private void iterarComBandas(ResultSet result, List<Banda> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                criarBanda(result)
            );
        }
    }
    
    private Banda criarBanda(ResultSet result) throws SQLException {
        int idBanda = result.getInt("idBanda");
        String nome = result.getString("nome");
        String localDeOrigem = result.getString("localDeOrigem");
        String integrantes = result.getString("integrantes");
        
        List<String> listaItegrantes = new ArrayList<String>(Arrays.asList(integrantes.split(",")));

        return new Banda(idBanda, nome, localDeOrigem, listaItegrantes);
    } 
}
