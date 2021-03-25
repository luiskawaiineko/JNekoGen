import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Vector;

public class SpringGen {
    private JTextField fieldMain;
    private JTextField fieldNew;
    private String outString;

    public SpringGen(Rectangle windowData)  {
        JFrame f = new JFrame("FxGen");
        JLabel labelMain = new JLabel();
        JLabel labelNew = new JLabel();

        fieldMain = new JTextField();
        fieldNew = new JTextField();

        TextPrompt placeHolderDefault = new TextPrompt("Leave blank for default", fieldMain);
        JTextArea output = new JTextArea();
        JTextField argBox = new JTextField();
        JTextField libBox = new JTextField();
        JScrollPane scroll = new JScrollPane (output);

        labelMain.setText("Principal Screen Name");
        labelNew.setText("New Screen Name");
        argBox.setHorizontalAlignment(JTextField.CENTER);
        libBox.setHorizontalAlignment(JTextField.CENTER);
        argBox.setText("--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml");
        libBox.setText(getFxLib());

        labelMain.setBounds(5, 50, 200, 20);
        labelNew.setBounds(250, 20, 200, 20);

        fieldMain.setBounds(5, 80, 200, 20);
        fieldNew.setBounds(250, 50, 200, 20);

        placeHolderDefault.changeAlpha(0.75f);
        placeHolderDefault.changeStyle(Font.ITALIC);

        scroll.setBounds(5, 110, 600, 320);
        Vector<Component> components = new Vector<Component>(7);
        components.add(fieldMain);
        components.add(fieldNew);

        f.setFocusTraversalPolicy(new CustomFocusTraversalPolicy(components));
        f.setResizable(false);
        f.add(labelMain);
        f.add(labelNew);

        f.add(fieldMain);
        f.add(fieldNew);
        f.add(argBox);
        f.add(libBox);

        scroll.getViewport().setBackground(Color.white);
        output.setEditable(false);
        argBox.setEditable(false);
        libBox.setEditable(false);
        f.add(scroll);
        f.setBounds(windowData);
        f.setLayout(null);
        f.setVisible(true);


        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                GenLauncher launcher = new GenLauncher(f.getBounds());
                f.dispose();
            }
        });

        fieldMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){

                        if (!fieldMain.getText().toLowerCase().endsWith(".fxml"))
                        {
                            fieldMain.setText(fieldMain.getText()+".fxml");
                        }
                        output.setText("//select JDBCTemplate\n" +
                                "\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public List<Asignatura> getAllAsignaturasJDBCTemplate() {\n" +
                                "\n" +
                                "    JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "        DBConnectionPool.getInstance().getDataSource());\n" +
                                "    return jtm.query(\"Select * from asignaturas\",\n" +
                                "        BeanPropertyRowMapper.newInstance(Asignatura.class));\n" +
                                "  }\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public Asignatura getAsignaturaJDBCTemplate(int id) {\n" +
                                "\n" +
                                "    JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "        DBConnectionPool.getInstance().getDataSource());\n" +
                                "    List<Asignatura> asignatura =\n" +
                                "            jtm.query(\"Select * from asignaturas where ID = ?\",\n" +
                                "       BeanPropertyRowMapper.newInstance(Asignatura.class),\n" +
                                "                    new Object[]{id});\n" +
                                "    return asignatura.isEmpty() ? null : asignatura.get(0);\n" +
                                "\n" +
                                "  }\n" +
                                "\n" +
                                "  //Select JDBCTemplate\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public List<Asignatura> getAllAsignaturasNotasJDBCTemplate() {\n" +
                                "    JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "        DBConnectionPool.getInstance().getDataSource());\n" +
                                "   return  jtm.query(\"SELECT * FROM asignaturas where id in(select distinct(ID_ASIGNATURAS) from notas)\",\n" +
                                "            BeanPropertyRowMapper.newInstance(Asignatura.class));\n" +
                                "\n" +
                                "  }\n" +
                                "\n" +
                                "  //insert spring jdbc template\n" +
                                "\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public Asignatura addAsignaturaJDBCTemplate(Asignatura a) {\n" +
                                "\n" +
                                "    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(\n" +
                                "        DBConnectionPool.getInstance().getDataSource())\n" +
                                "            .withTableName(\"asignaturas\")\n" +
                                "            .usingGeneratedKeyColumns(\"ID\");\n" +
                                "    Map<String, Object> parameters = new HashMap<>();\n" +
                                "\n" +
                                "    parameters.put(\"NOMBRE\", a.getNombre());\n" +
                                "    parameters.put(\"CICLO\", a.getCiclo());\n" +
                                "    parameters.put(\"CURSO\", a.getCurso());\n" +
                                "    a.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());\n" +
                                "    return a;\n" +
                                "  }\n" +
                                "\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public int insertJDBCTemplate(Asignatura a) {\n" +
                                "    KeyHolder keyHolder = new GeneratedKeyHolder();\n" +
                                "\n" +
                                "    JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "            DBConnectionPool.getInstance().getDataSource());\n" +
                                "    jtm.update(connection -> {\n" +
                                "      PreparedStatement ps = connection\n" +
                                "              .prepareStatement(\"insert into asignaturas (NOMBRE,CICLO,CURSO) VALUES (?,?,?)\",\n" +
                                "                      Statement.RETURN_GENERATED_KEYS);\n" +
                                "      ps.setString(1, a.getNombre());\n" +
                                "      ps.setString(2, a.getCiclo());\n" +
                                "      ps.setString(3, a.getCurso());\n" +
                                "\n" +
                                "      return ps;\n" +
                                "    },keyHolder);\n" +
                                "\n" +
                                "      a.setId((int)keyHolder.getKey());\n" +
                                "\n" +
                                "    return (int)keyHolder.getKey();\n" +
                                "  }\n" +
                                "\n" +
                                "   //update JDBCTemplate\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public int updateJDBCTemplate(Asignatura a) {\n" +
                                "\n" +
                                "    JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "        DBConnectionPool.getInstance().getDataSource());\n" +
                                "    String updateQuery = \"update asignaturas set NOMBRE = ?, CICLO=?, CURSO=? where id = ?\";\n" +
                                "    return jtm.update(updateQuery, a.getNombre(), a.getCiclo(), a.getCurso(), a.getId());\n" +
                                "  }\n" +
                                "\n" +
                                "//delete JDBCTemplate\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public int deleteJDBCTemplate(int id) {\n" +
                                "    int filas = -1;\n" +
                                "\n" +
                                "    try {\n" +
                                "      JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "          DBConnectionPool.getInstance().getDataSource());\n" +
                                "      String updateQuery = \"delete from asignaturas where id = ?\";\n" +
                                "\n" +
                                "      filas = jtm.update(updateQuery, id);\n" +
                                "    } catch (DataIntegrityViolationException e) {\n" +
                                "      if (e.getMessage().contains(\"violación\")) {\n" +
                                "        filas = -2;\n" +
                                "      }\n" +
                                "    } catch (Exception ex) {\n" +
                                "      Logger.getLogger(AsignaturasDaoImplSpring.class.getName()).log(Level.SEVERE, null, ex);\n" +
                                "    }\n" +
                                "    return filas;\n" +
                                "  }\n" +
                                "\n" +
                                "   //delete trannsaccion JDBCTemplate\n" +
                                "\n" +
                                "  @Override\n" +
                                "  public int deleteTransaccJDBCTemplate(Asignatura a) {\n" +
                                "    int filas = -1;\n" +
                                "    TransactionDefinition txDef = new DefaultTransactionDefinition();\n" +
                                "    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(\n" +
                                "    DBConnectionPool.getInstance().getDataSource());\n" +
                                "    TransactionStatus txStatus = transactionManager.getTransaction(txDef);\n" +
                                "\n" +
                                "    try {\n" +
                                "\n" +
                                "      JdbcTemplate jtm = new JdbcTemplate(\n" +
                                "          transactionManager.getDataSource());\n" +
                                "      String updateQuery = \"delete from notas where ID_ASIGNATURAS = ?\";\n" +
                                "      jtm.update(updateQuery, a.getId());\n" +
                                "\n" +
                                "      updateQuery = \"delete from asignaturas where ID = ?\";\n" +
                                "      filas = jtm.update(updateQuery, a.getId());\n" +
                                "\n" +
                                "      transactionManager.commit(txStatus);\n" +
                                "\n" +
                                "    } catch (Exception e) {\n" +
                                "\n" +
                                "      transactionManager.rollback(txStatus);\n" +
                                "\n" +
                                "      throw e;\n" +
                                "\n" +
                                "    }\n" +
                                "\n" +
                                "    return filas;\n" +
                                "  }");
                        fieldMain.setText("");
                    }
            }

        });

        fieldNew.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !fieldNew.getText().equals("")){
                    fieldNew.setText(fieldNew.getText().substring(0, 1).toUpperCase() + fieldNew.getText().substring(1).toLowerCase());
                    output.setText("//Before method\n" +
                            "FXMLLoader loader"+fieldNew.getText()+";\n" +
                            "AnchorPane pane"+fieldNew.getText()+";\n\n" +
                            "//Inside method\n" +
                            "if (pane"+fieldNew.getText()+" == null) {\n" +
                            "      loader"+fieldNew.getText()+" = new FXMLLoader();\n" +
                            "      pane"+fieldNew.getText()+" = loader"+fieldNew.getText()+".load(getClass().getResourceAsStream(\"/fxml/"+fieldNew.getText()+".fxml\"));\n" +
                            "      "+fieldNew.getText()+"Controller pantalla"+fieldNew.getText()+" = loader"+fieldNew.getText()+".getController();\n" +
                            "      loader"+fieldNew.getText()+".setRoot(null);\n" +
                            "      loader"+fieldNew.getText()+".setController(null);\n" +
                            "      pane"+fieldNew.getText()+" = loader"+fieldNew.getText()+".load(getClass().getResourceAsStream(\"/fxml/"+fieldNew.getText()+".fxml\"));\n" +
                            "      pantalla"+fieldNew.getText()+" = loader"+fieldNew.getText()+".getController();\n" +
                            "\n" +
                            "    }");
                    fieldNew.setText("");
                }
            }

        });
    }

    private String getFxLib() {
        String path = null;
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            if ((entry.getKey().toLowerCase().contains("fx")&&entry.getKey().toLowerCase().contains("path"))||(entry.getKey().toLowerCase().contains("fx")&&entry.getKey().toLowerCase().contains("java")))
            {
                path = entry.getValue();
            }
        }
        if(path == null)
        {
            path = "Error: You didn't set the enviroment variable PATH_TO_FX";
        }
        return path;
    }

    private class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
        Vector<Component> components;

        public CustomFocusTraversalPolicy(Vector<Component> componentsInput) {
            components = componentsInput;
        }

        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(fieldNew)) {
                return fieldMain;
            } else if (aComponent.equals(fieldMain)) {
                return fieldNew;
            }
            return fieldMain;
        }

        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(fieldMain)) {
                return fieldNew;
            }
            else if (aComponent.equals(fieldNew)) {
                return fieldMain;
            }
            return fieldMain;
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return fieldMain;
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return fieldNew;
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return fieldMain;
        }
    }

}