/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author Bruger
 */
public class MovieDTO {

    private long id;
    private int year;
    private String name;
    private String[] actors;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.year = movie.getYear();
        this.name = movie.getName();
        this.actors = movie.getActors();
    }
    
    
}
