package alfarobidev.jadwalbioskop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class City implements Serializable {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Data> dataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public class Data implements Serializable{
        @SerializedName("id")
        String id;
        @SerializedName("kota")
        String kota;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKota() {
            return kota;
        }

        public void setKota(String kota) {
            this.kota = kota;
        }
    }
}
