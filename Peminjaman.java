package UAS_PeminjamanBuku;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collection;
import java.util.ArrayList;

//inheritance 
//class induk
public class Peminjaman implements PerpustakaanSteva{

    Integer telat=0;
    Integer KodeBuku;
    public String namaPeminjam;
    public int idPeminjam;
    public String judulBuku;
    public String tahunTerbit;
    public String namaPenerbit;
    public Integer pengembalian;
    public Integer denda;
    public Integer Sanksi=0;
    public String tanggal;

Scanner input = new Scanner(System.in);

//constructor
public Peminjaman(Integer idPeminjam){

this.idPeminjam=idPeminjam;

}
//constructor
public Peminjaman() {
}

//getter
public Integer getIdPeminjam(){
    return idPeminjam;

}
//setter
public void setIdPeminjam(Integer idPeminjam){
    this.idPeminjam=idPeminjam;
}
    @Override
    public void IdPeminjam(){
    //ArrayList
    Collection<Peminjaman> p = new ArrayList<Peminjaman>();
    System.out.println("ID Peminjam : ");
    idPeminjam = input.nextInt();
    p.add(new Peminjaman(idPeminjam));
    }

    @Override
    public void NamaPeminjam(){
    System.out.println("Nama Peminjam : " );
    namaPeminjam = input.next();
        }
   
    @Override
    public void KodeBuku(){
    }
       
    @Override
    public void JudulBuku(){

    }
    @Override
    public void NamaPenerbit(){

    }
    @Override
    public void TahunTerbit(){

    }
    @Override
    public void Pengembalian(){
        
            boolean kondisi = true;
            //perulangan
            do{
                try{
                    System.out.println("Jumlah Hari Telat Pengembalian (15 hari): ");
                    pengembalian=input.nextInt();
                    
                    //percabangan
                if(pengembalian<0 || pengembalian>15) throw new Exception();
                kondisi=false;
                
                    //exception
                } catch(InputMismatchException e){
                    System.out.println("Inputkan dengan Benar!");
                    input.nextLine();
                } catch(Exception e){
                    System.out.println("done");
                    input.nextLine();
                }finally{
                    System.out.println("done"); 
                }
            }while(kondisi);
        }
           
     @Override
    public void sanksi()  {
        //operasi matematika
        Sanksi = 2000*pengembalian;
          System.out.println(Sanksi);
    }


}