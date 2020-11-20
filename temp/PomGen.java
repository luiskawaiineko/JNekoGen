import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class PomGen {
    private JCheckBox checkJavaFX;
    private JCheckBox checkLombok;
    private JCheckBox checkCdi;
    private JCheckBox checkJdbc;

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

        output = new JTextArea();
        JScrollPane scroll = new JScrollPane (output);

        labelJavaFx.setText("JavaFX");
        labelLombok.setText("Lombok");
        labelCdi.setText("CDI");
        labelProjectData.setText("Project Data");

        checkJavaFX = new JCheckBox();
        checkLombok = new JCheckBox();
        checkCdi = new JCheckBox();
        checkJdbc = new JCheckBox();

        fieldGroup = new JTextField();
        fieldArtifact = new JTextField();

        TextPrompt placeholderArtifact = new TextPrompt("Artifact name", fieldArtifact);
        placeholderArtifact.changeAlpha(0.75f);
        placeholderArtifact.changeStyle(Font.ITALIC);

        TextPrompt placeholderGroup = new TextPrompt("Group name", fieldGroup);
        placeholderGroup.changeAlpha(0.75f);
        placeholderGroup.changeStyle(Font.ITALIC);

        labelProjectData.setBounds(20, 20, 100, 20);
        labelLombok.setBounds(150, 20, 100, 20);
        labelJavaFx.setBounds(250, 20, 100, 20);
        labelCdi.setBounds(350, 20, 100, 20);

        checkLombok.setBounds(150, 40, 20, 20);
        checkJavaFX.setBounds(250, 40, 20, 20);
        checkCdi.setBounds(350, 40, 20, 20);

        fieldArtifact.setBounds(20, 60, 100, 20);
        fieldGroup.setBounds(20, 40, 100, 20);

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

        f.add(fieldArtifact);
        f.add(fieldGroup);
        f.add(checkJavaFX);
        f.add(checkLombok);
        f.add(checkCdi);

        scroll.getViewport().setBackground(Color.white);
        output.setEditable(false);
        f.add(scroll);
        f.setBounds(windowData);
        f.setLayout(null);
        f.setVisible(true);

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