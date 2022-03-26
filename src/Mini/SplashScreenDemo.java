package Mini;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static Mini.Main.areYouSure;
import static Mini.Main.loginFrame;

public class SplashScreenDemo {
    JFrame frame;
    URL imageUrl = ClassLoader.getSystemResource("imagexx.jpg");
    JLabel image = new JLabel(new ImageIcon(imageUrl));
    JLabel text = new JLabel("AE Sales System");
    JProgressBar progressBar = new JProgressBar();


    SplashScreenDemo() {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        runningPBar();
    }

    public void LogInFrameShow(){
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        loginFrame = new JFrame("Welcome To AE Sales System");
        loginFrame.setContentPane(new LogIn().getTheMainScreen());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setIconImage(img.getImage());
        loginFrame.setLocationRelativeTo(null);//Setting location to the center of screen
        loginFrame.getContentPane().setBackground(Color.white);//setting background color
        loginFrame.setSize(600,300);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loginFrame.setLocation(dim.width/2-loginFrame.getSize().width/2, dim.height/2-loginFrame.getSize().height/2);

        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

    public void createGUI() {
        URL imageUrl = ClassLoader.getSystemResource("icon1.png");
        ImageIcon img = new ImageIcon(imageUrl);
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.black);
        frame.setIconImage(img.getImage());
        frame.setVisible(true);
    }

    public void addImage() {
        image.setSize(600, 200);
        frame.add(image);
    }

    public void addText() {
        text.setFont(new Font("arial", Font.BOLD, 30));
        text.setBounds(170, 220, 600, 40);
        text.setForeground(Color.white);
        frame.add(text);
    }

    public void addProgressBar() {
        progressBar.setBounds(100, 280, 400, 5);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.white);
        progressBar.setForeground(Color.black);
        progressBar.setStringPainted(false);
        progressBar.setValue(0);
        frame.add(progressBar);
    }

    public void runningPBar() {
        int i=0;
        while( i<=100)
        {
            try{
                Thread.sleep(50);
                progressBar.setValue(i);
                i++;
                if(i==100) {
                    frame.dispose();
                    LogInFrameShow();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

}

