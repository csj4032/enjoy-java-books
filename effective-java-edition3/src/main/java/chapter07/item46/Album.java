package chapter07.item46;

public class Album {

    private Artist artist;
    private long sales;

    public Album(Artist artist, long sales) {
        this.artist = artist;
        this.sales = sales;
    }

    public Artist getArtist() {
        return artist;
    }

    public long getSales() {
        return sales;
    }
}
