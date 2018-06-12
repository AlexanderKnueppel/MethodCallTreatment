package de.launch;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.tubs.mt.FileControl;
import de.tubs.mt.CallGenerator.Program;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UILaunch extends JFrame {
	
	private Launcher launcher;
	
	private JTextField textFieldsearch;
	private JTextField textFieldruns;
	private JTextField textFieldwidth;
	private JTextField textFielddepth;
	private JTextField textFieldinfo;
	private JCheckBox chckbxCaching;
	private JCheckBox chckbxCompletespec;
	private JCheckBox chckbxContracting;
	private JCheckBox chckbxCreatexmsl;
	private JCheckBox chckbxFromTo;
	private JButton btnExecute;
	private JCheckBox chckbxChooseExistingJava;
	private JButton btnSearch;
	private JComboBox comboBox;
	
	private String choosenFile;

	public UILaunch(Launcher launcher) {
		this.launcher = launcher;
		this.setSize(450, 600);
		this.setLocation(200, 100);
		getContentPane().setBackground(UIManager.getColor("InternalFrame.borderColor"));

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));

		chckbxCaching = new JCheckBox("caching");
		chckbxCaching.setSelected(true);
		chckbxCompletespec = new JCheckBox("completeSpec");
		chckbxCompletespec.setSelected(true);
		chckbxContracting = new JCheckBox("contracting");
		chckbxContracting.setSelected(true);
		chckbxCreatexmsl = new JCheckBox("create .xls");
		textFieldruns = new JTextField();
		textFieldruns.setText("1");
		textFieldruns.setColumns(10);

		JLabel lblRuns = new JLabel("Runs");
		JLabel lblWidth = new JLabel("Width");

		textFieldwidth = new JTextField();
		textFieldwidth.setText("2");
		textFieldwidth.setColumns(10);

		chckbxFromTo = new JCheckBox("from 1 to depth");

		JLabel lblDepth = new JLabel("Depth");

		textFielddepth = new JTextField();
		textFielddepth.setText("2");
		textFielddepth.setColumns(10);

		textFieldinfo = new JTextField();
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);

		btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					executeLaunch();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(chckbxCaching))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(chckbxCompletespec))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(124)
									.addComponent(lblOptions))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(chckbxContracting)))
							.addGap(33)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDepth)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRuns)
										.addComponent(lblWidth))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldruns, 0, 0, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFielddepth, 0, 0, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(19)
											.addComponent(textFieldwidth, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldinfo, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(119)
							.addComponent(btnExecute))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxFromTo))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxCreatexmsl)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOptions)
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCaching)
						.addComponent(lblRuns)
						.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCompletespec)
						.addComponent(lblWidth)
						.addComponent(textFieldwidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDepth)
							.addComponent(textFielddepth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(chckbxContracting))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxFromTo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxCreatexmsl)
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addComponent(btnExecute)
					.addGap(18)
					.addComponent(textFieldinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
						.addContainerGap(95, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE).addContainerGap()));

		JLabel lblProgramm = new JLabel("Programs");
		lblProgramm.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));

		chckbxChooseExistingJava = new JCheckBox("Choose existing Java Class");

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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(123).addComponent(lblProgramm))
						.addGroup(gl_panel.createSequentialGroup().addGap(56).addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxChooseExistingJava, GroupLayout.PREFERRED_SIZE, 238,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(textFieldsearch, GroupLayout.PREFERRED_SIZE, 215,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblCreate, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addGap(45).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 166,
												GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblProgramm)
				.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblCreate).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(chckbxChooseExistingJava).addGap(38)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSearch).addComponent(
						textFieldsearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(43)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
	
	
	private void executeLaunch() throws Exception {
		Program program = Program.OWN;
		if(!chckbxChooseExistingJava.isSelected()) {
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
		
		launcher.setParameter(program, runs, width, depth, completeSpec, contracting, caching, isToDepth, isXls, javaFilePath);
		launcher.executeLauncher();
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
