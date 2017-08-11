package library.assistant.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import library.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author cccce
 */
public class MemberAddController implements Initializable {

    DatabaseHandler handler;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new DatabaseHandler();
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void addMember(ActionEvent event) {
        String memberName = name.getText();
        String memberId = id.getText();
        String memberMobile = mobile.getText();
        String memberEmail = email.getText();

        Boolean flag = memberName.isEmpty() || memberId.isEmpty() || memberMobile.isEmpty() || memberEmail.isEmpty();

        if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please type in all fields");
            alert.showAndWait();
            return;
        }

        String st = "INSERT INTO MEMBER VALUES ("
                + "'" + memberId + "',"
                + "'" + memberName + "',"
                + "'" + memberMobile + "',"
                + "'" + memberEmail + "'"
                + ")";

        System.out.println(st);
        if (handler.execAction(st)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("New member saved to the database");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to insert the reocrd in the database");
            alert.showAndWait();
        }
    }

}
