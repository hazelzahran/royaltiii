class Buku {
    private String judul;
    private String penulis;
    private String kategori;
    private String sinopsis;
    private Buku next;

    public Buku(String judul, String penulis, String kategori, String sinopsis) {
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.sinopsis = sinopsis;
        this.next = null;
    }

    // Getter methods
    public String getJudul() {
        return this.judul;
    }

    public String getPenulis() {
        return this.penulis;
    }

    public String getKategori() {
        return this.kategori;
    }

    public void setNext(Buku next) {
        this.next = next;
    }

    public Buku getNext() {
        return next;
    }

    public void tampilkanInfo() {
        System.out.println("Judul: " + judul);
        System.out.println("Penulis: " + penulis);
        System.out.println("Kategori: " + kategori);
        System.out.println("Sinopsis: " + sinopsis);
        System.out.println("Jumlah Kata dalam Sinopsis: " + hitungJumlahKataSinopsis());
        System.out.println("---------------------------");
    }

    public int hitungJumlahKataSinopsis() {
        return sinopsis.split(" ").length;
    }

    public double cekKesamaan(Buku bukuLain) {
        int totalAtribut = 4;
        double kesamaan = 0;

        // Menghitung kesamaan judul
        if (this.judul.toLowerCase().equals(bukuLain.judul.toLowerCase())) {
            kesamaan++;
        } else if (this.judul.toLowerCase().contains(bukuLain.judul.toLowerCase()) || 
                   bukuLain.judul.toLowerCase().contains(this.judul.toLowerCase())) {
            kesamaan += 0.5;
        }

        // Menghitung kesamaan penulis
        if (this.penulis.toLowerCase().equals(bukuLain.penulis.toLowerCase())) {
            kesamaan++;
        }

        // Menghitung kesamaan kategori
        if (this.kategori.toLowerCase().equals(bukuLain.kategori.toLowerCase())) {
            kesamaan++;
        }

        // Menghitung kesamaan sinopsis
        if (this.sinopsis.toLowerCase().equals(bukuLain.sinopsis.toLowerCase())) {
            kesamaan++;
        } else {
            String[] kataThis = this.sinopsis.toLowerCase().split(" ");
            String[] kataOther = bukuLain.sinopsis.toLowerCase().split(" ");
            int kataSama = 0;
            for (String kata1 : kataThis) {
                for (String kata2 : kataOther) {
                    if (kata1.equals(kata2)) {
                        kataSama++;
                    }
                }
            }
            double similarityRatio = (double) kataSama / Math.max(kataThis.length, kataOther.length);
            kesamaan += similarityRatio;
        }

        return (kesamaan / totalAtribut) * 100;
    }

    public Buku copy() {
        return new Buku(judul, penulis, kategori, sinopsis);
    }

    // Method hitungRoyalti dengan 1 parameter (default 10%)
    public double hitungRoyalti(double hargaBuku) {
        return 0.1 * hargaBuku;
    }

    // Method hitungRoyalti dengan 2 parameter
    public double hitungRoyalti(double hargaBuku, double persentase) {
        double persenDesimal = persentase / 100.0;
        return persenDesimal * hargaBuku;
    }
}

class Kategori {
    private String nama;
    private Buku firstBuku;

    public Kategori(String nama) {
        this.nama = nama;
        this.firstBuku = null;
    }

    public String getNama() {
        return nama;
    }

    public void tambahBuku(Buku buku) {
        if (firstBuku == null) {
            firstBuku = buku;
        } else {
            Buku temp = firstBuku;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(buku);
        }
    }

    public void tampilkanBuku() {
        System.out.println("Kategori: " + nama);
        System.out.println("===========================");
        Buku temp = firstBuku;
        while (temp != null) {
            temp.tampilkanInfo();
            temp = temp.getNext();
        }
    }
}

class Perpustakaan {
    private Kategori[] daftarKategori;
    private int jumlahKategori;

    public Perpustakaan() {
        daftarKategori = new Kategori[10];
        jumlahKategori = 0;
    }

    public void tambahKategori(String namaKategori) {
        if (jumlahKategori < daftarKategori.length) {
            daftarKategori[jumlahKategori] = new Kategori(namaKategori);
            jumlahKategori++;
        }
    }

    public void tambahBukuKeKategori(String namaKategori, Buku buku) {
        for (int i = 0; i < jumlahKategori; i++) {
            if (daftarKategori[i].getNama().equals(namaKategori)) {
                daftarKategori[i].tambahBuku(buku);
                return;
            }
        }
        System.out.println("Kategori " + namaKategori + " tidak ditemukan!");
    }

