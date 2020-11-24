import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class PomGen {
    private JCheckBox checkJavaFX;
    private JCheckBox checkLombok;
    private JCheckBox checkCdi;
    private JCheckBox checkJdbc;
    private JCheckBox checkSpring;
    private JCheckBox checklog4J;
    private JCheckBox checkYaml;
    private JCheckBox checkHikari;
    private JCheckBox check1;
    private JCheckBox check2;
    private JCheckBox check3;
    
    
    private JTextField fieldGroup;
    private JTextField fieldArtifact;

    private JTextArea output;

    public PomGen(Rectangle windowData)  {
        JFrame f = new JFrame("PomGen");
        JLabel labelJavaFx = new JLabel();
        JLabel labelLombok = new JLabel();
        JLabel labelCdi = new JLabel();
        JLabel labelProjectData = new JLabel();
        JLabel labelJdbc = new JLabel();
        JLabel labelSpring = new JLabel();
        JLabel labelLog4j = new JLabel();
        JLabel labelYaml = new JLabel();
        JLabel labelHikari = new JLabel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();

        output = new JTextArea();
        JScrollPane scroll = new JScrollPane (output);

        labelJavaFx.setText("JavaFX");
        labelLombok.setText("Lombok");
        labelCdi.setText("CDI");
        labelProjectData.setText("Project Data");
        labelJdbc.setText("JDBC");
        labelSpring.setText("Spring");
        labelLog4j.setText("Log4J");
        labelYaml.setText("Snake Yaml");
        labelHikari.setText("Hikari Pool");
        label1.setText("1");
        label2.setText("2");
        label3.setText("3");
        
        checkJavaFX = new JCheckBox();
        checkLombok = new JCheckBox();
        checkCdi = new JCheckBox();
        checkJdbc = new JCheckBox();
        checkSpring = new JCheckBox();
        checklog4J = new JCheckBox();
        checkYaml = new JCheckBox();
        checkHikari = new JCheckBox();
        check1 = new JCheckBox();
        check2 = new JCheckBox();
        check3 = new JCheckBox();

        fieldGroup = new JTextField();
        fieldArtifact = new JTextField();

        TextPrompt placeholderArtifact = new TextPrompt("Artifact name", fieldArtifact);
        placeholderArtifact.changeAlpha(0.75f);
        placeholderArtifact.changeStyle(Font.ITALIC);

        TextPrompt placeholderGroup = new TextPrompt("Group name", fieldGroup);
        placeholderGroup.changeAlpha(0.75f);
        placeholderGroup.changeStyle(Font.ITALIC);

        labelProjectData.setBounds(20, 20, 100, 20);
        labelLombok.setBounds(120, 20, 100, 20);
        labelJavaFx.setBounds(220, 20, 100, 20);
        labelCdi.setBounds(320, 20, 100, 20);
        labelJdbc.setBounds(420, 20, 100, 20);
        labelSpring.setBounds(520, 20, 100, 20);
        labelLog4j.setBounds(20, 80, 100, 20);
        labelYaml.setBounds(120, 80, 100, 20);
        labelHikari.setBounds(220, 80, 100, 20);
        label1.setBounds(320, 80, 100, 20);
        label2.setBounds(420, 80, 100, 20);
        label3.setBounds(520, 80, 100, 20);

        checkLombok.setBounds(120, 40, 20, 20);
        checkJavaFX.setBounds(220, 40, 20, 20);
        checkCdi.setBounds(320, 40, 20, 20);
        checkJdbc.setBounds(420, 40, 100, 20);
        checkSpring.setBounds(520, 40, 100, 20);
        checklog4J.setBounds(20, 100, 100, 20);
        checkYaml.setBounds(120, 100, 100, 20);
        checkHikari.setBounds(220, 100, 100, 20);
        check1.setBounds(320, 100, 100, 20);
        check2.setBounds(420, 100, 100, 20);
        check3.setBounds(520, 100, 100, 20);

        fieldArtifact.setBounds(20, 60, 90, 20);
        fieldGroup.setBounds(20, 40, 90, 20);

        scroll.setBounds(5, 130, 600, 350);

        Vector<Component> components = new Vector<Component>(7);
        components.add(fieldArtifact);
        components.add(fieldGroup);

        f.setFocusTraversalPolicy(new CustomFocusTraversalPolicy(components));
        f.setResizable(false);

        f.add(labelProjectData);
        f.add(labelJavaFx);
        f.add(labelLombok);
        f.add(labelCdi);
        f.add(labelJdbc);
        f.add(labelSpring);
        f.add(labelLog4j);
        f.add(labelYaml);
        f.add(labelHikari);
//        f.add(label1);
//        f.add(label2);
//        f.add(label3);
        
        f.add(fieldArtifact);
        f.add(fieldGroup);
        f.add(checkJavaFX);
        f.add(checkLombok);
        f.add(checkCdi);
        f.add(checkJdbc);
        f.add(checkSpring);
        f.add(checklog4J);
        f.add(checkYaml);
        f.add(checkHikari);
//        f.add(check1);
//        f.add(check2);
//        f.add(check3);

        scroll.getViewport().setBackground(Color.white);
        output.setEditable(false);
        f.add(scroll);
        f.setBounds(windowData);
        f.setLayout(null);
        f.setVisible(true);
        
        updatePom();

        fieldGroup.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updatePom();
            }
        });

        fieldArtifact.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updatePom();
            }
        });

        checkJavaFX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        checkLombok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        checkCdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });
        
        checkJdbc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });
        
        checkSpring.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });
        
        checklog4J.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });
        
        checkYaml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        checkHikari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        check1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        check2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        check3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePom();
            }
        });

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                GenLauncher launcher = new GenLauncher(f.getBounds());
                f.dispose();
            }
        });

    }

    private void updatePom() {
        output.setText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>"+fieldGroup.getText()+"</groupId>\n" +
                "    <artifactId>"+fieldArtifact.getText()+"</artifactId>\n" +
                "\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "\n" +
                "    <properties>\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "        <maven.compiler.source>11</maven.compiler.source>\n" +
                "        <maven.compiler.target>11</maven.compiler.target>\n" +
                "    </properties>\n" +
                "    <dependencies>\n");
                if (checkLombok.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>org.projectlombok</groupId>\n" +
                            "            <artifactId>lombok</artifactId>\n" +
                            "            <version>1.18.12</version>\n" +
                            "        </dependency>\n");
                }
                if (checkJavaFX.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>org.openjfx</groupId>\n" +
                            "            <artifactId>javafx-base</artifactId>\n" +
                            "            <version>11.0.1</version>\n" +
                            "        </dependency>\n" +
                            "\n        <dependency>\n" +
                            "            <groupId>org.openjfx</groupId>\n" +
                            "            <artifactId>javafx-fxml</artifactId>\n" +
                            "            <version>11.0.1</version>\n" +
                            "        </dependency>\n" +
                            "        <dependency>\n");
                }
		        if (checkCdi.isSelected())
		        {
		            output.append("\n        <dependency>\n" +
		                    "            <groupId>org.jboss.weld.se</groupId>\n" +
		                    "            <artifactId>weld-se-core</artifactId>\n" +
		                    "            <version>3.1.4.Final</version>\n" +
		                    "        </dependency>\n");
		        }
		        if (checkJdbc.isSelected())
		        {
		            output.append("\n        <dependency>\n" +
                            "            <groupId>mysql</groupId>\n" +
                            "            <artifactId>mysql-connector-java</artifactId>\n" +
                            "            <version>8.0.18</version>\n" +
                            "        </dependency>\n");
		        }
                if (checkSpring.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>org.springframework</groupId>\n" +
                            "            <artifactId>spring-jdbc</artifactId>\n" +
                            "            <version>5.3.0</version>\n" +
                            "        </dependency>\n");
                }
                if (checklog4J.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>org.apache.logging.log4j</groupId>\n" +
                            "            <artifactId>log4j-api</artifactId>\n" +
                            "            <version>2.13.3</version>\n" +
                            "        </dependency>\n" +
                            "\n        <dependency>\n" +
                            "            <groupId>org.apache.logging.log4j</groupId>\n" +
                            "            <artifactId>log4j-core</artifactId>\n" +
                            "            <version>2.13.3</version>\n" +
                            "        </dependency>\n" +
                            "\n        <dependency>\n" +
                            "            <groupId>org.slf4j</groupId>\n" +
                            "            <artifactId>slf4j-api</artifactId>\n" +
                            "            <version>1.7.30</version>\n" +
                            "        </dependency>\n" +
                            "\n        <dependency>\n" +
                            "            <groupId>org.apache.logging.log4j</groupId>\n" +
                            "            <artifactId>log4j-slf4j-impl</artifactId>\n" +
                            "            <version>2.13.3</version>\n" +
                            "        </dependency>\n");
                }
                if (checkYaml.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>org.yaml</groupId>\n" +
                            "            <artifactId>snakeyaml</artifactId>\n" +
                            "            <version>1.26</version>\n" +
                            "        </dependency>\n");
                }
                if (checkHikari.isSelected())
                {
                    output.append("\n        <dependency>\n" +
                            "            <groupId>com.zaxxer</groupId>\n" +
                            "            <artifactId>HikariCP</artifactId>\n" +
                            "            <version>3.4.0</version>\n" +
                            "        </dependency>\n");
                }
                if (check1.isSelected())
                {
                    //output.append("\n\n");
                }
                if (check2.isSelected())
                {
                    //output.append("\n\n");
                }
                if (check3.isSelected())
                {
                    //output.append("\n\n");
                }
                output.append("\n    </dependencies>\n" +
                "</project>");

    }

    private class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
        Vector<Component> components;

        public CustomFocusTraversalPolicy(Vector<Component> componentsInput) {
            components = componentsInput;
        }

        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(fieldGroup)) {
                return fieldArtifact;
            } else if (aComponent.equals(fieldArtifact)) {
                return fieldGroup;
            }
            return fieldGroup;
        }

        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(fieldGroup)) {
                return fieldGroup;
            }
            else if (aComponent.equals(fieldGroup)) {
                return fieldGroup;
            }

            return fieldArtifact;
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return fieldArtifact;
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return fieldGroup;
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return fieldArtifact;
        }
    }

}