package Gui;


import Controlador.CuentaCajaDeAhorro;
import Controlador.Usuario;
import Service.CuentaCajaDeAhorroService;
import Service.HomebankingService;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* TODO
*   Falta separar los metodos de calculos en su Service especifico
*   Hay que crear la base de datos de Cuenta Corriente
*   Hay que crear la base de datos de las Tarjetas
*   Hay que crear la base de datos de las Transferencias(Historial)
*   Hacer la interfaz de la Cuenta Corriente
*   Hacer la interfaz de las Tarjetas
*   Hacer la interfaz de las Transferencias
*   Hacer la interfaz de los Historiales
*   Hacer la interfaz del ADMIN
*   Hacer la interfaz de los errores */

public class MainWindow extends JFrame implements ActionListener {


    private Block[] array_blockes;

    private JButton[] button;

    private JTextField[] array_textfield;

    private String estado;

    private JComboBox[] array_comboBox;

    private HomebankingService controlador;


    public MainWindow()  throws ServiceException {

        estado = "login";
        controlador = new HomebankingService();

        array_blockes = new Block[8];
        array_textfield = new JTextField[12];
        array_comboBox = new JComboBox[2];

        Block block = new Block(Color.red,0,0,225,300);
        array_blockes[0] = block;


        Block block1 = new Block(Color.green,225,0,300,750);//middle block
        array_blockes[1] = block1;

        Block block2 = new Block(Color.blue,525,0,225,250);
        array_blockes[2] = block2;

        Block block3 = new Block(Color.magenta,525,250,225,500);
        array_blockes[3] = block3;

        Block block4 = new Block(Color.yellow,array_blockes[0].getX(),array_blockes[0].getY()+300,225,250);
        array_blockes[4] = block4;

        Block block5 = new Block(Color.pink,array_blockes[4].getX(),array_blockes[4].getY()+250,225,200);
        array_blockes[5] = block5;

        this.setTitle("PowerBank");//Sets title of this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
        this.setResizable(false);//set resizable
        this.setSize(750,750);//set x-Dim, y-Dim
        this.setLayout(null);//null layout manager
        this.getContentPane().setBackground(new Color(0, 0, 0));//change color of background
        this.add(block);
        this.add(block1);
        this.add(block2);
        this.add(block3);
        this.add(block4);
        this.add(block5);

        button = new JButton[10];
        button[0] = new JButton("Desconectar");
        button[0].addActionListener(this);
        button[1] =  new JButton("conectarse");
        button[1].addActionListener(this);



        generate_login_interface();

        this.setVisible(true);//this visible

    }

    public void generate_login_interface() throws ServiceException{

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel("LOGIN",SwingConstants.CENTER),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("ingresar DNI:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("ingresar USERNAME:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("ingresar PASSWORD:"),c);

        for (int i = 0; i < 3; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+1;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i] = new JTextField();
            array_blockes[1].add(array_textfield[i],c);
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        array_blockes[1].add(button[1],c);

    }


    public void generate_right_top() throws ServiceException {

        array_blockes[0].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel temp = new JLabel("Hola, "+ controlador.buscarNombreUsuario(controlador.getUserID()) + "!");
        temp.setFont(new Font("Roboto",Font.PLAIN,25));
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[0].add(temp,c);
        temp = new JLabel(controlador.saldoInString());
        temp.setFont( new Font( "Roboto",Font.PLAIN,25));
        c.gridx = 1;
        c.gridy = 1;
        array_blockes[0].add(temp,c);
        temp = new JLabel("Saldo");
        temp.setFont( new Font( "Roboto",Font.PLAIN,25));
        c.gridx = 1;
        c.gridy = 2;
        array_blockes[0].add(temp,c);
    }

