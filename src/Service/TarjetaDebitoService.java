package Service;

import Controlador.CuentaCajaDeAhorro;
import Controlador.TarjetaDebito;
import Model.DAOException;
import Model.DAOTarjetaDebito;

import java.util.ArrayList;

public class TarjetaDebitoService {

    private DAOTarjetaDebito daoTarjetaDebito;

    public TarjetaDebitoService() {
        daoTarjetaDebito = new DAOTarjetaDebito();
    }

    public void guardarTarjeta(TarjetaDebito tarjeta) throws ServiceException{
        try {
            daoTarjetaDebito.guardar(tarjeta);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminarTarjeta(long id) throws ServiceException{

        try {
            daoTarjetaDebito.eliminar(id);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public TarjetaDebito buscar(long id) throws ServiceException{

        TarjetaDebito tarjetita = null;

        try {
            tarjetita = daoTarjetaDebito.buscar(id);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return tarjetita;
    }

    public ArrayList<TarjetaDebito> buscarTarjetas()throws  ServiceException
    {
        ArrayList<TarjetaDebito> tarjeta;
        try {
            tarjeta= daoTarjetaDebito.buscarTodos();
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return tarjeta;
    }

    public ArrayList<TarjetaDebito> buscarTarjetas(long cbu)throws  ServiceException
    {
        ArrayList<TarjetaDebito> tarjeta;
        try {
            tarjeta= daoTarjetaDebito.buscarTodosPorCbu(cbu);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return tarjeta;
    }


}
