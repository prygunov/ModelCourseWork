package net.artux;

import net.artux.model.DataModel;
import net.artux.model.MainForm;

import javax.xml.crypto.Data;

public class Application {

    private final MainForm mainForm; // структура окна входа
    public static DataModel dataModel;

    Application(){
        mainForm = new MainForm();
        dataModel = new DataModel(mainForm);

        mainForm.setVisible(true);
    }

}
