package Service;

import Controlador.*;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Objects;

public class HomebankingService {

    private UsuarioService user;
    private CuentaCajaDeAhorroService cuentaAhorro;
    private CuentaCorrienteService cuentaCorriente;
    private TarjetaDebitoService tarjetaDebito;
    private TransferenciaService transferencia;

    private long UserID;

    public HomebankingService() {

        cuentaAhorro = new CuentaCajaDeAhorroService();
        user = new UsuarioService();
        cuentaCorriente = new CuentaCorrienteService();
        tarjetaDebito = new TarjetaDebitoService();
        transferencia = new TransferenciaService();

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

    public void guardarUser(Usuario Us) throws ServiceException {

        user.guardarCliente(Us);

    }

    public void guardarCuentaAhorro(CuentaCajaDeAhorro ahorro) throws ServiceException {

        cuentaAhorro.guardarCaja(ahorro);

    }

    public void guardarCuentaCorriente(CuentaCorriente corriente) throws ServiceException {

        cuentaCorriente.guardarCuenta(corriente);

    }

    public void guardarTarjeta(TarjetaDebito tarjeta) throws ServiceException{

        tarjetaDebito.guardarTarjeta(tarjeta);

    }


    public Long getUserID() {
        return UserID;
    }

    public boolean existeDNI(String dni){

        ArrayList<Usuario> array_user = user.buscarTodos();

        for (int i = 0; i < array_user.size(); i++) {

            if(Objects.equals(array_user.get(i).getDni(), dni)){

                return true;
            }

        }
        return false;
    }

    public long getNewIdUsuario(){

        ArrayList<Usuario> array_user = user.buscarTodos();

        return array_user.size();

    }

    public long getNewCbu() throws ServiceException {

        ArrayList<CuentaCajaDeAhorro> array_ahorro = cuentaAhorro.buscarTodos();
        ArrayList<CuentaCorriente> array_corriente = cuentaCorriente.buscarTodos();

        return array_ahorro.size() + array_corriente.size();

    }

    public long getNewIdTarjeta() throws ServiceException {

        ArrayList<TarjetaDebito> array_tajeta = tarjetaDebito.buscarTarjetas();

        return array_tajeta.size();

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
            return user.buscar(getUserID()).getTipoUsuario();
        }catch (ServiceException ex){
            throw new RuntimeException(ex);
        }

    }

    public ArrayList<CuentaCajaDeAhorro> getCajasdeAhorro() throws ServiceException{

        return cuentaAhorro.buscarCajas(UserID);

    }

    public ArrayList<String> getCajasParaComboBox() throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<CuentaCajaDeAhorro> temp = cuentaAhorro.buscarCajas(UserID);
        for (int i = 0; i < temp.size(); i++) {

            aux.add("Caja de Ahorro:" + temp.get(i).getCbu());

        }

        ArrayList<CuentaCorriente> temp2 = cuentaCorriente.buscarCajas(UserID);

        for (int i = 0; i < temp2.size(); i++) {

            aux.add("Caja Corriente:" + temp2.get(i).getCbu());

        }

        return aux;
    }

    public ArrayList<String> getTarjetasParaComboBox(long cbu) throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<TarjetaDebito> temp = tarjetaDebito.buscarTarjetas(cbu);
        if (!temp.isEmpty()){

            for (int i = 0; i < temp.size(); i++) {

                aux.add("Tarjeta: " + temp.get(i).getNumero() + "-" + temp.get(i).getId());

            }
            return aux;

        }
        aux.add("NINGUNA");
        return aux;

    }

    public ArrayList<String> getUsuarioParaComboBox() throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<Usuario> temp = user.buscarTodos();
        for (int i = 0; i < temp.size(); i++) {

            aux.add(temp.get(i).getUsuario() + "-" + temp.get(i).getId());

        }
        return aux;

    }

    public ArrayList<String> getCuentaParaComboBox() throws ServiceException{

        ArrayList<String> aux = new ArrayList<>();
        ArrayList<CuentaCajaDeAhorro> temp = cuentaAhorro.buscarTodos();
        for (int i = 0; i < temp.size(); i++) {

            aux.add("Ahorro:" + temp.get(i).getCbu());

        }

        ArrayList<CuentaCorriente> temp2 = cuentaCorriente.buscarTodos();

        for (int i = 0; i < temp2.size(); i++) {

            aux.add("Corriente:" + temp2.get(i).getCbu());

        }

        return aux;

    }


    public String getAliasCajaAhorro(long cbu) throws ServiceException {

            return cuentaAhorro.buscar(cbu).getAlias();
    }


    public Double getSaldoCajaAhorro(long cbu) throws ServiceException{

            return cuentaAhorro.buscar(cbu).getSaldo();

    }

    public Double getInteresCajaAhorro(long cbu) throws ServiceException{

        return cuentaAhorro.buscar(cbu).getInteres();

    }

    public String getAliasCajaCorriente(long cbu) throws ServiceException {

        return cuentaCorriente.buscar(cbu).getAlias();
    }


    public Double getSaldoCajaCorriente(long cbu) throws ServiceException{

        return cuentaCorriente.buscar(cbu).getSaldo();

    }

    public String getNumerotarjeta(long cbu) throws ServiceException {

            return tarjetaDebito.buscar(cbu).getNumero();
    }


    public Double getDisponibleTarjeta(long cbu) throws ServiceException{

        return tarjetaDebito.buscar(cbu).getDisponible();

    }

    public Double getSaldoTarjeta(long cbu) throws ServiceException{

        return tarjetaDebito.buscar(cbu).getSaldo();

    }

    public ArrayList<CuentaCorriente> getCajasCorrientes() throws ServiceException{

        return cuentaCorriente.buscarCajas(UserID);

    }


    public CuentaCajaDeAhorro getCuentaAhorro(String destino){

        try{

            return cuentaAhorro.buscar(Long.parseLong(destino));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    public CuentaCorriente getCuentaCorriente(String destino){

        try{

            return cuentaCorriente.buscar(Long.parseLong(destino));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }


    public CuentaCajaDeAhorro getCuentaAhorroXAlias(String destino){

        try{

            return cuentaAhorro.buscarXAlias(destino);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    public CuentaCorriente getCuentaCorrienteXAlias(String destino){

        try{

            return cuentaCorriente.buscarXAlias(destino);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }


    public void ejecutarTransferencia(CuentaCajaDeAhorro destinatario, CuentaCajaDeAhorro remitente, double cantidad, String motivo, String fecha, int tipoDestino, int tipoRemitente) throws ServiceException{

        if(remitente.getSaldo() > cantidad){

            transferencia.guardarTransferencia(new Transferencia(0,destinatario.getCbu(),remitente.getCbu(),cantidad,motivo,fecha));
            remitente.setSaldo(remitente.getSaldo()-cantidad);
            destinatario.setSaldo(destinatario.getSaldo()+cantidad);
            if(tipoDestino == 0){

                cuentaAhorro.modificarSaldo(destinatario);

            }else{

                cuentaCorriente.modificarSaldo(destinatario);

            }
            if(tipoRemitente == 0){

                cuentaAhorro.modificarSaldo(remitente);

            }else{

                cuentaCorriente.modificarSaldo(remitente);

            }

        }

        System.out.println("se ha logrado una transferencia!");

    }




}
