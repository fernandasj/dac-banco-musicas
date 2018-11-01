package io.github.fernandasj.infra;

import io.github.fernandasj.domain.Album;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import io.github.fernandasj.domain.Estilo;
import io.github.fernandasj.domain.InterfaceDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class AlbumEmJDBC implements InterfaceDAO<Album>{
    
    private Connection connection;
    
    public AlbumEmJDBC() {
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
    public void salvar(Album album) {
        try {
            PreparedStatement createStatement = this.connection.prepareStatement(
                    "INSERT INTO album(estilo, banda, anoDeLancamento) VALUES (?,?,?);"
            );
            createStatement.setString(1, String.valueOf(album.getEstilo()));
            createStatement.setInt(2, album.getBanda());
            createStatement.setDate(3, Date.valueOf(album.getAnoDeLancamento()));
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void excluir(Album album) {
        try{
            if(buscar(album.getIdAlbum()) != null){
                PreparedStatement createStatement = this.connection.prepareStatement(
                    "DELETE FROM album WHERE idAlbum = ?"
                );
                createStatement.setInt(1, album.getIdAlbum());
                createStatement.executeUpdate();   
            }
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public Album buscar(int id) {
        try{
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM album WHERE idAlbum = "+id);
            if(result.next()){
                return criarAlbum(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; 
    }

    @Override
    public void atualizar(Album album) {
        try{
            if(buscar(album.getIdAlbum()) != null){
                PreparedStatement createStatement = this.connection.prepareStatement(
                        "UPDATE album SET estilo = ?, banda = ?, anoDeLancamento = ?"
                                + "WHERE idAlbum = ?"
                );
                createStatement.setString(1, String.valueOf(album.getEstilo()));
                createStatement.setInt(2, album.getBanda());
                createStatement.setDate(3, Date.valueOf(album.getAnoDeLancamento()));
                createStatement.setInt(4, album.getIdAlbum());
                createStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Album> todosOsObjetos() {
        List<Album> lista = new ArrayList<>();
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM album;");
            iterarComAlbuns(result, lista);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
   
    private void iterarComAlbuns(ResultSet result, List<Album> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                criarAlbum(result)
            );
        }
    }
    
    private Album criarAlbum(ResultSet result) throws SQLException {
        int idAlbum = result.getInt("idAlbum");
        String anoDeLancamento = result.getString("anoDeLancamento");
        String estilo = result.getString("estilo");
        int banda = result.getInt("banda");

        return new Album(idAlbum, Estilo.valueOf(estilo), banda, LocalDate.parse(anoDeLancamento));
    }
}