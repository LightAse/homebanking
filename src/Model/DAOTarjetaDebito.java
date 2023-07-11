package Model;

import Controlador.CuentaCajaDeAhorro;
import Controlador.CuentaCorriente;
import Controlador.TarjetaDebito;

import java.sql.*;
import java.util.ArrayList;

public class DAOTarjetaDebito implements DAO<TarjetaDebito>{

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:~/base";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(TarjetaDebito elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO TARJETADEBITO values (?,?,?,?,?,?,?)");
            preparedStatement.setLong(1,elemento.getId());
            preparedStatement.setString(2, elemento.getNumero());
            preparedStatement.setLong(3, elemento.getCbuCuentaOwner());
            preparedStatement.setLong(4, elemento.getCodigoDeSeguridad());
            preparedStatement.setString(5, elemento.getFechaDeVencimiento());
            preparedStatement.setDouble(6, 0);
            preparedStatement.setDouble(7,elemento.getDisponible());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(TarjetaDebito elemento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE TARJETADEBITO SET saldo=? where id=?");
            preparedStatement.setDouble(1, elemento.getSaldo());
            preparedStatement.setLong(2, elemento.getId());
            int res= preparedStatement.executeUpdate();
            System.out.println("Se modificaron "+ res);

        }catch(ClassNotFoundException | SQLException e){
            throw new DAOException(e.getMessage());

        }

    }

    @Override
    public void eliminar(long id) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM TARJETADEBITO  WHERE id=?");
            preparedStatement.setLong(1,id);
            int res=preparedStatement.executeUpdate();
            System.out.println("Se elimino" + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public TarjetaDebito buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        TarjetaDebito tarjetaDebito =null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM TARJETADEBITO WHERE ID=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                tarjetaDebito = new TarjetaDebito();
                tarjetaDebito.setId(resultSet.getLong("ID"));
                tarjetaDebito.setNumero(resultSet.getString("numero"));
                tarjetaDebito.setCodigoDeSeguridad(resultSet.getLong("codigodeseguridad"));
                tarjetaDebito.setFechaDeVencimiento(resultSet.getString("fechadevencimiento"));
                tarjetaDebito.setSaldo(resultSet.getDouble("saldo"));
                tarjetaDebito.setDisponible(resultSet.getDouble("disponible"));
                tarjetaDebito.setCbuCuentaOwner(resultSet.getLong("cbucuentaowner"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return tarjetaDebito;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<TarjetaDebito> datos=new ArrayList<>();
        TarjetaDebito user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM TARJETADEBITO");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new TarjetaDebito();
                user.setId(resultSet.getLong("ID"));
                user.setCbuCuentaOwner(resultSet.getLong("cbucuentaowner"));
                user.setNumero(resultSet.getString("numero"));
                user.setCodigoDeSeguridad(resultSet.getInt("codigodeseguridad"));
                user.setSaldo(resultSet.getDouble("Saldo"));
                user.setDisponible(resultSet.getDouble("disponible"));
                user.setFechaDeVencimiento(resultSet.getString("fechadevencimiento"));
                datos.add(user);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }

    public ArrayList buscarTodosPorCbu(long cbu) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<TarjetaDebito> datos=new ArrayList<>();
        TarjetaDebito user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM TARJETADEBITO where CBUCUENTAOWNER=?");
            preparedStatement.setLong(1,cbu);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new TarjetaDebito();
                user.setId(resultSet.getLong("ID"));
                user.setCbuCuentaOwner(resultSet.getLong("cbucuentaowner"));
                user.setNumero(resultSet.getString("numero"));
                user.setCodigoDeSeguridad(resultSet.getInt("codigodeseguridad"));
                user.setSaldo(resultSet.getDouble("Saldo"));
                user.setDisponible(resultSet.getDouble("disponible"));
                user.setFechaDeVencimiento(resultSet.getString("fechadevencimiento"));
                datos.add(user);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }
}