    public void tampilkanSemuaBuku() {
        for (int i = 0; i < jumlahKategori; i++) {
            daftarKategori[i].tampilkanBuku();
        }
    }
}

public class PraktikumPemlanAwal3 {
    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();

        String[] kategoriBuku = {"Teknologi", "Filsafat", "Sejarah", "Agama", "Psikologi", "Politik", "Fiksi"};

        for (String kategori : kategoriBuku) {
            perpustakaan.tambahKategori(kategori);
        }

        Buku[] daftarBuku = {
            new Buku("Kecerdasan Buatan", "John McCarthy", "Teknologi", "Teknologi AI berkembang pesat dalam era modern ini."),
            new Buku("Data Science", "Jake VanderPlas", "Teknologi", "Analisis data menjadi elemen penting dalam dunia digital."),
            new Buku("Pemrograman Java", "James Gosling", "Teknologi", "Java adalah bahasa pemrograman yang sangat populer."),
            new Buku("Jaringan Komputer", "Andrew S. Tanenbaum", "Teknologi", "Jaringan komputer menjadi tulang punggung komunikasi modern."),
            new Buku("komputer bingung", "hazel gantenk", "Teknologi", "Jaringan komputer menjadi bingung aseli dah."),
            //teknologi
            new Buku("Ada dan Waktu", "Martin Heidegger", "Filsafat", "Eksistensialisme dalam kehidupan modern."),
            new Buku("Republik", "Plato", "Filsafat", "Filosofi tentang keadilan dalam suatu negara."),
            new Buku("al asikin azzah", "dilan", "Filsafat", "gatau males pengen beli truk."),
            new Buku("apa itu apa?", "Piraun", "Filsafat", "makan buah khuldi itu enak bat aseli."),
            new Buku("apalagi ya?", "jospar", "Filsafat", "bingung terhadap filsafat itu apa."),
            //filsafat
            new Buku("Sejarah Dunia", "E. H. Gombrich", "Sejarah", "Ringkasan sejarah dunia dalam berbagai era."),
            new Buku("Sapiens", "Yuval Noah Harari", "Sejarah", "Sejarah umat manusia dari zaman purba hingga kini."),
            new Buku("Sapri pemilik piramid", "sapri", "Sejarah", "memiliki piramid tapi ga bilang bilang."),
            new Buku("Sapra pemilik hatiku", "sapra", "Sejarah", "Sejarah sang sapra ternyata punya hati."),
            new Buku("Sapru suka typo", "sapru", "Sejarah", "Sejarah umat manusia ganteng itu sebenernya suka typo."),
            //sejarah
            new Buku("Kitab Tauhid", "Muhammad bin Abdul Wahhab", "Agama", "Penjelasan tentang konsep tauhid dalam Islam."),
            new Buku("Riyadhus Shalihin", "Abu Zakaria an-Nawawi", "Agama", "Kumpulan hadits tentang moral dan etika Islam."),
            new Buku("Tafsir Ibnu Katsir", "Ibnu Katsir", "Agama", "Penafsiran Al-Quran yang mendalam."),
            new Buku("Al-Umm", "Imam Syafi'i", "Agama", "membahas berbagai aspek hukum Islam (fiqh), mulai dari ibadah, muamalah, hingga sistem peradilan Islam."),
            new Buku("Bekal Muslim Sehari-hari", "Ustadz Khalid Basalamah", "Agama", "panduan praktis tentang ibadah harian seorang Muslim."),
            //agama
            new Buku("Psikologi Sosial", "David Myers", "Psikologi", "Bagaimana interaksi sosial mempengaruhi perilaku individu."),
            new Buku("Mindset", "Carol S. Dweck", "Psikologi", "Pentingnya pola pikir dalam meraih kesuksesan."),
            new Buku("otaku", "shin kam pri", "Psikologi", "Pentingnya dirimu dalam meraih kesuksesan."),
            new Buku("ikigay", "saskeh bin narto", "Psikologi", "Tentang tujuan dan cita-cita hidup."),
            new Buku("Eishen hower matriks", "eishen hower", "Psikologi", "skala prioritas dalam menjalankan suatu kewajiban"),
            //psikologi
            new Buku("The Prince", "Niccolò Machiavelli", "Politik", "Strategi kepemimpinan dalam dunia politik."),
            new Buku("Das Kapital", "Karl Marx", "Politik", "Analisis ekonomi dan sosial dalam sistem kapitalisme."),
            new Buku("Kamu Yang Gelap Bukan Indonesia", "Luhut pandjaitan", "Politik", "Alasan-alasan luhut atas seluruh kebijakan yang ada."),
            new Buku("tikus tikus berdasi", "zelgans", "Politik", "Kritikan terhadap pejabat-pejabat korup diindonesia."),
            new Buku("pilih banteng ya warna merah", "roman kamaru", "Politik", "Analisis partai politik di indonesia."),
            //politik
            new Buku("Harry Potter", "J.K. Rowling", "Fiksi", "Petualangan magis di dunia sihir yang luar biasa."),
            new Buku("Sikancil songong sama buaya", "Tok Dalang", "Fiksi", "Mengisahkan cerita hubungan kancil dan buaya yang tidak baik-baik saja."),
            new Buku("Kembara-Kembar Nackal", "Ros", "Fiksi", "Saudara kembar yang mempunyai tekad untuk menjadi superhero untuk menuntaskan kejahatan."),
            new Buku("Parable", "Brian Khrisna", "Fiksi", "Kisah seseorang yang mempunyai masalah hidup yang berliku."),
            new Buku("Sisi Tergelap Surga", "Brian Khrisna", "Fiksi", "Mengisahkan tentang sisi gelap kota metropolitan ternama yaitu jakarta."),
            //fiksi
        };

