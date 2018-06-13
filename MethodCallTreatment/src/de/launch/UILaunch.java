package de.launch;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.tubs.mt.FileControl;
import de.tubs.mt.codeanalyze.MethodPrinter;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.CallGenerator.Program;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class UILaunch extends JFrame {

	private Launcher launcher;

	private JTextField textFieldsearch;
	private JTextField textFieldruns;
	private JTextField textFieldwidth;
	private JTextField textFielddepth;
	private JTextField textFieldinfo;
	private JTextField textFieldStarterM;
	private JTextField textFieldSpecPerc;
	private JCheckBox chckbxCaching;
	private JCheckBox chckbxCompletespec;
	private JCheckBox chckbxContracting;
	private JCheckBox chckbxCreatexmsl;
	private JCheckBox chckbxFromTo;
	private JButton btnGenerate;
	private JCheckBox chckbxChooseExistingJava;
	private JButton btnSearch;
	private JComboBox comboBox;
	private JComboBox comboBoxGranulation;
	private JPanel panel_2;
	private JList listUI;

	private String choosenFile;
	private JList list;
	private JLabel lblNewLabel_1;
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			changeStarter();
		}
	};
	private JLabel lblSetSpecification;
	private JTextField textFieldStartPercent;
	private JLabel lblTo;
	private JTextField textFieldEndPercent;
	private List<PrepMethod> methodList;
	private JLabel lblSpecification;
	

	public UILaunch(Launcher launcher) {
		this.launcher = launcher;
		this.setSize(926, 626);
		this.setLocation(200, 100);
		getContentPane().setBackground(UIManager.getColor("InternalFrame.borderColor"));

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("info"));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("info"));
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));

		chckbxCaching = new JCheckBox("caching");
		chckbxCaching.setBackground(UIManager.getColor("textInactiveText"));
		chckbxCaching.setSelected(true);
		chckbxCompletespec = new JCheckBox("completeSpec");
		chckbxCompletespec.setBackground(UIManager.getColor("textHighlight"));
		chckbxCompletespec.setSelected(true);
		chckbxContracting = new JCheckBox("contracting");
		chckbxContracting.setBackground(UIManager.getColor("textHighlight"));
		chckbxContracting.setSelected(true);
		chckbxCreatexmsl = new JCheckBox("create .xls");
		chckbxCreatexmsl.setBackground(UIManager.getColor("textHighlight"));
		textFieldruns = new JTextField();
		textFieldruns.setText("1");
		textFieldruns.setColumns(10);

		JLabel lblRuns = new JLabel("Runs");

		textFieldinfo = new JTextField();
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);
		
		lblSetSpecification = new JLabel("Set Specification (%)");
		
		textFieldStartPercent = new JTextField();
		textFieldStartPercent.setText("0");
		textFieldStartPercent.setColumns(10);
		
		lblTo = new JLabel("to");
		
		textFieldEndPercent = new JTextField();
		textFieldEndPercent.setText("100");
		textFieldEndPercent.setColumns(10);
		
		JLabel lblGranulation = new JLabel("Granulation");
		
		comboBoxGranulation = new JComboBox();
		comboBoxGranulation.setModel(new DefaultComboBoxModel(new String[] {"1", "5", "10", "15", "20", "30", "40", "50"}));
		comboBoxGranulation.setSelectedIndex(2);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(124)
							.addComponent(lblOptions))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxContracting)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(chckbxCaching)
											.addGap(78)
											.addComponent(lblRuns)
											.addGap(7)
											.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
										.addComponent(chckbxCompletespec))
									.addGap(72)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSetSpecification)
										.addComponent(lblGranulation))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBoxGranulation, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldStartPercent, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxCreatexmsl))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldinfo, GroupLayout.PREFERRED_SIZE, 866, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOptions)
					.addGap(39)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCaching)
						.addComponent(lblRuns)
						.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSetSpecification)
						.addComponent(textFieldStartPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(chckbxCompletespec)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxContracting)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxCreatexmsl))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGranulation)
							.addComponent(comboBoxGranulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addComponent(textFieldinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);

		panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("EditorPane.foreground"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		Vector v = new Vector<PrepMethod>();
		list = new JList(v);
		

		list.addListSelectionListener(selListener);


		JScrollPane scrollPane = new JScrollPane(list);
		sl_panel_2.putConstraint(SpringLayout.NORTH, scrollPane, 63, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, scrollPane, -18, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, scrollPane, 306, SpringLayout.WEST, panel_2);
		panel_2.add(scrollPane);
		sl_panel_2.putConstraint(SpringLayout.NORTH, list, 39, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, list, 46, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, list, -45, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, list, -28, SpringLayout.EAST, panel_2);
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof PrepMethod) {
					PrepMethod nextMethod = (PrepMethod) value;
					setText(nextMethod.name);
					if (nextMethod.jml) {
						setBackground(Color.GREEN);
					} else {
						setBackground(Color.RED);
					}
					if (isSelected) {
						setBackground(getBackground().darker());
					}
				} else {
					setText("wrong instance");
				}
				return c;
			}

		});

		JLabel lblNewLabel = new JLabel("List of Methods   Green: Contract   Red: No Contract");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel, -95, SpringLayout.EAST, panel_2);
		lblNewLabel.setForeground(UIManager.getColor("DesktopIcon.background"));
		panel_2.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Starter-Method:");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 38, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel_1, 38, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel_1, -46, SpringLayout.EAST, panel_2);
		lblNewLabel_1.setForeground(UIManager.getColor("text"));
		panel_2.add(lblNewLabel_1);
		
		textFieldStarterM = new JTextField();
		textFieldStarterM.setEditable(false);
		sl_panel_2.putConstraint(SpringLayout.NORTH, textFieldStarterM, 19, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldStarterM, 23, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldStarterM, 18, SpringLayout.EAST, lblNewLabel_1);
		panel_2.add(textFieldStarterM);
		textFieldStarterM.setColumns(10);
		
		JButton btnExecVerify = new JButton("Verify");
		btnExecVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					executeVerify();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnExecVerify, 0, SpringLayout.SOUTH, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnExecVerify, -60, SpringLayout.EAST, panel_2);
		panel_2.add(btnExecVerify);
		
		lblSpecification = new JLabel("Specification (%):");
		lblSpecification.setForeground(UIManager.getColor("menu"));
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblSpecification, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblSpecification, 0, SpringLayout.WEST, scrollPane);
		panel_2.add(lblSpecification);
		
		textFieldSpecPerc = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textFieldSpecPerc, 4, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldSpecPerc, 16, SpringLayout.EAST, lblSpecification);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldSpecPerc, 105, SpringLayout.EAST, lblSpecification);
		panel_2.add(textFieldSpecPerc);
		textFieldSpecPerc.setColumns(10);

		JLabel lblProgramm = new JLabel("Programs");
		lblProgramm.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));

		chckbxChooseExistingJava = new JCheckBox("Choose existing Java Class");
		chckbxChooseExistingJava.setBackground(UIManager.getColor("info"));

		JLabel lblCreate = new JLabel("Create");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseInputFile();
			}
		});

		textFieldsearch = new JTextField();
		textFieldsearch.setEditable(false);
		textFieldsearch.setColumns(10);
		
				btnGenerate = new JButton("Generate");
				btnGenerate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							executeGenerate();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
		JLabel lblWidth = new JLabel("Width");
		
				JLabel lblDepth = new JLabel("Depth");
		
				textFieldwidth = new JTextField();
				textFieldwidth.setText("2");
				textFieldwidth.setColumns(10);
		
				textFielddepth = new JTextField();
				textFielddepth.setText("2");
				textFielddepth.setColumns(10);
		
				chckbxFromTo = new JCheckBox("from 1 to depth");
				chckbxFromTo.setBackground(UIManager.getColor("textHighlight"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(123)
							.addComponent(lblProgramm))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCreate, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWidth))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldwidth, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(textFielddepth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
									.addGap(29)
									.addComponent(chckbxFromTo)))))
					.addContainerGap(60, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(chckbxChooseExistingJava, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldsearch, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(35))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDepth)
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProgramm)
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreate)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldwidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDepth)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFielddepth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(chckbxFromTo)))
					.addGap(32)
					.addComponent(chckbxChooseExistingJava)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(textFieldsearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
	
	private void changeStarter() {
		PrepMethod pm = (PrepMethod) list.getModel().getElementAt(list.getSelectedIndex());
		textFieldStarterM.setText(pm.name);
		textFieldinfo.setText("");
	}
	
	private void executeVerify() throws Exception {
		setParameter();
		String whitelist = textFieldStarterM.getText();
		if(whitelist.equals("")) {
			textFieldinfo.setText("Choose a starter");
			return;
		}
		int startP = Integer.parseInt(textFieldStartPercent.getText());
		int endP = Integer.parseInt(textFieldEndPercent.getText());
		int gran = Integer.parseInt(comboBoxGranulation.getSelectedItem().toString());

		launcher.executeLauncher(whitelist, methodList, startP, endP, gran);
		
	}
	
	private void setParameter() {
		Program program = Program.OWN;
		if (!chckbxChooseExistingJava.isSelected()) {
			program = comboBox.getSelectedItem().toString() == "Add" ? Program.ADD : Program.BUBBLESORT;
		}

		int runs = Integer.parseInt(textFieldruns.getText());
		int width = Integer.parseInt(textFieldwidth.getText());
		int depth = Integer.parseInt(textFielddepth.getText());
		boolean completeSpec = chckbxCompletespec.isSelected();
		boolean contracting = chckbxContracting.isSelected();
		boolean caching = chckbxCaching.isSelected();
		boolean isToDepth = chckbxFromTo.isSelected();
		boolean isXls = chckbxCreatexmsl.isSelected();
		String javaFilePath = textFieldsearch.getText();


		launcher.setParameter(program, runs, width, depth, completeSpec, contracting, caching, isToDepth, isXls,
				javaFilePath);	
	}

	private void executeGenerate() throws Exception {
		setParameter();
		methodList = launcher.runGenerate();

		Vector v = new Vector<PrepMethod>();
		for (PrepMethod meth : methodList) {
			v.add(meth);
		}
		
		textFieldSpecPerc.setText("" + MethodPrinter.getSpecPercent(methodList));
		textFieldEndPercent.setText("" + MethodPrinter.getSpecPercent(methodList));
		
		list.removeListSelectionListener(selListener);
		list.setListData(v);
		list.addListSelectionListener(selListener);
		
	}

	private void chooseInputFile() {
		final JFileChooser fc = new JFileChooser("TestClasses");
		fc.setCurrentDirectory(FileControl.getTestclassesPath());
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
		fc.addChoosableFileFilter(filter);

		int selection = fc.showOpenDialog(this);

		if (selection == JFileChooser.APPROVE_OPTION) {
			choosenFile = fc.getSelectedFile().getName();
			textFieldsearch.setText(choosenFile);
		}

	}
}
