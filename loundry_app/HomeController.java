/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loundry_app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Toriq
 */
public class HomeController implements Initializable {
    Connection con = null;
    OraclePreparedStatement pst=null;
    OracleResultSet rs;  
    Statement stm =null; 
    @FXML
    private ProgressIndicator cust_prog;
    @FXML
    private JFXTextField nama_tf1;
    
    @FXML
    private JFXTextField alamat_tf1;

    @FXML
    private JFXTextField notlp_tf1;
    @FXML
    private JFXTextField id_update1;
    @FXML
    private JFXTextField input_tf1;

    @FXML
    private JFXHamburger humberger;
    @FXML
    private VBox box;
     @FXML
    private AnchorPane view_pane;
     @FXML
    private AnchorPane home_pane; 
    @FXML
    private JFXButton stock_btn1;
    @FXML
    private JFXButton home_btn1;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label usersession;
    @FXML
    private ImageView exit_btn;
    @FXML
    private TableView<Customerdata> tableCustomer;

    @FXML
    private TableColumn<Customerdata, String> id;

    @FXML
    private TableColumn<Customerdata, String> nama;

    @FXML
    private TableColumn<Customerdata, String> alamat;

    @FXML
    private TableColumn<Customerdata, String> notelfon;

    @FXML
    private TextField nama_tf;

    @FXML
    private TextField alamat_tf;

    @FXML
    private TextField notlp_tf;
    @FXML
    private TextField input_tf;
    @FXML
    private ImageView rfsh_btn;
     @FXML
    private ImageView tmbh_btn;
     ObservableList<String> combodata = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboisi;
    
    @FXML
    private TextField id_update;
    @FXML
    private JFXButton view_btn1;
    @FXML
    private JFXButton paket_btn;
    
    @FXML
    private JFXButton transaksi_btn;
    @FXML
    private Label total_cust;
      @FXML
    private TextField coba;
    
    @FXML
    private Button tmbh_btn11;
    String kolom ;
    String nama_kolom;

