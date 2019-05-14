import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class MazeApp extends JFrame implements ActionListener {

    MazeApp(){}
    MazePanel mp = new MazePanel(); 
    private static final long serialVersionUID = 1L;
   
    private JButton openButton = new JButton("Open Maze File");
    private JButton solveButton = new JButton("Solve");
    private JButton clearButton = new JButton("Clear Solution");
    JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
   

    public static void main(String[] args) 
    {

        try 
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        
        catch (UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException
                | InstantiationException ex) 
        {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        
        javax.swing.SwingUtilities.invokeLater(() -> {
            MazeApp ms = new MazeApp("Maze Solver");
            ms.createAndShowGUI();

        });
    }
    
    private MazeApp(String title) {
        super(title);
    }

    private void createAndShowGUI() {
        
        addComponentsToPane();
        openButton.addActionListener(this);
        solveButton.addActionListener(this);
        clearButton.addActionListener(this);       
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dimension.getWidth() - getWidth()) / 2;
         int y = (int) (dimension.getHeight() - getHeight()) / 2;
         setLocation(x,y);
        setVisible(true);
    }

    private void addComponentsToPane() {
        JPanel Panel1 = new JPanel(new BorderLayout());
        Panel1.setPreferredSize(new Dimension(600,600));
        Panel1.setBackground(Color.LIGHT_GRAY);
       
        Panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Panel1.add(mp, BorderLayout.CENTER);
        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(openButton);
        panel2.add(solveButton);
        solveButton.setEnabled(false);
        panel2.add(clearButton);
        clearButton.setEnabled(false);
        Panel1.add(panel2, BorderLayout.SOUTH);
        add(Panel1, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
              if (e.getSource() == openButton) {
          FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                try {
                    mp.readMaze(selectedFile);
                    solveButton.setEnabled(true);
                } catch ( NumberFormatException ex ) {
                    
                    JOptionPane.showMessageDialog(null,"Error!");
                    System.exit(1);
                }               
            }
        }
        
        else if(e.getSource() == solveButton) {
            mp.solveMaze();
            solveButton.setEnabled(false);
            clearButton.setEnabled(true);
        }
        
        else if(e.getSource() == clearButton){
            mp.clearMazePath();
            solveButton.setEnabled(true);
            clearButton.setEnabled(false);
        }

    }

}
