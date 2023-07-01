package Service;

import Controlador.Usuario;
import Model.DAOException;
import Model.DAOUsuario;

public class UsuarioService {
    private DAOUsuario daoUsuario;

    public UsuarioService() {
        daoUsuario=new DAOUsuario();
    }
    public void guardarCliente(Usuario user) throws ServiceException {
        try {
            daoUsuario.guardar(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarCliente(long id) throws ServiceException
    {
        try{
            daoUsuario.eliminar(id);
        }
        catch (DAOException e)
        {
            throw  new ServiceException(e.getMessage());
        }
    }
    public Usuario buscar(long id)throws  ServiceException
    {
        Usuario user=null;
        try {
            user=daoUsuario.buscar(id);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    public Usuario buscar(String username, String password, String dni)throws  ServiceException
    {
        Usuario user=null;
        try {
            user=daoUsuario.buscar(username,password,dni);
        }
        catch (DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    public void generarcolumna() throws ServiceException{

        try {
            daoUsuario.generarcolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public boolean existecolumna() throws ServiceException{

        try {
            return daoUsuario.existecolumna();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

}
