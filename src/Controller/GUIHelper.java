package Controller;

import Model.User;

import javax.swing.*;

public class GUIHelper {

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                         InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void emptyTextFields(JTextField field, JTextField field2) {
        field.setText("");
        field2.setText("");
    }

    public static void prepareStaffGUIEmptyValues(JComboBox combobox, JTextField field1, JTextField field2, JTextField field3, JTextField field4) {
        setComboboxEmpty(combobox);
        setPriceField(field1);
        setPriceField(field2);
        setPriceField(field3);
        setPriceField(field4);
    }

    public static void setComboboxEmpty(JComboBox combobox) {
        combobox.setSelectedIndex(0);
    }

    public static void setPriceField(JTextField field) {
        field.setText("0");
    }

}



