package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record InfEpisodio(@JsonAlias("Title")String titulo,
                          @JsonAlias("Episode")Integer nuumero,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonAlias("Released") String dataLancamento) {
}
