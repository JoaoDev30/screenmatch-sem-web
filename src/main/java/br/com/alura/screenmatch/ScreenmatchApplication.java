package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.InfSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoapi = new ConsumoApi();
		var json = consumoapi.obterDados("https://www.omdbapi.com/?t=the+flash&apikey=df521066");
		System.out.println(json);
		var conversor = new ConverteDados();
		var info = conversor.obterDados(json, InfSerie.class);
		System.out.println(info);
	}
}
