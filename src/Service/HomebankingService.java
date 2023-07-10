package Service;

import Controlador.CuentaCajaDeAhorro;
import Controlador.CuentaCorriente;
import Controlador.TarjetaDebito;
import Controlador.Usuario;

import java.util.ArrayList;
import java.util.Objects;

public class HomebankingService {

    private UsuarioService user;
    private CuentaCajaDeAhorroService cuentaAhorro;
    private CuentaCorrienteService cuentaCorriente;
    private TarjetaDebitoService tarjetaDebito;

    private long UserID;

    public HomebankingService() {

        cuentaAhorro = new CuentaCajaDeAhorroService();
        user = new UsuarioService();
        cuentaCorriente = new CuentaCorrienteService();
        tarjetaDebito = new TarjetaDebitoService();

    }

    public String saldoInString() throws ServiceException {
        double saldo = 0;
        ArrayList<CuentaCajaDeAhorro> CuentaAhorro = getCajasdeAhorro();
        ArrayList<CuentaCorriente> CuentaCorriente = getCajasCorrientes();
        for (int i = 0; i < CuentaAhorro.size(); i++) {

            CuentaCajaDeAhorro aux = CuentaAhorro.get(i);
            saldo += aux.getSaldo();

        }
        for (int i = 0; i < CuentaCorriente.size(); i++) {

            CuentaCorriente aux = CuentaCorriente.get(i);
            saldo += aux.getSaldo();

        }
        return String.valueOf(saldo);

    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(String username, String password,String dni) throws ServiceException {
        try{
            Usuario aux = user.buscar(username,password,dni);
            UserID = aux.getId();
        }catch (ServiceException ex){
            System.out.println("no se encuentra el usuario");
            throw new RuntimeException(ex);
        }

    }

    public String buscarNombreUsuario(long id) throws ServiceException{
        try {
            return user.buscar(id).getNombre();
        }catch (ServiceException ex){
            System.out.println("no se encontro el nombre del Usuario");
            throw new RuntimeException(ex);
        }

    }

    public String esAdmin(){

        try{
            if(Objects.equals(user.buscar(getUserID()).getTipoUsuario(), "Admin")){
                return "admin";
            }
        }catch (ServiceException ex){
            throw new RuntimeException(ex);
        }

        return "user";

    }

    public ArrayList<CuentaCajaDeAhorro> getCajasdeAhorro() throws ServiceException{

        return cuentaAhorro.buscarCajas(UserID);

    }

    public ArrayList<String> getCajasParaComboBox() throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<CuentaCajaDeAhorro> temp = cuentaAhorro.buscarCajas(UserID);
        for (int i = 0; i < temp.size(); i++) {

            aux.add("Caja de Ahorro: " + temp.get(i).getCbu());

        }

        ArrayList<CuentaCorriente> temp2 = cuentaCorriente.buscarCajas(UserID);

        for (int i = 0; i < temp2.size(); i++) {

            aux.add("Caja Corriente: " + temp2.get(i).getCbu());

        }

        return aux;
    }

    public ArrayList<String> getTarjetasParaComboBox(long cbu) throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<TarjetaDebito> temp = tarjetaDebito.buscarTarjetas(cbu);
        if (!temp.isEmpty()){

            for (int i = 0; i < temp.size(); i++) {

                aux.add("Tarjeta: " + temp.get(i).getNumero() + "-" + temp.get(i).getFechaDeVencimiento());

            }
            return aux;

        }
        aux.add("NINGUNA");
        return aux;

    }


    public String getAliasCaja(long cbu) throws ServiceException {

        if(cuentaAhorro.buscar(cbu) != null){

            return cuentaAhorro.buscar(cbu).getAlias();
        }
        return cuentaCorriente.buscar(cbu).getAlias();
    }


    public Double getSaldoCaja(long cbu) throws ServiceException{

        if(cuentaAhorro.buscar(cbu) != null){

            return cuentaAhorro.buscar(cbu).getSaldo();
        }
        return cuentaCorriente.buscar(cbu).getSaldo();

    }


    public ArrayList<CuentaCorriente> getCajasCorrientes() throws ServiceException{

        return cuentaCorriente.buscarCajas(UserID);

    }


}
