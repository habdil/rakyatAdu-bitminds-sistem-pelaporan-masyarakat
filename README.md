# RakyatAdu

RakyatAdu adalah suatu sistem perangkat lunak yang dirancang untuk membantu pemerintah dalam menangani berbagai permasalahan lokal. Melalui platform ini, masyarakat dapat dengan mudah melaporkan berbagai isu yang mereka hadapi di lingkungan mereka, mulai dari jalan rusak, masalah keamanan, hingga isu-isu lain yang mempengaruhi kualitas hidup sehari-hari.

## Fitur Utama

- **Pelaporan Real-Time:** Warga dapat mengirimkan laporan secara real-time dengan foto, lokasi GPS, dan deskripsi masalah.
- **Distribusi Otomatis:** Setiap laporan yang masuk akan diteruskan secara otomatis ke dinas terkait.
- **Pemantauan Status:** Warga dapat memantau status laporan mereka dan memberikan umpan balik.
- **Transparansi:** Lihat laporan-laporan lain yang telah diajukan di wilayah mereka.

## Teknologi yang Digunakan

- **Backend:** Java
- **Framework:** JavaFX
- **Database:** MySQL
- **Arsitektur:** Model-View-Controller (MVC)

## Prasyarat

Pastikan Anda sudah menginstal:

- Java JDK
- MySQL Server

## Instalasi

1. **Clone repository ini:**

    ```bash
    git clone https://github.com/habdil/rakyatAdu-bitminds-sistem-pelaporan-masyarakat.git
    cd rakyatAdu-bitminds-sistem-pelaporan-masyarakat
    ```

2. **Konfigurasi Database:**

    Buat database baru di MySQL dan impor skema database dari file `rakyatadu.sql` yang tersedia di repository ini.

    ```sql
    CREATE DATABASE rakyatadu;
    USE rakyatadu;
    SOURCE path/to/rakyatadu.sql;
    ```

## Penggunaan

1. **Membuat Laporan Baru:**

    - Buka aplikasi RakyatAdu.
    - Klik tombol "Beranda".
    - Isi form laporan dengan deskripsi, foto, dan lokasi GPS.
    - Klik "Kirim".

2. **Memantau Status Laporan:**

    - Klik tab "Status Laporan".
    - Anda dapat melihat status laporan Anda di sini.

3. **Memberikan Umpan Balik**

    - Klik tab "Umpan Balik".
    - Lihat laporan-laporan lain yang telah diajukan di wilayah Anda.

## Kontribusi

Kami sangat mengapresiasi kontribusi dari komunitas. Untuk kontribusi, silakan fork repository ini, buat branch fitur baru, dan ajukan pull request.

## Lisensi

Proyek ini dilisensikan di bawah lisensi MIT. Lihat file LICENSE untuk detail lebih lanjut.
