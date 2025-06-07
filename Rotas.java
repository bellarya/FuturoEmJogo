import java.io.File; // para importar arquivos

import javax.swing.JFrame; // para criar a janela

import javafx.application.Platform; // para rodar o JavaFX
import javafx.embed.swing.JFXPanel; // para rodar o JavaFX no Swing
import javafx.scene.Group; // para criar um grupo de elementos
import javafx.scene.Scene; // para criar uma cena
import javafx.scene.control.Button; // para criar botões
import javafx.scene.media.Media; // para carregar o video
import javafx.scene.media.MediaPlayer; // para reproduzir o video
import javafx.scene.media.MediaView; // para mostrar o video

public class Rotas {
    public static void iniciarJogo(JFrame janela) { // metodo para iniciar o jogo 
        
        mostrarVideoIntro(janela); // chma o de baixo
    }

    public static void mostrarVideoIntro(JFrame janela) { // outro metodo para mostrar o primeiro video
        reproduzirVideoComEscolhas(janela, 
            "src\\viuoFuturo.mp4",
            "Falar com amigo", () -> mostrarRota1(janela),
            "Investigar sozinho", () -> mostrarRota8(janela)); // aqui chama os metodos das rotas com os botões levando para diferentes medotos
    }

    public static void mostrarRota1(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\falarAmigoDemitido.mp4",
            "Desistir", () -> mostrarRota2(janela),
            "Espalhar nas redes", () -> mostrarRota3(janela));
    }

    public static void mostrarRota2(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\desistirFim.mp4",
            null, null,
            null, null); 
    }

    public static void mostrarRota3(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\espalharPerseguido.mp4",
            "Se entregar", () -> mostrarRota4(janela),
            "Se esconder", () -> mostrarRota5(janela));
    }
    public static void mostrarRota4(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\seEntregaMorre.mp4",
            null, null,
            null, null);
    }
    public static void mostrarRota5(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\esconderRebeliao.mp4",
            "Explodir empresa", () -> mostrarRota6(janela),
            "Denunciar para a imprensa", () -> mostrarRota7(janela));
    }
    public static void mostrarRota6(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\explodirEmpresa.mp4",
            null, null,
            null, null);
    }
    public static void mostrarRota7(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\exporTudoFimBom.mp4",
            null, null,
            null, null);
    }
    public static void mostrarRota8(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\investigarDescobrir.mp4",
            "Espalhar nas redes", () -> mostrarRota3(janela),
            "Derrubar empresa por conta própria", () -> mostrarRota10(janela));
    }
    public static void mostrarRota10(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\fazendoPlanos.mp4",
            "Modificar as fórmulas químicas", () -> mostrarRota11(janela),
            "Expor na imprensa", () -> mostrarRota12(janela));
    }
    public static void mostrarRota11(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\modificarFormulas.mp4",
            null, null,
            null, null);
    }
    public static void mostrarRota12(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\levarImprensa.mp4",
            "Desistir", () -> mostrarRota2(janela),
            "Solicitar uma investigação", () -> mostrarRota13(janela));
    }
    public static void mostrarRota13(JFrame janela) {
        reproduzirVideoComEscolhas(janela,
            "src\\investigaçãoEmpresaFecha.mp4",
            null, null,
            null, null);
    }

    public static void reproduzirVideoComEscolhas(JFrame janela, String caminhoVideo, String texto1, Runnable acao1, String texto2, Runnable acao2) { // metodo de reproduzir video com escolhas e ali esta todos os atributos

        JFXPanel fundo = new JFXPanel();
        janela.setContentPane(fundo); // define o painel de video como conteúdo da janela
        janela.revalidate(); // atualiza a janela

        Platform.runLater(() -> { // executa no thread da interface gráfica, sem isso o video não roda
            
                Media video = new Media(new File(caminhoVideo).toURI().toString()); // carrega o video
                MediaPlayer player = new MediaPlayer(video); // criação do player
                MediaView tela = new MediaView(player); // criação da tela

                tela.setFitWidth(janela.getWidth()); // ajusta a largura para ficar do mesmo tamanho da janela
                tela.setFitHeight(janela.getHeight()); // ajusta a altura para ficar do mesmo tamanho da janela
                tela.setPreserveRatio(false); // aqui preserva a proporção do video

                Group grupo = new Group(tela); // cria um grupo para adicionar o video
                Scene cena = new Scene(grupo, janela.getWidth(), janela.getHeight()); // cria uma cena para adicionar o video no mesmo tamanho da janela
                fundo.setScene(cena); // adiciona a cena no painel
                

                if (texto1 != null && acao1 != null) { // se o texto1 e a acao1 não forem nulos, o botão é criado
                    Button botao1 = new Button(texto1); // cria o botão
                    botao1.setStyle("font-size: 18px; background-color: #ffffffaa;"); // estilo do botão
                    botao1.setMinSize(200, 50); // tamanho do botão
                    botao1.setLayoutX(300); // ajuste do botão
                    botao1.setLayoutY(20); // ajuste do botão
                    botao1.setOnAction(evento -> { // a ação que o botão tem
                        player.stop(); // parar o video
                        acao1.run(); // executar a acao1
                    });
                    grupo.getChildren().add(botao1); // adiciona o botão no grupo
                }

                if (texto2 != null && acao2 != null) { // mesmam coisa, se o texto2 e a acao2 não forem nulos, o botão é criado
                    Button botao2 = new Button(texto2);
                    botao2.setStyle("font-size: 18px; background-color: #ffffffaa;");
                    botao2.setMinSize(200, 50); 
                    botao2.setLayoutX(800);
                    botao2.setLayoutY(20);
                    botao2.setOnAction(e -> {
                        player.stop();
                        acao2.run();
                    });
                    grupo.getChildren().add(botao2);
                }

                player.setOnEndOfMedia(() -> { // quando o video acabar tal coisa acontece
                    player.dispose(); // finaliza o video
                });

                player.play(); // inicia o video
            
        });
    }
}