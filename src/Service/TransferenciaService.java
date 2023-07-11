package Service;

import Controlador.Transferencia;
import Model.DAOException;
import Model.DAOTransferencia;

public class TransferenciaService {

    private DAOTransferencia daotransferencia;

    public TransferenciaService(){

        daotransferencia = new DAOTransferencia();

    }


    public void guardarTransferencia(Transferencia transfer) throws ServiceException{

        try{

            daotransferencia.guardar(transfer);

        } catch (DAOException e){

            throw new RuntimeException(e);
        }


    }


}
