package Gui;

import Controlador.Usuario;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginWindow extends JFrame implements ActionListener {

    private Block[] array_blockes;
    private JButton button;
    private JTextField textfield1;
    private JTextField textfield;

    private UsuarioService user;

    private MainWindow nextPage;


    public LoginWindow() throws ServiceException {

        this.setTitle("PowerBank");//Sets title of this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
        this.setResizable(false);//set resizable
        this.setSize(750,750);//set x-Dim, y-Dim
        this.setLayout(null);// layout manager
        this.getContentPane().setBackground(new Color(68, 81, 184));//change color of background

        array_blockes = new Block[4];
        nextPage = new MainWindow(this);

        generate_page();

        this.setVisible(true);//this visible


    }

    public void cleantextfields(){

        textfield.setText("");
        textfield1.setText("");

    }

    public void generate_page(){

        Block block = new Block(Color.white,225,100,400,100);
        block.addLabel("LOGIN");
        array_blockes[0] = block;


        Block block1 = new Block(Color.white,225,200,400,100);
        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(250,40));
        block1.addLabel("usuario:");
        block1.add(textfield);
        array_blockes[1] = block1;

        Block block2 = new Block(Color.white,225,300,400,100);
        textfield1 = new JTextField();
        textfield1.setPreferredSize(new Dimension(250,40));
        block2.addLabel("password:");
        block2.add(textfield1);
        array_blockes[2] = block2;

        Block block3 = new Block(Color.white,225,400,400,100);
        button = new JButton("confirmar");
        button.addActionListener(this);
        block3.add(button);
        array_blockes[3] = block3;

        this.add(block);
        this.add(block1);
        this.add(block2);
        this.add(block3);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button){

            user = new UsuarioService();

            try {

                Usuario temp = user.buscar(textfield.getText(),textfield1.getText());
                if(temp != null){

                    nextPage.setUserID(temp);
                    nextPage.CrearPartes();
                    nextPage.setVisible(true);
                    this.setVisible(false);

                }


            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}
