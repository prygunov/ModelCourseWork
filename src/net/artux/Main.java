package net.artux;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()); // получение и установка для программы стиля системы
        new Application(); // запуск приложения
    }
}
