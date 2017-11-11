package PacoteVisao;
import PacoteDAO.AcessoFrame;
public class Principal {
    public static void main(String[] args) {
        AcessoFrame a;
        a = new AcessoFrame();
        
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        a.setSize(600, 500);
        
    }
    
}
