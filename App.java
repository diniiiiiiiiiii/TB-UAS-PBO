package UAS_PeminjamanBuku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
   
	//CRUD
	static Connection conn;
    
    public static void main(String[] args) throws Exception { 
		
        Scanner input = new Scanner (System.in);
		String pil;
		boolean lanjut = true;
						
		String url = "jdbc:mysql://localhost:3306/perpustakaan";

		//exception
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			Date tgl = new Date();
			String tanggal = String.format("%tc", tgl);
			System.out.println(tanggal);
			
			//perulangan
			while (lanjut) {
				System.out.println("\n---------------------------------------");
				System.out.println("      Database Perpustakaan Steva");
				System.out.println("-----------------------------------------");
				System.out.println("1. View Data Peminjam");
				System.out.println("2. Insert Data Peminjam");
				System.out.println("3. Update Data Peminjam");
				System.out.println("4. Delete Data Peminjam");
				System.out.println("5. Search Data Peminjam");
				
				System.out.print("\nMasukkan Pilihan Anda (1-5): ");
				pil = input.next();
				
				switch (pil) {
				case "1":
					view();
					break;
				case "2":
					insert();
					break;
				case "3":
					update();
					break;
				case "4":
					delete();
					break;
				case "5":
					search();
					break;
				default:
					System.err.println("\nMenu tidak tersedia \nSilahkan pilih [1-5]");
				}
				System.out.print("Apakah Anda ingin melanjutkan [y/n]? ");
				pil = input.next();
			
				lanjut = pil.equals("y");
			}
			System.out.println("\nSampai jumpa Kembali!!!");
		}

		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil terkoneksi");
		}
		finally{
			input.close();
		}		
		}
		
	private static void view() throws SQLException {
		String text1 = "\n===Daftar Seluruh Data Peminjam Buku===";
		System.out.println(text1.toUpperCase());
		
		//mengambil data dari database perpustakaan 
		String sql ="SELECT * FROM peminjam";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){

            System.out.print("\nNama Peminjam\t  : ");
            System.out.print(result.getString("namaPeminjam"));
            System.out.print("\nNo. ID Peminjam\t  : ");
            System.out.print(result.getInt("idPeminjam"));
			System.out.print("\nKode Buku\t  : ");
            System.out.print(result.getString("kodeBuku"));
            System.out.print("\nJudul Buku\t  : ");
			System.out.print(result.getString("judulBuku"));
            System.out.print("\nNama Penerbit\t  : ");
			System.out.print(result.getString("namaPenerbit"));
            System.out.print("\nTahun Terbit\t  : ");
			System.out.print(result.getInt("tahunTerbit"));
            System.out.print("\nPengembalian\t  : ");
			System.out.print(result.getInt("pengembalian"));
			System.out.print("\nSanksi\t\t  : ");
			System.out.print(result.getInt("Sanksi"));
			System.out.println("\n\n");
           
		}
	}
	private static void insert() throws SQLException{
		String text2 = "\n===Tambah Data Peminjam===";
		System.out.println(text2.toUpperCase());
		
		Scanner input = new Scanner(System.in);		
		try {
		Buku pinjam = new Buku();
		
			pinjam.NamaPeminjam();
			pinjam.IdPeminjam(); 
			pinjam.KodeBuku();
			pinjam.JudulBuku();
			pinjam.NamaPenerbit();
			pinjam.TahunTerbit();

		Peminjaman pinjam2=new Peminjaman();
			pinjam2.Pengembalian();
			pinjam2.sanksi();
			
		String namaPeminjam=pinjam.namaPeminjam;
		Integer idPeminjam = pinjam.idPeminjam;
		String kodeBuku = pinjam.kodeBuku;
		String judulBuku = pinjam.judulBuku;
		String namaPenerbit = pinjam.namaPenerbit;
		Integer tahunTerbit = pinjam.tahunTerbit;
		Integer pengembalian = pinjam2.pengembalian;
		Integer.toString(pinjam2.Sanksi);
		
		String sql = "INSERT INTO peminjam (namaPeminjam, idPeminjam, kodeBuku, judulBuku, namaPenerbit, tahunTerbit, pengembalian, Sanksi) VALUES ('"+namaPeminjam+"', '"+idPeminjam+"', '"+kodeBuku+"', '"+judulBuku+"', '"+namaPenerbit+"', '"+tahunTerbit+"', '"+pengembalian+"', '"+pinjam2.Sanksi+"')";
	
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil menginput data");

	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan saat menginput data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan benar!");
	   	} finally{
			   input.close();
		   }
	} 
	private static void update() throws SQLException{
		String text3 = "\n===Ubah Data Peminjam===";
		System.out.println(text3.toUpperCase());
		
		Scanner input = new Scanner (System.in);
		
		try {
            view();
            System.out.print("\nMasukkan No. ID Peminjam yang akan di update : ");
            Integer idPeminjam = Integer.parseInt(input.nextLine());
            
            String sql = "SELECT * FROM peminjam WHERE idPeminjam = " +idPeminjam;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
				Buku pinjam = new Buku();
		
				pinjam.NamaPeminjam();
				pinjam.IdPeminjam(); 
				pinjam.KodeBuku();
				pinjam.JudulBuku();
				pinjam.NamaPenerbit();
				pinjam.TahunTerbit();
				
				Peminjaman pinjam2=new Peminjaman();
				pinjam2.Pengembalian();
				pinjam2.sanksi();
				
				String namaPeminjam=pinjam.namaPeminjam;
				String kodeBuku = pinjam.kodeBuku;
				String judulBuku = pinjam.judulBuku;
				String namaPenerbit = pinjam.namaPenerbit;
				Integer tahunTerbit = pinjam.tahunTerbit;
				Integer pengembalian = pinjam2.pengembalian;
				Integer Sanksi = pinjam2.Sanksi;
				
                sql = "UPDATE peminjam SET namaPeminjam='"+namaPeminjam+"',idPeminjam= '"+idPeminjam+"',kodeBuku='"+kodeBuku+"', judulBuku='"+judulBuku+"', namaPenerbit='"+namaPenerbit+"', tahunTerbit='"+tahunTerbit+"', pengembalian='"+pengembalian+"', Sanksi='"+Sanksi+"' WHERE idPeminjam='"+idPeminjam+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data peminjam (No. ID Peminjam "+idPeminjam+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	finally{
			input.close();
	}
		}
	private static void delete() {
		String text4 = "\n===Hapus Data Peminjam===";
		System.out.println(text4.toUpperCase());
		
		Scanner input = new Scanner (System.in);
		try{
	        view();
	        System.out.print("Masukkan No.ID Peminjam yang akan Anda Hapus : ");
	        Integer idPeminjam= Integer.parseInt(input.nextLine());
	        
	        String sql = "DELETE FROM peminjam WHERE idPeminjam = "+ idPeminjam;
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data peminjam (No. ID Peminjam "+idPeminjam+")");
	        }
	   }catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data Peminjam");
	        }finally{
				input.close();
			}
		}
	private static void search () throws SQLException {
		String text5 = "\n===Cari Data Peminjam===";
		System.out.println(text5.toUpperCase());
		
		Scanner input = new Scanner (System.in);
		try{
				
		System.out.print("Masukkan ID Peminjam : ");
        
		String i = input.nextLine();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM peminjam WHERE idPeminjam LIKE '%"+i+"%'";
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
			System.out.print("\nNama Peminjam\t  : ");
            System.out.print(result.getString("namaPeminjam"));
            System.out.print("\nNo. ID Peminjam\t  : ");
            System.out.print(result.getInt("idPeminjam"));
			System.out.print("\nKode Buku\t  : ");
            System.out.print(result.getString("kodeBuku"));
            System.out.print("\nJudul Buku\t  : ");
			System.out.print(result.getString("judulBuku"));
            System.out.print("\nNama Penerbit\t  : ");
			System.out.print(result.getString("namaPenerbit"));
            System.out.print("\nTahun Terbit\t  : ");
			System.out.print(result.getInt("tahunTerbit"));
            System.out.print("\nPengembalian\t  : ");
			System.out.print(result.getInt("pengembalian"));
			System.out.print("\nSanksi\t\t  : ");
			System.out.print(result.getInt("Sanksi"));
			System.out.println("\n\n"); 
        }
	}finally{

		input.close();
	}
	}
}