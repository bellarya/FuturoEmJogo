import java.io.File; // para importar arquivos

import javax.swing.JFrame; // para criar a janela
import javax.swing.SwingUtilities; // para rodar o JavaFX no Swing

import javafx.application.Platform; // para rodar o JavaFX
import javafx.embed.swing.JFXPanel; // para rodar o JavaFX no Swing
import javafx.scene.media.Media; // para carregar o video
import javafx.scene.media.MediaPlayer; // para reproduzir o video
import javafx.scene.media.MediaView; // para mostrar o video

public class Narracao {
    public static void telaVideo(JFrame janela) { // método para mostrar a tela de introdução
        SwingUtilities.invokeLater(() -> { // executa no thread da interface gráfica, sem isso o video não roda
                
            JFXPanel introVideo = new JFXPanel(); // cria o painel para o video
            janela.setContentPane(introVideo); // define o painel de video como conteúdo da janela
            janela.revalidate(); // atualiza a janela

            
            Platform.runLater(() -> { // executa no thread do JavaFX
                try {
                    String narracao = new File("src\\narração.mp4").toURI().toString(); // caminho do video
                    Media media = new Media(narracao); // cria o media com o caminho do video
                    MediaPlayer player = new MediaPlayer(media); // cria o player com o media
                    MediaView view = new MediaView(player); // cria o view com o player

                    view.setFitWidth(1366); // define a largura do video
                    view.setFitHeight(768); // define a altura do video
                    view.setPreserveRatio(false); // preserva a proporção do video
                    
                    javafx.scene.Group grupo = new javafx.scene.Group(view); // cria um grupo para adicionar o video
                    javafx.scene.Scene cena = new javafx.scene.Scene(grupo, janela.getWidth(), janela.getHeight()); // cria uma cena para adicionar o video no mesmo tamanho da janela

                    introVideo.setScene(cena); // adiciona a cena no painel

                    javafx.scene.control.Button botaoPular = new javafx.scene.control.Button("Pular Introdução"); // cria o botão pular
                    botaoPular.setLayoutX(1100); 
                    botaoPular.setLayoutY(600);
                    botaoPular.setStyle("font-size: 18px; background-color: #ffffffaa;");

                    grupo.getChildren().add(botaoPular); // adiciona o botão no grupo

                    botaoPular.setOnAction(eventobotaoPular -> { // ação do botão pular
                        player.stop();
                        player.dispose();
                        grupo.getChildren().remove(botaoPular); // remove o botão pular

                        SwingUtilities.invokeLater(() -> {
                        janela.getContentPane().removeAll(); // remove tudo
                        janela.revalidate(); // recalcula o layout
                        janela.repaint();    // redesenha a tel
                        
                        });

                    Rotas.iniciarJogo(janela); // chama o método iniciarJogo da classe Rotas
                        
                    });

                    player.setOnEndOfMedia(() -> {
                        player.dispose(); // finaliza o video
                        grupo.getChildren().remove(botaoPular);

                        SwingUtilities.invokeLater(() -> {
                        janela.getContentPane().removeAll(); // Remove tudo
                        janela.revalidate(); // Recalcula o layout
                        janela.repaint();    // Redesenha a tela
                        
                        });
                        
                        Rotas.iniciarJogo(janela); // chama o método iniciarJogo da classe Rotas
                        
                    });

                    player.play(); // inicia o video
                } catch (Exception excecao) { // captura qualquer erro
                    System.out.println("Erro: " + excecao.getMessage());// imprime o erro
                }
            });
        });
}
}