    public void generate_combobox_tarjeta(Long cbu) throws  ServiceException{

        array_comboBox[1] = new JComboBox(controlador.getTarjetasParaComboBox(cbu).toArray());
        String temp = array_comboBox[1].getSelectedItem().toString();
        if(Objects.equals(temp, "NINGUNA")){

            array_comboBox[1].setVisible(false);
        }else{

            array_comboBox[1].setVisible(true);

        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[4].add(array_comboBox[1],c);


    }


    public void generate_right_middle() throws ServiceException {

        array_blockes[4].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        array_comboBox[0] = new JComboBox(controlador.getCajasParaComboBox().toArray());
        array_comboBox[0].addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        array_blockes[4].add(array_comboBox[0],c);
        String temp = array_comboBox[0].getSelectedItem().toString();
        temp = temp.substring(temp.indexOf(":")+2);
        generate_combobox_tarjeta(Long.valueOf(temp));
    }

    public void generate_right_bottom(){

        array_blockes[5].add(button[0]);
    }

    public void generate_middle() throws ServiceException {

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel(array_comboBox[0].getSelectedItem().toString(),SwingConstants.CENTER),c);
        String temp = array_comboBox[0].getSelectedItem().toString();
        temp = temp.substring(temp.indexOf(":")+2);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("CBU: "+ temp),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("ALIAS: " + controlador.getAliasCaja(Long.parseLong(temp))),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("Saldo: " + controlador.getSaldoCaja(Long.parseLong(temp))),c);
        if(array_comboBox[1].getItemCount() != 0){

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;
            array_blockes[1].add(new JLabel("----------------------------------------"),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 5;
            array_blockes[1].add(new JLabel(array_comboBox[1].getSelectedItem().toString(),SwingConstants.CENTER),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 6;
            //array_blockes[1].add(new JLabel("Numero: "+),c);
        }


    }

    public void generate_left_bottom_Transfer(){
        array_blockes[3].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        array_blockes[3].add(new JLabel("Transferencias con "),c);
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[3].add(new JLabel(array_comboBox[0].getSelectedItem().toString()),c);
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[3].add(new JLabel("id/cbu/alias: "),c);
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[3].add(new JLabel("Cantidad: "),c);
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[3].add(new JLabel("Motivo: "),c);

        for (int i = 0; i < 3; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+2;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i+3] = new JTextField();
            array_blockes[3].add(array_textfield[i+3],c);
        }




    }

    public void CrearPartes() throws ServiceException {

        generate_right_top();
        generate_right_middle();
        generate_right_bottom();
        generate_middle();
        generate_left_bottom_Transfer();

    }



    public void limpiarPartes(){

        for (int i = 0; i < array_blockes.length; i++) {

            if(array_blockes[i] != null){

                array_blockes[i].removeAll();
                array_blockes[i].revalidate();
                array_blockes[i].repaint();

            }

        }

    }

    public void limpiarUnaParte(int i){

        if(array_blockes[i] != null){

            array_blockes[i].removeAll();
            array_blockes[i].revalidate();
            array_blockes[i].repaint();

        }

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void addLabel(String text, int pos_array ){

        array_blockes[pos_array].addLabel(text);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button[1]){

            try {
                System.out.println(array_textfield[1].getText() + array_textfield[2].getText() +array_textfield[0].getText());
                controlador.setUserID(array_textfield[1].getText(),array_textfield[2].getText(),array_textfield[0].getText());
                limpiarPartes();
                setEstado(controlador.esAdmin());
                CrearPartes();
                System.out.println("Logeado");
            } catch (ServiceException ex) {
                System.out.println("Fallo el logeo");
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == button[0]) {

            try {
                limpiarPartes();
                generate_login_interface();
                System.out.println("Deslogeado");
            } catch (ServiceException ex) {
                System.out.println("Fallo el deslogeo");
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == array_comboBox[0]) {

            limpiarUnaParte(1);
            limpiarUnaParte(3);
            try {
                String temp = array_comboBox[0].getSelectedItem().toString();
                temp = temp.substring(temp.indexOf(":")+2);
                array_blockes[4].remove(1);
                array_blockes[4].revalidate();
                array_blockes[4].repaint();
                generate_combobox_tarjeta(Long.valueOf(temp));
                generate_middle();
                generate_left_bottom_Transfer();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

        }


    }
}
