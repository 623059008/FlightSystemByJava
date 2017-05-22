package com.frame.java;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.flight.java.DateTime;
import com.flight.java.DbSelect;
import com.flight.java.Flight;

public class ReserveFlight {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveFlight window = new ReserveFlight();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReserveFlight() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Flight f = new DbSelect().FlightSelect(Login.FlightId);
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("\u8D77\u98DE\u57CE\u5E02");
		label.setBounds(43, 75, 108, 29);
		frame.getContentPane().add(label);

		JLabel label2 = new JLabel("\u964D\u843D\u57CE\u5E02");
		label2.setBounds(43, 133, 108, 29);
		frame.getContentPane().add(label2);

		JLabel label_2 = new JLabel("\u8D77\u98DE\u65F6\u95F4");
		label_2.setBounds(43, 212, 62, 29);
		frame.getContentPane().add(label_2);

		JLabel lblNewLabel = new JLabel("\u4EF7\u683C");
		lblNewLabel.setBounds(43, 306, 44, 29);
		frame.getContentPane().add(lblNewLabel);

		JLabel flightNamelabel = new JLabel("\u822A\u73ED\u53F7\uFF1A");
		flightNamelabel.setBounds(43, 36, 62, 29);
		frame.getContentPane().add(flightNamelabel);

		JButton Create = new JButton("\u70B9\u51FB\u652F\u4ED8");
		Create.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Pay windowPay = new Pay();
				windowPay.getFrame().setVisible(true);
			}
		});
		Create.setBounds(92, 386, 153, 37);
		frame.getContentPane().add(Create);

		JLabel label_1 = new JLabel("\u5BB9\u91CF");
		label_1.setBounds(439, 36, 55, 29);
		frame.getContentPane().add(label_1);

		JLabel label_3 = new JLabel("\u964D\u843D\u65F6\u95F4");
		label_3.setBounds(43, 264, 54, 15);
		frame.getContentPane().add(label_3);

		JButton button = new JButton("\u53D6\u6D88\u9884\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Research window = new Research();
				window.getFrame().setVisible(true);
			}
		});

		button.setBounds(413, 386, 153, 37);
		frame.getContentPane().add(button);

		JLabel FlightName = new JLabel("New label");
		FlightName.setBounds(121, 36, 74, 29);
		frame.getContentPane().add(FlightName);

		JLabel StartCity = new JLabel("New label");
		StartCity.setBounds(121, 75, 74, 29);
		frame.getContentPane().add(StartCity);

		JLabel ArrCity = new JLabel("New label");
		ArrCity.setBounds(121, 133, 92, 29);
		frame.getContentPane().add(ArrCity);

		JLabel StartTime = new JLabel("New label");
		StartTime.setBounds(121, 219, 145, 15);
		frame.getContentPane().add(StartTime);

		JLabel ArrTime = new JLabel("New label");
		ArrTime.setBounds(121, 264, 145, 15);
		frame.getContentPane().add(ArrTime);

		JLabel Price = new JLabel("New label");
		Price.setBounds(121, 313, 92, 15);
		frame.getContentPane().add(Price);

		JLabel Seat = new JLabel("New label");
		Seat.setBounds(558, 43, 123, 15);
		frame.getContentPane().add(Seat);
		FlightName.setText(f.getFlightName());
		StartCity.setText(f.getStartCity());
		ArrCity.setText(f.getArrivalCity());
		StartTime.setText(DateTime.GetDateTimeStr(f.getStartTime()));
		ArrTime.setText(DateTime.GetDateTimeStr(f.getArrivalTime()));
		Price.setText(Float.toString(f.getPrice()) + " Ԫ");
		Seat.setText(Integer.toString(f.getSeatCapacity()));

	}

	public Window getFrame() {
		// TODO �Զ����ɵķ������
		return frame;
	}
}
