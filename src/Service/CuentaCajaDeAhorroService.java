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
    public CuentaCorriente buscar(long id)throws  ServiceException
    {
        CuentaCorriente cuentaCorriente =null;
        try {
            cuentaCorriente = daoCuentaCajaDeAhorro.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuentaCorriente;
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

