/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundryinaja;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author kiki
 */
public class Pemesanan {
    double hitungHarga(double harga, int jumlah){
        double totalHarga = jumlah* harga;
        return totalHarga;
    }
    String kalkulasiSelesai(String tglAntar,int lama){
        String tglSelesai = "19-02-02";
        int tmp = Integer.parseInt(tglAntar.substring(6));
        int bulan = Integer.parseInt(tglAntar.substring(3,5));
        String hasil = tglAntar.substring(0,6);
        switch(bulan){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
                tmp += lama;
                if(tmp <= 31){
                    if(tmp <10){
                        hasil = hasil+"0"+tmp;
                    }else{
                        hasil = hasil+tmp;
                    }
                }else{//"19-04-09
                    hasil = tglAntar.substring(0,2);
                    int tmpbulan = bulan + 1;
                    tmp -= 31;
                    if(tmpbulan <10){
                        if(tmp<10){
                            hasil = hasil+"-0"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-0"+tmpbulan+"-"+tmp;
                        }
                    }else{
                        if(tmp<10){
                            hasil = hasil+"-"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-"+tmpbulan+"-"+tmp;
                        }
                    }  
                }
                tglSelesai = hasil;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                tmp += lama;
                if(tmp <= 30){
                    if(tmp <10){
                        hasil = hasil+"0"+tmp;
                    }else{
                        hasil = hasil+tmp;
                    }
                }else{
                    hasil = tglAntar.substring(0,2);
                    int tmpbulan = bulan +1;
                    tmp -= 30;
                    if(tmpbulan <10){
                        if(tmp<10){
                            hasil = hasil+"-0"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-0"+tmpbulan+"-"+tmp;
                        }
                    }else{
                        if(tmp<10){
                            hasil = hasil+"-"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-"+tmpbulan+"-"+tmp;
                        }
                    }  
                }
                tglSelesai = hasil;
                break;
            case 2:
                tmp += lama;
                if(tmp <= 28){
                    if(tmp <10){
                        hasil = hasil+"0"+tmp;
                    }else{
                        hasil = hasil+tmp;
                    }
                }else{
                    hasil = tglAntar.substring(0,2);
                    int tmpbulan = bulan +1;
                    tmp -= 28;
                    if(tmpbulan <10){
                        if(tmp<10){
                            hasil = hasil+"-0"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-0"+tmpbulan+"-"+tmp;
                        }
                    }else{
                        if(tmp<10){
                            hasil = hasil+"-"+tmpbulan+"-0"+tmp;
                        }else{
                            hasil = hasil+"-"+tmpbulan+"-"+tmp;
                        }
                    }  
                }
                tglSelesai = hasil;
                break;
            case 12:
                tmp += lama;
                if(tmp <= 31){
                    if(tmp <10){
                        hasil = hasil+"0"+tmp;
                    }else{
                        hasil = hasil+tmp;
                    }
                }else{
                    int tahun = Integer.parseInt(tglAntar.substring(0,2))+1;
                    hasil = ""+tahun;
                    if(hasil.length()>2){
                        hasil = hasil.substring(1);
                    }
                    int tmpbulan = 1;
                    
                    tmp -= 31;
                    if(tmp<10){
                        hasil = hasil+"-0"+tmpbulan+"-"+"0"+tmp;
                    }else{
                        hasil = hasil+"-0"+tmpbulan+"-"+tmp;
                    }
                }
                tglSelesai = hasil;
                break;
        }
        return tglSelesai;
    }
    
    String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
	Date date = new Date();
	return dateFormat.format(date);
    }
    double harga(String tipe, int lama){
        double harga = 0;
        switch(tipe){
            case "kg":
                if(lama < 2){
                    harga = 6000;
                }else{
                    harga = 5000;
                }
                break;
            case "item":
                harga = 10000;
                break;
        }
        return harga;
    }
    
    public static void main(String[] args) {
        Pemesanan pesan = new Pemesanan();
        System.out.println(pesan.kalkulasiSelesai("19-05-30", 4));
        
    }
}
