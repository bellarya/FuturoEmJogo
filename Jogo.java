import javax.swing.JFrame; // para criar a janela

public class Jogo {
    public static void main(String[] args) { // classe principal
        
        JFrame janela = new JFrame("Futuro em Jogo"); // cria a janela
        janela.setSize(1366, 768); // define o tamanho da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha a janela quando clicar no X 
        janela.setLocationRelativeTo(null); // centraliza a janela na tela
        janela.setVisible(true); // torna a janela visivel

        Intro.telaIntro(janela); // chama a tela de intro
                
    }
}
