/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.CourseBUS;
import BUS.CourseInstructorBUS;
import BUS.DepartmentBUS;
import BUS.PersonBUS;
import BUS.StudentGradeBUS;
import DTO.CourseDTO;
import DTO.CourseInstructorDTO;
import DTO.DepartmentDTO;
import DTO.PersonDTO;
import DTO.StudentGradeDTO;
import DoAnPhanLop.ConnectDatabase;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class Index extends javax.swing.JFrame {
    private PersonBUS pBUS = new PersonBUS();
    private CourseBUS cBUS = new CourseBUS();
    private CourseInstructorBUS ciBUS = new CourseInstructorBUS();
    private DepartmentBUS dBUS = new DepartmentBUS();
    private StudentGradeBUS sgBUS = new StudentGradeBUS();
    DefaultTableModel modelTableStudent = new DefaultTableModel();
    DefaultTableModel modelTableInstructor = new DefaultTableModel();
    DefaultTableModel modelTableStudentGrade = new DefaultTableModel();
    DefaultTableModel modelTableCourseOnline = new DefaultTableModel();
    DefaultTableModel modelTableCourseOnsite = new DefaultTableModel();
    DefaultTableModel modelTableCourseInstructor = new DefaultTableModel();
    DateFormat formatType = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 23, 1);
    SpinnerModel spinnerModelmin = new SpinnerNumberModel(0, 0, 59, 1);

    /**
     * Creates new form Index
     */
    public Index() {
        initComponents();        
        modelTableStudent = (DefaultTableModel) tblStudent.getModel();
        modelTableInstructor = (DefaultTableModel) tblInstructor.getModel();
        modelTableStudentGrade = (DefaultTableModel) tblStudentGrade.getModel();
        modelTableCourseOnline = (DefaultTableModel) tblecourseonline.getModel();
        modelTableCourseOnsite = (DefaultTableModel) tblcourseonsite.getModel();
        modelTableCourseInstructor = (DefaultTableModel) tblCourseInstructor.getModel();

        readTableOnlineCourse();
        readTableOnsiteCourse();
        readTableStudent();
        readTableInstructor();
        readTableStudentGrade();
        readTableCourseInstrcutor();
    }

    private void readTableStudent() {        
        try {
            pBUS.ShowPersonList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableStudent.setRowCount(0);
        for (PersonDTO p : PersonBUS.PersonList) {
            if (p.getHireDate() == null) {
                modelTableStudent.addRow(new Object[]{
                    p.getPersonID(), p.getLastName(), p.getFirstName(), p.getEnrollmentDate()
                });
            }
        }
    }

    private void readTableInstructor() {        
        try {
            pBUS.ShowPersonList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableInstructor.setRowCount(0);
        for (PersonDTO p : PersonBUS.PersonList) {
            if (p.getEnrollmentDate() == null) {
                modelTableInstructor.addRow(new Object[]{
                    p.getPersonID(), p.getLastName(), p.getFirstName(), p.getHireDate()
                });
            }
        }
    }

    private void readTableOnlineCourse() {        
        try {
            cBUS.ShowCourseList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableCourseOnline.setRowCount(0);
        for (CourseDTO cdto : CourseBUS.CourseList) {
            if (cdto.getURL() != null) {
                modelTableCourseOnline.addRow(new Object[]{
                    cdto.getCourseID(), cdto.getTitle(), cdto.getCredits(), cdto.getDepartmentName(), cdto.getURL()
                });
            }
        }
    }

    private void readTableOnsiteCourse() {
        try {
            cBUS.ShowCourseList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableCourseOnsite.setRowCount(0);
        for (CourseDTO cdto : CourseBUS.CourseList) {
            if (cdto.getLocation() != null) {
                modelTableCourseOnsite.addRow(new Object[]{
                    cdto.getCourseID(), cdto.getTitle(), cdto.getCredits(), cdto.getDepartmentName(), cdto.getLocation(), cdto.getDays(), cdto.getTime()
                });
            }
        }
    }

    private void readTableStudentGrade() {        
        try {
            sgBUS.GetStudentGradeList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableStudentGrade.setRowCount(0);
        for (StudentGradeDTO stDTO : StudentGradeBUS.sgList) {
            modelTableStudentGrade.addRow(new Object[]{
                stDTO.getPersonID(), stDTO.getCourseTitle(), stDTO.getGrade()
            });
        }
    }

    private void readTableCourseInstrcutor() {        
        try {
            ciBUS.ShowCourseInstructorList();
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelTableCourseInstructor.setRowCount(0);
        for (CourseInstructorDTO ctDTO : CourseInstructorBUS.CourseInstructorList) {
            modelTableCourseInstructor.addRow(new Object[]{
                ctDTO.getCourseID(), ctDTO.getTitleCourse(), ctDTO.getPersonID(), ctDTO.getFirstName(), ctDTO.getLastName()
            });
        }
    }

    public String[] ComboboxDepartmentname() {
        try {            
            dBUS.showdpList();
            int temp = DepartmentBUS.dpList.size();
            String s[] = new String[temp];
            int dem = 0;
            for (DepartmentDTO dpDTO : DepartmentBUS.dpList) {
                s[dem] = dpDTO.getName();
                dem++;
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String[] ComboBoxPersonID() {
        try {            
            pBUS.ShowPersonList();
            ArrayList<String> personList = new ArrayList();

            int dem = 0;
            for (PersonDTO pDTO : PersonBUS.PersonList) {
                if (pDTO.getHireDate() == null) {
                    personList.add(pDTO.getPersonID() + "");
                }
            }
            int temp = personList.size();
            String s[] = new String[temp];
            for (String id : personList) {
                s[dem] = id;
                dem++;
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String[] ComboBoxPersonInstructorID() {
        try {            
            pBUS.ShowPersonList();
            ArrayList<String> personList = new ArrayList();

            int dem = 0;
            for (PersonDTO pDTO : PersonBUS.PersonList) {
                if (pDTO.getEnrollmentDate() == null) {
                    personList.add(pDTO.getPersonID() + "");
                }
            }
            int temp = personList.size();
            String s[] = new String[temp];
            for (String id : personList) {
                s[dem] = id;
                dem++;
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String[] ComboBoxCourseTitle() {
        try {            
            cBUS.ShowCourseList();
            int temp = CourseBUS.CourseList.size();
            String s[] = new String[temp];
            int dem = 0;
            for (CourseDTO cDTO : CourseBUS.CourseList) {
                s[dem] = cDTO.getTitle();
                dem++;
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        pnlMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlInformation = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlInstructor = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtLastNameIns = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFirstNameIns = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAddIns = new javax.swing.JButton();
        btnEditIns = new javax.swing.JButton();
        btnDeleteIns = new javax.swing.JButton();
        btnResetIns = new javax.swing.JButton();
        btnSearchIns = new javax.swing.JButton();
        txtSearchIns = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstructor = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        dateHire = new com.toedter.calendar.JDateChooser();
        pnlStudent = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLastNameStu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFirstNameStu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAddStu = new javax.swing.JButton();
        btnEditStu = new javax.swing.JButton();
        btnDeleteStu = new javax.swing.JButton();
        btnResetStu = new javax.swing.JButton();
        btnSearchStu = new javax.swing.JButton();
        txtSearchStu = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        dateEnrollment = new com.toedter.calendar.JDateChooser();
        pnlCourse = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        pnlOnsite = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnSearch3 = new javax.swing.JButton();
        btnResetonsitecourse = new javax.swing.JButton();
        txtSearchonsitecourse = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblcourseonsite = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        txtdays = new javax.swing.JTextField();
        jdepartmentname = new javax.swing.JComboBox<>();
        txtcoursetitle = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtcredits = new javax.swing.JTextField();
        btnAddonsitecourse = new javax.swing.JButton();
        btnEditonsitecourse = new javax.swing.JButton();
        btnDeleteonsitecourse = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtlocation = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jSpinnerHour = new javax.swing.JSpinner(spinnerModel);
        jSpinnerMinutes = new javax.swing.JSpinner(spinnerModelmin);
        pnlOnline = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcoursetitle2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtcredits2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnAddonlinecourse = new javax.swing.JButton();
        btnEditonlinecourse = new javax.swing.JButton();
        btnDeleteonlinecourse = new javax.swing.JButton();
        btnSearch2 = new javax.swing.JButton();
        btnResetonlinecourse = new javax.swing.JButton();
        txtSearchonlinecourse = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblecourseonline = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txturl = new javax.swing.JTextField();
        jdpnameonline = new javax.swing.JComboBox<>();
        pnlStudenGrade = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btnSearch5 = new javax.swing.JButton();
        btnReset5 = new javax.swing.JButton();
        txtSearchStudentGrade = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblStudentGrade = new javax.swing.JTable();
        cbPersonID = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        btnAdd8 = new javax.swing.JButton();
        btnEdit8 = new javax.swing.JButton();
        btnDelete8 = new javax.swing.JButton();
        cbCourseTitle = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtGrade = new javax.swing.JTextField();
        pnlCourseIntructor = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnSearchCouIns = new javax.swing.JButton();
        btnResetCouIns = new javax.swing.JButton();
        txtSearchCouIns = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCourseInstructor = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        btnAddCouIns = new javax.swing.JButton();
        btnEditCouIns = new javax.swing.JButton();
        btnDeleteCouIns = new javax.swing.JButton();
        cbPersonIdCouIns = new javax.swing.JComboBox<>();
        cbCourseTitleCouIns = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý khóa học");
        setBackground(new java.awt.Color(204, 255, 204));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        pnlMain.setPreferredSize(new java.awt.Dimension(1122, 650));
        pnlMain.setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(255, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-course-64.png"))); // NOI18N
        jLabel1.setText("COURSE MANAGEMENT");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                .addContainerGap(403, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(361, 361, 361))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMain.add(jPanel1);
        jPanel1.setBounds(0, 0, 1100, 80);

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 204));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFocusTraversalPolicyProvider(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1561, 48));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1100, 1000));

        pnlInformation.setBackground(new java.awt.Color(204, 204, 255));
        pnlInformation.setEnabled(false);
        pnlInformation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlInformation.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlInformation.setPreferredSize(new java.awt.Dimension(1500, 1000));

        jTabbedPane2.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane2.setForeground(new java.awt.Color(0, 153, 0));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        pnlInstructor.setBackground(new java.awt.Color(204, 204, 255));
        pnlInstructor.setForeground(new java.awt.Color(204, 204, 255));
        pnlInstructor.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlInstructor.setPreferredSize(new java.awt.Dimension(0, 0));
        pnlInstructor.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Last Name:");
        pnlInstructor.add(jLabel2);
        jLabel2.setBounds(200, 110, 100, 15);

        txtLastNameIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(txtLastNameIns);
        txtLastNameIns.setBounds(310, 100, 202, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("First Name:");
        pnlInstructor.add(jLabel4);
        jLabel4.setBounds(200, 160, 100, 15);
        pnlInstructor.add(txtFirstNameIns);
        txtFirstNameIns.setBounds(310, 150, 202, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("HireDate:");
        pnlInstructor.add(jLabel5);
        jLabel5.setBounds(200, 210, 100, 15);

        btnAddIns.setBackground(new java.awt.Color(204, 255, 204));
        btnAddIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAddIns.setText("Add");
        btnAddIns.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAddIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddInsMouseClicked(evt);
            }
        });
        btnAddIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(btnAddIns);
        btnAddIns.setBounds(640, 150, 100, 33);

        btnEditIns.setBackground(new java.awt.Color(204, 255, 204));
        btnEditIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEditIns.setText("Edit");
        btnEditIns.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEditIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditInsMouseClicked(evt);
            }
        });
        btnEditIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(btnEditIns);
        btnEditIns.setBounds(790, 150, 100, 33);

        btnDeleteIns.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDeleteIns.setText("Delete");
        btnDeleteIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteInsMouseClicked(evt);
            }
        });
        btnDeleteIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(btnDeleteIns);
        btnDeleteIns.setBounds(640, 200, 100, 32);

        btnResetIns.setBackground(new java.awt.Color(204, 255, 204));
        btnResetIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnResetIns.setText("Reset");
        btnResetIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(btnResetIns);
        btnResetIns.setBounds(790, 200, 100, 33);

        btnSearchIns.setBackground(new java.awt.Color(204, 255, 204));
        btnSearchIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearchIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchInsMouseClicked(evt);
            }
        });
        btnSearchIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchInsActionPerformed(evt);
            }
        });
        pnlInstructor.add(btnSearchIns);
        btnSearchIns.setBounds(850, 100, 40, 30);
        pnlInstructor.add(txtSearchIns);
        txtSearchIns.setBounds(640, 100, 202, 30);

        tblInstructor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblInstructor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "InstructorID", "Last Name", "First Name", "Hire Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInstructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInstructorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInstructor);

        pnlInstructor.add(jScrollPane1);
        jScrollPane1.setBounds(100, 260, 900, 170);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-person-male-skin-type-5-30.png"))); // NOI18N
        jLabel3.setText("INSTRUCTOR INFORMATION");
        pnlInstructor.add(jLabel3);
        jLabel3.setBounds(440, 10, 270, 30);
        pnlInstructor.add(dateHire);
        dateHire.setBounds(310, 202, 200, 30);

        jTabbedPane2.addTab("INSTRUCTOR", pnlInstructor);

        pnlStudent.setBackground(new java.awt.Color(204, 204, 255));
        pnlStudent.setForeground(new java.awt.Color(204, 204, 255));
        pnlStudent.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlStudent.setPreferredSize(new java.awt.Dimension(0, 0));
        pnlStudent.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("STUDENT INFORMATION");
        pnlStudent.add(jLabel6);
        jLabel6.setBounds(460, 10, 270, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Last Name:");
        pnlStudent.add(jLabel7);
        jLabel7.setBounds(200, 100, 100, 15);

        txtLastNameStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameStuActionPerformed(evt);
            }
        });
        pnlStudent.add(txtLastNameStu);
        txtLastNameStu.setBounds(310, 90, 202, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("First Name:");
        pnlStudent.add(jLabel8);
        jLabel8.setBounds(200, 150, 100, 15);
        pnlStudent.add(txtFirstNameStu);
        txtFirstNameStu.setBounds(310, 140, 202, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("EnrollmentDate:");
        pnlStudent.add(jLabel9);
        jLabel9.setBounds(200, 200, 100, 15);

        btnAddStu.setBackground(new java.awt.Color(204, 255, 204));
        btnAddStu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAddStu.setText("Add");
        btnAddStu.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAddStu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddStuMouseClicked(evt);
            }
        });
        pnlStudent.add(btnAddStu);
        btnAddStu.setBounds(640, 140, 100, 33);

        btnEditStu.setBackground(new java.awt.Color(204, 255, 204));
        btnEditStu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEditStu.setText("Edit");
        btnEditStu.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEditStu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditStuMouseClicked(evt);
            }
        });
        btnEditStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStuActionPerformed(evt);
            }
        });
        pnlStudent.add(btnEditStu);
        btnEditStu.setBounds(790, 140, 100, 33);

        btnDeleteStu.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteStu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDeleteStu.setText("Delete");
        btnDeleteStu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteStuMouseClicked(evt);
            }
        });
        btnDeleteStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStuActionPerformed(evt);
            }
        });
        pnlStudent.add(btnDeleteStu);
        btnDeleteStu.setBounds(640, 190, 100, 32);

        btnResetStu.setBackground(new java.awt.Color(204, 255, 204));
        btnResetStu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnResetStu.setText("Reset");
        btnResetStu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetStuMouseClicked(evt);
            }
        });
        pnlStudent.add(btnResetStu);
        btnResetStu.setBounds(790, 190, 100, 33);

        btnSearchStu.setBackground(new java.awt.Color(204, 255, 204));
        btnSearchStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearchStu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchStuMouseClicked(evt);
            }
        });
        btnSearchStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchStuActionPerformed(evt);
            }
        });
        pnlStudent.add(btnSearchStu);
        btnSearchStu.setBounds(850, 90, 40, 30);

        txtSearchStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchStuActionPerformed(evt);
            }
        });
        pnlStudent.add(txtSearchStu);
        txtSearchStu.setBounds(640, 90, 202, 30);

        tblStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "StudentID", "Last Name", "First Name", "Enrollment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblStudent);

        pnlStudent.add(jScrollPane2);
        jScrollPane2.setBounds(110, 260, 890, 180);
        pnlStudent.add(dateEnrollment);
        dateEnrollment.setBounds(310, 192, 200, 30);

        jTabbedPane2.addTab("STUDENT", pnlStudent);

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1561, Short.MAX_VALUE)
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("INSTRUCTOR ");

        jTabbedPane1.addTab("INFORMATION", new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-person-male-skin-type-5-30.png")), pnlInformation); // NOI18N

        pnlCourse.setBackground(new java.awt.Color(204, 204, 255));
        pnlCourse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCourse.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlCourse.setPreferredSize(new java.awt.Dimension(1500, 1000));

        jTabbedPane3.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane3.setForeground(new java.awt.Color(0, 153, 0));
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        pnlOnsite.setBackground(new java.awt.Color(204, 204, 255));
        pnlOnsite.setForeground(new java.awt.Color(0, 204, 51));
        pnlOnsite.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlOnsite.setPreferredSize(new java.awt.Dimension(0, 0));
        pnlOnsite.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-course-64 (2).png"))); // NOI18N
        jLabel19.setText("  ONSITE COURSE");
        pnlOnsite.add(jLabel19);
        jLabel19.setBounds(490, 10, 210, 60);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Course Title:");
        pnlOnsite.add(jLabel20);
        jLabel20.setBounds(200, 90, 80, 15);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Department Name:");
        pnlOnsite.add(jLabel22);
        jLabel22.setBounds(200, 170, 120, 10);

        btnSearch3.setBackground(new java.awt.Color(204, 255, 204));
        btnSearch3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearch3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearch3MouseClicked(evt);
            }
        });
        btnSearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch3ActionPerformed(evt);
            }
        });
        pnlOnsite.add(btnSearch3);
        btnSearch3.setBounds(860, 80, 40, 30);

        btnResetonsitecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnResetonsitecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetonsitecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnResetonsitecourse.setText("Reset");
        btnResetonsitecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetonsitecourseMouseClicked(evt);
            }
        });
        btnResetonsitecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetonsitecourseActionPerformed(evt);
            }
        });
        pnlOnsite.add(btnResetonsitecourse);
        btnResetonsitecourse.setBounds(800, 180, 100, 33);
        pnlOnsite.add(txtSearchonsitecourse);
        txtSearchonsitecourse.setBounds(650, 80, 202, 30);

        tblcourseonsite.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblcourseonsite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "Credits", "Department Name", "Location", "Days", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcourseonsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcourseonsiteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblcourseonsite);

        pnlOnsite.add(jScrollPane4);
        jScrollPane4.setBounds(100, 290, 900, 150);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Location:");
        pnlOnsite.add(jLabel23);
        jLabel23.setBounds(200, 210, 120, 15);
        pnlOnsite.add(txtdays);
        txtdays.setBounds(330, 240, 202, 30);

        jdepartmentname.setModel(new DefaultComboBoxModel(ComboboxDepartmentname()));
        pnlOnsite.add(jdepartmentname);
        jdepartmentname.setBounds(330, 160, 202, 30);

        txtcoursetitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoursetitleActionPerformed(evt);
            }
        });
        pnlOnsite.add(txtcoursetitle);
        txtcoursetitle.setBounds(330, 80, 202, 30);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Credits:");
        pnlOnsite.add(jLabel26);
        jLabel26.setBounds(200, 130, 80, 15);
        pnlOnsite.add(txtcredits);
        txtcredits.setBounds(330, 120, 202, 30);

        btnAddonsitecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnAddonsitecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddonsitecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAddonsitecourse.setText("Add");
        btnAddonsitecourse.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAddonsitecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddonsitecourseMouseClicked(evt);
            }
        });
        btnAddonsitecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddonsitecourseActionPerformed(evt);
            }
        });
        pnlOnsite.add(btnAddonsitecourse);
        btnAddonsitecourse.setBounds(650, 130, 100, 33);

        btnEditonsitecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnEditonsitecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditonsitecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEditonsitecourse.setText("Edit");
        btnEditonsitecourse.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEditonsitecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditonsitecourseMouseClicked(evt);
            }
        });
        btnEditonsitecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditonsitecourseActionPerformed(evt);
            }
        });
        pnlOnsite.add(btnEditonsitecourse);
        btnEditonsitecourse.setBounds(800, 130, 100, 33);

        btnDeleteonsitecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteonsitecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteonsitecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDeleteonsitecourse.setText("Delete");
        btnDeleteonsitecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteonsitecourseMouseClicked(evt);
            }
        });
        pnlOnsite.add(btnDeleteonsitecourse);
        btnDeleteonsitecourse.setBounds(650, 180, 100, 32);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Time:");
        pnlOnsite.add(jLabel25);
        jLabel25.setBounds(660, 250, 40, 15);
        pnlOnsite.add(txtlocation);
        txtlocation.setBounds(330, 200, 202, 30);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Days:");
        pnlOnsite.add(jLabel27);
        jLabel27.setBounds(200, 250, 40, 15);
        pnlOnsite.add(jSpinnerHour);
        jSpinnerHour.setBounds(720, 242, 64, 30);
        ((JSpinner.DefaultEditor)jSpinnerHour.getEditor()).getTextField().setEditable(false);
        pnlOnsite.add(jSpinnerMinutes);
        jSpinnerMinutes.setBounds(800, 242, 64, 30);
        ((JSpinner.DefaultEditor)jSpinnerMinutes.getEditor()).getTextField().setEditable(false);

        jTabbedPane3.addTab("ONSITE ", pnlOnsite);

        pnlOnline.setBackground(new java.awt.Color(204, 204, 255));
        pnlOnline.setForeground(new java.awt.Color(0, 204, 51));
        pnlOnline.setPreferredSize(new java.awt.Dimension(0, 0));
        pnlOnline.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-teacher-64.png"))); // NOI18N
        jLabel10.setText("ONLINE COURSE");
        pnlOnline.add(jLabel10);
        jLabel10.setBounds(480, 10, 210, 60);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("CourseTitle:");
        pnlOnline.add(jLabel11);
        jLabel11.setBounds(200, 90, 80, 15);

        txtcoursetitle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoursetitle2ActionPerformed(evt);
            }
        });
        pnlOnline.add(txtcoursetitle2);
        txtcoursetitle2.setBounds(330, 80, 202, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Credits:");
        pnlOnline.add(jLabel12);
        jLabel12.setBounds(200, 140, 80, 15);
        pnlOnline.add(txtcredits2);
        txtcredits2.setBounds(330, 130, 202, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Department Name:");
        pnlOnline.add(jLabel13);
        jLabel13.setBounds(200, 190, 120, 15);

        btnAddonlinecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnAddonlinecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddonlinecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAddonlinecourse.setText("Add");
        btnAddonlinecourse.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAddonlinecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddonlinecourseMouseClicked(evt);
            }
        });
        btnAddonlinecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddonlinecourseActionPerformed(evt);
            }
        });
        pnlOnline.add(btnAddonlinecourse);
        btnAddonlinecourse.setBounds(650, 140, 100, 33);

        btnEditonlinecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnEditonlinecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditonlinecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEditonlinecourse.setText("Edit");
        btnEditonlinecourse.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEditonlinecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditonlinecourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditonlinecourseMouseEntered(evt);
            }
        });
        btnEditonlinecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditonlinecourseActionPerformed(evt);
            }
        });
        pnlOnline.add(btnEditonlinecourse);
        btnEditonlinecourse.setBounds(800, 140, 100, 33);

        btnDeleteonlinecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteonlinecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteonlinecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDeleteonlinecourse.setText("Delete");
        btnDeleteonlinecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteonlinecourseMouseClicked(evt);
            }
        });
        pnlOnline.add(btnDeleteonlinecourse);
        btnDeleteonlinecourse.setBounds(650, 190, 100, 32);

        btnSearch2.setBackground(new java.awt.Color(204, 255, 204));
        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearch2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearch2MouseClicked(evt);
            }
        });
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        pnlOnline.add(btnSearch2);
        btnSearch2.setBounds(860, 90, 40, 30);

        btnResetonlinecourse.setBackground(new java.awt.Color(204, 255, 204));
        btnResetonlinecourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetonlinecourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnResetonlinecourse.setText("Reset");
        btnResetonlinecourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetonlinecourseMouseClicked(evt);
            }
        });
        btnResetonlinecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetonlinecourseActionPerformed(evt);
            }
        });
        pnlOnline.add(btnResetonlinecourse);
        btnResetonlinecourse.setBounds(800, 190, 100, 33);
        pnlOnline.add(txtSearchonlinecourse);
        txtSearchonlinecourse.setBounds(650, 90, 202, 30);

        tblecourseonline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblecourseonline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "Credits", "Frist Name", "Last Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblecourseonline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblecourseonlineMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblecourseonline);

        pnlOnline.add(jScrollPane3);
        jScrollPane3.setBounds(100, 290, 900, 150);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("URL:");
        pnlOnline.add(jLabel14);
        jLabel14.setBounds(200, 240, 120, 15);
        pnlOnline.add(txturl);
        txturl.setBounds(330, 230, 202, 30);

        jdpnameonline.setModel(new DefaultComboBoxModel(ComboboxDepartmentname()));
        pnlOnline.add(jdpnameonline);
        jdpnameonline.setBounds(330, 180, 202, 30);

        jTabbedPane3.addTab("ONLINE", pnlOnline);

        javax.swing.GroupLayout pnlCourseLayout = new javax.swing.GroupLayout(pnlCourse);
        pnlCourse.setLayout(pnlCourseLayout);
        pnlCourseLayout.setHorizontalGroup(
            pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlCourseLayout.setVerticalGroup(
            pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("COURSE", new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-classroom-30.png")), pnlCourse); // NOI18N

        pnlStudenGrade.setBackground(new java.awt.Color(204, 204, 255));
        pnlStudenGrade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlStudenGrade.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlStudenGrade.setPreferredSize(new java.awt.Dimension(1500, 1000));
        pnlStudenGrade.setLayout(null);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-grade-64.png"))); // NOI18N
        jLabel24.setText("STUDENT GRADE");
        pnlStudenGrade.add(jLabel24);
        jLabel24.setBounds(480, 10, 210, 60);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("PersonID:");
        pnlStudenGrade.add(jLabel31);
        jLabel31.setBounds(220, 100, 90, 15);

        btnSearch5.setBackground(new java.awt.Color(204, 255, 204));
        btnSearch5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearch5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearch5MouseClicked(evt);
            }
        });
        btnSearch5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch5ActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(btnSearch5);
        btnSearch5.setBounds(850, 90, 40, 30);

        btnReset5.setBackground(new java.awt.Color(204, 255, 204));
        btnReset5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnReset5.setText("Reset");
        btnReset5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReset5MouseClicked(evt);
            }
        });
        btnReset5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset5ActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(btnReset5);
        btnReset5.setBounds(790, 190, 100, 33);
        pnlStudenGrade.add(txtSearchStudentGrade);
        txtSearchStudentGrade.setBounds(640, 90, 202, 30);

        jScrollPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane6MouseClicked(evt);
            }
        });

        tblStudentGrade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblStudentGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "PersonID", "Course Title", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentGradeMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblStudentGrade);

        pnlStudenGrade.add(jScrollPane6);
        jScrollPane6.setBounds(100, 280, 900, 190);

        cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
        pnlStudenGrade.add(cbPersonID);
        cbPersonID.setBounds(320, 90, 202, 30);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("CourseTitle:");
        pnlStudenGrade.add(jLabel36);
        jLabel36.setBounds(220, 160, 90, 15);

        btnAdd8.setBackground(new java.awt.Color(204, 255, 204));
        btnAdd8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAdd8.setText("Add");
        btnAdd8.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAdd8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdd8MouseClicked(evt);
            }
        });
        btnAdd8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd8ActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(btnAdd8);
        btnAdd8.setBounds(640, 140, 100, 33);

        btnEdit8.setBackground(new java.awt.Color(204, 255, 204));
        btnEdit8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEdit8.setText("Edit");
        btnEdit8.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEdit8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit8MouseClicked(evt);
            }
        });
        btnEdit8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit8ActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(btnEdit8);
        btnEdit8.setBounds(790, 140, 100, 33);

        btnDelete8.setBackground(new java.awt.Color(204, 255, 204));
        btnDelete8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDelete8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDelete8.setText("Delete");
        btnDelete8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelete8MouseClicked(evt);
            }
        });
        pnlStudenGrade.add(btnDelete8);
        btnDelete8.setBounds(640, 190, 100, 32);

        cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
        cbCourseTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCourseTitleActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(cbCourseTitle);
        cbCourseTitle.setBounds(320, 150, 202, 30);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Grade:");
        pnlStudenGrade.add(jLabel28);
        jLabel28.setBounds(220, 220, 50, 15);

        txtGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGradeActionPerformed(evt);
            }
        });
        pnlStudenGrade.add(txtGrade);
        txtGrade.setBounds(320, 210, 202, 30);

        jTabbedPane1.addTab("STUDENT GRADE", new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-exam-30.png")), pnlStudenGrade); // NOI18N

        pnlCourseIntructor.setBackground(new java.awt.Color(204, 204, 255));
        pnlCourseIntructor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCourseIntructor.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlCourseIntructor.setPreferredSize(new java.awt.Dimension(1500, 1000));
        pnlCourseIntructor.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-course-64 (1).png"))); // NOI18N
        jLabel21.setText("COURSE INSTRUCTOR");
        pnlCourseIntructor.add(jLabel21);
        jLabel21.setBounds(450, 20, 230, 50);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Course Title:");
        pnlCourseIntructor.add(jLabel30);
        jLabel30.setBounds(200, 120, 90, 15);

        btnSearchCouIns.setBackground(new java.awt.Color(204, 255, 204));
        btnSearchCouIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-24.png"))); // NOI18N
        btnSearchCouIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchCouInsMouseClicked(evt);
            }
        });
        btnSearchCouIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCouInsActionPerformed(evt);
            }
        });
        pnlCourseIntructor.add(btnSearchCouIns);
        btnSearchCouIns.setBounds(830, 90, 40, 30);

        btnResetCouIns.setBackground(new java.awt.Color(204, 255, 204));
        btnResetCouIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetCouIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-available-updates-24.png"))); // NOI18N
        btnResetCouIns.setText("Reset");
        btnResetCouIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetCouInsMouseClicked(evt);
            }
        });
        btnResetCouIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCouInsActionPerformed(evt);
            }
        });
        pnlCourseIntructor.add(btnResetCouIns);
        btnResetCouIns.setBounds(770, 190, 100, 33);
        pnlCourseIntructor.add(txtSearchCouIns);
        txtSearchCouIns.setBounds(620, 90, 202, 30);

        tblCourseInstructor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCourseInstructor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "PersonID", "Frist Name", "Last Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCourseInstructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCourseInstructorMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCourseInstructor);

        pnlCourseIntructor.add(jScrollPane5);
        jScrollPane5.setBounds(100, 280, 900, 180);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("PersonID:");
        pnlCourseIntructor.add(jLabel35);
        jLabel35.setBounds(200, 190, 90, 15);

        btnAddCouIns.setBackground(new java.awt.Color(204, 255, 204));
        btnAddCouIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddCouIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-new-16.png"))); // NOI18N
        btnAddCouIns.setText("Add");
        btnAddCouIns.setPreferredSize(new java.awt.Dimension(80, 21));
        btnAddCouIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCouInsMouseClicked(evt);
            }
        });
        btnAddCouIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCouInsActionPerformed(evt);
            }
        });
        pnlCourseIntructor.add(btnAddCouIns);
        btnAddCouIns.setBounds(620, 140, 100, 33);

        btnEditCouIns.setBackground(new java.awt.Color(204, 255, 204));
        btnEditCouIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditCouIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-pencil-16.png"))); // NOI18N
        btnEditCouIns.setText("Edit");
        btnEditCouIns.setPreferredSize(new java.awt.Dimension(80, 33));
        btnEditCouIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCouInsMouseClicked(evt);
            }
        });
        btnEditCouIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCouInsActionPerformed(evt);
            }
        });
        pnlCourseIntructor.add(btnEditCouIns);
        btnEditCouIns.setBounds(770, 140, 100, 33);

        btnDeleteCouIns.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteCouIns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteCouIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-16.png"))); // NOI18N
        btnDeleteCouIns.setText("Delete");
        btnDeleteCouIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteCouInsMouseClicked(evt);
            }
        });
        pnlCourseIntructor.add(btnDeleteCouIns);
        btnDeleteCouIns.setBounds(620, 190, 100, 32);

        cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
        pnlCourseIntructor.add(cbPersonIdCouIns);
        cbPersonIdCouIns.setBounds(290, 180, 180, 30);

        cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
        pnlCourseIntructor.add(cbCourseTitleCouIns);
        cbCourseTitleCouIns.setBounds(290, 110, 180, 30);

        jTabbedPane1.addTab("COURSE INSTRUTOR", new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-calendar-30.png")), pnlCourseIntructor); // NOI18N

        pnlMain.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 80, 1100, 530);
        jTabbedPane1.getAccessibleContext().setAccessibleName("PERSON");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditInsActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_btnEditInsActionPerformed

    private void btnResetInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInsActionPerformed
        // TODO add your handling code here:
        txtFirstNameIns.setText("");
        txtLastNameIns.setText("");
        dateHire.setDate(null);
        readTableInstructor();
    }//GEN-LAST:event_btnResetInsActionPerformed

    private void btnSearchInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchInsActionPerformed

    private void txtLastNameInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameInsActionPerformed

    private void txtLastNameStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameStuActionPerformed

    private void btnEditStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditStuActionPerformed

    private void btnSearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch3ActionPerformed

    private void btnResetonsitecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetonsitecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetonsitecourseActionPerformed

    private void txtcoursetitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoursetitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoursetitleActionPerformed

    private void btnAddonsitecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddonsitecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddonsitecourseActionPerformed

    private void btnEditonsitecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditonsitecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditonsitecourseActionPerformed

    private void btnEditCouInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCouInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditCouInsActionPerformed

    private void btnAddCouInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCouInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCouInsActionPerformed

    private void btnResetCouInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCouInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetCouInsActionPerformed

    private void btnSearchCouInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCouInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchCouInsActionPerformed

    private void btnSearch5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch5ActionPerformed

    private void btnReset5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset5ActionPerformed

    private void btnAdd8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdd8ActionPerformed

    private void btnEdit8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEdit8ActionPerformed

    private void txtGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGradeActionPerformed

    private void btnResetonlinecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetonlinecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetonlinecourseActionPerformed

    private void btnEditonlinecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditonlinecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditonlinecourseActionPerformed

    private void btnAddonlinecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddonlinecourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddonlinecourseActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void txtcoursetitle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoursetitle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoursetitle2ActionPerformed

    private void txtSearchStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchStuActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        int i = tblStudent.getSelectedRow();
        int column = 1;
        if (i >= 0) {
            txtLastNameStu.setText(tblStudent.getModel().getValueAt(i, column++).toString());
            txtFirstNameStu.setText(tblStudent.getModel().getValueAt(i, column++).toString());
            String date = tblStudent.getModel().getValueAt(i, column++).toString();
            Date dateFormatted = null;
            try {
                dateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
            dateEnrollment.setDate(dateFormatted);
        }
    }//GEN-LAST:event_tblStudentMouseClicked

    private void tblInstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInstructorMouseClicked
        // TODO add your handling code here:
        int i = tblInstructor.getSelectedRow();
        int column = 1;
        if (i >= 0) {
            txtLastNameIns.setText(tblInstructor.getModel().getValueAt(i, column++).toString());
            txtFirstNameIns.setText(tblInstructor.getModel().getValueAt(i, column++).toString());
            String date = tblInstructor.getModel().getValueAt(i, column++).toString();
            Date dateFormatted = null;
            try {
                dateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
            dateHire.setDate(dateFormatted);
        }
    }//GEN-LAST:event_tblInstructorMouseClicked

    private void btnAddInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddInsMouseClicked
        // TODO add your handling code here:

        PersonDTO pDTO = new PersonDTO();        
        pDTO.setFirstName(txtFirstNameIns.getText());
        pDTO.setLastName(txtLastNameIns.getText());
        pDTO.setHireDate(formatType.format(dateHire.getDate()));

