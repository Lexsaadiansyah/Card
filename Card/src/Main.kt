class StudentCard(
    val nim: String,            // constructor: properti read-only
    nama: String,               // constructor: parameter biasa, divalidasi di init
    val prodi: String
) {
    companion object {
        const val MAKS_SKS = 24
    }

    // Encapsulation: nama bisa diubah, tapi hanya lewat setter yang divalidasi
    var nama: String = nama
        set(value) {
            require(value.isNotBlank()) { "Nama tidak boleh kosong" }
            field = value.trim()
        }

    // Encapsulation: totalSks bisa dibaca dari luar, tapi hanya class ini yang bisa mengubahnya
    var totalSks: Int = 0
        private set

    // Computed property: dihitung otomatis dari totalSks, tidak disimpan
    val status: String
        get() = if (totalSks > 0) "Aktif" else "Baru"

    val sisaSks: Int
        get() = MAKS_SKS - totalSks

    // Validasi saat objek dibuat
    init {
        require(nim.length >= 7) { "NIM tidak valid" }
        require(nama.isNotBlank()) { "Nama tidak boleh kosong" }
        require(prodi.isNotBlank()) { "Prodi tidak boleh kosong" }
        this.nama = nama // memicu setter agar nama ikut di-trim
    }

    // Method: validasi jumlah SKS sebelum mengubah state
    fun ambilSks(jumlah: Int): Boolean {
        if (jumlah <= 0 || totalSks + jumlah > MAKS_SKS) return false
        totalSks += jumlah
        return true
    }

    fun ringkasan() = "$nim · $nama · $prodi · $totalSks SKS"

    // Method untuk menampilkan output: NIM, nama, prodi, total SKS
    fun tampilkan() {
        println("=== Kartu Mahasiswa ===")
        println("NIM        : $nim")
        println("Nama       : $nama")
        println("Prodi      : $prodi")
        println("Total SKS  : $totalSks")
        println("Status     : $status")
    }
}

fun main() {
    val mhs = StudentCard("2309010015", "Alexander Christie", "Teknologi Informasi")
    mhs.ambilSks(10)
    mhs.ambilSks(10)
    mhs.ambilSks(2)
    mhs.tampilkan()
    println()
    println(mhs.ringkasan())
}