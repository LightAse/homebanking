package Model;

import Controlador.CuentaCajaDeAhorro;



import java.sql.*;
import java.util.ArrayList;

public class DAOCuentaCajaDeAhorro implements DAO<CuentaCajaDeAhorro>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:~/base";
    private String DB_USER="sa";
    private String DB_PASSWORD="";
    @Override
    public void guardar(CuentaCajaDeAhorro elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CUENTACAJADEAHORRO values (?,?,?,?,?,?)");
            preparedStatement.setLong(1, elemento.getCbu());
            preparedStatement.setLong(2, elemento.getUserOwner());
            preparedStatement.setString(3, elemento.getAlias());
            preparedStatement.setDouble(4, elemento.getSaldo());
            preparedStatement.setString(5, elemento.getMoneda());
            preparedStatement.setDouble(6,elemento.getInteres());
            int res=preparedStatement.executeUpdate();
            System.out.println("Se agregaron "+ res);
        }catch(ClassNotFoundException | SQLException e){
            throw  new DAOException(e.getMessage());
        }
    }

    @Override
    public void modificar(CuentaCajaDeAhorro elemento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CUENTACAJADEAHORRO SET alias=?, saldo=?, interes=? where cbu=?");
            preparedStatement.setString(1, elemento.getAlias());
            preparedStatement.setDouble(2, elemento.getSaldo());
            preparedStatement.setDouble(3, elemento.getInteres());
            preparedStatement.setLong(4,elemento.getCbu());
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
            preparedStatement=connection.prepareStatement("DELETE FROM CUENTACAJADEAHORRO  WHERE cbu=?");
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
    public CuentaCajaDeAhorro buscar(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        CuentaCajaDeAhorro CuentaCajaDeAhorro=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACAJADEAHORRO  WHERE cbu=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                CuentaCajaDeAhorro = new CuentaCajaDeAhorro();
                CuentaCajaDeAhorro.setCbu(resultSet.getLong("CBU"));
                CuentaCajaDeAhorro.setAlias(resultSet.getString("ALIAS"));
                CuentaCajaDeAhorro.setUserOwner(resultSet.getLong("usuario"));
                CuentaCajaDeAhorro.setCbu(id);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException(e.getMessage());
        }
        return CuentaCajaDeAhorro;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<CuentaCajaDeAhorro> datos=new ArrayList<>();
        CuentaCajaDeAhorro user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACAJADEAHORRO");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new CuentaCajaDeAhorro();
                user.setCbu(resultSet.getLong("cbu"));
                user.setUserOwner(resultSet.getLong("usuario"));
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


    public ArrayList<CuentaCajaDeAhorro> buscarCajas(long id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<CuentaCajaDeAhorro> datos=new ArrayList<>();
        CuentaCajaDeAhorro user;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement=connection.prepareStatement("SELECT * FROM CUENTACAJADEAHORRO where USERID=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new CuentaCajaDeAhorro();
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
            preparedStatement=connection.prepareStatement("CREATE TABLE CUENTACAJADEAHORRO (" +
                    "  CBU INT NOT NULL primary key ," +
                    "  USERID INT NOT NULL," +
                    "  ALIAS VARCHAR(100) NOT NULL," +
                    " SALDO DOUBLE NOT NULL, "+
                    " MONEDA VARCHAR(100) NOT NULL, "+
                    " INTERES INT,"+
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
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='PUBLIC' AND table_name='CUENTACAJADEAHORRO'";

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

