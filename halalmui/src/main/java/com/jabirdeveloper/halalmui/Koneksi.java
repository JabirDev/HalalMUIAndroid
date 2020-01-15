package com.jabirdeveloper.halalmui;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class Koneksi {

    static final String BASE_URL = "https://www.halalmui.org/";
    static final String URL_CARI = BASE_URL + "mui14/searchproduk/search";
    static final String KUNCI_KATEGORI_NAMA_PRODUK = "?kategori=nama_produk";
    static final String KUNCI_KATEGORI_NAMA_PRODUSEN = "?kategori=nama_produsen";
    static final String KUNCI_KATEGORI_NOMOR_SERTIFIKAT = "?kategori=nomor_sertifikat";
    static final String KUNCI_QUERY = "&katakunci=";

    public interface HalalData {
        @GET
        Call<ResponseBody> getHalalData(@Url String url);
    }

    private static HalalData halalData = null;
    public static HalalData getHalal(){
        if (halalData == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            halalData = retrofit.create(HalalData.class);
        }
        return halalData;
    }

}
