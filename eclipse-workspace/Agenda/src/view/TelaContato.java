package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class TelaContato extends JFrame {

	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaContato frame = new TelaContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaContato() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// ativação do formulario (formulario carregado)
				// status da conexão
				status();
			}
		});
		setTitle("Agenda de Contato");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaContato.class.getResource("/icones/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(381, 214, 32, 32);
		contentPane.add(lblStatus);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 64, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setBounds(10, 107, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(10, 151, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(52, 22, 156, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(52, 61, 262, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBounds(52, 104, 156, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(52, 148, 262, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarContato();
			}
		});
		btnRead.setEnabled(false);
		btnRead.setBorder(null);
		btnRead.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/read.png")));
		btnRead.setToolTipText("Bucar contato");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.setBackground(SystemColor.control);
		btnRead.setBounds(336, 42, 64, 64);
		contentPane.add(btnRead);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirContato();
			}
		});
		btnCreate.setEnabled(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBorder(null);
		btnCreate.setBackground(SystemColor.control);
		btnCreate.setToolTipText("Adicionar contato");
		btnCreate.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/create.png")));
		btnCreate.setBounds(10, 187, 64, 64);
		contentPane.add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBorder(null);
		btnUpdate.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/update.png")));
		btnUpdate.setToolTipText("Atualizar contato");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBackground(SystemColor.control);
		btnUpdate.setBounds(125, 187, 64, 64);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarContato();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBorder(null);
		btnDelete.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/delete.png")));
		btnDelete.setToolTipText("Deletar contato");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBackground(SystemColor.control);
		btnDelete.setBounds(242, 187, 64, 64);
		contentPane.add(btnDelete);
	} // fim do construtor

	// criação de um objeto para acessar o metodo da classe DAO
	DAO dao = new DAO();
	private JButton btnRead;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;

	/**
	 * status da conexão
	 */
	private void status() {
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// trocando o icone do banco de dados (status da conexão)
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));
				btnRead.setEnabled(true);
				// btnCreate.setEnabled(true);
				// btnUpdate.setEnabled(true);
				// btnDelete.setEnabled(true);
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			}
			// encerrar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Selecionar contato
	 */
	private void selecionarContato() {
		// instrução sql para pesquisar um contato pelo nome
		String read = "select * from Contatos where nome = ?";
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// preparar a instrução sql
			PreparedStatement pst = con.prepareStatement(read);
			// subistituir o parametro(?) pelo nome do contato
			pst.setString(1, txtNome.getText());
			// resultado (obter os dados do contato pesquisado)
			ResultSet rs = pst.executeQuery();
			// se existir um contato correspondente
			if (rs.next()) {
				txtId.setText(rs.getString(1)); // 1 é o campo idcon
				txtFone.setText(rs.getString(3)); // 3 é o fone
				txtEmail.setText(rs.getString(4)); // 4 é o email
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnRead.setEnabled(false);

			} else {
				// criar uma caixa de mensagem do Java
				// JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnCreate.setEnabled(true);
				btnRead.setEnabled(false);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * inserir um novo contato CRUD Creat
	 */
	private void inserirContato() {
		// validação dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Contato");

		} else if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone do Contato");

		} else if (txtNome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 50 caracteres");

		} else if (txtFone.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 15 caracteres");
		} else if (txtEmail.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 50 caracteres");
		} else {
			String create = "insert into Contatos (nome,fone,email) values (?,?,?)";

			try {
				// estabelecer uma conexão
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(create);
				// subistituir os parametros (?,?,?) pelo conteudo das caixas de texto
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato adicionado");
				con.close();
				limpar();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Editar contato CRUD Update
	 */
	private void alterarContato() {
		// validação dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Contato");

		} else if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone do Contato");

		} else if (txtNome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 50 caracteres");

		} else if (txtFone.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 15 caracteres");
		} else if (txtEmail.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 50 caracteres");
		} else {
			String update = "update Contatos set nome=?,fone=?,email=? where idcon=?";

			try {
				// estabelecer uma conexão
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(update);
				// subistituir os parametros (?,?,?) pelo conteudo das caixas de texto
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato atualizado");
				con.close();
				limpar();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * excluir contato CUD Delete
	 */
	private void deletarContato() {
		String delete = " delete from contatos where idcon=?";
		int confirma = JOptionPane.showConfirmDialog(null, "Comfirma a exclusão deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();
				limpar();
				JOptionPane.showMessageDialog(null, "Contato excluido");
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			limpar();
		}
	}

	/**
	 * limpar campos e configurar botões
	 */

	private void limpar() {
		// limpar os campos
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);

		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}

}
