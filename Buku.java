package UAS_PeminjamanBuku;

import java.util.Scanner;

//class anak
public class Buku extends Peminjaman{
  Scanner input = new Scanner(System.in);

  String kodeBuku;
  String judulBuku;
  String namaPenerbit;
  Integer tahunTerbit;

  //constructor
  public Buku(String namaPeminjam, Integer idPeminjam, String judulBuku, String kodeBuku){
      this.judulBuku=judulBuku;
      this.kodeBuku=kodeBuku;
  }
  //constructor
  public Buku(String namaPenerbit, Integer tahunTerbit){
      this.namaPenerbit=namaPenerbit;
      this.tahunTerbit=tahunTerbit;
  }
  //constructor
  public Buku(){

  }
  @Override
  public void JudulBuku(){
      System.out.println("Judul Buku : ");
      judulBuku=input.next();

  }
  @Override
  public void KodeBuku(){
    System.out.println("Kode Buku : ");
    kodeBuku=input.next();

  }
  @Override
  public void NamaPenerbit(){
    System.out.println("Nama Penerbit : ");
    namaPenerbit=input.next();
  }
  @Override
  public void TahunTerbit(){
    System.out.println("Tahun Terbit : ");
    tahunTerbit=input.nextInt();
  }
  @Override
  public void sanksi(){
    
  }
  @Override
  public void Pengembalian(){
      
  }
}

