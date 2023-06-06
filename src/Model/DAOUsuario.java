package Model;

import Controlador.Usuario;
import Controlador.UsuarioRegular;

import java.sql.*;
import java.util.ArrayList;

public class DAOUsuario implements DAO<Usuario>{

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2~/base";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Usuario elemento) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("INSERT into Cliente Values(?,?,?,?)");
            preparedStatement.setLong(1,elemento.getId());
            preparedStatement.setString(2, elemento.getNombre());
            preparedStatement.setString(3, elemento.getDomicilio());
            preparedStatement.setString(4, elemento.getTelefono());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron " + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(Usuario elemento) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;/*
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("UPDATE cliente SET nombre=?, domicilio=?, telefono=? WHERE id=?");

            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getDomicilio());
            preparedStatement.setString(3, elemento.getTelefono());
            preparedStatement.setLong(4,elemento.getId());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se modificaron " + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }*/


    }

    @Override
    public void eliminar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM cliente  WHERE id=?");

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
    public Usuario buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Usuario cliente=null;/*
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM cliente  WHERE id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                cliente = new Usuario();
                cliente.setNombre(resultSet.getString("NOMBRE"));
                cliente.setDomicilio(resultSet.getString("DOMICILIO"));
                cliente.setTelefono(resultSet.getString("TELEFONO"));
                cliente.setId(id);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }*/
        return cliente;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Usuario> datos=new ArrayList<>();
        Usuario cliente;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM cliente");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                cliente = new UsuarioRegular();
                cliente.setNombre(resultSet.getString("NOMBRE"));
                cliente.setDomicilio(resultSet.getString("DOMICILIO"));
                cliente.setTelefono(resultSet.getString("TELEFONO"));
                cliente.setId(resultSet.getLong("ID"));
                datos.add(cliente);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }


}
