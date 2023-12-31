import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    // Declaración de componentes de la interfaz gráfica
    private Image icon;
    private JButton btnMateriales;
    private JButton btnPrestamo;
    private JButton btnInicio;
    private JButton creditos;
    private JPanel principal;
    private JPanel contenedor;
    private JPanel opciones;
    private JLabel txtEncabezado;
    private JPanel aux;
    private JMenuBar menu;
    private JMenu menuPrestamos;
    private JMenu menuMateriales;
    private JMenuItem itemConsultMat;
    private JMenuItem itemAgregarMat;
    private JMenuItem itemConsultPrest;
    private JMenuItem itemHacerPrest;
    private JButton btnCerrarSesion;
    JFrame cred;

    // Usuario que está en sesión
    private Usuario usuario;

    // Constructor del JFrame
    public MenuPrincipal( Usuario us){
        cred = null;
        usuario = us;
        icon = new ImageIcon("./img/Icono.png").getImage();
        // Configuraciones de los elementos del JFrame
        setIconImage(icon);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("APA6");
        setVisible(true);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1));
        principal = new JPanel();
        principal.setBackground(new Color(100,150,200));
        principal.setLayout(new GridBagLayout());
        opciones = new JPanel();
        opciones.setLayout( new GridLayout(6,1) );
        btnInicio = new JButton("Consultar préstamos");
        btnPrestamo = new JButton("Hacer préstamos");
        btnMateriales = new JButton("Materiales");
        btnInicio.setPreferredSize(new Dimension(30,15));
        btnPrestamo.setPreferredSize(new Dimension(30,15));
        btnMateriales.setPreferredSize(new Dimension(30,15));
        txtEncabezado = new JLabel("");
        txtEncabezado.setIcon( new ImageIcon( "./img/Icono.png") );
        contenedor = new JPanel();
        contenedor.setLayout( new FlowLayout(FlowLayout.CENTER) );
        contenedor.setBackground(new Color(70, 191,200));
        menu = new JMenuBar();
        menuPrestamos = new JMenu("Préstamos",true);
        menuPrestamos.setMnemonic('P');
        menuMateriales = new JMenu("Inventario", true);
        menuMateriales.setMnemonic('I');
        itemConsultMat = new JMenuItem("Consultar",'C');
        itemAgregarMat = new JMenuItem("Agregar Material",'A');
        itemConsultPrest = new JMenuItem("Consultar Préstamos",'C');
        itemHacerPrest = new JMenuItem("Registrar Préstamo",'R');
        menuPrestamos.add( itemHacerPrest );
        menuPrestamos.add( itemConsultPrest );
        menuMateriales.add( itemAgregarMat );
        menuMateriales.add( itemConsultMat );
        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(Color.RED);
        creditos = new JButton("Creditos");
        creditos.setBackground(Color.BLUE);
        creditos.setForeground(Color.WHITE);
        menu.setOpaque(false);
        menu.setBorderPainted(true);
        menu.setForeground(Color.WHITE);
        menu.setLayout(new GridBagLayout());
        menu.add(menuPrestamos, new GridBagConstraints(0,0,1,1,1.0,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0 ));
        menu.add(menuMateriales, new GridBagConstraints(1,0,1,1,1.0,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0 ));

        // Evento del item Consultar préstamos del menú Préstamo
        itemConsultPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remuevo todos los componentes de contenedor
                contenedor.removeAll();
                // Actualizo la interfaz
                contenedor.updateUI();
                contenedor.repaint();
                contenedor.setLayout(new BorderLayout());
                aux = new JPanelConsultaPrestamos(usuario );
                aux.setMaximumSize(new Dimension(300, 400));
                // Agregamos el JPanel para consultar préstamos al JPanel contenedor
                contenedor.add( aux, BorderLayout.CENTER );
            }
        });
        // Adición de los componentes al JPanel principal
        principal.add(menu, new GridBagConstraints(0,0,5,1,1.0,0.5,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 400, 40));
        principal.add(contenedor, new GridBagConstraints(0,1,5,10,6.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        principal.add(txtEncabezado, new GridBagConstraints(6,1,1,2,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        JLabel sistema = new JLabel("Administración de Préstamos de Audiovisuales P6");
        sistema.setFont(new Font("Arial", Font.ITALIC, 18));
        sistema.setForeground(Color.white);
        sistema.setHorizontalAlignment(JLabel.CENTER);
        principal.add(sistema, new GridBagConstraints(6,0,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        JLabel bienvenida = new JLabel("Bienvenido/a \n" + usuario.getNomUsuario());
        bienvenida.setFont(new Font("Arial", Font.PLAIN, 16));
        bienvenida.setForeground(Color.WHITE);
        principal.add(bienvenida, new GridBagConstraints(6,5,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        principal.add(btnCerrarSesion, new GridBagConstraints(6,7,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 80, 20));
        principal.add(creditos, new GridBagConstraints(6,9,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        // Evento del item Hacer préstamo del menú Préstamos
        itemHacerPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remuevo todos los componentes del JPanel contenedor
                contenedor.removeAll();
                contenedor.updateUI();
                contenedor.repaint();
                contenedor.setLayout(new BorderLayout());
                JPanelRealizarPrestamo p = new JPanelRealizarPrestamo(usuario, itemConsultPrest);
                // Pone el JPanel para registrar préstmaos en el JPanel contenedor
                contenedor.add( p.getPaneel(), BorderLayout.CENTER );
            }
        });
        // Agrega el JPanel principal, es donde se sitúan todos los componentes de la interfaz
        add(principal);
        // Simula el click en el item Realizar préstamo en el menú Préstamos para que empiece la sesión con esa JPanel
        itemHacerPrest.doClick();

        // Evento del botón para cerrar sesión
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si los créditos están visibles, los cierra
                if ( cred != null && cred.isVisible() ){
                    cred.setVisible(false);
                    cred.dispose();
                }
                // Cierra el JFrame MenuPrincipal
                setVisible(false);
                dispose();
                // Abre el modal de Login
                new LogIn("LogIn");
            }
        });

        // Evento del item Agregar Material del menú Materiales
        itemAgregarMat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedor.removeAll();
                contenedor.updateUI();
                contenedor.repaint();
                // Agrega el JPanel para agregar materiales en el JPanel contenedor
                AddMateriales adM = new AddMateriales(contenedor,usuario, null);
                contenedor.add (adM.getPanela());
            }
        });

        // Evento del bóton de los créditos
        creditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configuraciones del JFrame cred
                cred = new JFrame("Créditos");
                cred.setIconImage(icon);
                cred.setSize(350, 550);
                JPanel imagen = new JPanel();
                imagen.setLayout(new BorderLayout());
                cred.setResizable(false);
                cred.setLocationRelativeTo(null);
                JLabel credits = new JLabel();
                credits.setIcon(new ImageIcon("./img/Creditos.png"));
                credits.setHorizontalAlignment(JLabel.CENTER);
                // Agrega los componentes
                imagen.add(credits, BorderLayout.CENTER);
                cred.add(imagen);
                // Hace visible al JFrame
                cred.setVisible(true);
            }
        });

        // Evento del item Consultar materiales del menú Materiales
        itemConsultMat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedor.removeAll();
                contenedor.updateUI();
                contenedor.repaint();
                contenedor.setLayout(new BorderLayout());
                // Agrega el JPanel para consultar y modificar materiales en el JPanel contenedor   
                contenedor.add(new ModificarMaterial(contenedor, usuario), BorderLayout.CENTER);
            }
        });

    }


}