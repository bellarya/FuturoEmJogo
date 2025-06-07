import java.awt.Graphics; // para desenhar na tela
import java.awt.Image; // para manipular imagens
import java.awt.image.BufferedImage; // para armazenar imagens
import java.io.File; // para importar arquivos

import javax.imageio.ImageIO; // para ler imagens
import javax.swing.JButton; // para criar botões
import javax.swing.JFrame; // para criar a janela
import javax.swing.JPanel; // para criar painéis

public class Intro {
    public static void telaIntro(JFrame janela) { // método para mostrar a tela de introdução

        final BufferedImage[] imagemFundo = new BufferedImage[1]; // para armazenar apenas imagem de fundo
        try {
            imagemFundo[0] = ImageIO.read(new File("src\\imagemFundo2.jpg")); // caminho da imagem de fundo
        } catch (Exception excecao) { // captura qualquer erro
            System.out.println("Erro: " + excecao.getMessage());// imprime o erro
        }

        JPanel painelIntro = new JPanel() { // painel para a tela de introdução
            protected void paintComponent(Graphics g) { // sobreescrito, método para desenhar a imagem de fundo
                if (imagemFundo[0] != null) {  // verifica se a imagem de fundo não é nula, se não for, a imagem aparece
                    Image imagem = imagemFundo[0].getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); // redimensiona a imagem de fundo
                    g.drawImage(imagem, 0, 0, this); // desenha a imagem de fundo, g é o pincel
                }
            }
        };

        painelIntro.setLayout(null); // layout livre pra colocar os botões onde quiser

        JButton botaoJogar = new JButton("Jogar"); // cria o botão jogar
        botaoJogar.setBounds(990, 600, 170, 70); // posição do botão
        botaoJogar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        painelIntro.add(botaoJogar); // adiciona o botão ao painel de introdução

        botaoJogar.addActionListener(eventoBotaoJogar -> {
            painelIntro.remove(botaoJogar); // remove o botão jogar
            janela.remove(painelIntro); // fecha a janela

            Regras.telaRegras(janela); // chama o método telaRegras da classe Regras
        
});
        janela.setContentPane(painelIntro); // define o painel de introdução como conteúdo da janela
        janela.setVisible(true); // torna a janela visível
}
}