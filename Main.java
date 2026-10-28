/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi1;

/**
 *
 * @author VICTUS
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ManajemenLogistik manajer = new ManajemenLogistik();
        int pilihan = 0;

        do {
            System.out.println("\n=== SISTEM LOGISTIK BANTUAN 3T ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Daftar Muatan");
            System.out.println("3. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Pilihan harus berupa angka 1-3!");
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1:
                    tambahBarang(input, manajer);
                    break;
                case 2:
                    manajer.tampilkanDaftar();
                    break;
                case 3:
                    if (manajer.getJumlahBarang() == 0) {
                        System.out.println("Belum ada barang. Tambahkan minimal satu barang dulu.");
                        pilihan = 0;
                    } else {
                        manajer.cetakManifes("manifest_pengiriman.txt");
                    }
                    break;
                default:
                    System.out.println("Menu tidak tersedia!");
            }
        } while (pilihan != 3);

        System.out.println("Terima kasih. Program selesai.");
        input.close();
    }

    // Ulangi input sampai data valid
    private static void tambahBarang(Scanner input, ManajemenLogistik manajer) {
        boolean berhasil = false;

        while (!berhasil) {
            try {
                System.out.print("Kode barang (awali EDX-)  : ");
                String kode = input.nextLine().trim();
                System.out.print("Nama barang               : ");
                String nama = input.nextLine().trim();
                System.out.print("Kategori (Elektronik/Buku): ");
                String kategori = input.nextLine().trim();
                System.out.print("Berat (kg)                : ");
                double berat = Double.parseDouble(input.nextLine().trim());

                manajer.tambahBarang(kode, nama, kategori, berat);
                System.out.println(">> Barang berhasil ditambahkan.");
                berhasil = true;

            } catch (BeratInvalidException e) {
                System.out.println("[ERROR] " + e.getMessage() + ". Silakan ulangi input.");
            } catch (IllegalArgumentException e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("[ERROR] Berat harus berupa angka. Silakan ulangi input.");
                } else {
                    System.out.println("[ERROR] " + e.getMessage() + ". Silakan ulangi input.");
                }
            } finally {
                System.out.println("(Proses validasi input selesai)");
            }
        }
    }
}
  
