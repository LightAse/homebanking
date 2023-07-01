package Gui;


import Controlador.CuentaCajaDeAhorro;
import Controlador.Usuario;
import Service.CuentaService;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener {


    private Block[] array_blockes;
    private Usuario UserID;

    private UsuarioService user;

    private CuentaService cuenta;
    private JButton[] button;

    private JTextField[] array_textfield;

    private String estado;


    public MainWindow()  throws ServiceException {

        estado = "login";
        cuenta = new CuentaService();
        user = new UsuarioService();

        array_blockes = new Block[8];
        array_textfield = new JTextField[12];

        Block block = new Block(Color.red,0,0,225,300);
        array_blockes[0] = block;


        Block block1 = new Block(Color.green,225,0,300,750);//middle block
        array_blockes[1] = block1;

        Block block2 = new Block(Color.blue,525,0,225,250);
        array_blockes[2] = block2;

        Block block3 = new Block(Color.magenta,525,250,500,500);
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

    }

    public void generate_right_bottom() throws ServiceException {

    }

    public void CrearPartes() throws ServiceException {

        generate_right_top();
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


    public String saldoInString() throws ServiceException {
        double saldo = 0;
        ArrayList<CuentaCajaDeAhorro> Cuenta = cuenta.buscarCajas(UserID.getId());
        for (int i = 0; i < Cuenta.size(); i++) {

            CuentaCajaDeAhorro aux = Cuenta.get(i);
            saldo += aux.getSaldo();

        }
        return String.valueOf(saldo);

    }

    public ArrayList<CuentaCajaDeAhorro> getCajasdeAhorro() throws ServiceException{

        return cuenta.buscarCajas(UserID.getId());

    }

    public void addLabel(String text, int pos_array ){

        array_blockes[pos_array].addLabel(text);

    }

    public Usuario getUserID() {
        return UserID;
    }

    public void setUserID(Usuario userID) {
        UserID = userID;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button[1]){

            try {
                setUserID(user.buscar(array_textfield[1].getText(),array_textfield[2].getText(),array_textfield[0].getText()));
                limpiarPartes();
                System.out.println("Funciono");
            } catch (ServiceException ex) {
                System.out.println("Fallo");
                throw new RuntimeException(ex);
            }


        }


    }
}
