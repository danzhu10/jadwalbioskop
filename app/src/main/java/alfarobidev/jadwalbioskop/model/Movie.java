package alfarobidev.jadwalbioskop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class Movie implements Serializable {
    @SerializedName("status")
    String status;
    @SerializedName("kota")
    String kota;
    @SerializedName("date")
    String date;
    @SerializedName("data")
    List<DataMovie> dataMovies;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DataMovie> getDataMovies() {
        return dataMovies;
    }

    public void setDataMovies(List<DataMovie> dataMovies) {
        this.dataMovies = dataMovies;
    }

    public class DataMovie implements Serializable{
        @SerializedName("movie")
        String movie;
        @SerializedName("poster")
        String poster;
        @SerializedName("genre")
        String genre;
        @SerializedName("duration")
        String duration;
        @SerializedName("jadwal")
        List<Schedule> scheduleList;

        public String getMovie() {
            return movie;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public List<Schedule> getScheduleList() {
            return scheduleList;
        }

        public void setScheduleList(List<Schedule> scheduleList) {
            this.scheduleList = scheduleList;
        }
    }

    public class Schedule implements Serializable{
        @SerializedName("bioskop")
        String bioskop;
        @SerializedName("harga")
        String harga;
        @SerializedName("jam")
        List<String> timeList;

        public String getBioskop() {
            return bioskop;
        }

        public void setBioskop(String bioskop) {
            this.bioskop = bioskop;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public List<String> getTimeList() {
            return timeList;
        }

        public void setTimeList(List<String> timeList) {
            this.timeList = timeList;
        }
    }
}
