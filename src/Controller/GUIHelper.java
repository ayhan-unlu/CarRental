package Controller;

import javax.swing.*;

public class GUIHelper {

    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch(ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void emptyTextFields(JTextField field,JTextField field2){
        field.setText("");
        field2.setText("");
    }


}
