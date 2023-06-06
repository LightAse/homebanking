package Service;

import Controlador.Usuario;
import Model.DAOException;
import Model.DAOUsuario;

public class UsuarioService {
    private DAOUsuario daoCliente;

    public UsuarioService() {
        daoCliente=new DAOUsuario();
    }
    public void guardarCliente(Usuario cliente) throws ServiceException {
        try {
            daoCliente.guardar(cliente);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarCliente(long id) throws ServiceException
    {
        try{
            daoCliente.eliminar(id);
        }
        catch (DAOException e)
        {
            throw  new ServiceException(e.getMessage());
        }
    }
    public Usuario buscar(long id)throws  ServiceException
    {
        Usuario cliente=null;
        try {
            cliente=daoCliente.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return cliente;
    }
}
