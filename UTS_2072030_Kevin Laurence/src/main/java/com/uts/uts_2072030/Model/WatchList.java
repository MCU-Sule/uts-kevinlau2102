package com.uts.uts_2072030.Model;

public class WatchList {
    private int idWatchList;
    private int LastWatch;
    private int Favorite;
    private Movie Movie_idMovie;
    private User User_idUser;

    public WatchList(int idWatchList, int lastWatch, int favorite, Movie movie_idMovie, User user_idUser) {
        this.idWatchList = idWatchList;
        LastWatch = lastWatch;
        Favorite = favorite;
        Movie_idMovie = movie_idMovie;
        User_idUser = user_idUser;
    }

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public int getLastWatch() {
        return LastWatch;
    }

    public void setLastWatch(int lastWatch) {
        LastWatch = lastWatch;
    }

    public int getFavorite() {
        return Favorite;
    }

    public void setFavorite(int favorite) {
        Favorite = favorite;
    }

    public Movie getMovie_idMovie() {
        return Movie_idMovie;
    }

    public void setMovie_idMovie(Movie movie_idMovie) {
        Movie_idMovie = movie_idMovie;
    }

    public User getUser_idUser() {
        return User_idUser;
    }

    public void setUser_idUser(User user_idUser) {
        User_idUser = user_idUser;
    }

    @Override
    public String toString() {
        return "WatchList{" +
                "idWatchList=" + idWatchList +
                ", LastWatch=" + LastWatch +
                ", Favorite=" + Favorite +
                ", Movie_idMovie=" + Movie_idMovie +
                ", User_idUser=" + User_idUser +
                '}';
    }
    public String getDurasiWatch() {
        String durasiWatch = LastWatch + "/" + Movie_idMovie.getDurasi();
        return durasiWatch;
    }
    public boolean getBoolFavorite() {
        boolean bool;
        if (Favorite == 1) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }
}
