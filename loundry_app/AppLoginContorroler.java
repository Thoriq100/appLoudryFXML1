/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loundry_app;


import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;



/**
 *
 * @author Toriq
 */
public class AppLoginContorroler implements Initializable {
    
     @FXML
    private StackPane stackPane;
    @FXML
    private Button button;
    @FXML
    private Label failed;
    @FXML
    private Label label;
      @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
    @FXML
    private JFXTextField username1;

    @FXML
    private JFXTextField password1;
    Connection con = null;
    OraclePreparedStatement pst=null;
    OracleResultSet rs;   
    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private ProgressIndicator progresindi;
       
     
     int progress = 0;
    @FXML
    private void Sumbit(ActionEvent event) throws IOException {
      String user =null,pass=null;
        try {
            con = Koneksi.KoneksiBD();
        } catch (ClassNotFoundException ex) {
           
        }
    
        try {
           String sql = "select * from Admin where username=? and password=? ";
            pst = (OraclePreparedStatement) con.prepareStatement(sql);
           pst.setString(1,username1.getText());
            pst.setString (2,password1.getText());
           rs =(OracleResultSet) pst.executeQuery();
          
           while (rs.next()){
              
               user = rs.getString("username");
               pass = rs.getString("password");
                UserSession.setUsername(user);
                UserSession.setPass(pass);
                
                    if(user.equals("admin")){
                       
                         AnchorPane root4 = FXMLLoader.load(getClass().getResource("Home.fxml"));
                 Scene home_scane = new Scene (root4);
                 Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                 app_stage.setScene(home_scane);
                 app_stage.show();
                    }else if(user.equals("manager")){
                        AnchorPane root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
                 Scene home_scane1 = new Scene (root2);
                 Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                 app_stage.setScene(home_scane1);
                 app_stage.show();
                    }
                   
           }
        
        
            if(rs.next()){
                
   
            }else{
                failed.setText("Sorry,try again check your username and password");
//              
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(user);
//                alert.setContentText("Login gagal");
//                alert.show();
                 
              
            }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,""+ex);
        }
    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
   

}
