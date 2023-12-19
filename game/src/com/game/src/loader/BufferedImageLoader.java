package com.game.src.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

    private BufferedImage image;

    // Fungsi untuk memuat gambar dari path yang diberikan
    public BufferedImage loadImage(String path) {
        try {
            // Membaca gambar dari path menggunakan ImageIO
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            // Menangani kesalahan jika gambar tidak dapat dimuat
            e.printStackTrace();
        }
        // Mengembalikan gambar yang telah dimuat
        return image;
    }

    // Fungsi untuk memuat gambar dari path dengan spesifikasi potongan gambar (subimage)
    public BufferedImage loadImage(String path, int x, int y, int width, int height) {
        try {
            // Membaca gambar dari path menggunakan ImageIO
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            // Menangani kesalahan jika gambar tidak dapat dimuat
            e.printStackTrace();
        }
        // Mengambil potongan (subimage) dari gambar berdasarkan koordinat dan ukuran yang diberikan
        image = image.getSubimage(x, y, width, height);

        // Mengembalikan potongan (subimage) dari gambar yang telah dimuat
        return image;
    }
}
