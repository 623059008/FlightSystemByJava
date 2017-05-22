package com.flight.java;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Passenger {
	private int id = 0;
	private String RealName = "";
	private String IdentityId = "";
	private String Password = "";
	private int[] OrderList;

	public Passenger(int id, String RealName, String IdentityId,

	String Password, String OrderList) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.RealName = RealName;
		this.IdentityId = IdentityId;
		this.Password = Password;
		this.OrderList = this.GetOrderList(OrderList);
	}

	/**
	 * @name ��ѯ�˿Ͷ���
	 * @return ���س˿͵����ж����б�
	 */
	public Order[] SelectOrders(String pwd) {
		if (Passenger.CheckPwd(this.getRealName(), pwd)) {
			return new DbSelect().PassengerOrders(this.getId());
		} else {
			System.err.println("�˿��������");
			return null;
		}
	}

	/**
	 * @name �˿͵�¼��֤����
	 * @param RealName
	 * @param pwd
	 * @return true��false
	 */
	public static boolean CheckPwd(String RealName, String pwd) {
		DbSelect _s = new DbSelect();
		Passenger _a = _s.PassengerSelect(RealName, pwd);
		if (_a != null) {

			return true;

		}
		return false;

	}

	/**
	 * @name �˿Ͷ�Ʊ����
	 * @param pid
	 *            �˿�id
	 * @param fid
	 *            ����id
	 * @param pwd
	 *            �˿�����
	 * @return true��false PS:�����ȡ�˿͵ĸո����ɵĶ���������ʹ����һ�����غ���
	 */
	public static boolean ReserveFlight(int pid, int fid, String pwd) {

		DbSelect select = new DbSelect();
		Flight f = select.FlightSelect(fid);
		if (f.getFlightStatus().equals("AVAILABLE")) {
			Passenger p = select.PassengerSelect(pid);
			if (Passenger.CheckPwd(p.getRealName(), pwd)) {
				// һ���˿����һ������ֻ����һ��Ʊ
				if (select.OrderSelect(pid, fid,"") == null) {
					DbInsert insert = new DbInsert();
					// �������ڸ�ʽ
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss");
					String CreateDate = df.format(new Date());

					boolean re = insert.OrderInsert(p.getId(), p.getId(),
							f.getId(), CreateDate, "PAID");
					// ����Flight��Passenger�б��CurrentPassengers������Passenger��OrderList
					re = re && Flight.ReserveFlight(pid, fid)
							&& p.UpdateOrderList(fid);
					if (re) {
						return true;
					}
				} else {
					System.err.println("�����Ѵ��ڣ��޷��ظ���Ʊ");
				}
			} else {
				System.err.println("�˿��������");
				return false;
			}
		} else {
			System.err.println("����״̬�쳣������Ԥ��");
			return false;
		}
		return false;
	}

	/**
	 * @name �˿Ͷ�Ʊ����
	 * @param pid
	 *            �˿�id
	 * @param fid
	 *            ����id
	 * @param pwd
	 *            �˿�����
	 * @param mode
	 *            ֻ��Ϊ����������ĺ��������ض�������,��㸳ֵ��
	 * @return ���ظոմ����Ķ��������null PS:һ���˿����һ������ֻ����һ��Ʊ
	 */
	public static Order ReserveFlight(int pid, int fid, String pwd, int mode) {

		DbSelect select = new DbSelect();
		Flight f = select.FlightSelect(fid);

		if (f.getFlightStatus().equals("AVAILABLE")) {
			Passenger p = select.PassengerSelect(pid);

			if (Passenger.CheckPwd(p.getRealName(), pwd)) {
				if (select.OrderSelect(pid, fid) == null) {

					DbInsert insert = new DbInsert();
					// �������ڸ�ʽ
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss");
					String CreateDate = df.format(new Date());

					boolean re = insert.OrderInsert(p.getId(), p.getId(),
							f.getId(), CreateDate, "PAID");
					// ����Flight��Passenger�б��CurrentPassengers������Passenger��OrderList
					re = re && Flight.ReserveFlight(pid, fid)
							&& p.UpdateOrderList(fid);
					if (re) {
						return select.OrderSelect(pid, fid);
					}
				} else {
					System.err.println("�����Ѵ��ڣ��޷��ظ���Ʊ");
				}
			} else {
				System.err.println("�˿��������");
				return null;
			}
		} else {
			System.err.println("����״̬�쳣������Ԥ��");
			return null;
		}
		return null;
	}

	/**
	 * @name ���³˿͵ĺ����б�
	 * @param fid
	 *            ����Id
	 * @return true��false PS��������OrderList�������ŵ�ȴ��FlightId
	 *         ��Ҫ�õ�Order�Ļ�����Ҫ��PassengerId��FlightIdȥ�߼���ѯOrder ���Ƕ��һ��=_=
	 */
	public boolean UpdateOrderList(int fid) {
		int[] _o = this.getOrderList();
		String OrderList = "";
		if (_o != null) {
			for (int i = 0; i < _o.length; i++) {
				OrderList += _o[i] + ";";
			}
		}
		OrderList += fid + ";";
		return new DbUpdate().UpdateOrderList(this.getId(), OrderList);
	}

	public boolean UpdateOrderList2(int fid) {
		int[] _o = this.getOrderList();
		String OrderList = "";
		if (_o != null) {
			for (int i = 0; i < _o.length; i++) {
				if (_o[i] != fid) {
					OrderList += _o[i] + ";";
				}
			}
		}
		return new DbUpdate().UpdateOrderList(this.getId(), OrderList);
	}

	/**
	 * @name �˶���Ʊ����
	 * @param pid
	 *            �˿�id
	 * @param fid
	 *            ����id
	 * @param pwd
	 *            �˿�����
	 * @return true��false ��ͬ�������err����д��
	 */
	public static boolean UnsubscribeFlight(int pid, int fid, String pwd) {
		DbSelect select = new DbSelect();
		Flight f = select.FlightSelect(fid);
		// �������������������˶�
		if (!f.getFlightStatus().equals("TERMINATE")) {
			Passenger p = select.PassengerSelect(pid);
			if (Passenger.CheckPwd(p.getRealName(), pwd)) {
				// ����Ƿ���ڶ���
				if (select.OrderSelect(pid, fid) != null) {
					// ����Flight��Passenger�б��CurrentPassengers������Passenger��OrderList
					boolean re = f.UnreserveFlight(pid, fid)
							&& p.UpdateOrderList2(fid);
					if (re) {
						return true;
					}
				} else {
					System.err.println("���������ڣ��޷��˶�");
				}
			} else {
				System.err.println("�˿��������");
				return false;
			}
		} else {
			System.err.println("�����������������˶�");
			return false;
		}
		return false;

	}

	// get������
	public int getId() {
		return id;
	}

	public String getRealName() {
		return RealName;
	}

	public String getIdentityId() {
		return IdentityId;
	}

	public String getPassword() {
		return Password;
	}

	public int[] getOrderList() {
		return OrderList;
	}

	// ����Id��ԃ���w��Ϣ���@��Passenger�Č���
	public Passenger GetPassengerById(int id) {
		DbSelect select = new DbSelect();
		return select.PassengerSelect(id);
	}

	public String GetOrderString(int[] oi) {
		String s = "";
		for (int i = 0; i < oi.length; i++) {
			s += Integer.toString(oi[i]) + ";";
		}
		return s;

	}

	// �є������e��OrderList String׃��OrderId�Ĕ��M
	public int[] GetOrderList(String _o) {
		if (_o.length() > 0) {
			String[] _s;
			_s = _o.split(";");
			int[] _t = new int[_s.length];
			for (int i = 0; i < _s.length; i++) {
				_t[i] = Integer.parseInt(_s[i]);
			}

			return _t;
		}
		return null;
	}

	public static void main(String[] args) {
		// System.out.println(Passenger.UnsubscribeFlight(3, 2, "balabala"));

	}
}
