import Controlador.CuentaCajaDeAhorro;
import Controlador.Usuario;
import Gui.LoginWindow;
import Gui.MainWindow;
import Service.CuentaService;
import Service.ServiceException;
import Service.UsuarioService;

public class Main {
    public static void main(String[] args) throws ServiceException {





        UsuarioService user = new UsuarioService();
        CuentaService cuenta = new CuentaService();

        if(!user.existecolumna()){

            user.generarcolumna();
            user.guardarCliente(new Usuario(0,"4356116","Marcos","1121523132","av3432 palermo","21/03/1994","marqui1","1234","marquitos@gmail.com"));
        }

        if(!cuenta.existecolumna()){

            cuenta.generarcolumna();
            cuenta.guardarCliente(new CuentaCajaDeAhorro(0,"pancho.marco",2000,0,15,"peso"));

        }


        LoginWindow login = new LoginWindow();






    }
}