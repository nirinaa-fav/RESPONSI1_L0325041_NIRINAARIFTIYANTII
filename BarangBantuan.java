// Nama : Nirina Ariftiyanti 
// NIM  : L0325041

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi1;

/**
 *
 * @author VICTUS
 */
public class BarangBantuan {

    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;

    public BarangBantuan(String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public double getBeratKg() {
        return beratKg;
    }

    public void tampilkan() {
        System.out.printf("%-10s | %-22s | %-12s | %8.2f kg%n",
                kodeBarang, namaBarang, kategori, beratKg);
    }
}