//        Vector row = new Vector();
//        row.add(pDTO.getPersonID());
//        row.add(pDTO.getLastName());
//        row.add(pDTO.getFirstName());
//        row.add(pDTO.getHireDate());
        if (pBUS.AddPerson(pDTO)) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
//            modelTableInstructor.addRow(row);
//            tblInstructor.setModel(modelTableInstructor);
            readTableInstructor();
            cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
            cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");

        }

    }//GEN-LAST:event_btnAddInsMouseClicked

    private void btnDeleteStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteStuActionPerformed

    private void btnDeleteStuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteStuMouseClicked
        // TODO add your handling code here:

        int i = tblStudent.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn học sinh cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá học sinh không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                int idStudent = Integer.parseInt(modelTableStudent.getValueAt(i, 0).toString());
                if (pBUS.DeleteStudent(idStudent)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    txtFirstNameStu.setText("");
                    txtLastNameStu.setText("");
                    dateEnrollment.setDate(null);
                    readTableStudent();
                    cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
                    cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                };
            }
        }
    }//GEN-LAST:event_btnDeleteStuMouseClicked

    private void btnEditStuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditStuMouseClicked
        // TODO add your handling code here:

        int i = tblStudent.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn học sinh cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật học sinh không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                PersonDTO pDTO = new PersonDTO();
                pDTO.setPersonID(Integer.parseInt(modelTableStudent.getValueAt(i, 0).toString()));
                pDTO.setFirstName(txtFirstNameStu.getText());
                pDTO.setLastName(txtLastNameStu.getText());
                pDTO.setEnrollmentDate(formatType.format(dateEnrollment.getDate()));
                if (pBUS.UpdatePerson(pDTO)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    txtFirstNameStu.setText("");
                    txtLastNameStu.setText("");
                    dateEnrollment.setDate(null);
                    readTableStudent();
                    cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
                    cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");

                }
            }
        }
    }//GEN-LAST:event_btnEditStuMouseClicked

    private void btnResetStuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetStuMouseClicked
        // TODO add your handling code here:
        txtFirstNameStu.setText("");
        txtLastNameStu.setText("");
        dateEnrollment.setDate(null);
        txtSearchStu.setText("");
        readTableStudent();
    }//GEN-LAST:event_btnResetStuMouseClicked

    private void btnAddStuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddStuMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm học sinh không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            PersonDTO pDTO = new PersonDTO();            
            pDTO.setFirstName(txtFirstNameStu.getText());
            pDTO.setLastName(txtLastNameStu.getText());
            pDTO.setEnrollmentDate(formatType.format(dateEnrollment.getDate()));