        for (Buku buku : daftarBuku) {
            perpustakaan.tambahBukuKeKategori(buku.getKategori(), buku);
        }

        // Menampilkan semua buku
        perpustakaan.tampilkanSemuaBuku();

        // Menampilkan perbandingan buku
        System.out.println("\n=====================================");
        System.out.println("    HASIL PERBANDINGAN BUKU");
        System.out.println("=====================================\n");

        // 1. Perbandingan buku dengan penulis yang sama
        Buku buku1 = daftarBuku[33];  // Parable
        Buku buku2 = daftarBuku[34];  // Sisi Tergelap Surga
        System.out.println("1. Perbandingan Buku dengan Penulis Yang Sama:");
        System.out.println("   Buku 1: " + buku1.getJudul() + " oleh " + buku1.getPenulis());
        System.out.println("   Buku 2: " + buku2.getJudul() + " oleh " + buku2.getPenulis());
        System.out.println("   Persentase Kesamaan: " + buku1.cekKesamaan(buku2) + "%");
        System.out.println("-------------------------------------\n");

        // 2. Perbandingan buku teknologi
        Buku buku3 = daftarBuku[0];
        Buku buku4 = daftarBuku[1];
        System.out.println("2. Perbandingan Buku dalam Kategori Teknologi:");
        System.out.println("   Buku 1: " + buku3.getJudul() + " (" + buku3.getKategori() + ")");
        System.out.println("   Buku 2: " + buku4.getJudul() + " (" + buku4.getKategori() + ")");
        System.out.println("   Persentase Kesamaan: " + buku3.cekKesamaan(buku4) + "%");
        System.out.println("-------------------------------------\n");

        // 3. Perbandingan buku beda kategori
        Buku buku5 = daftarBuku[0];
        Buku buku6 = daftarBuku[5];
        System.out.println("3. Perbandingan Buku Beda Kategori:");
        System.out.println("   Buku 1: " + buku5.getJudul() + " (" + buku5.getKategori() + ")");
        System.out.println("   Buku 2: " + buku6.getJudul() + " (" + buku6.getKategori() + ")");
        System.out.println("   Persentase Kesamaan: " + buku5.cekKesamaan(buku6) + "%");
        System.out.println("-------------------------------------\n");

        // Menampilkan perhitungan royalti
        System.out.println("\n=====================================");
        System.out.println("    PERHITUNGAN ROYALTI BUKU");
        System.out.println("=====================================\n");

        // Mengambil buku pertama sebagai contoh
        Buku bukuContoh = daftarBuku[0];
        double hargaBuku = 150000.0; // Harga buku Rp 150.000

        // Menghitung royalti dengan 1 parameter (default 10%)
        System.out.println("1. Royalti untuk buku \"" + bukuContoh.getJudul() + "\"");
        System.out.println("   Harga buku: Rp " + String.format("%,.2f", hargaBuku));
        System.out.println("   Royalti (10%): Rp " + String.format("%,.2f", bukuContoh.hitungRoyalti(hargaBuku)));
        System.out.println("-------------------------------------\n");

        // Menghitung royalti dengan 2 parameter (custom percentage)
        double persentaseKustom = 15.0;
        System.out.println("2. Royalti kustom untuk buku \"" + bukuContoh.getJudul() + "\"");
        System.out.println("   Harga buku: Rp " + String.format("%,.2f", hargaBuku));
        System.out.println("   Royalti (" + persentaseKustom + "%): Rp " + 
                          String.format("%,.2f", bukuContoh.hitungRoyalti(hargaBuku, persentaseKustom)));
        System.out.println("-------------------------------------");
    }
}