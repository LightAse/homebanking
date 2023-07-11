package Model;

import Controlador.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DAOUsuario implements DAO<Usuario> {

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/base";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";

    @Override
    public void guardar(Usuario elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT into Usuario Values(?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, elemento.getId());
            preparedStatement.setString(2, elemento.getDni());
            preparedStatement.setString(3, elemento.getNombre());
            preparedStatement.setString(4, elemento.getTelefono());
            preparedStatement.setString(5, elemento.getDomicilio());
            preparedStatement.setString(6, elemento.getFechaDeNacimiento());
            preparedStatement.setString(7, elemento.getUsuario());
            preparedStatement.setString(8, elemento.getPassword());
            preparedStatement.setString(9, elemento.getEmail());
            preparedStatement.setString(10, elemento.getTipoUsuario());
            int res = preparedStatement.executeUpdate();
            System.out.println("Se agregaron " + res);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(Usuario elemento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE Usuario SET usuario=?, domicilio=?, telefono=?, password=?, email=? WHERE id=?");
            preparedStatement.setString(1, elemento.getUsuario());
            preparedStatement.setString(2, elemento.getDomicilio());
            preparedStatement.setString(3, elemento.getTelefono());
            preparedStatement.setString(4, elemento.getPassword());
            preparedStatement.setString(5, elemento.getEmail());
            preparedStatement.setLong(6, elemento.getId());
            int res = preparedStatement.executeUpdate();
            System.out.println("Se modificaron " + res);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }


    }

    @Override
    public void eliminar(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM Usuario  WHERE id=?");

            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("Se elimino" + res);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public Usuario buscar(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario user = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM Usuario  WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new Usuario();
                user.setNombre(resultSet.getString("NOMBRE"));
                user.setDomicilio(resultSet.getString("DOMICILIO"));
                user.setTelefono(resultSet.getString("TELEFONO"));
                user.setTipoUsuario(resultSet.getString("TIPOUSUARIO"));
                user.setId(id);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return user;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Usuario> datos = new ArrayList<>();
        Usuario user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM Usuario");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new Usuario();
                user.setNombre(resultSet.getString("NOMBRE"));
                user.setUsuario(resultSet.getString("USUARIO"));
                user.setTelefono(resultSet.getString("TELEFONO"));
                user.setId(resultSet.getLong("ID"));
                datos.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return datos;
    }

    public Usuario buscar(String usuario, String password, String dni) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario user = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM Usuario  WHERE usuario=? and password=? and dni=?");
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new Usuario();
                user.setNombre(resultSet.getString("NOMBRE"));
                user.setDomicilio(resultSet.getString("DOMICILIO"));
                user.setTelefono(resultSet.getString("TELEFONO"));
                user.setId(resultSet.getLong("ID"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return user;

    }

    public void generarcolumna() throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE Usuario (" +
                    "  ID INT NOT NULL primary key ," +
                    "  DNI INT NOT NULL," +
                    "  NOMBRE VARCHAR(100) NOT NULL," +
                    " TELEFONO VARCHAR(100) NOT NULL, " +
                    " DOMICILIO VARCHAR(100) NOT NULL, " +
                    " FECHADENACIMIENTO VARCHAR(100)," +
                    " USUARIO VARCHAR(100)," +
                    " PASSWORD VARCHAR(100)," +
                    " EMAIL VARCHAR(100)," +
                    " TIPOUSUARIO VARCHAR(100)" +
                    ")");
            int res = preparedStatement.executeUpdate();
            System.out.println("Se agregaron " + res);
        } catch (ClassNotFoundException | SQLException e) {

            throw new DAOException(e.getMessage());

        }
    }

    public boolean existecolumna() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='PUBLIC' AND table_name='USUARIO'";

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = resultSet.getInt(1);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {

            new DAOException(e.getMessage());
            return true;

        }

    }


}
