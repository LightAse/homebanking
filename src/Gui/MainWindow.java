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
    private JButton button;

    private LoginWindow pastPage;

    public MainWindow(LoginWindow page)  throws ServiceException {

        cuenta = new CuentaService();
        pastPage = page;

        array_blockes = new Block[8];

        Block block = new Block(Color.white,0,0,225,300);
        array_blockes[0] = block;


        Block block1 = new Block(Color.white,225,0,300,750);
        array_blockes[1] = block1;

        Block block2 = new Block(Color.white,525,0,225,250);
        array_blockes[2] = block2;

        Block block3 = new Block(Color.white,525,250,500,500);
        array_blockes[3] = block3;

        Block block4 = new Block(Color.white,array_blockes[0].getX(),array_blockes[0].getY()+300,225,450);
        array_blockes[4] = block4;

        Block block5 = new Block(Color.white,array_blockes[4].getX(),array_blockes[4].getY()+100,225,300);
        array_blockes[5] = block5;

        this.setTitle("PowerBank");//Sets title of this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
        this.setResizable(false);//set resizable
        this.setSize(750,750);//set x-Dim, y-Dim
        this.setVisible(false);//this visible
        this.setLayout(null);//null layout manager
        this.getContentPane().setBackground(new Color(68, 81, 184));//change color of background
        this.add(block);
        this.add(block1);
        this.add(block2);
        this.add(block3);
        this.add(block4);
        this.add(block5);

        button = new JButton("Desconectar");
        button.addActionListener(this);

    }

    public MainWindow(Usuario userID, LoginWindow page)  throws ServiceException {

        UserID = userID;
        cuenta = new CuentaService();
        pastPage = page;

        array_blockes = new Block[10];

        Block block = new Block(Color.white,0,0,225,300);
        array_blockes[0] = block;


        Block block1 = new Block(Color.white,225,0,300,750);
        array_blockes[1] = block1;

        Block block2 = new Block(Color.white,525,0,225,250);
        array_blockes[2] = block2;

        Block block3 = new Block(Color.white,525,250,500,500);
        array_blockes[3] = block3;

        Block block4 = new Block(Color.white,array_blockes[0].getX(),array_blockes[0].getY()+300,225,150);
        array_blockes[4] = block4;

        Block block5 = new Block(Color.white,array_blockes[4].getX(),array_blockes[4].getY()+100,225,300);
        array_blockes[5] = block5;
        
        this.setTitle("PowerBank");//Sets title of this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
        this.setResizable(false);//set resizable
        this.setSize(750,750);//set x-Dim, y-Dim
        this.setVisible(true);//this visible
        this.setLayout(null);//null layout manager
        this.getContentPane().setBackground(new Color(68, 81, 184));//change color of background
        this.add(block);
        this.add(block1);
        this.add(block2);
        this.add(block3);
        this.add(block4);
        this.add(block5);

        button = new JButton("Desconectar");


        generate_right_top();
        generate_right_bottom();



    }

    public void generate_right_top() throws ServiceException {
        addLabel("Hola "+ getUserID().getNombre() + "!",0);
        addLabel("$"+saldoInString()+"       ",0);
        addLabel("Saldo",0);

    }

    public void generate_right_bottom() throws ServiceException {
        ArrayList<CuentaCajaDeAhorro> listCuenta = getCajasdeAhorro();
        addLabel("Caja de Ahorro",4);
        addLabel("$"+String.valueOf(listCuenta.get(0).getSaldo())+"   ",4);
        addLabel("cbu: "+String.valueOf(listCuenta.get(0).getCbu()),4);
        array_blockes[5].add(button);

    }

    public void CrearPartes() throws ServiceException {

        generate_right_top();
        generate_right_bottom();

    }

    public void limpiarPartes(){

        for (int i = 0; i < array_blockes.length; i++) {

            array_blockes[i].removeAll();
            array_blockes[i].revalidate();
            array_blockes[i].repaint();

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

        if(e.getSource()==button){

            pastPage.setVisible(true);
            pastPage.cleantextfields();
            this.setVisible(false);
            limpiarPartes();

        }


    }
}
