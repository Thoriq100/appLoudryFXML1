/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loundry_app;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Toriq
 */
public class Customerdata {
    private final StringProperty Nama_Lengkap ;
    private final StringProperty Alamat;
    private final StringProperty datatel;
     private final StringProperty id;
    
    public Customerdata(String id,String datatel,String nama,String alamat) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.id = new SimpleStringProperty(id); 
          this.datatel = new SimpleStringProperty(datatel);
        this.Nama_Lengkap = new SimpleStringProperty(nama);
         this.Alamat = new SimpleStringProperty(alamat);
       
    }

   
public String getId(){
    return id.get();
}
    
public void setId(String idd){
     id.set(idd);
}
 public String getTlp(){
        return datatel.get();
    }
    public void setTlp(String notlp11){
       datatel.set(notlp11);
    }
    public String getNama(){
    return Nama_Lengkap.get();
}
   
    public void setNama(String Nama){
        Nama_Lengkap.set(Nama);
    }
    public String getAlamat(){
        return Alamat.get();
    }
    public void setAlamat(String alamat){
        Alamat.set(alamat);
    }
  
}
