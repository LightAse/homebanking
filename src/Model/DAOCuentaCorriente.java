package Model;

import Controlador.CuentaCajaDeAhorro;
import Controlador.CuentaCorriente;

import java.sql.*;
import java.util.ArrayList;

public class DAOCuentaCorriente implements DAO<CuentaCorriente>{

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:~/base";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(CuentaCorriente elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CUENTACORRIENTE values (?,?,?,?,?)");
            preparedStatement.setLong(1, elemento.getCbu());
            preparedStatement.setLong(2, elemento.getUserOwner());
            preparedStatement.setString(3, elemento.getAlias());
            preparedStatement.setDouble(4, elemento.getSaldo());
            preparedStatement.setString(5, elemento.getMoneda());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw  new DAOException(e.getMessage());

        }
    }

    @Override
    public void modificar(CuentaCorriente elemento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CUENTACORRIENTE SET alias=?, saldo=? where cbu=?");
            preparedStatement.setString(1, elemento.getAlias());
            preparedStatement.setDouble(2, elemento.getSaldo());
            preparedStatement.setLong(3,elemento.getCbu());
            int res= preparedStatement.executeUpdate();
            System.out.println("Se modificaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw new DAOException(e.getMessage());

        }

    }

    public void modificarSaldo(CuentaCorriente elemento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CUENTACORRIENTE SET saldo=? where cbu=?");preparedStatement.setString(1, elemento.getAlias());
            preparedStatement.setDouble(1, elemento.getSaldo());
            preparedStatement.setLong(2,elemento.getCbu());
            int res= preparedStatement.executeUpdate();
            System.out.println("Se modificaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw new DAOException(e.getMessage());

        }

    }



    @Override
    public void eliminar(long cbu) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("DELETE FROM CUENTACORRIENTE  WHERE cbu=?");
            preparedStatement.setLong(1,cbu);
            int res=preparedStatement.executeUpdate();
            System.out.println("Se elimino" + res);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public CuentaCorriente buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        CuentaCorriente CuentaCorriente=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACORRIENTE WHERE cbu=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                CuentaCorriente = new CuentaCajaDeAhorro();
                CuentaCorriente.setCbu(resultSet.getLong("CBU"));
                CuentaCorriente.setAlias(resultSet.getString("ALIAS"));
                CuentaCorriente.setUserOwner(resultSet.getLong("userid"));
                CuentaCorriente.setSaldo(resultSet.getDouble("saldo"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return CuentaCorriente;
    }

    public CuentaCorriente buscarXAlias(String id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        CuentaCorriente CuentaCorriente=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACORRIENTE WHERE alias=?");
            preparedStatement.setString(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                CuentaCorriente = new CuentaCajaDeAhorro();
                CuentaCorriente.setCbu(resultSet.getLong("CBU"));
                CuentaCorriente.setAlias(resultSet.getString("ALIAS"));
                CuentaCorriente.setUserOwner(resultSet.getLong("userid"));
                CuentaCorriente.setSaldo(resultSet.getDouble("saldo"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return CuentaCorriente;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<CuentaCorriente> datos=new ArrayList<>();
        CuentaCorriente user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACORRIENTE");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new CuentaCorriente();
                user.setCbu(resultSet.getLong("cbu"));
                user.setUserOwner(resultSet.getLong("userid"));
                user.setAlias(resultSet.getString("alias"));
                user.setSaldo(resultSet.getDouble("Saldo"));
                user.setMoneda(resultSet.getString("moneda"));
                datos.add(user);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }


    public ArrayList<CuentaCorriente> buscarCajas(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<CuentaCorriente> datos=new ArrayList<>();
        CuentaCorriente user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACORRIENTE where USERID=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new CuentaCorriente();
                user.setCbu(resultSet.getLong("cbu"));
                user.setUserOwner(resultSet.getLong("userid"));
                user.setAlias(resultSet.getString("alias"));
                user.setSaldo(resultSet.getDouble("Saldo"));
                user.setMoneda(resultSet.getString("moneda"));
                datos.add(user);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return datos;
    }






    public void generarcolumna() throws DAOException{

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("CREATE TABLE CUENTACORRIENTE (" +
                    "  CBU INT NOT NULL primary key ," +
                    "  USERID INT NOT NULL," +
                    "  ALIAS VARCHAR(100) NOT NULL," +
                    " SALDO DOUBLE NOT NULL, "+
                    " MONEDA VARCHAR(100) NOT NULL, "+
                    " FOREIGN KEY (USERID) REFERENCES Usuario(ID)"+
                    ")");
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron "+ res);

        }catch (ClassNotFoundException | SQLException e){

            throw new DAOException(e.getMessage());

        }

    }

    public boolean existecolumna() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='PUBLIC' AND table_name='CUENTACORRIENTE'";
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
