/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi1;

/**
 *
 * @author VICTUS
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManajemenLogistik {

    private ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();

    // Validasi data lalu simpan ke ArrayList
    public void tambahBarang(String kode, String nama, String kategori, double berat)
            throws BeratInvalidException {

        if (!kode.startsWith("EDX-")) {
            throw new IllegalArgumentException("Kode barang harus diawali EDX-");
        }
        if (berat <= 0) {
            throw new BeratInvalidException("Berat barang harus lebih dari 0 kg (input: " + berat + ")");
        }

        daftarBarang.add(new BarangBantuan(kode, nama, kategori, berat));
    }

    public int getJumlahBarang() {
        return daftarBarang.size();
    }

    public double hitungTotalBerat() {
        double total = 0;
        for (BarangBantuan b : daftarBarang) {
            total += b.getBeratKg();
        }
        return total;
    }

    // Percabangan if-else untuk label armada
    public String tentukanArmada(double totalBerat) {
        String armada;
        if (totalBerat < 50) {
            armada = "Mobil Pickup";
        } else {
            armada = "Truk Ekspedisi";
        }
        return armada;
    }

    public void tampilkanDaftar() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang yang didata.");
            return;
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s | %-22s | %-12s | %s%n", "KODE", "NAMA BARANG", "KATEGORI", "BERAT");
        System.out.println("---------------------------------------------------------------");
        for (BarangBantuan b : daftarBarang) {
            b.tampilkan();
        }
        System.out.println("---------------------------------------------------------------");
    }

    // File I/O: Cetak manifest
    public void cetakManifes(String namaFile) {
        int totalBarang = daftarBarang.size();
        double totalBerat = hitungTotalBerat();
        String armada = tentukanArmada(totalBerat);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile))) {
            bw.write("==============================================================");
            bw.newLine();
            bw.write("        MANIFES PENGIRIMAN LOGISTIK PENDIDIKAN DAERAH 3T");
            bw.newLine();
            bw.write("==============================================================");
            bw.newLine();
            bw.write(String.format("%-4s %-10s %-22s %-12s %10s", "No", "Kode", "Nama Barang", "Kategori", "Berat (kg)"));
            bw.newLine();
            bw.write("--------------------------------------------------------------");
            bw.newLine();

            int no = 1;
            for (BarangBantuan b : daftarBarang) {
                bw.write(String.format("%-4d %-10s %-22s %-12s %10.2f",
                        no++, b.getKodeBarang(), b.getNamaBarang(), b.getKategori(), b.getBeratKg()));
                bw.newLine();
            }

            bw.write("--------------------------------------------------------------");
            bw.newLine();
            bw.write("Total Barang  : " + totalBarang + " item");
            bw.newLine();
            bw.write(String.format("Total Berat   : %.2f kg", totalBerat));
            bw.newLine();
            bw.write("Armada        : " + armada);
            bw.newLine();
            bw.write("==============================================================");
            bw.newLine();

            System.out.println("Data manifes berhasil diekspor ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menulis file manifes: " + e.getMessage());
        }
    }
}