//        Vector row = new Vector();
//        row.add(pDTO.getPersonID());
//        row.add(pDTO.getLastName());
//        row.add(pDTO.getFirstName());
//        row.add(pDTO.getHireDate());
            if (pBUS.AddPerson(pDTO)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
//            modelTableInstructor.addRow(row);
//            tblInstructor.setModel(modelTableInstructor);
                readTableStudent();
                cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
                cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }

    }//GEN-LAST:event_btnAddStuMouseClicked

    private void btnSearchStuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchStuMouseClicked
        // TODO add your handling code here:        
        String input = txtSearchStu.getText();
        System.out.println(input);
        if (!input.isEmpty()) {
            try {
                ArrayList<PersonDTO> listPerson = pBUS.findPersonByName(input);
                if (!listPerson.isEmpty()) {
                    modelTableStudent.setRowCount(0);
                    for (PersonDTO p : listPerson) {
                        if (p.getHireDate() == null) {
                            modelTableStudent.addRow(new Object[]{
                                p.getPersonID(), p.getLastName(), p.getFirstName(), p.getEnrollmentDate()
                            });
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Tìm thấy rồi");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể tìm thấy");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tìm thấy");
        }


    }//GEN-LAST:event_btnSearchStuMouseClicked

    private void btnAddInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInsActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_btnAddInsActionPerformed

    private void btnDeleteInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInsActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_btnDeleteInsActionPerformed

    private void btnSearchStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchStuActionPerformed

    private void btnSearchInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchInsMouseClicked
        // TODO add your handling code here:        
        String input = txtSearchIns.getText();
        System.out.println(input);
        if (!input.isEmpty()) {
            try {
                ArrayList<PersonDTO> listPerson = pBUS.findPersonByName(input);
                if (!listPerson.isEmpty()) {
                    modelTableInstructor.setRowCount(0);
                    for (PersonDTO p : listPerson) {
                        if (p.getEnrollmentDate() == null) {
                            modelTableInstructor.addRow(new Object[]{
                                p.getPersonID(), p.getLastName(), p.getFirstName(), p.getHireDate()
                            });
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Tìm thấy rồi");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể tìm thấy");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thiếu Thông Tin");
        }

    }//GEN-LAST:event_btnSearchInsMouseClicked

    private void btnEditInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditInsMouseClicked
        // TODO add your handling code here:
        int i = tblInstructor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn giảng viên cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật giảng viên không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                PersonDTO pDTO = new PersonDTO();
                pDTO.setPersonID(Integer.parseInt(modelTableInstructor.getValueAt(i, 0).toString()));
                pDTO.setFirstName(txtFirstNameIns.getText());
                pDTO.setLastName(txtLastNameIns.getText());
                pDTO.setHireDate(formatType.format(dateHire.getDate()));
                if (pBUS.UpdatePerson(pDTO)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    txtFirstNameIns.setText("");
                    txtLastNameIns.setText("");
                    dateHire.setDate(null);
                    readTableInstructor();
                    cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
                    cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");

                }
            }
        }
    }//GEN-LAST:event_btnEditInsMouseClicked

    private void btnDeleteInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteInsMouseClicked
        // TODO add your handling code here:
        int i = tblInstructor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn giảng viên cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá giảng viên không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                int idInstructor = Integer.parseInt(modelTableInstructor.getValueAt(i, 0).toString());
                if (pBUS.DeleteInstructor(idInstructor)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    txtFirstNameIns.setText("");
                    txtLastNameIns.setText("");
                    dateHire.setDate(null);
                    readTableInstructor();
                    cbPersonID.setModel(new DefaultComboBoxModel(ComboBoxPersonID()));
                    cbPersonIdCouIns.setModel(new DefaultComboBoxModel(ComboBoxPersonInstructorID()));
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                };
            }
        }
    }//GEN-LAST:event_btnDeleteInsMouseClicked

    private void btnReset5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReset5MouseClicked
        // TODO add your handling code here:
        readTableStudentGrade();
        txtGrade.setText("");
        txtSearchStudentGrade.setText("");
    }//GEN-LAST:event_btnReset5MouseClicked

    private void btnAdd8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdd8MouseClicked
        // TODO add your handling code here:
//        comboBox_tenhang.getSelectedItem().toString()
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm điểm cho học sinh này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        StudentGradeDTO stDTO = new StudentGradeDTO();
        stDTO.setPersonID(Integer.parseInt(cbPersonID.getSelectedItem().toString()));
        stDTO.setCourseTitle(cbCourseTitle.getSelectedItem().toString());
        stDTO.setGrade(Float.parseFloat(txtGrade.getText()));        
        try {
            if (sgBUS.AddStudentGradeList(stDTO)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                readTableStudentGrade();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAdd8MouseClicked

    private void jScrollPane6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane6MouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_jScrollPane6MouseClicked

    private void tblStudentGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentGradeMouseClicked
        // TODO add your handling code here:
        int i = tblStudentGrade.getSelectedRow();
        if (i >= 0) {
            cbPersonID.setSelectedItem(tblStudentGrade.getModel().getValueAt(i, 0).toString());
            cbCourseTitle.setSelectedItem(tblStudentGrade.getModel().getValueAt(i, 1).toString());
            txtGrade.setText(tblStudentGrade.getModel().getValueAt(i, 2).toString());
        }
    }//GEN-LAST:event_tblStudentGradeMouseClicked

    private void btnEdit8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit8MouseClicked
        // TODO add your handling code here:   
        int i = tblStudentGrade.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn học sinh cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật học sinh này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                StudentGradeDTO sgDTO = new StudentGradeDTO();
                String oldCourseTitle = tblStudentGrade.getModel().getValueAt(i, 1).toString();
                int oldIdPerson = Integer.parseInt(tblStudentGrade.getModel().getValueAt(i, 0).toString());
                sgDTO.setCourseTitle(oldCourseTitle);
                sgDTO.setPersonID(oldIdPerson);
                sgDTO.setGrade(Float.parseFloat(txtGrade.getText()));
                try {
                    if (sgBUS.UpdateStudentGrade(sgDTO)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                        readTableStudentGrade();
                    } else {                    
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnEdit8MouseClicked

    private void btnSearch5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch5MouseClicked
        // TODO add your handling code here:        
        String input = txtSearchStudentGrade.getText();
        System.out.println(input);
        if (!input.isEmpty()) {
            try {
                ArrayList<StudentGradeDTO> listStudentGrade = sgBUS.findByID(input);
                if (!listStudentGrade.isEmpty()) {
                    modelTableStudentGrade.setRowCount(0);
                    for (StudentGradeDTO sgDTO : listStudentGrade) {
                        modelTableStudentGrade.addRow(new Object[]{
                            sgDTO.getPersonID(), sgDTO.getCourseTitle(), sgDTO.getGrade()
                        });
                    }
                    JOptionPane.showMessageDialog(null, "Tìm thấy rồi");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể tìm thấy");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thiếu Thông Tin");
        }
    }//GEN-LAST:event_btnSearch5MouseClicked

    private void cbCourseTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCourseTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCourseTitleActionPerformed

    private void tblcourseonsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcourseonsiteMouseClicked
        // TODO add your handling code here:
        int i = tblcourseonsite.getSelectedRow();
        if (i >= 0) {
            txtcoursetitle.setText(tblcourseonsite.getModel().getValueAt(i, 1).toString());
            txtcredits.setText(tblcourseonsite.getModel().getValueAt(i, 2).toString());
            jdepartmentname.setSelectedItem(tblcourseonsite.getModel().getValueAt(i, 3).toString());
            txtlocation.setText(tblcourseonsite.getModel().getValueAt(i, 4).toString());
            txtdays.setText(tblcourseonsite.getModel().getValueAt(i, 5).toString());
            String temp = tblcourseonsite.getModel().getValueAt(i, 6).toString();
            String[] atemp = temp.split(":");
            jSpinnerHour.setValue(Integer.parseInt(atemp[0]));
            jSpinnerMinutes.setValue(Integer.parseInt(atemp[1]));
        }
    }//GEN-LAST:event_tblcourseonsiteMouseClicked

    private void tblecourseonlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblecourseonlineMouseClicked
        int i = tblecourseonline.getSelectedRow();
        if (i >= 0) {
            txtcoursetitle2.setText(tblecourseonline.getModel().getValueAt(i, 1).toString());
            txtcredits2.setText(tblecourseonline.getModel().getValueAt(i, 2).toString());
            jdpnameonline.setSelectedItem(tblecourseonline.getModel().getValueAt(i, 3).toString());
            txturl.setText(tblecourseonline.getModel().getValueAt(i, 4).toString());
        }
    }//GEN-LAST:event_tblecourseonlineMouseClicked

    private void btnAddonlinecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddonlinecourseMouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm courseonline này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }        
        CourseDTO cDTO = new CourseDTO();
        int temp = 0;
        for (DepartmentDTO dpDTO : DepartmentBUS.dpList) {
            if (dpDTO.getName() == jdpnameonline.getSelectedItem()) {
                temp = dpDTO.getDepartmentID();
                break;
            }
        }
        cDTO.setTitle(txtcoursetitle2.getText().toString());
        cDTO.setCredits(Integer.parseInt(txtcredits2.getText()));
        cDTO.setDepartmentID(temp);
        cDTO.setURL(txturl.getText());
        try {
            if (cBUS.addcourseonline(cDTO)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                readTableOnlineCourse();
                cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddonlinecourseMouseClicked

    private void btnEditonlinecourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditonlinecourseMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditonlinecourseMouseEntered

    private void btnEditonlinecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditonlinecourseMouseClicked

        int i = tblecourseonline.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật course không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                CourseDTO cDTO = new CourseDTO();
                int temp = 0;
                for (DepartmentDTO dpDTO : DepartmentBUS.dpList) {
                    if (dpDTO.getName() == jdpnameonline.getSelectedItem()) {
                        temp = dpDTO.getDepartmentID();
                        break;
                    }
                }
                cDTO.setCourseID(Integer.parseInt(tblecourseonline.getValueAt(i, 0).toString()));
                cDTO.setTitle(txtcoursetitle2.getText().toString());
                cDTO.setCredits(Integer.parseInt(txtcredits2.getText()));
                cDTO.setDepartmentID(temp);
                cDTO.setURL(txturl.getText());
                if (cBUS.updateonlinecourse(cDTO)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    txtcoursetitle2.setText("");
                    txtcredits2.setText("");
                    txturl.setText("");
                    readTableOnlineCourse();
                    cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                    cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");

                }
            }
        }
    }//GEN-LAST:event_btnEditonlinecourseMouseClicked

    private void btnDeleteonlinecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteonlinecourseMouseClicked
        // TODO add your handling code here:
        int i = tblecourseonline.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá course không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                int idcourse = Integer.parseInt(tblecourseonline.getValueAt(i, 0).toString());
                try {
                    if (cBUS.deleteonlinecourse(idcourse)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        txtcoursetitle2.setText("");
                        txtcredits2.setText("");
                        txturl.setText("");
                        readTableOnlineCourse();
                        cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                        cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteonlinecourseMouseClicked

    private void btnResetonlinecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetonlinecourseMouseClicked
        // TODO add your handling code here:
        readTableOnlineCourse();
    }//GEN-LAST:event_btnResetonlinecourseMouseClicked

    private void btnSearch2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch2MouseClicked
        // TODO add your handling code here
        String inputsearch = txtSearchonlinecourse.getText();        
        ArrayList<CourseDTO> rssearch = new ArrayList<>();
        try {
            rssearch = cBUS.findIDonline(inputsearch);
            modelTableCourseOnline.setRowCount(0);
            for (CourseDTO cdto : rssearch) {
                if (cdto.getURL() != null) {
                    modelTableCourseOnline.addRow(new Object[]{
                        cdto.getCourseID(), cdto.getTitle(), cdto.getCredits(), cdto.getDepartmentName(), cdto.getURL()
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch2MouseClicked

    private void btnSearch3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch3MouseClicked
        // TODO add your handling code here:
        String inputsearch = txtSearchonsitecourse.getText();        
        ArrayList<CourseDTO> rssearch = new ArrayList<>();
        try {
            rssearch = cBUS.findIDonsite(inputsearch);
            modelTableCourseOnsite.setRowCount(0);
            for (CourseDTO cdto : rssearch) {
                if (cdto.getLocation() != null) {
                    modelTableCourseOnsite.addRow(new Object[]{
                        cdto.getCourseID(), cdto.getTitle(), cdto.getCredits(), cdto.getDepartmentName(), cdto.getLocation(), cdto.getDays(), cdto.getTime()
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch3MouseClicked

    private void btnResetonsitecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetonsitecourseMouseClicked
        // TODO add your handling code here:
        readTableOnsiteCourse();
    }//GEN-LAST:event_btnResetonsitecourseMouseClicked

    private void btnDeleteonsitecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteonsitecourseMouseClicked
        // TODO add your handling code here:
        int i = tblcourseonsite.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá course không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                int idcourse = Integer.parseInt(tblcourseonsite.getValueAt(i, 0).toString());
                try {
                    if (cBUS.deleteonsitecourse(idcourse)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        txtcoursetitle.setText("");
                        txtcredits.setText("");
                        txtlocation.setText("");
                        txtdays.setText("");
                        readTableOnsiteCourse();
                        cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                        cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteonsitecourseMouseClicked

    private void btnAddonsitecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddonsitecourseMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm courseonsite này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }        
        CourseDTO cDTO = new CourseDTO();
        int temp = 0;
        for (DepartmentDTO dpDTO : DepartmentBUS.dpList) {
            if (dpDTO.getName().equals(jdepartmentname.getSelectedItem())) {
                temp = dpDTO.getDepartmentID();
                break;
            }
        }
        String timecourse = jSpinnerHour.getValue().toString() + ":" + jSpinnerMinutes.getValue().toString() + ":" + "00";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date temp2 = null;
        try {
            temp2 = dateFormat.parse(timecourse);
        } catch (ParseException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        cDTO.setTitle(txtcoursetitle.getText().toString());
        cDTO.setCredits(Integer.parseInt(txtcredits.getText()));
        cDTO.setDepartmentID(temp);
        cDTO.setLocation(txtlocation.getText());
        cDTO.setDays(txtdays.getText());
        cDTO.setTime(temp2);
        try {
            if (cBUS.addcourseonsite(cDTO)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                readTableOnsiteCourse();
                cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddonsitecourseMouseClicked

    private void btnEditonsitecourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditonsitecourseMouseClicked
        // TODO add your handling code here:
        int i = tblcourseonsite.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật course không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                CourseDTO cDTO = new CourseDTO();
                int temp = 0;
                for (DepartmentDTO dpDTO : DepartmentBUS.dpList) {
                    if (dpDTO.getName().equals(jdepartmentname.getSelectedItem())) {
                        temp = dpDTO.getDepartmentID();
                        break;
                    }
                }
                String timecourse = jSpinnerHour.getValue().toString() + ":" + jSpinnerMinutes.getValue().toString() + ":" + "00";
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date temp2 = null;
                try {
                    temp2 = dateFormat.parse(timecourse);
                } catch (ParseException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
                cDTO.setCourseID(Integer.parseInt(tblcourseonsite.getValueAt(i, 0).toString()));
                cDTO.setTitle(txtcoursetitle.getText().toString());
                cDTO.setCredits(Integer.parseInt(txtcredits.getText()));
                cDTO.setDepartmentID(temp);
                cDTO.setLocation(txtlocation.getText().toString());
                cDTO.setDays(txtdays.getText().toString());
                cDTO.setTime(temp2);
                if (cBUS.updateonsitecourse(cDTO)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    txtcoursetitle.setText("");
                    txtcredits.setText("");
                    txtlocation.setText("");
                    txtdays.setText("");
                    jSpinnerHour.setValue(0);
                    jSpinnerMinutes.setValue(0);
                    readTableOnsiteCourse();
                    cbCourseTitle.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                    cbCourseTitleCouIns.setModel(new DefaultComboBoxModel(ComboBoxCourseTitle()));
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");

                }
            }
        }
    }//GEN-LAST:event_btnEditonsitecourseMouseClicked

    private void tblCourseInstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCourseInstructorMouseClicked
        // TODO add your handling code here:
        int i = tblCourseInstructor.getSelectedRow();
        if (i >= 0) {
            System.out.println(tblCourseInstructor.getModel().getValueAt(i, 1).toString());
            cbCourseTitleCouIns.setSelectedItem(tblCourseInstructor.getModel().getValueAt(i, 1).toString());
            cbPersonIdCouIns.setSelectedItem(tblCourseInstructor.getModel().getValueAt(i, 2).toString());
        }
    }//GEN-LAST:event_tblCourseInstructorMouseClicked

    private void btnAddCouInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCouInsMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn phân công cho giảng viên này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        CourseInstructorDTO ciDTO = new CourseInstructorDTO();
        ciDTO.setPersonID(Integer.parseInt(cbPersonIdCouIns.getSelectedItem().toString()));
        ciDTO.setTitleCourse(cbCourseTitleCouIns.getSelectedItem().toString());
        ciDTO.setFirstName("");
        ciDTO.setLastName("");
        ciDTO.setCourseID(0);        
        try {
            if (ciBUS.AddCourseInstructor(ciDTO)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                readTableCourseInstrcutor();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddCouInsMouseClicked

    private void btnDeleteCouInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteCouInsMouseClicked
        // TODO add your handling code here:
        int i = tblCourseInstructor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá lịch phân công này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                int idPerson = Integer.parseInt(cbPersonIdCouIns.getSelectedItem().toString());
                String courseTitle = cbCourseTitleCouIns.getSelectedItem().toString();
                try {
                    if (ciBUS.deletecourse(idPerson, courseTitle)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        readTableCourseInstrcutor();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteCouInsMouseClicked

    private void btnEditCouInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCouInsMouseClicked
        // TODO add your handling code here:
        int i = tblCourseInstructor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn lịch cần cập nhật");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật lịch không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                CourseInstructorDTO ciDTO = new CourseInstructorDTO();
                String oldCourseTitle = tblCourseInstructor.getModel().getValueAt(i, 1).toString();
                int oldIdPerson = Integer.parseInt(tblCourseInstructor.getModel().getValueAt(i, 2).toString());

                ciDTO.setCourseID(0);
                ciDTO.setFirstName("");
                ciDTO.setLastName("");
                ciDTO.setTitleCourse(cbCourseTitleCouIns.getSelectedItem().toString());
                ciDTO.setPersonID(Integer.parseInt(cbPersonIdCouIns.getSelectedItem().toString()));
                try {
                    if (ciBUS.UpdateCourseInstructor(ciDTO, oldIdPerson, oldCourseTitle)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                        readTableCourseInstrcutor();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnEditCouInsMouseClicked

    private void btnSearchCouInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchCouInsMouseClicked
        // TODO add your handling code here:        
        String input = txtSearchCouIns.getText();
        System.out.println(input);
        if (!input.isEmpty()) {
            try {
                ArrayList<CourseInstructorDTO> listCourseInstructor = ciBUS.findCourseInstructorByTitle(input);
                if (!listCourseInstructor.isEmpty()) {
                    modelTableCourseInstructor.setRowCount(0);
                    for (CourseInstructorDTO ciDTO : listCourseInstructor) {

                        modelTableCourseInstructor.addRow(new Object[]{
                            ciDTO.getCourseID(), ciDTO.getTitleCourse(), ciDTO.getPersonID(), ciDTO.getFirstName(), ciDTO.getLastName()
                        });

                    }
                    JOptionPane.showMessageDialog(null, "Tìm thấy rồi");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể tìm thấy");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thiếu Thông Tin");
        }
    }//GEN-LAST:event_btnSearchCouInsMouseClicked

    private void btnResetCouInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetCouInsMouseClicked
        // TODO add your handling code here:
        readTableCourseInstrcutor();
    }//GEN-LAST:event_btnResetCouInsMouseClicked

    private void btnDelete8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelete8MouseClicked
        // TODO add your handling code here:
        int i = tblStudentGrade.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn điểm của học sinh cần xoá");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá điểm của học sinh này không?", "Chấp thuận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {                
                String CourseTitle = tblStudentGrade.getModel().getValueAt(i, 1).toString();
                int IdPerson = Integer.parseInt(tblStudentGrade.getModel().getValueAt(i, 0).toString());                                
                try {
                    if (sgBUS.DeleteStudentGrade(IdPerson, CourseTitle)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        readTableStudentGrade();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDelete8MouseClicked
//

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd8;
    private javax.swing.JButton btnAddCouIns;
    private javax.swing.JButton btnAddIns;
    private javax.swing.JButton btnAddStu;
    private javax.swing.JButton btnAddonlinecourse;
    private javax.swing.JButton btnAddonsitecourse;
    private javax.swing.JButton btnDelete8;
    private javax.swing.JButton btnDeleteCouIns;
    private javax.swing.JButton btnDeleteIns;
    private javax.swing.JButton btnDeleteStu;
    private javax.swing.JButton btnDeleteonlinecourse;
    private javax.swing.JButton btnDeleteonsitecourse;
    private javax.swing.JButton btnEdit8;
    private javax.swing.JButton btnEditCouIns;
    private javax.swing.JButton btnEditIns;
    private javax.swing.JButton btnEditStu;
    private javax.swing.JButton btnEditonlinecourse;
    private javax.swing.JButton btnEditonsitecourse;
    private javax.swing.JButton btnReset5;
    private javax.swing.JButton btnResetCouIns;
    private javax.swing.JButton btnResetIns;
    private javax.swing.JButton btnResetStu;
    private javax.swing.JButton btnResetonlinecourse;
    private javax.swing.JButton btnResetonsitecourse;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSearch3;
    private javax.swing.JButton btnSearch5;
    private javax.swing.JButton btnSearchCouIns;
    private javax.swing.JButton btnSearchIns;
    private javax.swing.JButton btnSearchStu;
    private javax.swing.JComboBox<String> cbCourseTitle;
    private javax.swing.JComboBox<String> cbCourseTitleCouIns;
    private javax.swing.JComboBox<String> cbPersonID;
    private javax.swing.JComboBox<String> cbPersonIdCouIns;
    private com.toedter.calendar.JDateChooser dateEnrollment;
    private com.toedter.calendar.JDateChooser dateHire;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinnerHour;
    private javax.swing.JSpinner jSpinnerMinutes;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JComboBox<String> jdepartmentname;
    private javax.swing.JComboBox<String> jdpnameonline;
    private javax.swing.JPanel pnlCourse;
    private javax.swing.JPanel pnlCourseIntructor;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JPanel pnlInstructor;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlOnline;
    private javax.swing.JPanel pnlOnsite;
    private javax.swing.JPanel pnlStudenGrade;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTable tblCourseInstructor;
    private javax.swing.JTable tblInstructor;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTable tblStudentGrade;
    private javax.swing.JTable tblcourseonsite;
    private javax.swing.JTable tblecourseonline;
    private javax.swing.JTextField txtFirstNameIns;
    private javax.swing.JTextField txtFirstNameStu;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtLastNameIns;
    private javax.swing.JTextField txtLastNameStu;
    private javax.swing.JTextField txtSearchCouIns;
    private javax.swing.JTextField txtSearchIns;
    private javax.swing.JTextField txtSearchStu;
    private javax.swing.JTextField txtSearchStudentGrade;
    private javax.swing.JTextField txtSearchonlinecourse;
    private javax.swing.JTextField txtSearchonsitecourse;
    private javax.swing.JTextField txtcoursetitle;
    private javax.swing.JTextField txtcoursetitle2;
    private javax.swing.JTextField txtcredits;
    private javax.swing.JTextField txtcredits2;
    private javax.swing.JTextField txtdays;
    private javax.swing.JTextField txtlocation;
    private javax.swing.JTextField txturl;
    // End of variables declaration//GEN-END:variables
}