    private double x=0 ,y=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cust_prog.setProgress(-5.50f);
        loadCombo();
        //tampil ke table
          id.setCellValueFactory(new PropertyValueFactory<Customerdata, String>("id"));
          alamat.setCellValueFactory(new PropertyValueFactory<Customerdata, String>("Nama"));
          notelfon.setCellValueFactory(new PropertyValueFactory<Customerdata, String>("Alamat"));
          nama.setCellValueFactory(new PropertyValueFactory<Customerdata, String>("Tlp"));
        try {
            getCustomer();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //text pop up button fungsi
        Tooltip btnView = new Tooltip();
        btnView.setText("Melihat dan Menambah Customer");
        view_btn1.setTooltip(btnView);
        
        Tooltip tfid = new Tooltip();
        tfid.setText("isi sesuai dengan id Customer ");
        id_update1.setTooltip(tfid);
        
        Tooltip tfrubah = new Tooltip();
        tfrubah.setText("berguna untuk inputan data baru");
        input_tf1.setTooltip(tfrubah);
        
        Tooltip plhkolom = new Tooltip();
        plhkolom.setText("pilih kolom yang mau di edit");
        comboisi.setTooltip(plhkolom);
        
        Tooltip stokbtn = new Tooltip();
        stokbtn.setText("Lihat isi Stock");
        stock_btn1.setTooltip(stokbtn);
        
         Tooltip paketbtn = new Tooltip();
        paketbtn.setText("Daftar Paket Loundry");
        paket_btn.setTooltip(paketbtn);
        
         Tooltip transaksitbtn = new Tooltip();
        transaksitbtn.setText("Daftar Transaksi Loundry");
        transaksi_btn.setTooltip(transaksitbtn);
        
        // SlidePane Menu
        String user = UserSession.getUsername();
        usersession.setText(user);
        drawer.setSidePane(box);
         HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition (humberger);
        burgerTask2.setRate(-1);
        
        humberger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)-> {
            burgerTask2.setRate(burgerTask2.getRate() *-1);
            burgerTask2.play();
            if(drawer.isShown()){
                drawer.close();
            }else{
                drawer.open();
            };
            
        });
    }  
    
      @FXML
    void drag(MouseEvent event) {
        Node node = (Node)  event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void press(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    @FXML
    void close(MouseEvent event) {
         
        System.exit(0);
    }
    @FXML
    void logout(MouseEvent event) throws IOException {
              AnchorPane root10 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                 Scene home_scane9 = new Scene (root10);
                 Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                 app_stage.setScene(home_scane9);
                 app_stage.show();
    }
    @FXML
    void handlebutton(ActionEvent event){
        if(event.getSource()== home_btn1){
           home_pane.setVisible(true);
           view_pane.setVisible(false);
           rfsh_btn.setVisible(false);
           tmbh_btn.setVisible(false);
                   

        }
        if(event.getSource()== view_btn1){
                
                view_pane.setVisible(true);
                rfsh_btn.setVisible(true);
                tmbh_btn.setVisible(true);
                home_pane.setVisible(false);
                
            }
        
    }
   @FXML
    void handleEdit(ActionEvent event){
        if(event.getSource()== tmbh_btn11){
            comboisi.setVisible(false);
            id_update1.setVisible(false);
            input_tf.setVisible(false);
        }
    }
     @FXML
    void tambahbtn(MouseEvent event) throws ClassNotFoundException {
           
                try {
            con = Koneksi.KoneksiBD();
            String sql = "insert into Customer (id_cust,nama_cust,alamat_cust,no_tlp) values (id_customer_sequence.nextval,?,?,?)";
            pst = (OraclePreparedStatement) con.prepareStatement(sql);
          
          
            pst.setString(1, nama_tf1.getText());
            pst.setString(2, alamat_tf1.getText());
            pst.setString(3, notlp_tf1.getText());
            rs = (OracleResultSet) pst.executeQuery();
            if(rs.next()){
                  Image img = new Image("/icon/tick2.png");
                  ImageView iv = new ImageView();
                  iv.setImage(img);
      
                  Notifications notif = Notifications.create().title("Success").text("Saved data").hideAfter(Duration.seconds(5))
                 .position(Pos.BASELINE_RIGHT).onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              System.out.print("Cliked");
            }
        });
         notif.darkStyle();
         notif.graphic(iv);
         notif.show();
               nama_tf1.setText("");
               alamat_tf1.setText("");
               notlp_tf1.setText("");
            }else{
               Image img = new Image("/icon/tick3.png");
                  ImageView iv = new ImageView();
                  iv.setImage(img);
      
         Notifications notif = Notifications.create().title("Error").text(" No Saved data").hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              System.out.print("Cliked");
            }
        });
         notif.darkStyle();
         notif.graphic(iv);
         notif.show();
            }
        } catch (SQLException ex) {
          //    JOptionPane.showMessageDialog(null,""+ex);
        }
    
           
    }
    @FXML
    void refesh(MouseEvent event) throws ClassNotFoundException, SQLException {
           getCustomer();
           total();
    }
    
     public ObservableList<Customerdata> getCustomer() throws ClassNotFoundException, SQLException{
        int y=1;
        ObservableList<Customerdata> cust = FXCollections.observableArrayList();
      
     try{
         con = Koneksi.KoneksiBD();
     
         String sql = "select * from Customer";
          pst = (OraclePreparedStatement) con.prepareStatement(sql);
         rs = (OracleResultSet) pst.executeQuery();
        
      
         while (rs.next()){
             
            cust.addAll(new Customerdata(
            rs.getString("id_cust"),
             rs.getString("nama_cust"),
             rs.getString("alamat_cust"),
             rs.getString("no_tlp")
           
            ));

          }
         

        tableCustomer.setItems(cust);
       
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,""+e);
         }
        return cust;
    }
     @FXML
    void edit_btn(MouseEvent event) throws ClassNotFoundException, SQLException {
        tableCustomer.setEditable(true);
     
        try{
        con = Koneksi.KoneksiBD();
        
        
        String sql = "update Customer set "+nama_kolom+" =' "+input_tf1.getText()+"' where id_cust ='"+id_update1.getText()+"'";
        pst = (OraclePreparedStatement) con.prepareStatement(sql);
        pst.execute();
        rs = (OracleResultSet) pst.executeQuery();
       if(rs.next()){
           Image img = new Image("/icon/tick2.png");
                  ImageView iv = new ImageView();
                  iv.setImage(img);
      
                  Notifications notif = Notifications.create().title("Success").text("Update data").hideAfter(Duration.seconds(5))
                 .position(Pos.BASELINE_RIGHT).onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
            }
        });
         notif.darkStyle();
         notif.graphic(iv);
         notif.show();
         id_update1.setText("");
         input_tf1.setText("");   
       }else{
           Image imge = new Image("/icon/error.png");
                  ImageView ive = new ImageView();
                  ive.setImage(imge);
      
                  Notifications notif = Notifications.create().title("Error").text("gagal Update").hideAfter(Duration.seconds(5))
                 .position(Pos.BASELINE_RIGHT).onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
            }
        });
         notif.darkStyle();
         notif.graphic(ive);
         notif.show();
       }
       
        
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex);
           
        }
        }
     @FXML
    void combo_update(ActionEvent event) {
        kolom = comboisi.getValue();
        if(kolom == "Nama"){
        nama_kolom ="nama_cust";
        }
        else if(kolom =="Alamat"){
            nama_kolom = "alamat_cust";
        }else if(kolom == "No Telfon"){
             nama_kolom = "no_tlp";
        }
        System.out.print(nama_kolom);
    }
   void loadCombo(){
       combodata.removeAll(combodata);
       String nama = "Nama";
       String alamat = "Alamat";
       String notlp = "No Telfon";
       combodata.addAll(nama,alamat,notlp);
       comboisi.getItems().addAll(combodata);
   }
   void total() throws ClassNotFoundException, SQLException{
        con = Koneksi.KoneksiBD();
     
        try{
        String sql = "Select count(*) jml from customer";
          pst = (OraclePreparedStatement) con.prepareStatement(sql);
         rs = (OracleResultSet) pst.executeQuery();
        
        
         while (rs.next()){
             String ttl = rs.getString("jml");
                coba.setText(ttl);
             
          }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
   }
}
