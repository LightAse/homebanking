package Model;

import Controlador.Transferencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTransferencia implements DAO<Transferencia> {

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:~/base";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Transferencia elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO TRANSFERENCIA values (?,?,?,?,?,?)");
            preparedStatement.setLong(1,elemento.getId());
            preparedStatement.setString(2, elemento.getDestinatario());
            preparedStatement.setString(3, elemento.getRemitente());
            preparedStatement.setDouble(4, elemento.getCantidad());
            preparedStatement.setString(5, elemento.getMotivo());
            preparedStatement.setString(6, elemento.getFecha());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(Transferencia elemento) throws DAOException {

    }

    @Override
    public void eliminar(long id) throws DAOException {

    }

    @Override
    public Transferencia buscar(long id) throws DAOException {
        return null;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        return null;
    }
}
