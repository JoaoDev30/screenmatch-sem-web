package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.InfEpisodio;
import br.com.alura.screenmatch.model.InfSerie;
import br.com.alura.screenmatch.model.InfTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner  scan = new Scanner(System.in);

    private ConverteDados conversor = new ConverteDados();

    private  ConsumoApi consumo =  new ConsumoApi();


    //Além do final usado também static para conseguir acessar esses atributos em outras classes
    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=df521066";



    public void exibeMenu(){
        // PERGUNTA AO USUÁRIO QUAL SÉRIE BUSCAR
        System.out.println("Digite a série que deseja buscar: ");
        var nomeSerie = scan.nextLine();

        // FAZ A BUSCA DO USUÁRIO JUNTO COM OS ATRIBUTOS
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        InfSerie infSerie = conversor.obterDados(json, InfSerie.class);
        System.out.println(infSerie);

        List<InfTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i<= infSerie.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+")  +"&season="+ i +API_KEY);
            InfTemporada infTemporada = conversor.obterDados(json, InfTemporada.class);
            temporadas.add(infTemporada);

        }
        temporadas.forEach(System.out::println);

//            for (int i = 0;i < infSerie.totalTemporadas(); i++){
//                List<InfEpisodio> episodioTemporadas = temporada.get(i).episodios();
//                for(int j = 0; j< episodioTemporadas.size(); j++)
//                    System.out.println(episodioTemporadas.get(j).titulo());

        // FUNÇÃO LAMBDA PARA CÓDIGO MAIS LIMPO
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo()) ) );
        temporadas.forEach(System.out::println);


        List<InfEpisodio> infEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
    }   //.toList(); não poderia adcionar mais nada, por ser uma collection imutavel
}


