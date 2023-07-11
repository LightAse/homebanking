package Gui;


import Controlador.CuentaCajaDeAhorro;
import Controlador.CuentaCorriente;
import Controlador.TarjetaDebito;
import Controlador.Usuario;
import Service.HomebankingService;
import Service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Usuario temporario;


    public MainWindow()  throws ServiceException {

        estado = "login";
        controlador = new HomebankingService();

        array_blockes = new Block[8];
        array_textfield = new JTextField[25];
        array_comboBox = new JComboBox[10];

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

        button = new JButton[9];
        button[0] = new JButton("Desconectar");
        button[0].addActionListener(this);
        button[1] =  new JButton("conectarse");
        button[1].addActionListener(this);
        button[2] = new JButton("Enviar Transferencia");
        button[2].addActionListener(this);
        button[3] = new JButton("Admin");
        button[3].addActionListener(this);
        button[4] = new JButton("Siguiente");
        button[4].addActionListener(this);
        button[5] = new JButton("Retroceder");
        button[5].addActionListener(this);
        button[6] = new JButton("Terminar"); //user
        button[6].addActionListener(this);
        button[7] = new JButton("Terminar"); //cuenta
        button[7].addActionListener(this);
        button[8] = new JButton("Terminar"); //tarjeta
        button[8].addActionListener(this);



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
        array_comboBox[1].addActionListener(this);
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
        String[] aux = temp.split(":");
        generate_combobox_tarjeta(Long.valueOf(aux[1]));
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
        String[] aux = temp.split(":");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("CBU: "+ aux[1]),c);
        if(Objects.equals(aux[0], "Caja de Ahorro")){

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            array_blockes[1].add(new JLabel("ALIAS: " + controlador.getAliasCajaAhorro(Long.parseLong(aux[1]))),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            array_blockes[1].add(new JLabel("Saldo: " + controlador.getSaldoCajaAhorro(Long.parseLong(aux[1]))),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;
            array_blockes[1].add(new JLabel("Interes: " + controlador.getInteresCajaAhorro(Long.parseLong(aux[1]))+"%"),c);

        }else {

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            array_blockes[1].add(new JLabel("ALIAS: " + controlador.getAliasCajaCorriente(Long.parseLong(aux[1]))),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            array_blockes[1].add(new JLabel("Saldo: " + controlador.getSaldoCajaCorriente(Long.parseLong(aux[1]))),c);
        }

        if(array_comboBox[1].getItemCount() != 0){

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 5;
            array_blockes[1].add(new JLabel("----------------------------------------"),c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 6;
            array_blockes[1].add(new JLabel(array_comboBox[1].getSelectedItem().toString(),SwingConstants.CENTER),c);
            if(!Objects.equals(array_comboBox[1].getSelectedItem().toString(), "NINGUNA")){

                String temp1 = array_comboBox[1].getSelectedItem().toString();
                String[] aux1 = temp1.split("-");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 7;
                array_blockes[1].add(new JLabel("Numero: "+ controlador.getNumerotarjeta(Long.parseLong(aux1[1]))),c);
                c.gridx = 0;
                c.gridy = 8;
                array_blockes[1].add(new JLabel("Disponible: "+ controlador.getDisponibleTarjeta(Long.parseLong(aux1[1]))),c);
                c.gridx = 0;
                c.gridy = 9;
                array_blockes[1].add(new JLabel("Saldo: "+ controlador.getSaldoTarjeta(Long.parseLong(aux1[1]))),c);

            }
        }


    }

    public void generate_left_bottom_Transfer(){
        array_blockes[3].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        array_blockes[3].add(new JLabel("Transferencias"),c);
        c.gridx = 0;
        c.gridy = 1;
        String temp = array_comboBox[0].getSelectedItem().toString();
        String[] aux = temp.split(":");
        array_blockes[3].add(new JLabel(aux[0]),c);
        c.gridx = 1;
        c.gridy = 1;
        array_blockes[3].add(new JLabel(aux[1]),c);
        c.gridx = 0;
        c.gridy = 2;
        String[] opciones = {"cbu","alias"};
        array_comboBox[2] = new JComboBox(opciones);
        array_blockes[3].add(array_comboBox[2],c);
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[3].add(new JLabel("Cantidad: "),c);
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[3].add(new JLabel("Motivo: "),c);
        c.gridx = 0;
        c.gridy = 5;
        array_blockes[3].add(new JLabel("Fecha: "),c);
        c.gridx = 0;
        c.gridy = 6;
        array_blockes[3].add(new JLabel("Tipo Caja: "),c);
        c.gridx = 1;
        c.gridy = 6;
        String[] op = {"Ahorro","Corriente"};
        array_comboBox[3] = new JComboBox(op);
        array_blockes[3].add(array_comboBox[3],c);

        for (int i = 0; i < 4; i++) {
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

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 7;
        array_blockes[3].add(button[2],c);




    }

    public void CrearPartes() throws ServiceException {

        generate_right_top();
        generate_right_middle();
        generate_right_bottom();
        generate_middle();
        generate_left_bottom_Transfer();
        if(Objects.equals(getEstado(), "Admin")){

            array_blockes[2].add(button[3]);
            array_blockes[2].revalidate();
            array_blockes[2].repaint();

        }

    }


    public void generate_right_top_Admin() throws ServiceException {

        array_blockes[0].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel temp = new JLabel("Hola, "+ controlador.buscarNombreUsuario(controlador.getUserID()) + "!");
        temp.setFont(new Font("Roboto",Font.PLAIN,25));
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[0].add(temp,c);
        temp = new JLabel("MODO ADMIN");
        temp.setFont( new Font( "Roboto",Font.PLAIN,25));
        c.gridx = 1;
        c.gridy = 2;
        array_blockes[0].add(temp,c);

    }

    public void generate_middle_user_Admin(){

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel("Crear Usuario",SwingConstants.CENTER),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("ingresar DNI:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("ingresar Nombre:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("ingresar Telefono:"),c);
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[1].add(new JLabel("ingresar Domicilio:"),c);
        c.gridx = 0;
        c.gridy = 5;
        array_blockes[1].add(new JLabel("ingresar Nacimiento:"),c);
        c.gridx = 0;
        c.gridy = 6;
        array_blockes[1].add(new JLabel("ingresar Username:"),c);
        c.gridx = 0;
        c.gridy = 7;
        array_blockes[1].add(new JLabel("ingresar Password:"),c);
        c.gridx = 0;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("ingresar Email:"),c);

        for (int i = 0; i < 8; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+1;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i+7] = new JTextField();
            array_blockes[1].add(array_textfield[i+7],c);
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 9;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        array_blockes[1].add(button[4],c);



    }

    public void generate_interes_interface(int pos_combo,int n1, int n2){

        if(Objects.equals(array_comboBox[pos_combo].getSelectedItem().toString(), "Ahorro")){

            array_blockes[1].getComponent(n1).setVisible(true);
            array_blockes[1].getComponent(n2).setVisible(true);

        }else {

            array_blockes[1].getComponent(n1).setVisible(false);
            array_blockes[1].getComponent(n2).setVisible(false);

        }

    }

    public void generate_middle_user_cuenta_Admin(){

        limpiarUnaParte(1);

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel("Crear Cuenta Bancaria",SwingConstants.CENTER),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("Tipo de Cuenta:"),c);
        c.gridx = 1;
        c.gridy = 1;
        String[] aux = {"Corriente","Ahorro"};
        array_comboBox[5] = new JComboBox(aux);
        array_comboBox[5].addActionListener(this);
        array_blockes[1].add(array_comboBox[5],c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("ingresar Alias:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("ingresar Moneda:"),c);
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[1].add(new JLabel("Ingresar interes"),c);
        array_blockes[1].getComponent(5).setVisible(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        array_textfield[17] = new JTextField();
        array_blockes[1].add(array_textfield[17],c);
        array_blockes[1].getComponent(6).setVisible(false);


        for (int i = 0; i < 2; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+2;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i+15] = new JTextField();
            array_blockes[1].add(array_textfield[i+15],c);
        }

        c.gridx = 0;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);
        c.gridx = 1;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 0;
        c.gridy = 9;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        array_blockes[1].add(button[5],c);
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 9;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        array_blockes[1].add(button[6],c);



    }

    public void generate_middle_cuenta_Admin() throws ServiceException {

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel("Crear Cuenta Bancaria",SwingConstants.CENTER),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("Tipo de Cuenta:"),c);
        c.gridx = 1;
        c.gridy = 1;
        String[] aux = {"Corriente","Ahorro"};
        array_comboBox[6] = new JComboBox(aux);
        array_comboBox[6].addActionListener(this);
        array_blockes[1].add(array_comboBox[6],c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("Usuario:"),c);
        c.gridx = 1;
        c.gridy = 2;
        array_comboBox[7] = new JComboBox(controlador.getUsuarioParaComboBox().toArray());
        array_blockes[1].add(array_comboBox[7],c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("ingresar Alias:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[1].add(new JLabel("ingresar Moneda:"),c);
        c.gridx = 0;
        c.gridy = 5;
        array_blockes[1].add(new JLabel("Ingresar interes"),c);
        array_blockes[1].getComponent(7).setVisible(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        array_textfield[17] = new JTextField();
        array_blockes[1].add(array_textfield[17],c);
        array_blockes[1].getComponent(8).setVisible(false);
        for (int i = 0; i < 2; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+3;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i+15] = new JTextField();
            array_blockes[1].add(array_textfield[i+15],c);
        }

        c.gridx = 0;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);
        c.gridx = 1;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);

        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 9;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        array_blockes[1].add(button[7],c);


    }


    public void generate_middle_tarjeta_Admin() throws ServiceException {

        array_blockes[1].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        array_blockes[1].add(new JLabel("Crear Tarjeta",SwingConstants.CENTER),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        array_blockes[1].add(new JLabel("CBU:"),c);
        c.gridx = 1;
        c.gridy = 1;
        array_comboBox[8] = new JComboBox(controlador.getCuentaParaComboBox().toArray());
        array_blockes[1].add(array_comboBox[8],c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        array_blockes[1].add(new JLabel("ingresar numero:"),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        array_blockes[1].add(new JLabel("ingresar codigo:"),c);
        c.gridx = 0;
        c.gridy = 4;
        array_blockes[1].add(new JLabel("Ingresar fecha"),c);
        c.gridx = 0;
        c.gridy = 5;
        array_blockes[1].add(new JLabel("Ingresar Disponible"),c);

        for (int i = 0; i < 4; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;
            c.gridx = 1;
            c.gridy = i+2;
            c.weightx = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            array_textfield[i+18] = new JTextField();
            array_blockes[1].add(array_textfield[i+18],c);
        }

        c.gridx = 0;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);
        c.gridx = 1;
        c.gridy = 8;
        array_blockes[1].add(new JLabel("--------------"),c);

        c.ipadx = 20;
        c.gridx = 1;
        c.gridy = 9;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        array_blockes[1].add(button[8],c);

    }

    public void generate_middle_Admin() throws ServiceException {

        switch (array_comboBox[4].getSelectedItem().toString()) {
            case "usuario" -> {
                limpiarUnaParte(1);
                generate_middle_user_Admin();
            }
            case "cuenta"-> {
                limpiarUnaParte(1);
                generate_middle_cuenta_Admin();
            }
            case "tarjeta" -> {
                limpiarUnaParte(1);
                generate_middle_tarjeta_Admin();
            }
        }

    }

    public void CrearPartesAdmin() throws ServiceException{

        generate_right_top_Admin();
        String[] temp = {"usuario","cuenta","tarjeta"};
        array_comboBox[4] = new JComboBox(temp);
        array_comboBox[4].addActionListener(this);
        array_blockes[4].add(array_comboBox[4]);
        generate_right_bottom();

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

    public CuentaCajaDeAhorro conseguirCuentaCB2(String cbu){

        if(Objects.equals(array_comboBox[3].getSelectedItem().toString(), "Ahorro")){

            return controlador.getCuentaAhorro(cbu);

        }else {

            return (CuentaCajaDeAhorro) controlador.getCuentaCorriente(cbu);

        }


    }

    public CuentaCajaDeAhorro conseguirCuenta(){

        String temp = array_comboBox[0].getSelectedItem().toString();
        String[] aux = temp.split(":");
        temp = temp.substring(temp.indexOf(":")+2);
        if(Objects.equals(aux[0], "Caja de Ahorro")){

            return controlador.getCuentaAhorro(temp);

        }else {

            return (CuentaCajaDeAhorro) controlador.getCuentaCorriente(temp);

        }

    }

    public CuentaCajaDeAhorro conseguirCuentaCB2XAlias(String cbu){

        if(Objects.equals(array_comboBox[3].getSelectedItem().toString(), "Ahorro")){

            return controlador.getCuentaAhorroXAlias(cbu);

        }else {

            return (CuentaCajaDeAhorro) controlador.getCuentaCorrienteXAlias(cbu);

        }


    }


    public int conseguirTipoCB2(){

        if(Objects.equals(array_comboBox[3].getSelectedItem().toString(), "Ahorro")){

            return 0;

        }else {

            return 1;

        }
    }

    public int conseguirTipo(){

        String temp = array_comboBox[0].getSelectedItem().toString();
        String[] aux = temp.split(":");
        if(Objects.equals(aux[0], "Caja de Ahorro")){

            return 0;

        }else {

            return 1;

        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button[1]){

            try {
                controlador.setUserID(array_textfield[1].getText(),array_textfield[2].getText(),array_textfield[0].getText());
                limpiarPartes();
                setEstado(controlador.esAdmin());
                CrearPartes();
                System.out.println("Logeado");
                System.out.println(getEstado());
            } catch (ServiceException ex) {
                System.out.println("Fallo el logeo");
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == button[0]) {

            try {
                temporario = null;
                limpiarPartes();
                generate_login_interface();
                System.out.println("Deslogeado");
            } catch (ServiceException ex) {
                System.out.println("Fallo el deslogeo");
                throw new RuntimeException(ex);
            }

        }

        if(e.getSource() == button[2]){
            String temp = array_comboBox[0].getSelectedItem().toString();
            temp = temp.substring(temp.indexOf(":")+2);
            if(array_comboBox[2].getSelectedItem() == "cbu"){

                try {
                    controlador.ejecutarTransferencia(conseguirCuentaCB2(array_textfield[3].getText()),conseguirCuenta(),Double.parseDouble(array_textfield[4].getText()),array_textfield[5].getText(),array_textfield[6].getText(),conseguirTipoCB2(),conseguirTipo());
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }else{

                try {
                    controlador.ejecutarTransferencia(conseguirCuentaCB2XAlias(array_textfield[3].getText()),conseguirCuenta(),Double.parseDouble(array_textfield[4].getText()),array_textfield[5].getText(),array_textfield[6].getText(),conseguirTipoCB2(),conseguirTipo());
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }

        if (e.getSource() == array_comboBox[0]) {

            limpiarUnaParte(1);
            limpiarUnaParte(3);
            try {
                String temp = array_comboBox[0].getSelectedItem().toString();
                String[] aux = temp.split(":");
                array_blockes[4].remove(1);
                array_blockes[4].revalidate();
                array_blockes[4].repaint();
                generate_combobox_tarjeta(Long.valueOf(aux[1]));
                generate_middle();
                generate_left_bottom_Transfer();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

        }

        if(e.getSource() == array_comboBox[1]){

            limpiarUnaParte(1);
            try {
                generate_middle();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

        }

        if(e.getSource() == button[4]){

            if(controlador.existeDNI(array_textfield[7].getText() )){

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 10;
                array_blockes[1].add(new JLabel("El DNI esta duplicado"));

            }else {
                temporario = new Usuario(controlador.getNewIdUsuario(),array_textfield[7].getText(),array_textfield[8].getText(),array_textfield[9].getText(),array_textfield[10].getText(),array_textfield[11].getText(),array_textfield[12].getText(),array_textfield[13].getText(),array_textfield[14].getText());
                generate_middle_user_cuenta_Admin();

            }

        }

        if (e.getSource() == button[5]){

            temporario = null;
            limpiarUnaParte(1);
            generate_middle_user_Admin();

        }

        if(e.getSource() == button[6]){

            try {
                controlador.guardarUser(temporario);
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

            if(Objects.equals(array_comboBox[5].getSelectedItem().toString(), "Ahorro")){

                try {
                    controlador.guardarCuentaAhorro(new CuentaCajaDeAhorro(controlador.getNewCbu(),array_textfield[15].getText(),0.0,temporario.getId(),Double.parseDouble(array_textfield[17].getText()),array_textfield[16].getText()));
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }else {

                try {
                    controlador.guardarCuentaCorriente(new CuentaCorriente(controlador.getNewCbu(),array_textfield[15].getText(),0.0, temporario.getId(), array_textfield[16].getText()));
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }

            temporario = null;

            limpiarUnaParte(1);
            array_blockes[1].add(new JLabel("EXITO AL CREAR UN USUARIO Y SU CUENTA"));


        }

        if (e.getSource() == button[7]){

            String temp = array_comboBox[7].getSelectedItem().toString();
            String[] aux = temp.split("-");
            if(Objects.equals(array_comboBox[6].getSelectedItem().toString(), "Ahorro")){

                try {
                    controlador.guardarCuentaAhorro(new CuentaCajaDeAhorro(controlador.getNewCbu(),array_textfield[15].getText(),0.0,Long.parseLong(aux[1]),Double.parseDouble(array_textfield[17].getText()),array_textfield[16].getText()));
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }else {

                try {
                    controlador.guardarCuentaCorriente(new CuentaCorriente(controlador.getNewCbu(),array_textfield[15].getText(),0.0, Long.parseLong(aux[1]), array_textfield[16].getText()));
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

            }

            limpiarUnaParte(1);
            array_blockes[1].add(new JLabel("EXITO AL CREAR UNA CUENTA"));

        }

        if(e.getSource() == button[8]){

            String temp = array_comboBox[8].getSelectedItem().toString();
            String[] aux = temp.split(":");
            try {
                controlador.guardarTarjeta(new TarjetaDebito(controlador.getNewIdTarjeta(),array_textfield[18].getText(),Long.parseLong(array_textfield[19].getText()),array_textfield[20].getText(),Long.parseLong(aux[1]),Double.parseDouble(array_textfield[21].getText())));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

            limpiarUnaParte(1);
            array_blockes[1].add(new JLabel("EXITO AL CREAR UNA TARJETA"));
        }

        if(e.getSource() == array_comboBox[4]){

            try {
                generate_middle_Admin();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

        }

        if(e.getSource() == array_comboBox[5]){

            generate_interes_interface(5,5,6);

        }

        if(e.getSource() == array_comboBox[6]){

            generate_interes_interface(6,7,8);

        }


        if(e.getSource() == button[3]){

            if(Objects.equals(button[3].getText(), "Admin")){


                limpiarPartes();
                try {
                    CrearPartesAdmin();
                    array_blockes[2].add(button[3]);
                    button[3].setText("user");
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                temporario = null;
                limpiarPartes();
                try {
                    CrearPartes();
                    array_blockes[2].add(button[3]);
                    button[3].setText("Admin");
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }


    }
}
