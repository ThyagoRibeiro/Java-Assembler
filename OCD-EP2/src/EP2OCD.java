/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thyago Ribeiro
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

@SuppressWarnings("serial")
public class EP2OCD extends javax.swing.JFrame {

	// Declarando componentes
	int index;
	String[] flagsHeader;
	String[][] flags;
	JButton btnExec;
	JButton btnNext;
	JPanel jPanel1;
	JPanel jPanel2;
	JPanel jPanel5;
	JPanel jPanel6;
	JScrollPane jScrollPane1;
	JScrollPane jScrollPane2;
	JScrollPane jScrollPane3;
	JScrollPane jScrollPane4;
	JLabel lblAx;
	JLabel lblAxBin;
	JLabel lblAxDec;
	JLabel lblAxHex;
	JLabel lblBin;
	JLabel lblBx;
	JLabel lblBxBin;
	JLabel lblBxDec;
	JLabel lblBxHex;
	JLabel lblCx;
	JLabel lblCxBin;
	JLabel lblCxDec;
	JLabel lblCxHex;
	JLabel lblDec;
	JLabel lblDiagramImage;
	JLabel lblDx;
	JLabel lblDxBin;
	JLabel lblDxDec;
	JLabel lblDxHex;
	JLabel lblFlags;
	JLabel lblHex;
	JLabel lblIr;
	JLabel lblIrOpcode;
	JLabel lblIrOpcodeValue;
	JLabel lblIrP1;
	JLabel lblIrP1Value;
	JLabel lblIrP2;
	JLabel lblIrP2Value;
	JLabel lblMar;
	JLabel lblMarValue;
	JLabel lblMbr;
	JLabel lblMbrValue;
	JLabel lblPc;
	JLabel lblPcValue;
	JLabel lblRegisters;
	JPanel panelButtons;
	JPanel panelRegisters;
	JTabbedPane tabbedPanel;
	JTable tableFlags;
	JTable tableMemory;
	JTextPane textPanelCode;
	JTextArea textAreaMicroOperations;

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			System.out.println(1);
		} catch (InstantiationException ex) {
			System.out.println(2);
		} catch (IllegalAccessException ex) {
			System.out.println(3);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			System.out.println(4);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EP2OCD().setVisible(true);
			}
		});
	}

	// Construtor
	public EP2OCD() {

		iniciarComponentes();

	}

	private void addTableModel() {

		tableFlags.setModel(new DefaultTableModel(flags, flagsHeader) {
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
	}

	private void iniciarComponentes() {

		// Iniciando variaveis de componentes

		jScrollPane1 = new JScrollPane();
		textPanelCode = new JTextPane();
		panelButtons = new JPanel();
		btnExec = new JButton();
		btnNext = new JButton();
		tabbedPanel = new JTabbedPane();
		jPanel1 = new JPanel();
		panelRegisters = new JPanel();
		lblDxHex = new JLabel();
		lblAx = new JLabel();
		lblBx = new JLabel();
		lblCx = new JLabel();
		lblDx = new JLabel();
		lblHex = new JLabel();
		lblDec = new JLabel();
		lblBin = new JLabel();
		lblBxHex = new JLabel();
		lblBxDec = new JLabel();
		lblCxHex = new JLabel();
		lblAxHex = new JLabel();
		lblAxDec = new JLabel();
		lblCxDec = new JLabel();
		lblAxBin = new JLabel();
		lblDxDec = new JLabel();
		lblRegisters = new JLabel();
		lblBxBin = new JLabel();
		lblCxBin = new JLabel();
		lblDxBin = new JLabel();
		lblMar = new JLabel();
		lblMbr = new JLabel();
		lblMbrValue = new JLabel();
		lblMarValue = new JLabel();
		lblIr = new JLabel();
		lblPc = new JLabel();
		lblPcValue = new JLabel();
		lblIrOpcodeValue = new JLabel();
		lblIrOpcode = new JLabel();
		lblIrP1 = new JLabel();
		lblIrP1Value = new JLabel();
		lblIrP2 = new JLabel();
		lblIrP2Value = new JLabel();
		jPanel2 = new JPanel();
		lblFlags = new JLabel();
		jScrollPane2 = new JScrollPane();
		tableFlags = new JTable();
		jPanel5 = new JPanel();
		lblDiagramImage = new JLabel();
		jScrollPane4 = new JScrollPane();
		textAreaMicroOperations = new JTextArea();
		jPanel6 = new JPanel();
		jScrollPane3 = new JScrollPane();
		tableMemory = new JTable();

		// Configuracoes da janela
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Assembler - OCD - EP2");
		setPreferredSize(new java.awt.Dimension(800, 600));
		setResizable(false);
		setSize(new java.awt.Dimension(800, 600));
		setLocationRelativeTo(null);

		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		setVisible(true);

		textPanelCode.setContentType("text/html");

		jScrollPane1.setViewportView(textPanelCode);

		// Configuracoes dos botoes
		btnExec.setText("Executar");
		btnExec.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExecActionPerformed(evt);
			}
		});

		btnNext.setText("Próxima Linha");
		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNextActionPerformed(evt);
			}
		});

		// Organizando botoes
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(panelButtons);
		panelButtons.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addComponent(btnExec, javax.swing.GroupLayout.DEFAULT_SIZE, 126,
												Short.MAX_VALUE)
										.addGap(138, 138, 138).addComponent(btnNext,
												javax.swing.GroupLayout.PREFERRED_SIZE, 126,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnExec, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		tabbedPanel.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
		panelRegisters.setPreferredSize(new java.awt.Dimension(286, 200));

		// Adicionando texto aos labels
		lblDxHex.setText("0000");
		lblAx.setText("ax");
		lblBx.setText("bx");
		lblCx.setText("cx");
		lblDx.setText("dx");
		lblHex.setText("Hex");
		lblDec.setText("Dec");
		lblBin.setText("Bin");
		lblBxHex.setText("0000");
		lblBxDec.setText("00000");
		lblCxHex.setText("0000");
		lblAxHex.setText("0000");
		lblAxDec.setText("00000");
		lblCxDec.setText("00000");
		lblAxBin.setText("0000000000000000");
		lblDxDec.setText("00000");
		lblRegisters.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		lblRegisters.setText("Registradores");
		lblBxBin.setText("0000000000000000");
		lblCxBin.setText("0000000000000000");
		lblDxBin.setText("0000000000000000");
		lblMar.setText("MAR");
		lblMbr.setText("MBR");
		lblMbrValue.setText("0000");
		lblMarValue.setText("0000");
		lblIr.setText("IR");
		lblPc.setText("PC");
		lblPcValue.setText("0000");
		lblIrOpcodeValue.setText("0000");
		lblIrOpcode.setText("opcode");
		lblIrP1.setText("p1");
		lblIrP1Value.setText("0000");
		lblIrP2.setText("p2");
		lblIrP2Value.setText("0000");

		// Organizando componentes de registradores
		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(panelRegisters);
		panelRegisters.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(lblRegisters))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(50, 50, 50).addComponent(lblHex)
								.addGap(81, 81, 81)
								.addComponent(lblDec, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(64, 64, 64).addComponent(lblBin, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(
						jPanel4Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblAx).addGap(18, 18, 18)
								.addComponent(lblAxHex, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(65, 65, 65)
						.addComponent(lblAxDec, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(64, 64, 64).addComponent(lblAxBin))
				.addGroup(
						jPanel4Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblBx).addGap(18, 18, 18)
								.addComponent(lblBxHex, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(65, 65, 65)
						.addComponent(lblBxDec, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(64, 64, 64).addComponent(lblBxBin))
				.addGroup(
						jPanel4Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblCx).addGap(18, 18, 18)
								.addComponent(lblCxHex, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(65, 65, 65)
						.addComponent(lblCxDec, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(64, 64, 64).addComponent(lblCxBin))
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(50, 50, 50).addComponent(lblIr))
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(50, 50, 50).addComponent(lblIrOpcode)
						.addGap(65, 65, 65).addComponent(lblIrP1).addGap(84, 84, 84).addComponent(lblIrP2))
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(50, 50, 50).addComponent(lblIrOpcodeValue)
						.addGap(76, 76, 76).addComponent(lblIrP1Value).addGap(72, 72, 72).addComponent(lblIrP2Value))
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(lblMarValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMar, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel4Layout.createSequentialGroup().addComponent(lblDx).addGap(18, 18, 18)
								.addComponent(lblDxHex, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(65, 65, 65)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblDxDec, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMbr, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMbrValue, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										jPanel4Layout.createSequentialGroup().addGap(64, 64, 64).addComponent(lblDxBin))
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(lblPcValue, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												jPanel4Layout.createSequentialGroup().addGap(60, 60, 60).addComponent(
														lblPc, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
														javax.swing.GroupLayout.PREFERRED_SIZE))))));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(11, 11, 11).addComponent(lblRegisters)
						.addGap(17, 17, 17)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblHex).addComponent(lblDec).addComponent(lblBin))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblAx).addComponent(lblAxHex).addComponent(lblAxDec)
								.addComponent(lblAxBin))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblBx).addComponent(lblBxHex).addComponent(lblBxDec)
								.addComponent(lblBxBin))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblCx).addComponent(lblCxHex).addComponent(lblCxDec)
								.addComponent(lblCxBin))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblDx).addComponent(lblDxHex).addComponent(lblDxDec)
								.addComponent(lblDxBin))
						.addGap(16, 16, 16)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblMar).addComponent(lblMbr).addComponent(lblPc))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblMarValue).addComponent(lblMbrValue).addComponent(lblPcValue))
						.addGap(18, 18, 18).addComponent(lblIr).addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblIrOpcode).addComponent(lblIrP1).addComponent(lblIrP2))
						.addGap(6, 6, 6)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblIrOpcodeValue).addComponent(lblIrP1Value)
								.addComponent(lblIrP2Value))));

		// Iniciando componentes de flags

		lblFlags.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		lblFlags.setText("Flags");

		flags = new String[][] { { "", "", "", "", "", "", "", "", "" } };
		flagsHeader = new String[] { "O", "D", "I", "T", "S", "Z", "A", "P", "C" };
		tableFlags.setBackground(new java.awt.Color(204, 204, 204));
		addTableModel();
		tableFlags.setEnabled(false);
		jScrollPane2.setViewportView(tableFlags);

		// Organizando componentes de flags
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(lblFlags)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel2Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(91, 91, 91)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(lblFlags)
								.addGap(18, 18, 18)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(panelRegisters, javax.swing.GroupLayout.PREFERRED_SIZE, 372,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(panelRegisters, javax.swing.GroupLayout.PREFERRED_SIZE, 306,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(121, Short.MAX_VALUE)));

		// Adiciona painel dos registradores e flags ao menu com abas
		tabbedPanel.addTab("Registradores e Flags", jPanel1);

		// Inicia componentes de micro-operacoes
		lblDiagramImage.setBackground(new java.awt.Color(255, 255, 255));
		lblDiagramImage
				.setIcon(new javax.swing.ImageIcon("C:\\Users\\Thyago Ribeiro\\Desktop\\DiagramaExemplo Pequeno.png")); // NOI18N
		lblDiagramImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		textAreaMicroOperations.setEditable(false);
		textAreaMicroOperations.setColumns(20);
		textAreaMicroOperations.setRows(5);
		jScrollPane4.setViewportView(textAreaMicroOperations);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 365,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiagramImage, javax.swing.GroupLayout.PREFERRED_SIZE, 365,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel5Layout
				.setVerticalGroup(
						jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
										.addComponent(lblDiagramImage, javax.swing.GroupLayout.PREFERRED_SIZE, 316,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185,
												javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));

		// Adiciona painel das micro-operacoes ao menu com abas
		tabbedPanel.addTab("Micro-operações", jPanel5);

		// Iniciando tabela da memoria
		tableMemory
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
								{ null, null }, { null, null }, { null, null }, { null, null }, { null, null },
								{ null, null }, { null, null }, { null, null }, { null, null }, { null, null },
								{ null, null }, { null, null }, { null, null }, { null, null }, { null, null },
								{ null, null }, { null, null }, { null, null }, { null, null }, { null, null },
								{ null, null }, { null, null }, { null, null }, { null, null }, { null, null } },
						new String[] { "Endereço", "Palavrra" }) {
					boolean[] canEdit = new boolean[] { false, false };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		tableMemory.setPreferredSize(new java.awt.Dimension(90, 480));
		tableMemory.getTableHeader().setReorderingAllowed(false);
		jScrollPane3.setViewportView(tableMemory);
		if (tableMemory.getColumnModel().getColumnCount() > 0) {
			tableMemory.getColumnModel().getColumn(0).setPreferredWidth(5);
		}

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout
						.createSequentialGroup().addContainerGap().addComponent(jScrollPane3,
								javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE)));

		// Adiciona painel da memoria ao menu com abas
		tabbedPanel.addTab("Memória", jPanel6);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane1))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tabbedPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(33, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tabbedPanel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 29, Short.MAX_VALUE)))
						.addContainerGap()));

		pack();
	}

	public void setFlagTableValue(int column, String text) {

		flags[0][column] = text;
		addTableModel();
	}

	public void setLblAxBin(String text) {
		this.lblAxBin.setText(text);
	}

	public void setLblAxDec(String text) {
		this.lblAxDec.setText(text);
	}

	public void setLblAxHex(String text) {
		this.lblAxHex.setText(text);
	}

	public void setLblBin(String text) {
		this.lblBin.setText(text);
	}

	public void setLblBxBin(String text) {
		this.lblBxBin.setText(text);
	}

	public void setLblBxDec(String text) {
		this.lblBxDec.setText(text);
	}

	public void setLblBxHex(String text) {
		this.lblBxHex.setText(text);
	}

	public void setLblCxBin(String text) {
		this.lblCxBin.setText(text);
	}

	public void setLblCxDec(String text) {
		this.lblCxDec.setText(text);
	}

	public void setLblCxHex(String text) {
		this.lblCxHex.setText(text);
	}

	public void setLblDxBin(String text) {
		this.lblDxBin.setText(text);
	}

	public void setLblDxDec(String text) {
		this.lblDxDec.setText(text);
	}

	public void setLblDxHex(String text) {
		this.lblDxHex.setText(text);
	}

	public void setLblIrOpcodeValue(String text) {
		this.lblIrOpcodeValue.setText(text);
	}

	public void setLblIrP1Value(String text) {
		this.lblIrP1Value.setText(text);
	}

	public void setLblIrP2Value(String text) {
		this.lblIrP2Value.setText(text);
	}

	public void setLblMarValue(String text) {
		this.lblMarValue.setText(text);
	}

	public void setLblMbrValue(String text) {
		this.lblMbrValue.setText(text);
	}

	public void setLblPcValue(String text) {
		this.lblPcValue.setText(text);
	}

	private void btnExecActionPerformed(java.awt.event.ActionEvent evt) {

		System.out.println("exec");

		String temp;

		for (String strings : textPanelCode.getText().split("\n")) {
			System.out.println(strings);
		}

	}

	private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {

		String[] text = null;
		String temp = "";
		try {
			text = textPanelCode.getDocument().getText(0, textPanelCode.getDocument().getLength()).split("\n");
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		text[index] = "<div style=\"background-color:red\">".concat(text[index]).concat("</div>");

		for (String string : text) {
//			System.out.println("1o " + !string.equals(""));
			if (!string.equals("")) {
				System.out.println("2o " + string.contains("div"));
				if (string.contains("div"))
					temp = temp + string;
				else
					temp = temp + "<p style=\"margin-top: 0\">" + string + "</p>";
				textPanelCode.setText(temp);

			}

		}

		System.out.println("--");

		index++;

	}

}