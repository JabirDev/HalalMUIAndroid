package com.jabirdeveloper.halalmui;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.jabirdeveloper.halalmui.Koneksi.KUNCI_KATEGORI_NAMA_PRODUK;
import static com.jabirdeveloper.halalmui.Koneksi.KUNCI_KATEGORI_NAMA_PRODUSEN;
import static com.jabirdeveloper.halalmui.Koneksi.KUNCI_KATEGORI_NOMOR_SERTIFIKAT;
import static com.jabirdeveloper.halalmui.Koneksi.KUNCI_QUERY;
import static com.jabirdeveloper.halalmui.Koneksi.URL_CARI;

public class HalalMui {
    private static List<String> namaProdukList = new ArrayList<>();
    private static List<String> namaProdusenList = new ArrayList<>();
    private static List<String> tanggalList = new ArrayList<>();
    private static List<String> sertifikatList = new ArrayList<>();
    private static List<HalalData> halalData = new ArrayList<>();
    private static String url;
    private static HalalListener halal;
    private static String query;

    private static void hapusList() {
        namaProdukList.clear();
        namaProdusenList.clear();
        tanggalList.clear();
        sertifikatList.clear();
        halalData.clear();
    }

    public static void byNamaProduk(String q, HalalListener listener) {
        hapusList();
        halal = listener;
        query = q;
        if (q == null) {
            Log.e(TAG, "Query tidak boleh kosong");
        } else {
            url = URL_CARI + KUNCI_KATEGORI_NAMA_PRODUK + KUNCI_QUERY + q;
            ambilData("produk");
        }
    }

    public static void byNomorSertifikat(String q, HalalListener listener) {
        hapusList();
        halal = listener;
        if (q == null) {
            Log.e(TAG, "Query tidak boleh kosong");
        } else {
            url = URL_CARI + KUNCI_KATEGORI_NOMOR_SERTIFIKAT + KUNCI_QUERY + q;
            ambilData("sertifikat");
        }
    }

    public static void byNamaProdusen(String q, HalalListener listener) {
        hapusList();
        halal = listener;
        if (q == null) {
            Log.e(TAG, "Query tidak boleh kosong");
        } else {
            url = URL_CARI + KUNCI_KATEGORI_NAMA_PRODUSEN + KUNCI_QUERY + q;
            ambilData("produsen");
        }
    }

    private static void ambilData(final String produk) {
        Call<ResponseBody> data = Koneksi.getHalal().getHalalData(url);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Document document = null;
                try {
                    document = Jsoup.parse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String a = document.select(".table.table-striped.table-bordered.table-cph.table-cph-v2").toString();
                Document docTable = Jsoup.parse(a);
                Elements trElements = docTable.select("tr td");
                for (int i = 0; i < trElements.size(); i++) {
                    String b = trElements.get(i).toString();
                    Document docSpan = Jsoup.parse(b);
                    Elements elSpan = docSpan.select("span");
                    for (int j = 0; j < elSpan.size(); j++) {
                        String c = elSpan.get(j).text();
                        if (c.contains("Nama Produk :")) {
                            String namaProduk = c.replace("Nama Produk :", "");
                            namaProdukList.add(namaProduk);
                        }
                        if (c.contains("Nomor Sertifikat :")) {
                            String sertifikat = c.replace("Nomor Sertifikat :", "");
                            sertifikatList.add(sertifikat);
                        }
                    }
                    String id = trElements.get(i).select("a.table-cph-btn").attr("id");
                    String[] p = id.split("\\|");
                    for (int j = 0; j < p.length; j++) {
                        if (j == 3) {
                            namaProdusenList.add(p[j]);
                        }
                        if (j == 5) {
                            tanggalList.add(p[j]);
                        }
                    }
                }

                ambilUnextractData();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                halal.ketikaGagal(t);
            }
        });
    }

    private static void ambilUnextractData() {
        for (int i = 0; i < namaProdukList.size(); i++) {
            if (!isExist(namaProdukList.get(i))) {
                halalData.add(new HalalData(namaProdukList.get(i), namaProdusenList.get(i), tanggalList.get(i), sertifikatList.get(i)));
            }
        }
        if (halalData.size() != 0) {
            halal.ketikaSukses(halalData);
        } else {
            halal.ketikaTidakAdaData("Data untuk " + query + " tidak tersedia.");
        }
    }

    private static boolean isExist(String produk) {
        for (int i = 0; i < halalData.size(); i++) {
            if (halalData.get(i).getNamaProduk().equals(produk)) {
                return true;
            }
        }
        return false;
    }

}
