package Service;

import Controlador.Cuenta;
import Controlador.CuentaCajaDeAhorro;
import Model.DAOCuentaCajaDeAhorro;
import Model.DAOException;

import java.util.ArrayList;
import java.util.Currency;

public class CuentaService {

    private DAOCuentaCajaDeAhorro daoCuentaCajaDeAhorro;

    public CuentaService() {
        daoCuentaCajaDeAhorro =new DAOCuentaCajaDeAhorro();
    }
    public void guardarCliente(CuentaCajaDeAhorro user) throws ServiceException {
        try {
            daoCuentaCajaDeAhorro.guardar(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarCliente(long id) throws ServiceException
    {
        try{
            daoCuentaCajaDeAhorro.eliminar(id);
        }
        catch (DAOException e)
        {
            throw  new ServiceException(e.getMessage());
        }
    }
    public Cuenta buscar(long id)throws  ServiceException
    {
        Cuenta cuenta=null;
        try {
            cuenta= daoCuentaCajaDeAhorro.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuenta;
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

