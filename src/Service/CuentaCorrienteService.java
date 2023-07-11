package Service;

import Controlador.CuentaCajaDeAhorro;
import Controlador.CuentaCorriente;
import Model.DAOCuentaCorriente;
import Model.DAOException;

import java.util.ArrayList;

public class CuentaCorrienteService {

    private DAOCuentaCorriente daoCuentaCorriente;

    public CuentaCorrienteService() {
        daoCuentaCorriente =new DAOCuentaCorriente();
    }
    public void guardarCuenta(CuentaCorriente user) throws ServiceException {
        try {
            daoCuentaCorriente.guardar(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarCaja(long id) throws ServiceException
    {
        try{
            daoCuentaCorriente.eliminar(id);
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
            cuentaCorriente = daoCuentaCorriente.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuentaCorriente;
    }

    public ArrayList<CuentaCorriente> buscarTodos()throws  ServiceException
    {
        try {
            return daoCuentaCorriente.buscarTodos();
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public CuentaCorriente buscarXAlias(String id)throws  ServiceException
    {
        CuentaCorriente cuentaCorriente =null;
        try {
            cuentaCorriente = daoCuentaCorriente.buscarXAlias(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuentaCorriente;
    }

    public void modificarSaldo(CuentaCorriente caja) throws ServiceException{

        try{

            daoCuentaCorriente.modificarSaldo(caja);

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }


    public ArrayList<CuentaCorriente> buscarCajas(long id)throws  ServiceException
    {
        ArrayList<CuentaCorriente> cuenta;
        try {
            cuenta= daoCuentaCorriente.buscarCajas(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cuenta;
    }

    public void generarcolumna() throws ServiceException{

        try {
            daoCuentaCorriente.generarcolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public boolean existecolumna() throws ServiceException{

        try {
            return daoCuentaCorriente.existecolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    
}
