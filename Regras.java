import java.awt.Graphics; // para desenhar na tela
import java.awt.Image; // para manipular imagens
import java.awt.image.BufferedImage; // para armazenar imagens
import java.io.File; // para importar arquivos

import javax.imageio.ImageIO; // para ler imagens
import javax.swing.JButton; // para criar botões
import javax.swing.JFrame; // para criar a janela
import javax.swing.JPanel; // para criar painéis


public class Regras {
    public static void telaRegras(JFrame janela) { // método para mostrar a tela de regras
        // Implementar a tela de regras
        final BufferedImage[] imagemRegras = new BufferedImage[1];
        try {
            imagemRegras[0] = ImageIO.read(new File("src\\regras.jpg"));
        } catch (Exception excecao) { // captura qualquer erro
            System.out.println("Erro: " + excecao.getMessage());// imprime o erro
        }

        JPanel painelRegras = new JPanel() {
            protected void paintComponent(Graphics g) {
                if (imagemRegras[0] != null) {
                    Image imagem = imagemRegras[0].getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(imagem, 0, 0, this);
                }
            }
        };

        painelRegras.setLayout(null); // layout livre pra colocar os botões onde quiser

        JButton botaoContinuar = new JButton("Continuar"); // cria o botão continuar
        botaoContinuar.setBounds(580, 600, 170, 70); // posição do botão
        botaoContinuar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20)); // fonte do botão
        painelRegras.add(botaoContinuar); // adiciona o botão ao painel de regras

        janela.setContentPane(painelRegras); // define o painel de regras como conteúdo da janela
        janela.revalidate(); // atualiza a janela
        janela.repaint(); // repinta a janela

        botaoContinuar.addActionListener(e -> { // ação do botão continuar
            janela.remove(painelRegras); // fecha a janela
            painelRegras.remove(botaoContinuar); // remove o botão continuar
            janela.revalidate(); // atualiza a janela
            janela.repaint(); // repinta a janela

            Narracao.telaVideo(janela); // chama o método telaVideo da classe Narracao
        });

    }
}