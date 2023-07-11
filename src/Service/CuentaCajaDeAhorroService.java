package Service;

import Controlador.CuentaCorriente;
import Controlador.CuentaCajaDeAhorro;
import Model.DAOCuentaCajaDeAhorro;
import Model.DAOException;

import java.util.ArrayList;

public class CuentaCajaDeAhorroService {

    private DAOCuentaCajaDeAhorro daoCuentaCajaDeAhorro;

    public CuentaCajaDeAhorroService() {
        daoCuentaCajaDeAhorro =new DAOCuentaCajaDeAhorro();
    }
    public void guardarCaja(CuentaCajaDeAhorro user) throws ServiceException {
        try {
            daoCuentaCajaDeAhorro.guardar(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarCaja(long id) throws ServiceException
    {
        try{
            daoCuentaCajaDeAhorro.eliminar(id);
        }
        catch (DAOException e)
        {
            throw  new ServiceException(e.getMessage());
        }
    }

    public void modificarSaldo(CuentaCajaDeAhorro caja) throws ServiceException{

        try{

            daoCuentaCajaDeAhorro.modificarSaldo(caja);

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }


    public CuentaCajaDeAhorro buscar(long id)throws  ServiceException
    {
        CuentaCajaDeAhorro cuentaAhorro =null;
        try {
            cuentaAhorro = daoCuentaCajaDeAhorro.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuentaAhorro;
    }

    public ArrayList<CuentaCajaDeAhorro> buscarTodos()throws  ServiceException
    {

        try {
            return daoCuentaCajaDeAhorro.buscarTodos();
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public CuentaCajaDeAhorro buscarXAlias(String id)throws  ServiceException
    {
        CuentaCajaDeAhorro cuentaAhorro =null;
        try {
            cuentaAhorro = daoCuentaCajaDeAhorro.buscarXAlias(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuentaAhorro;
    }

    public ArrayList<CuentaCajaDeAhorro> buscarCajas(long id)throws  ServiceException
    {
        ArrayList<CuentaCajaDeAhorro> cuenta;
        try {
            cuenta= daoCuentaCajaDeAhorro.buscarCajas(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuenta;
    }

    public void generarcolumna() throws ServiceException{

        try {
            daoCuentaCajaDeAhorro.generarcolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public boolean existecolumna() throws ServiceException{

        try {
            return daoCuentaCajaDeAhorro.existecolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }


}

