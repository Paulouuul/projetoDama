package controller;

import java.awt.EventQueue;

import view.TelaInicioDamas;

public class JogoDeDamas {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaInicioDamas frame = new TelaInicioDamas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	}


